package com.moh.phlat.backend.esb.json;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moh.phlat.backend.esb.boundary.PlrToken;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

public class OFRelationshipResponse implements PlrResponse {
	private static final Logger logger = LoggerFactory.getLogger(OFRelationshipResponse.class);
	
	private String hdsId;
	
	private boolean isLoaded = false;
	private boolean isDuplicate = false;
	private boolean hasError = false;
	
	private List<PlrError> plrErrors = new ArrayList<PlrError>();
	
	public OFRelationshipResponse(Control control) {
		
	}
	
	@Override
	public void plrJsonToProcessData(String json, ProcessData data) {
		try {
			DateFormat df = JSON_DATE_FORMAT_OJDK11;
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			ObjectMapper om = new ObjectMapper();
			om.setDateFormat(df);
			om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			om.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
			om.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
			om.setSerializationInclusion(Include.NON_NULL);
			
			JsonNode root = om.readTree(json);
			for (JsonNode ack : root.get("acknowledgments")) {
				if (ack.get("msgCode") != null && ack.get("msgCode").asText().contains("GRS.SYS.UNK.UNK.1.0.7071")) {
					hasError = true;
					break;
				}
			}
			if (!hasError) {
				JsonNode hds = root.get("facility");
				if (hds.get("facilityIdentifiers") != null && hds.get("facilityIdentifiers").findValue("identifier") != null) {
					hdsId = hds.get("facilityIdentifiers").findValue("identifier").asText();
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
	public void handleKeyCloakError(PlrToken token) {
		hasError = true;
		addError("KeyCloakError", "ERROR", token.getError() + " : " + token.getErrorDesc());
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
	
	public String getHdsId() {
		return hdsId;
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
