package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public interface PlrRequest {
	DateFormat JSON_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public String processDataToPlrJson() throws IOException;
}
