package com.moh.phlat.backend.esb.json;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

public interface PlrResponse {
	DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	String SUCCESSFUL_RESPONSE_CODE = "PRS.PRP.MTN.UNK.0.0.0000";
	String FAILED_RESPONSE_CODE = "GRS.SYS.UNK.UNK.1.0.7071";
	
	String ACKNOWLEDGEMENTS = "acknowledgments";
	String MSG_CODE = "msgCode";
	String MSG_TEXT = "msgText";
	
	public void plrJsonToProcessData(String plrJsonResponse);
	public void handlePlrError(Exception ex);
}
