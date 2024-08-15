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
import com.moh.phlat.backend.model.ProcessData;

import lombok.Getter;

public class OFRelationshipResponse implements PlrResponse {
	private static final Logger logger = LoggerFactory.getLogger(OFRelationshipResponse.class);
	
	private ProcessData data;
	
	@Getter
	private String hdsId;
	
	@Getter
	private boolean isLoaded = false;
	@Getter
	private boolean isDuplicate = false;
	private boolean hasError = false;
	
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
			
			JsonNode root = objectMapper.readTree(json);
			for (JsonNode ack : root.get(ACKNOWLEDGEMENTS)) {
				if (ack.get(MSG_CODE) != null && ack.get(MSG_CODE).asText().contains(FAILED_RESPONSE_CODE)) {
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
			hasError = true;
			logger.error("PLR's response could not be parsed: ", ex);
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
	}
	
	@Override
	public boolean verifyStatus() {
		boolean pass = !hasError && (isLoaded || isDuplicate);
		if (!pass && !hasError) {
			addError("UNKNOWN", "ERROR", 
					"An unknown error occured while trying to load this record");
		}
		return pass;
	}
	
	public class PlrError {
		@Getter
		private String errorCode;
		@Getter
		private String errorType;
		@Getter
		private String errorMessage;
		
		public PlrError(String errorCode, String errorType, String errorMessage) {
			this.errorCode = errorCode;
			this.errorType = errorType;
			this.errorMessage = errorMessage;
		}
	}
	
	private void addError(String errorCode, String errorType, String errorMessage) {
		plrErrors.add(new PlrError(errorCode, errorType, errorMessage));
	}
}
