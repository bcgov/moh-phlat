package com.moh.phlat.backend.esb.json;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.Getter;

public class MaintainFacilityResponse implements PlrResponse {
	private static final Logger logger = LoggerFactory.getLogger(MaintainFacilityResponse.class);
	
	private ProcessData data;
	
	private String facilityId;
	
	private boolean isLoaded = false;
	private boolean isDuplicate = false;
	private boolean hasError = false;
	
	@Getter
	private List<PlrError> plrErrors = new ArrayList<PlrError>();
	
	public MaintainFacilityResponse(Control control, ProcessData data) {
		this.data = data;
	}
	
	@Override
	public void plrJsonToProcessData(String json) {
		try {
			DateFormat dateFormat = JSON_DATE_FORMAT_OJDK11;
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(dateFormat);
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			objectMapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
			objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			
			JsonNode root = objectMapper.readTree(json);
			for (JsonNode ack : root.get("acknowledgments")) {
				if (ack.get("msgCode") != null) {
					if (ack.get("msgCode").asText().contains("GRS.SYS.UNK.UNK.1.0.7071")) {
						hasError = true;
					}
					if (hasError) {
						if (ack.get("msgCode").asText().contains("PRS.SYS.ADR.UNK.1.0.7055")) {
							logger.warn("{} was identifed as a duplicate facility by PLR: {}", data.getId(), ack.get("msgText").asText());
							addError("PRS.SYS.ADR.UNK.1.0.7055", "WARNING", ack.get("msgText").asText());
						} else if (!ack.get("msgCode").asText().contains("PRS.PRP.MTN.UNK.0.0.0000")
								&& !ack.get("msgCode").asText().contains("PRS.PRV.OID.CRE.0.0.0003")
								&& !ack.get("msgCode").asText().contains("GRS.SYS.UNK.UNK.1.0.7071")) {
							logger.error("PLR returned with an error for ProcessData ID {}: {}", data.getId(), ack.get("msgText").asText());
							addError(ack.get("msgCode").asText(), "ERROR", ack.get("msgText").asText());
						}
					}
				}
			}
			if (!hasError) {
				JsonNode facility = root.get("facility");
				if (facility.get("facilityIdentifiers") != null && facility.get("facilityIdentifiers").findValue("identifier") != null) {
					facilityId = facility.get("facilityIdentifiers").findValue("identifier").asText();
				}
			}
			
		} catch (Exception ex) {
			hasError = true;
			logger.error("PLR's response to load attempt for record #{} could not be parsed: ", ex, data.getId());
			addError("ParsingError", "ERROR", 
					"An error occurred when trying to parse PLR's response to this load request");
		}
	}
	
	@Override
	public void handlePlrError(Exception ex) {
		hasError = true;
		if (ex instanceof WebClientResponseException) {
			WebClientResponseException webEx = (WebClientResponseException) ex;
			addError("HTTP " + String.valueOf(webEx.getStatusCode().value()), "ERROR", 
					"PLR is unreachable or could not process the request");
		}
		if (ex instanceof CallNotPermittedException) {
			CallNotPermittedException callEx = (CallNotPermittedException) ex;
			addError(callEx.getClass().getName(), "ERROR",
					"Too many load attempts have failed in succession; this is the last attempted record");
		}
	}
	
	@Override
	public boolean verifyStatus() {
		boolean pass = !hasError && (isLoaded || isDuplicate);
		if (!(pass || hasError)) {
			addError("UNKNOWN", "ERROR", 
					"An unknown error occured while trying to load this record");
		}
		return pass;
	}
	
	public String getFacilityId() {
		return facilityId;
	}

	public boolean isLoaded() {
		return isLoaded;
	}
	
	public boolean isDuplicate() {
		return isDuplicate;
	}
	
	public class PlrError {
		private String errorCode;
		private String errorType;
		private String errorMessage;
		
		public PlrError(String errorCode, String errorType, String errorMessage) {
			this.errorCode = errorCode;
			this.errorType = errorType;
			this.errorMessage = errorMessage;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public String getErrorType() {
			return errorType;
		}

		public String getErrorMessage() {
			return errorMessage;
		}
	}
	
	private void addError(String errorCode, String errorType, String errorMessage) {
		plrErrors.add(new PlrError(errorCode, errorType, errorMessage));
	}
}
