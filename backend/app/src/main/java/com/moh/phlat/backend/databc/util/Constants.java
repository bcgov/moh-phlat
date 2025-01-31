package com.moh.phlat.backend.databc.util;

public class Constants {
	
	// DataBC
    public static final String DATABC_SRS_COORD = "4326";
    public static final String DATABC_SRS_UTM = "3005";
    
    // CHSA
    public static final String CHSA_CODE = "CMNTY_HLTH_SERV_AREA_CODE";
    public static final String CHSA_NAME = "CMNTY_HLTH_SERV_AREA_NAME";
    public static final String LHA_NAME = "LOCAL_HLTH_AREA_NAME";
    public static final String HSDA_NAME = "HLTH_SERVICE_DLVR_AREA_NAME";
    public static final String HA_NAME = "HLTH_AUTHORITY_NAME";
    
    // End Reason Codes
    public static enum EndReasonCodes {
    	CHG, UNK, CEASE, CORR;
    	
    	@Override
    	public String toString() {
    		return this.name();
    	}
    }
}
