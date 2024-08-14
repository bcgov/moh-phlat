package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moh.phlat.backend.model.ProcessData;

import ca.bc.gov.health.plr.dto.esb.MaintainProviderRequest;
import ca.bc.gov.health.plr.dto.facility.esb.CivicAddressDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDetailsDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityNameDto;
import ca.bc.gov.health.plr.dto.provider.esb.AddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;

public class MaintainFacilityRequest implements PlrRequest {
	
	private ProcessData data;
	
	public MaintainFacilityRequest(ProcessData data) {
		this.data = data;
	}
	
	@Override
	public String processDataToPlrJson() throws IOException {
		DateFormat dateFormat = JSON_DATE_FORMAT_OJDK11;
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(dateFormat);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		
		return objectMapper.writeValueAsString(createMaintainProviderRequest());
	}
	
	private MaintainProviderRequest createMaintainProviderRequest() {
		MaintainProviderRequest maintainProviderRequest = new MaintainProviderRequest();
		maintainProviderRequest.setUniqueMessageID(UUID.randomUUID().toString());
		maintainProviderRequest.setMessageCreationTime(new Date());
		maintainProviderRequest.setProcessingCode("T");
		maintainProviderRequest.setProcessModeCode("T");
		maintainProviderRequest.setAcceptAckCode("AA");
		maintainProviderRequest.setRegistryUserOrgId("PHLAT");
		maintainProviderRequest.setEsbMetadata(createEsbMetadata());
		maintainProviderRequest.setProviderDetails(createProviderDetails());
		maintainProviderRequest.setFacility(createFacilityDto());
		return maintainProviderRequest;
	}

	private Map<String,Object> createEsbMetadata() {
		Map<String,Object> esbMetaData = new HashMap<String,Object>();
		esbMetaData.put("isLRA", false);
		return esbMetaData;
	}
	
	private ProviderDetails createProviderDetails() {
		return new ProviderDetails();
	}
	
	private FacilityDto createFacilityDto() {
		FacilityDto facility = new FacilityDto();
		facility.setFacilityDetails(createFacilityDetailsDtos());
		facility.setFacilityNames(createFacilityNameDtos());
		facility.setAddresses(createAddressDtos());
		return facility;
	}
	
	private List<FacilityDetailsDto> createFacilityDetailsDtos() {
		
		FacilityDetailsDto facilityDetails = new FacilityDetailsDto();
		facilityDetails.setCreatedDate(data.getCreatedAt());
		facilityDetails.setEffectiveStartDate(data.getCreatedAt());
		facilityDetails.setType("BUILDING");
		facilityDetails.setTypeCode("BUILDING");
		
		List<FacilityDetailsDto> facilityDetailsList = new ArrayList<>();
		facilityDetailsList.add(facilityDetails);
		return facilityDetailsList;
	}
	
	private List<FacilityNameDto> createFacilityNameDtos() {
		
		FacilityNameDto facilityName = new FacilityNameDto();
		if (StringUtils.hasText(data.getFacBuildingName())) {
			facilityName.setName(data.getFacBuildingName());
		} else {
			return null;
		}
		
		facilityName.setCreatedDate(data.getCreatedAt());
		facilityName.setEffectiveStartDate(data.getCreatedAt());
		
		List<FacilityNameDto> facilityNameList = new ArrayList<>();
		facilityNameList.add(facilityName);
		return facilityNameList;
	}
	
	private List<AddressDto> createAddressDtos() {
		List<AddressDto> addressList = new ArrayList<>();
		addressList.add(createPhysicalAddressDto());
		return addressList;
	}
	
	private AddressDto createPhysicalAddressDto() {
		AddressDto address = new AddressDto();
		address.setActive(true);
		if (StringUtils.hasText(data.getFacCivicAddr())) {
			String addrLine1 = data.getFacCivicAddr().split(",")[0].trim();
			address.setAddressLineOne(addrLine1);
		} else if (StringUtils.hasText(data.getPhysicalAddr1())) {
			address.setAddressLineOne(data.getPhysicalAddr1());
		}
		if (StringUtils.hasText(data.getPhysicalCity())) {
			address.setCity(data.getPhysicalCity());
		}
		address.setTypeCode("P");
		address.setCommunicationPurposeCode("FC");
		if (StringUtils.hasText(data.getPhysicalCountry())) {
			address.setCountry(data.getPhysicalCountry());
		}
		address.setCountryCode("CA");
		address.setCreatedDate(data.getCreatedAt());
		address.setDisplayActive(true);
		address.setEffectiveStartDate(data.getCreatedAt());
		address.setNoChangeOnUpdate(true);
		if (StringUtils.hasText(data.getPhysicalPcode())) {
			address.setPostalCode(data.getPhysicalPcode());
		}
		if (StringUtils.hasText(data.getPhysicalProvince())) {
			address.setProvinceOrStateTxt(data.getPhysicalProvince());
		}
		address.setUpdatable(true);
		address.setValidCpc(true);
		
		return address;
	}
	
