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
                    processData.setFacStreetName(properties.getStreetName());
                    processData.setFacStreetType(properties.getStreetType());
                    processData.setFacStreetDirection(properties.getStreetDirection());
                    processData.setFacLocalityName(properties.getLocalityName());
                    processData.setFacProvinceCode(properties.getProvinceCode());
                    // Full Address
                    String fullAddress;
                    String street = properties.getStreetName();
                    String streetType = properties.getStreetType();
                    String streetDirection = properties.getStreetDirection();
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
                    	processData.setFacCivicAddr((fullAddress+", "+properties.getLocalityName()+", "+properties.getProvinceCode()).toUpperCase());
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
