package com.moh.phlat.backend.esb.json;

import java.text.SimpleDateFormat;

import com.moh.phlat.backend.esb.boundary.PlrToken;

public interface PlrResponse {
	public static final SimpleDateFormat JSON_DATE_FORMAT_OJDK11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public void plrJsonToProcessData(String json);
	public void handlePlrError(Exception ex);
	public void handleKeyCloakError(PlrToken token);
	public boolean verifyStatus();
}