	private List<CivicAddressDto> createCivicAddressDtos() {
		CivicAddressDto civicAddress = new CivicAddressDto();
		civicAddress.setActive(true);
		
		if (StringUtils.hasText(data.getFacCivicAddr())) {
			civicAddress.setAddressLineOne(data.getFacCivicAddr());
		} else {
			civicAddress.setAddressLineOne(data.getPhysicalAddr1());
		}
		
		if (StringUtils.hasText(data.getPhysicalAddr2())) {
			civicAddress.setAddressLineTwo(data.getPhysicalAddr2());
		}
		if (StringUtils.hasText(data.getPhysicalAddr3())) {
			civicAddress.setAddressLineThree(data.getPhysicalAddr3());
		}
		if (StringUtils.hasText(data.getPhysicalCity())) {
			civicAddress.setCity(data.getPhysicalCity());
		}
		if (StringUtils.hasText(data.getPhysicalProvince())) {
			civicAddress.setProvinceOrStateTxt(data.getPhysicalProvince());
		}
		civicAddress.setCountryCode("CA");
		civicAddress.setCreatedDate(data.getCreatedAt());
		civicAddress.setDisplayActive(true);
		civicAddress.setEffectiveStartDate(data.getCreatedAt());
		civicAddress.setNoChangeOnUpdate(false);
		if (StringUtils.hasText(data.getFacStreetType())) {
			civicAddress.setStreetType(data.getFacStreetType());
		}
		civicAddress.setUpdatable(true);
		if (StringUtils.hasText(data.getFacChsaCode())) {
			civicAddress.setChsaNameCode(data.getFacChsaCode());
		}
		if (StringUtils.hasText(data.getFacChsaName())) {
			civicAddress.setChsaDescTxt(data.getFacChsaName());
		}
		if (StringUtils.hasText(data.getFacChsaStatus())) {
			civicAddress.setChsaStatus(data.getFacChsaStatus());
		}
		if (StringUtils.hasText(data.getFacDatabcResults())) {
			civicAddress.setDataBCResult(data.getFacDatabcResults());
		}
		if (StringUtils.hasText(data.getFacHaName())) {
			civicAddress.setHaDescTxt(data.getFacHaName());
		}
		if (StringUtils.hasText(data.getFacHsdaName())) {
			civicAddress.setHsdaDescTxt(data.getFacHsdaName());
		}
		if (StringUtils.hasText(data.getFacLatitude())) {
			civicAddress.setLatitude(BigDecimal.valueOf(Double.valueOf(data.getFacLatitude())));
		}
		if (StringUtils.hasText(data.getFacLongitude())) {
			civicAddress.setLongitude(BigDecimal.valueOf(Double.valueOf(data.getFacLongitude())));
		}
		if (StringUtils.hasText(data.getFacLhaName())) {
			civicAddress.setLhaDescTxt(data.getFacLhaName());
		}
		if (StringUtils.hasText(data.getFacMatchPrecision())) {
			civicAddress.setMatchPrecision(data.getFacMatchPrecision());
		}
		if (StringUtils.hasText(data.getFacPcnName())) {
			civicAddress.setPcnDescTxt(null);
		}
		if (StringUtils.hasText(data.getFacPcnCode())) {
			civicAddress.setPcnNameCode(data.getFacPcnCode());
		}
		if (StringUtils.hasText(data.getFacPcnStatus())) {
			civicAddress.setPcnStatus(data.getFacPcnStatus());
		}
		if (StringUtils.hasText(data.getFacPrecisionPoints())) {
			civicAddress.setPrecisionPoints(Integer.valueOf(data.getFacPrecisionPoints()));
		}
		if (StringUtils.hasText(data.getFacScore())) {
			civicAddress.setScore(Integer.valueOf(data.getFacScore()));
		}
		if (StringUtils.hasText(data.getFacSiteId())) {
			civicAddress.setSiteID(data.getFacSiteId());
		}
		if (StringUtils.hasText(data.getFacStreetDirection())) {
			civicAddress.setStreetDirection(data.getFacStreetDirection());
		}
		if (StringUtils.hasText(data.getStreetDirectionPrefix())) {
			civicAddress.setIsDirectionPrefix(Boolean.valueOf(data.getStreetDirectionPrefix()));
		}
		if (StringUtils.hasText(data.getStreetTypePrefix())) {
			civicAddress.setIsTypePrefix(Boolean.valueOf(data.getStreetTypePrefix()));
		}
		
		List<CivicAddressDto> civicAddressList = new ArrayList<>();
		civicAddressList.add(civicAddress);
		return civicAddressList;
	}
}
