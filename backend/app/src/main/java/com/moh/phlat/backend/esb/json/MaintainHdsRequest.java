package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDto;
import ca.bc.gov.health.plr.dto.provider.esb.AddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.ElectronicAddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.HdsTypeDto;
import ca.bc.gov.health.plr.dto.provider.esb.JurisdictionNameCodeDto;
import ca.bc.gov.health.plr.dto.provider.esb.OrgNameDto;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;
import ca.bc.gov.health.plr.dto.provider.esb.StatusDto;
import ca.bc.gov.health.plr.dto.provider.esb.TelecommunicationDto;

public class MaintainHdsRequest implements PlrRequest {
	
	private ProcessData data;
	
	public MaintainHdsRequest(ProcessData data) {
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
		return maintainProviderRequest;
	}

	private Map<String,Object> createEsbMetadata() {
		Map<String,Object> esbMetaData = new HashMap<String,Object>();
		esbMetaData.put("isLRA", false);
		return esbMetaData;
	}
	
	private ProviderDetails createProviderDetails() {
		ProviderDetails providerDetails = new ProviderDetails();
		providerDetails.setOrgNames(createOrgNameDtos());
		providerDetails.setType("HDS");
		providerDetails.setProviderType("ORG");
		providerDetails.setHdsType(createHdsTypeDto());
		providerDetails.setJurisdiction(createJurisdictionDto());
		providerDetails.setAddresses(createAddressDtos());
		providerDetails.setTelecommunication(createTelecomunicationDtos());
		providerDetails.setElectronicAddresses(createElectronicAddressDtos());
		return providerDetails;
	}
	
	private List<OrgNameDto> createOrgNameDtos() {
		List<OrgNameDto> orgNameList = new ArrayList<>();
		
		OrgNameDto orgName = new OrgNameDto();
		orgName.setLongName(data.getHdsName());
		orgName.setTypeCode("1147");
		orgNameList.add(orgName);
		
		return orgNameList;
	}
	
	private HdsTypeDto createHdsTypeDto() {
		HdsTypeDto hdsType = new HdsTypeDto();
		hdsType.setHdsType(data.getHdsType());
		
		return hdsType;
	}
	
	private JurisdictionNameCodeDto createJurisdictionDto() {
		JurisdictionNameCodeDto jursidictionDto = new JurisdictionNameCodeDto();
		jursidictionDto.setJurisdicationNameCode(data.getPhysicalProvince());
		
		return jursidictionDto;
	}
	
	private List<StatusDto> createStatusDtos() {
		List<StatusDto> statusList = new ArrayList<>();
		
		StatusDto status = new StatusDto();
		status.setType("ACTIVE");
		status.setClassCode("LIC");
		statusList.add(status);
		
		return statusList;
	}
	
