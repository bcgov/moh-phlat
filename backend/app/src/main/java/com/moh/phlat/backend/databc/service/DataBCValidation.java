package com.moh.phlat.backend.databc.service;

import com.moh.phlat.backend.databc.dto.CHSAResults;
import com.moh.phlat.backend.databc.dto.DataBCAddress;
import com.moh.phlat.backend.databc.dto.Geometry;
import com.moh.phlat.backend.databc.dto.Properties;
import com.moh.phlat.backend.databc.util.Constants;
import com.moh.phlat.backend.model.ProcessData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DataBCValidation {
    
	@Autowired
	private DataBCService dataBCService;
	
	@Autowired
	private OpenMapsService openMapsService;
	
    private String convertOrdinalStreetName(String streetName) {
		String ordinalStreetName;
		
		ordinalStreetName = streetName.toUpperCase();
		
		ordinalStreetName = ordinalStreetName.replace("1ST","1");
		ordinalStreetName = ordinalStreetName.replace("2ND","2");
		ordinalStreetName = ordinalStreetName.replace("3RD","3");
		ordinalStreetName = ordinalStreetName.replace("4TH","4");
		ordinalStreetName = ordinalStreetName.replace("5TH","5");
		ordinalStreetName = ordinalStreetName.replace("6TH","6");
		ordinalStreetName = ordinalStreetName.replace("7TH","7");
		ordinalStreetName = ordinalStreetName.replace("8TH","8");
		ordinalStreetName = ordinalStreetName.replace("9TH","9");
		ordinalStreetName = ordinalStreetName.replace("10TH","10");
		ordinalStreetName = ordinalStreetName.replace("11TH","11");
		ordinalStreetName = ordinalStreetName.replace("12TH","12");
		ordinalStreetName = ordinalStreetName.replace("13TH","13");
		ordinalStreetName = ordinalStreetName.replace("14TH","14");
		ordinalStreetName = ordinalStreetName.replace("15TH","15");
		ordinalStreetName = ordinalStreetName.replace("16TH","16");
		ordinalStreetName = ordinalStreetName.replace("17TH","17");
		ordinalStreetName = ordinalStreetName.replace("18TH","18");
		ordinalStreetName = ordinalStreetName.replace("19TH","19");
		ordinalStreetName = ordinalStreetName.replace("20TH","20");	

		ordinalStreetName = ordinalStreetName.replace("FIRST","1");
		ordinalStreetName = ordinalStreetName.replace("SECOND","2");
		ordinalStreetName = ordinalStreetName.replace("THIRD","3");
		ordinalStreetName = ordinalStreetName.replace("FOURTH","4");
		ordinalStreetName = ordinalStreetName.replace("FIFTH","5");
		ordinalStreetName = ordinalStreetName.replace("SIXTH","6");
		ordinalStreetName = ordinalStreetName.replace("SEVENTH","7");
		ordinalStreetName = ordinalStreetName.replace("EIGHTH","8");
		ordinalStreetName = ordinalStreetName.replace("NINTH","9");
		ordinalStreetName = ordinalStreetName.replace("TENTH","10");
		ordinalStreetName = ordinalStreetName.replace("ELEVENTH","11");
		ordinalStreetName = ordinalStreetName.replace("TWELTH","12");
		ordinalStreetName = ordinalStreetName.replace("THIRTEENTH","13");
		ordinalStreetName = ordinalStreetName.replace("FOURTEENTH","14");
		ordinalStreetName = ordinalStreetName.replace("FIFTEENTH","15");
		ordinalStreetName = ordinalStreetName.replace("SIXTEENTH","16");
		ordinalStreetName = ordinalStreetName.replace("SEVENTEENTH","17");
		ordinalStreetName = ordinalStreetName.replace("EIGHTEENTH","18");
		ordinalStreetName = ordinalStreetName.replace("NINTETEENTH","19");
		ordinalStreetName = ordinalStreetName.replace("TWENTIETH","20");

		return ordinalStreetName;
	}
    
    private String convertOrdinalStreetDirection(String streetDirection) {
		String ordinalStreetDirection;
		
		if (!StringUtils.hasText(streetDirection)) {
			return "";
		}
		ordinalStreetDirection = streetDirection.toUpperCase();
		
		ordinalStreetDirection = ordinalStreetDirection.replace("WEST","W");
		ordinalStreetDirection = ordinalStreetDirection.replace("EAST","E");
		ordinalStreetDirection = ordinalStreetDirection.replace("NORTH","N");
		ordinalStreetDirection = ordinalStreetDirection.replace("SOUTH","S");

		return ordinalStreetDirection;
	}

    private String convertOrdinalCity(String cityName) {
		String ordinalCityName = cityName.toUpperCase();
        ordinalCityName = ordinalCityName.replace("DISTRICT OF ","").replace("TOWNSHIP OF ","");
		
		return ordinalCityName;
	}

    public void getDataBCResults(ProcessData processData) {
    	
    	String address = processData.getFacCivicAddr();
    	
        // Begin processing each address
        if (StringUtils.hasText(address)) {
            // Check if this address is out of province (not BC)
            boolean isBC = true;
            String[] lineElements = address.split("[\\s,]+");
            if (!lineElements[lineElements.length - 1].contains("BC")) {
                // Address is out of province, set scores to 0 and match precision to OOP
                processData.setFacScore("0");
                processData.setFacMatchPrecision("OOP");
                processData.setFacPrecisionPoints("0");
                isBC = false;
            }
            // DataBC coord search
            if (isBC) {
                DataBCAddress dataBcCoord = dataBCService.callDataBC(Constants.DATABC_SRS_COORD, address);
                if (dataBcCoord != null && dataBcCoord.getFeatures() != null && !dataBcCoord.getFeatures().isEmpty()) {
                    // Geometry
                    Geometry geometry = dataBcCoord.getFeatures().get(0).getGeometry();
                    if (geometry.getCoordinates() != null && geometry.getCoordinates().length > 0) {
                    	processData.setFacLongitude(geometry.getCoordinates()[0]);
                    	processData.setFacLatitude(geometry.getCoordinates()[1]);
                    }
                    // Features
                    Properties properties = dataBcCoord.getFeatures().get(0).getProperties();
                    // Civic Address Details
                    String number = properties.getCivicNumber();
                    if (StringUtils.hasText(number)) {
                    	processData.setFacCivicNumber(number);
                    }

                    String city = convertOrdinalCity(properties.getLocalityName());

                    processData.setFacStreetName(properties.getStreetName());
                    processData.setFacStreetType(properties.getStreetType());
                    processData.setFacStreetDirection(properties.getStreetDirection());
                    processData.setFacLocalityName(city);
                    processData.setFacProvinceCode(properties.getProvinceCode());

                    // Full Address
                    String fullAddress;
                    String street = convertOrdinalStreetName(properties.getStreetName());
                    String streetType = properties.getStreetType();

                    String streetDirection = convertOrdinalStreetDirection(properties.getStreetDirection());


                    if(properties.isStreetDirectionPrefix()){
                        if(properties.isStreetTypePrefix()){
                            fullAddress = number+" "+streetType+" "+streetDirection+" "+street;
                        } else {
                            fullAddress = number+" "+streetDirection+" "+street+" "+streetType;
                        }
                    } else {
                        fullAddress = number+" "+street+" "+streetType;
                    }
                    if (StringUtils.hasText(StringUtils.trimAllWhitespace(fullAddress))) {
                    	processData.setFacCivicAddr((fullAddress + ", " + city + ", " + properties.getProvinceCode()).toUpperCase());
                    }
                    // Match Scores
                    processData.setFacSiteId(properties.getSiteID());
                    if (StringUtils.hasText(properties.getScore())) {
                    	processData.setFacScore(properties.getScore());
                    }
                    processData.setFacMatchPrecision(properties.getMatchPrecision());
                    if (StringUtils.hasText(properties.getPrecisionPoints())) {
                    	processData.setFacPrecisionPoints(properties.getPrecisionPoints());
                    }
                    // DataBC Result
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                    	processData.setFacDatabcResults(mapper.writeValueAsString(dataBcCoord));
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void getCHSAResults(ProcessData processData) {
    	
    	String address = processData.getFacCivicAddr();
    	
        // Begin processing each address
        if (StringUtils.hasText(address)) {
            // Check if this address is out of province (not BC)
            boolean isBC = true;
            String[] lineElements = address.split("[\\s,]+");
            if (!lineElements[lineElements.length - 1].contains("BC")) {
                // Address is out of province, set scores to 0 and match precision to OOP
                processData.setFacScore("0");
                processData.setFacMatchPrecision("OOP");
                processData.setFacPrecisionPoints("0");
                isBC = false;
            }
            // DataBC CHSA coord search
            if (isBC) {
                DataBCAddress dataBcCHSACoord = dataBCService.callDataBC(Constants.DATABC_SRS_UTM, address);
                if (dataBcCHSACoord != null && dataBcCHSACoord.getFeatures() != null && !dataBcCHSACoord.getFeatures().isEmpty()) {
                    // Geometry
                    Geometry geometry = dataBcCHSACoord.getFeatures().get(0).getGeometry();
                    String chsaLongitude = "";
                    String chsaLatitude = "";
                    if (geometry.getCoordinates() != null && geometry.getCoordinates().length > 0) {
                    	chsaLongitude = geometry.getCoordinates()[0];
                    	chsaLatitude = geometry.getCoordinates()[1];
                    }
                    // OpenMaps CHSA Lookup
                    if (StringUtils.hasText(chsaLongitude) && StringUtils.hasText(chsaLatitude)) {
                    	CHSAResults chsaResults = openMapsService.callOpenMapsCHSA(chsaLongitude, chsaLatitude);
                    	if (chsaResults != null) {
                    		processData.setFacChsaCode(chsaResults.getChsaAreaCode());
                    		processData.setFacChsaName(chsaResults.getChsaAreaName());
                    		processData.setFacHaName(chsaResults.getHaName());
                    		processData.setFacHsdaName(chsaResults.getHsdaName());
                    		processData.setFacLhaName(chsaResults.getLhaName());
                    	}
                    }
                }
            }
        }
    }
}
