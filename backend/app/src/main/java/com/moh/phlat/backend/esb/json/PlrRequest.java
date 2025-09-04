package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public interface PlrRequest {
	DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	String EFFECTIVE_DATE_FORMAT = "yyyy-MM-dd";
	String EFFECTIVE_DATE_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public String processDataToPlrJson() throws IOException, ParseException;
}
