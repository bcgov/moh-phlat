package com.moh.phlat.backend.esb.json;

import java.text.SimpleDateFormat;

public interface PlrResponse {
	SimpleDateFormat JSON_DATE_FORMAT_OJDK11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	String SUCCESSFUL_RESPONSE_CODE = "PRS.PRP.MTN.UNK.0.0.0000";
	String FAILED_RESPONSE_CODE = "GRS.SYS.UNK.UNK.1.0.7071";
	
	String ACKNOWLEDGEMENTS = "acknowledgments";
	String MSG_CODE = "msgCode";
	String MSG_TEXT = "msgText";
	
	public void plrJsonToProcessData(String json);
	public void handlePlrError(Exception ex);
}
