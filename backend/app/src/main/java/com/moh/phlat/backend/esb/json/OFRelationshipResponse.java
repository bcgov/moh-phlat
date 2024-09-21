package com.moh.phlat.backend.esb.json;

import java.io.IOException;
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
import com.moh.phlat.backend.esb.boundary.PlrError;
import com.moh.phlat.backend.model.ProcessData;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.Getter;

public class OFRelationshipResponse implements PlrResponse {
	private static final Logger logger = LoggerFactory.getLogger(OFRelationshipResponse.class);
	
	private ProcessData data;
	
	@Getter
	private String hdsId;
	
	@Getter
	private boolean loaded = false;
	
	@Getter
	private List<PlrError> plrErrors = new ArrayList<>();
	
	public OFRelationshipResponse(ProcessData data) {
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
			
			boolean hasError = false;
			JsonNode root = objectMapper.readTree(json);
			for (JsonNode ack : root.get(ACKNOWLEDGEMENTS)) {
				if (ack.has(MSG_CODE) && ack.get(MSG_CODE).asText().contains(FAILED_RESPONSE_CODE)) {
					hasError = true;
					break;
				}
			}
			if (!hasError) {
				JsonNode ofRelationship = root.get("facility");
				if (ofRelationship.get("facilityIdentifiers") != null && ofRelationship.get("facilityIdentifiers").findValue("identifier") != null) {
					hdsId = ofRelationship.get("facilityIdentifiers").findValue("identifier").asText();
				}
			}
			
		} catch (Exception ex) {
			logger.error("PLR's response could not be parsed: ", ex);
			addError("ParsingError", "ERROR", 
					"An error occurred when trying to parse PLR's response to this load request");
		}
	}
	
	@Override
	public void handlePlrError(Exception ex) {
		if (ex instanceof WebClientResponseException) {
			WebClientResponseException webEx = (WebClientResponseException) ex;
			addError("HTTP " + String.valueOf(webEx.getStatusCode().value()), "ERROR", 
					"PLR is unreachable or could not process the request.");
		} else if (ex instanceof CallNotPermittedException) {
			CallNotPermittedException callEx = (CallNotPermittedException) ex;
			addError(callEx.getClass().getName(), "ERROR",
					"Too many load attempts have failed in succession; this record's load attempt has been cancelled.");
		} else if (ex instanceof IOException) {
			addError("InputProblem", "ERROR",
					"The input record was invalid or could not be converted into a PLR request.");
		}
	}
	
	private void addError(String errorCode, String errorType, String errorMessage) {
		plrErrors.add(new PlrError(errorCode, errorType, errorMessage));
	}
}
