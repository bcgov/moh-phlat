package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.math.BigDecimal;
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
	
	private static ObjectMapper objectMapper;
	static {
		JSON_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(JSON_DATE_FORMAT);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	private ProcessData processData;
	
	public MaintainFacilityRequest(ProcessData processData) {
		this.processData = processData;
	}
	
	@Override
	public String processDataToPlrJson() throws IOException {
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
		facilityDetails.setCreatedDate(processData.getCreatedAt());
		facilityDetails.setEffectiveStartDate(processData.getCreatedAt());
		facilityDetails.setType("BUILDING");
		facilityDetails.setTypeCode("BUILDING");
		
		List<FacilityDetailsDto> facilityDetailsList = new ArrayList<>();
		facilityDetailsList.add(facilityDetails);
		return facilityDetailsList;
	}
	
	private List<FacilityNameDto> createFacilityNameDtos() {
		
		FacilityNameDto facilityName = new FacilityNameDto();
		if (StringUtils.hasText(processData.getFacBuildingName())) {
			facilityName.setName(processData.getFacBuildingName());
		} else {
			return null;
		}
		
		facilityName.setCreatedDate(processData.getCreatedAt());
		facilityName.setEffectiveStartDate(processData.getCreatedAt());
		
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
		address.setActive(false);
		if (StringUtils.hasText(processData.getFacCivicAddr())) {
			String addrLine1 = processData.getFacCivicAddr().split(",")[0].trim();
			address.setAddressLineOne(addrLine1);
		} else if (StringUtils.hasText(processData.getPhysicalAddr1())) {
			address.setAddressLineOne(processData.getPhysicalAddr1());
		}
		if (StringUtils.hasText(processData.getPhysicalCity())) {
			address.setCity(processData.getPhysicalCity());
		}
		address.setTypeCode("P");
		address.setCommunicationPurposeCode("FC");
		if (StringUtils.hasText(processData.getPhysicalCountry())) {
			address.setCountry(processData.getPhysicalCountry());
		}
		address.setCountryCode("CA");
		address.setCreatedDate(processData.getCreatedAt());
		address.setDisplayActive(false);
		address.setEffectiveStartDate(processData.getCreatedAt());
		// Address is not updatable for a Facility - Flags are updated in PLR app
		address.setNoChangeOnUpdate(false);
		if (StringUtils.hasText(processData.getPhysicalPcode())) {
			address.setPostalCode(processData.getPhysicalPcode());
		}
		if (StringUtils.hasText(processData.getPhysicalProvince())) {
			address.setProvinceOrStateTxt(processData.getPhysicalProvince());
		}
		address.setUpdatable(false);
		address.setValidCpc(true);
		
		return address;
	}
	
	// *** CURRENTLY NOT IN USE, BUT KEEPING HERE IN CASE WE NEED IT LATER ***
	// *** FOR FACILITY LOAD OR POTENTIALLY FOR FACILITY VALIDATION.       ***
	private List<CivicAddressDto> createCivicAddressDtos() {
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
		
		List<CivicAddressDto> civicAddressList = new ArrayList<>();
		civicAddressList.add(civicAddress);
		return civicAddressList;
	}
}