	private List<AddressDto> createAddressDtos() {
		List<AddressDto> addressList = new ArrayList<>();
		addressList.add(createPhysicalAddressDto());
		if (StringUtils.hasText(data.getMailAddr1()) && StringUtils.hasText(data.getMailBc())) {
			addressList.add(createMailingAddressDto());
		}
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
	
	private AddressDto createMailingAddressDto() {
		AddressDto address = new AddressDto();
		address.setActive(true);
		address.setAddressLineOne(data.getMailAddr1());
		if (StringUtils.hasText(data.getMailAddr2())) {
			address.setAddressLineTwo(data.getMailAddr2());
		}
		if (StringUtils.hasText(data.getMailAddr3())) {
			address.setAddressLineThree(data.getMailAddr3());
		}
		if (StringUtils.hasText(data.getMailAddr4())) {
			address.setAddressLineFour(data.getMailAddr4());
		}
		if (StringUtils.hasText(data.getMailCity())) {
			address.setCity(data.getMailCity());
		}
		address.setTypeCode("M");
		address.setCommunicationPurposeCode("FC");
		address.setCountry(data.getMailCountry());
		address.setCountryCode("CA");
		address.setCreatedDate(data.getCreatedAt());
		address.setDisplayActive(true);
		address.setEffectiveStartDate(data.getCreatedAt());
		address.setNoChangeOnUpdate(true);
		if (StringUtils.hasText(data.getMailPcode())) {
			address.setPostalCode(data.getMailPcode());
		}
		address.setProvinceOrStateTxt(data.getMailBc());
		address.setUpdatable(true);
		address.setValidCpc(true);
		
		return address;
	}
	
	private List<TelecommunicationDto> createTelecomunicationDtos() {
		if (!StringUtils.hasText(data.getHdsBusTelNumber() + data.getHdsCellNumber() + data.getHdsFaxNumber())) {
			return null;
		}
		
		List<TelecommunicationDto> telecomList = new ArrayList<>();
		
		if (StringUtils.hasText(data.getHdsBusTelNumber())) {
			TelecommunicationDto hdsBusTelNumber = new TelecommunicationDto();
			hdsBusTelNumber.setNumber(data.getHdsBusTelNumber());
			if (StringUtils.hasText(data.getHdsTelExtension())) {
				hdsBusTelNumber.setExtension(data.getHdsTelExtension());
			}
			if (StringUtils.hasText(data.getHdsBusTelAreaCode())) {
				hdsBusTelNumber.setAreaCode(data.getHdsBusTelAreaCode());
			}
			hdsBusTelNumber.setCreatedDate(data.getCreatedAt());
			hdsBusTelNumber.setEffectiveStartDate(data.getCreatedAt());
			telecomList.add(hdsBusTelNumber);
		}
		
		if (StringUtils.hasText(data.getHdsCellNumber())) {
			TelecommunicationDto hdsBusCellNumber = new TelecommunicationDto();
			hdsBusCellNumber.setNumber(data.getHdsCellNumber());
			if (StringUtils.hasText(data.getHdsCellAreaCode())) {
				hdsBusCellNumber.setAreaCode(data.getHdsCellAreaCode());
			}
			hdsBusCellNumber.setCreatedDate(data.getCreatedAt());
			hdsBusCellNumber.setEffectiveStartDate(data.getCreatedAt());
			telecomList.add(hdsBusCellNumber);
		}
		
		if (StringUtils.hasText(data.getHdsFaxNumber())) {
			TelecommunicationDto hdsBusFaxNumber = new TelecommunicationDto();
			hdsBusFaxNumber.setNumber(data.getHdsFaxNumber());
			if (StringUtils.hasText(data.getHdsFaxAreaCode())) {
				hdsBusFaxNumber.setAreaCode(data.getHdsFaxAreaCode());
			}
			hdsBusFaxNumber.setCreatedDate(data.getCreatedAt());
			hdsBusFaxNumber.setEffectiveStartDate(data.getCreatedAt());
			telecomList.add(hdsBusFaxNumber);
		}
		return telecomList;
	}
	
	private List<ElectronicAddressDto> createElectronicAddressDtos() {
		if (!StringUtils.hasText(data.getHdsEmail() + data.getHdsWebsite())) {
			return null;
		}
		
		List<ElectronicAddressDto> electronicAddressList = new ArrayList<>();
		SimpleDateFormat startDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		SimpleDateFormat endDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		if (StringUtils.hasText(data.getHdsEmail())) {
			ElectronicAddressDto hdsEmail = new ElectronicAddressDto();
			hdsEmail.setAddress(data.getHdsEmail());
			hdsEmail.setCreatedDate(data.getCreatedAt());
			try {
				hdsEmail.setEffectiveStartDate(startDateFormat.parse(data.getHdsEffectiveStartDate()));
			} catch (ParseException ex) {
				hdsEmail.setEffectiveStartDate(data.getCreatedAt());
			}
			try {
				hdsEmail.setEffectiveEndDate(endDateFormat.parse(data.getHdsEffectiveEndDate()));
			} catch (ParseException ex) {
			}
			electronicAddressList.add(hdsEmail);
		}
		
		if (StringUtils.hasText(data.getHdsWebsite())) {
			ElectronicAddressDto hdsWebsite = new ElectronicAddressDto();
			hdsWebsite.setAddress(data.getHdsWebsite());
			hdsWebsite.setCreatedDate(data.getCreatedAt());
			try {
				hdsWebsite.setEffectiveStartDate(startDateFormat.parse(data.getHdsEffectiveStartDate()));
			} catch (ParseException ex) {
				hdsWebsite.setEffectiveStartDate(data.getCreatedAt());
			}
			try {
				hdsWebsite.setEffectiveEndDate(endDateFormat.parse(data.getHdsEffectiveEndDate()));
			} catch (ParseException ex) {
			}
			electronicAddressList.add(hdsWebsite);
		}
		
		return electronicAddressList;
	}
}
