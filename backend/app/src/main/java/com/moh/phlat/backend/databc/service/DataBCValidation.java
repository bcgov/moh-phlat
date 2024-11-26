package com.moh.phlat.backend.databc.service;

import com.moh.phlat.backend.databc.dto.DataBCAddress;
import com.moh.phlat.backend.databc.dto.Geometry;
import com.moh.phlat.backend.databc.dto.Properties;
import com.moh.phlat.backend.databc.util.Constants;
import com.moh.phlat.backend.model.ProcessData;

import ca.bc.gov.health.plr.dto.facility.esb.CivicAddressDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DataBCValidation {
    
	@Autowired
	private DataBCService dataBCService;
	
    public CivicAddressDto getDataBCResults(ProcessData processData) {
    	
    	CivicAddressDto address = createCivicAddressDto(processData);
    	
        // Begin processing each address
        if (address.getAddressLineOne() != null) {
            // Check if this address is out of province (not BC)
            boolean isBC = true;
            String[] lineElements = address.getAddressLineOne().split("[\\s,]+");
            if (!lineElements[lineElements.length - 1].contains("BC")) {
                // Address is out of province, set scores to 0 and match precision to OOP
                address.setScore(0);
                address.setMatchPrecision("OOP");
                address.setPrecisionPoints(0);
                isBC = false;
            }
            // DataBC coord search
            if (isBC) {
                DataBCAddress dataBcCoord = dataBCService.callDataBC(Constants.DATABC_SRS_COORD, address);
                if (dataBcCoord != null && dataBcCoord.getFeatures() != null && !dataBcCoord.getFeatures().isEmpty()) {
                    // Geometry
                    Geometry geometry = dataBcCoord.getFeatures().get(0).getGeometry();
                    if (geometry.getCoordinates() != null && geometry.getCoordinates().length > 0) {
                        String longitudeStr = geometry.getCoordinates()[0];
                        String latitudeStr = geometry.getCoordinates()[1];
                        Double longitudeDbl = longitudeStr != null && !longitudeStr.isEmpty() ? Double.valueOf(longitudeStr) : null;
                        Double latitudeDbl = latitudeStr != null && !latitudeStr.isEmpty() ? Double.valueOf(latitudeStr) : null;
                        address.setLongitude(longitudeDbl != null ? BigDecimal.valueOf(longitudeDbl) : null);
                        address.setLatitude(latitudeDbl != null ? BigDecimal.valueOf(latitudeDbl) : null);
                    }
                    // Features
                    Properties properties = dataBcCoord.getFeatures().get(0).getProperties();
                    // Civic Address Details
                    String number = properties.getCivicNumber();
                    if (StringUtils.hasText(number)) {
                    	address.setNumber(Long.valueOf(number));
                    }
                    address.setStreetName(properties.getStreetName());
                    address.setStreetType(properties.getStreetType());
                    address.setStreetDirection(properties.getStreetDirection());
                    address.setCity(properties.getLocalityName());
                    address.setProvinceOrStateTxt(properties.getProvinceCode());
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
                    address.setAddressLineOne((fullAddress+", "+properties.getLocalityName()+", "+properties.getProvinceCode()).toUpperCase());
                    // Match Scores
                    address.setSiteID(properties.getSiteID());
                    if (StringUtils.hasText(properties.getScore())) {
                    	address.setScore(Integer.parseInt(properties.getScore()));
                    }
                    address.setMatchPrecision(properties.getMatchPrecision());
                    if (StringUtils.hasText(properties.getPrecisionPoints())) {
                    	address.setPrecisionPoints(Integer.parseInt(properties.getPrecisionPoints()));
                    }
                    // DataBC Result
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        address.setDataBCResult(mapper.writeValueAsString(dataBcCoord));
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return address;
    }
    
    private CivicAddressDto createCivicAddressDto(ProcessData processData) {
    	
    	CivicAddressDto civicAddress = new CivicAddressDto();
		civicAddress.setActive(true);
		
		if (StringUtils.hasText(processData.getFacCivicAddr())) {
			civicAddress.setAddressLineOne(processData.getFacCivicAddr());
		} else {
			civicAddress.setAddressLineOne(processData.getPhysicalAddr1());
		}
		
		if (StringUtils.hasText(processData.getPhysicalAddr2())) {
			civicAddress.setAddressLineTwo(processData.getPhysicalAddr2());
		}
		if (StringUtils.hasText(processData.getPhysicalAddr3())) {
			civicAddress.setAddressLineThree(processData.getPhysicalAddr3());
		}
		if (StringUtils.hasText(processData.getPhysicalCity())) {
			civicAddress.setCity(processData.getPhysicalCity());
		}
		if (StringUtils.hasText(processData.getPhysicalProvince())) {
			civicAddress.setProvinceOrStateTxt(processData.getPhysicalProvince());
		}
		civicAddress.setCountryCode("CA");
		civicAddress.setCreatedDate(processData.getCreatedAt());
		civicAddress.setDisplayActive(true);
		civicAddress.setEffectiveStartDate(processData.getCreatedAt());
		civicAddress.setNoChangeOnUpdate(false);
		if (StringUtils.hasText(processData.getFacStreetType())) {
			civicAddress.setStreetType(processData.getFacStreetType());
		}
		civicAddress.setUpdatable(true);
		if (StringUtils.hasText(processData.getFacChsaCode())) {
			civicAddress.setChsaNameCode(processData.getFacChsaCode());
		}
		if (StringUtils.hasText(processData.getFacChsaName())) {
			civicAddress.setChsaDescTxt(processData.getFacChsaName());
		}
		if (StringUtils.hasText(processData.getFacChsaStatus())) {
			civicAddress.setChsaStatus(processData.getFacChsaStatus());
		}
		if (StringUtils.hasText(processData.getFacDatabcResults())) {
			civicAddress.setDataBCResult(processData.getFacDatabcResults());
		}
		if (StringUtils.hasText(processData.getFacHaName())) {
			civicAddress.setHaDescTxt(processData.getFacHaName());
		}
		if (StringUtils.hasText(processData.getFacHsdaName())) {
			civicAddress.setHsdaDescTxt(processData.getFacHsdaName());
		}
		if (StringUtils.hasText(processData.getFacLatitude())) {
			civicAddress.setLatitude(BigDecimal.valueOf(Double.valueOf(processData.getFacLatitude())));
		}
		if (StringUtils.hasText(processData.getFacLongitude())) {
			civicAddress.setLongitude(BigDecimal.valueOf(Double.valueOf(processData.getFacLongitude())));
		}
		if (StringUtils.hasText(processData.getFacLhaName())) {
			civicAddress.setLhaDescTxt(processData.getFacLhaName());
		}
		if (StringUtils.hasText(processData.getFacMatchPrecision())) {
			civicAddress.setMatchPrecision(processData.getFacMatchPrecision());
		}
		if (StringUtils.hasText(processData.getFacPcnName())) {
			civicAddress.setPcnDescTxt(null);
		}
		if (StringUtils.hasText(processData.getFacPcnCode())) {
			civicAddress.setPcnNameCode(processData.getFacPcnCode());
		}
		if (StringUtils.hasText(processData.getFacPcnStatus())) {
			civicAddress.setPcnStatus(processData.getFacPcnStatus());
		}
		if (StringUtils.hasText(processData.getFacPrecisionPoints())) {
			civicAddress.setPrecisionPoints(Integer.valueOf(processData.getFacPrecisionPoints()));
		}
		if (StringUtils.hasText(processData.getFacScore())) {
			civicAddress.setScore(Integer.valueOf(processData.getFacScore()));
		}
		if (StringUtils.hasText(processData.getFacSiteId())) {
			civicAddress.setSiteID(processData.getFacSiteId());
		}
		if (StringUtils.hasText(processData.getFacStreetDirection())) {
			civicAddress.setStreetDirection(processData.getFacStreetDirection());
		}
		if (StringUtils.hasText(processData.getStreetDirectionPrefix())) {
			civicAddress.setIsDirectionPrefix(Boolean.valueOf(processData.getStreetDirectionPrefix()));
		}
		if (StringUtils.hasText(processData.getStreetTypePrefix())) {
			civicAddress.setIsTypePrefix(Boolean.valueOf(processData.getStreetTypePrefix()));
		}
    	
    	return civicAddress;
    }
}
