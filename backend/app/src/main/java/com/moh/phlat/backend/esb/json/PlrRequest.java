package com.moh.phlat.backend.esb.json;

import java.text.SimpleDateFormat;

import com.moh.phlat.backend.model.ProcessData;

public interface PlrRequest {
	public static final SimpleDateFormat JSON_DATE_FORMAT_OJDK11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public String processDataToPlrJson(ProcessData data);
}
