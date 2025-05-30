package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.service.MessageSourceSystem;
import com.moh.phlat.backend.service.RowStatusService;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.Getter;

public class MaintainFacilityResponse implements PlrResponse {
	private static final Logger logger = LoggerFactory.getLogger(MaintainFacilityResponse.class);
	
	private static final String DUPLICATE_FACILITY_RESPONSE_CODE = "PRS.SYS.ADR.UNK.1.0.7055";
	private static final String FACILITY_LOADED_RESPONSE_CODE = "PRS.PRV.OID.CRE.0.0.0003";

	private static ObjectMapper objectMapper;
	static {
		JSON_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(JSON_DATE_FORMAT);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	private ProcessData processData;
	
	@Getter
	private boolean loaded = false;
	private boolean duplicate = false;
	private boolean hasError = false;
	
	public MaintainFacilityResponse(ProcessData processData) {
		this.processData = processData;
	}
	
	public MaintainFacilityResponse(boolean loaded) {
		this.loaded = loaded;
	}
	
	@Override
	public void plrJsonToProcessData(String facilityJsonResponse) {
		try {
			JsonNode root = objectMapper.readTree(facilityJsonResponse);
			for (JsonNode ack : root.path(ACKNOWLEDGEMENTS)) {
				
				// Find the message code and text of each acknowledgement node
				String msgCode = ack.path(MSG_CODE).asText();
				String msgText = ack.path(MSG_TEXT).asText();
				
				if (msgCode.contains(FAILED_RESPONSE_CODE)) {
					// PLR is returning one or more errors; set hasError to true and look for other PLR error(s) in the response
					hasError = true;
					
				} else if (msgCode.contains(DUPLICATE_FACILITY_RESPONSE_CODE)) {
					// PLR found a duplicate facility record; set duplicate to true and mark this ProcessData record as a duplicate
					logger.warn("{} was identifed as a duplicate facility by PLR: {}", processData.getId(), msgText);
					addError(DUPLICATE_FACILITY_RESPONSE_CODE, "WARNING", msgText);
					duplicate = true;
					
				} else if (hasError && !duplicate
						&& !msgCode.contains(SUCCESSFUL_RESPONSE_CODE)
						&& !msgCode.contains(FACILITY_LOADED_RESPONSE_CODE)
						&& !msgCode.contains(FAILED_RESPONSE_CODE)) {
					// PLR gave this error response; mark this ProcessData record as a failed load
					logger.error("PLR returned with an error for ProcessData ID {}: {}", processData.getId(), msgText);
					addError(msgCode, "ERROR", msgText);
				} 
			}
			if (!hasError) {
				// No errors were found; find the facility identifier
				JsonNode facility = root.path("facility");
				facility.path("facilityIdentifiers").forEach(facIdentifier -> {
					String typeCode = facIdentifier.path("typeCode").asText();
					if ("IFC".equals(typeCode)) {
						processData.setPlrFacilityId(facIdentifier.path("facilityId").asText());
						processData.setFacIfcId(facIdentifier.path("identifier").asText());
					}
				});
				
				if (StringUtils.hasText(processData.getPlrFacilityId()) && StringUtils.hasText(processData.getFacIfcId())) {
					loaded = true;
				} else {
					// The facility identifiers were not given or could not be found
					hasError = true;
					logger.error("PHLAT could not find all facility identifiers for ProcessData ID {}", processData.getId());
					addError("ParsingError", "ERROR", "PHLAT could not verify from PLR's response if this record's facility was loaded");
				}
			}
			
		} catch (Exception ex) {
			// The JSON message could not be parsed
			logger.error("PLR's response to load attempt for record #{} could not be parsed: ", processData.getId(), ex);
			addError("ParsingError", "ERROR", 
					"An error occurred when trying to parse PLR's response to this load request");
		}
		setRowStatusCode();
	}
	
	@Override
	public void handlePlrError(Exception ex) {
		hasError = true;
		if (ex instanceof WebClientResponseException webEx) {
			addError("HTTP " + webEx.getStatusCode().value(), "ERROR", 
					"PLR could not process the request.");
		} else if (ex instanceof WebClientRequestException) {
			addError("RequestProblem", "ERROR", "PLR could not be reached.");
		} else if (ex instanceof CallNotPermittedException callEx) {
			addError(callEx.getClass().getName(), "ERROR",
					"Too many load attempts have failed in succession; this record's load attempt has been cancelled.");
		} else if (ex instanceof IOException) {
			addError("InputProblem", "ERROR",
					"The input record was invalid or could not be converted into a PLR request.");
		}
		setRowStatusCode();
	}
	
	private void addError(String errorCode, String errorType, String errorMessage) {
		
		Message msg = Message.builder()
				 .messageType(errorType)
				 .messageCode(errorCode)
				 .messageDesc(errorMessage)
				 .sourceSystemName(MessageSourceSystem.PLR)
				 .processData(processData)
				 .build();
		processData.getMessages().add(msg);
	}
	
	private void setRowStatusCode() {
		if (duplicate) {
			processData.setRowstatusCode(RowStatusService.POTENTIAL_DUPLICATE);
		} else if (hasError) {
			processData.setRowstatusCode(RowStatusService.LOAD_ERROR);
		}
	}
}
