package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.text.SimpleDateFormat;

public interface PlrRequest {
	SimpleDateFormat JSON_DATE_FORMAT_OJDK11 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public String processDataToPlrJson() throws IOException;
}
