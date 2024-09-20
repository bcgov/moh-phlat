package com.moh.phlat.backend.esb.json;

import java.io.IOException;
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
import ca.bc.gov.health.plr.dto.provider.esb.AddressDto;
import ca.bc.gov.health.plr.dto.provider.esb.CollegeIdentifierDto;
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
		providerDetails.setIdentifiers(createIdentifierDtos());
		providerDetails.setJurisdiction(createJurisdictionDto());
		providerDetails.setStatuses(createStatusDtos());
		providerDetails.setAddresses(createAddressDtos());
		providerDetails.setTelecommunication(createTelecomunicationDtos());
		providerDetails.setElectronicAddresses(createElectronicAddressDtos());
		return providerDetails;
	}
	
	private List<OrgNameDto> createOrgNameDtos() {
		List<OrgNameDto> orgNameList = new ArrayList<>();
		
		OrgNameDto orgName = new OrgNameDto();
		orgName.setName(data.getHdsName());
		if (data.getHdsPreferredNameFlag() != null) {
			orgName.setPreferred(Boolean.parseBoolean(data.getHdsPreferredNameFlag()));
		}
		orgName.setTypeCode("CURR");
		orgName.setEffectiveStartDate(data.getCreatedAt());
		orgNameList.add(orgName);
		
		return orgNameList;
	}
	
	private HdsTypeDto createHdsTypeDto() {
		HdsTypeDto hdsType = new HdsTypeDto();
		hdsType.setHdsType(data.getHdsType());
		hdsType.setEffectiveStartDate(data.getCreatedAt());
		
		return hdsType;
	}
	
	private List<CollegeIdentifierDto> createIdentifierDtos() {
		
		List<CollegeIdentifierDto> identifierList = new ArrayList<>();
		
		if (StringUtils.hasText(data.getHdsProviderIdentifier1())
				&& StringUtils.hasText(data.getHdsProviderIdentifierType1())) {
			CollegeIdentifierDto identifier1 = new CollegeIdentifierDto();
			identifier1.setIdentifier(data.getHdsProviderIdentifier1());
			identifier1.setType(data.getHdsProviderIdentifierType1());
			identifier1.setEffectiveStartDate(data.getCreatedAt());
			identifierList.add(identifier1);
		}
		if (StringUtils.hasText(data.getHdsProviderIdentifier2())
				&& StringUtils.hasText(data.getHdsProviderIdentifierType2())) {
			CollegeIdentifierDto identifier2 = new CollegeIdentifierDto();
			identifier2.setIdentifier(data.getHdsProviderIdentifier2());
			identifier2.setType(data.getHdsProviderIdentifierType2());
			identifier2.setEffectiveStartDate(data.getCreatedAt());
			identifierList.add(identifier2);
		}
		if (StringUtils.hasText(data.getHdsProviderIdentifier3())
				&& StringUtils.hasText(data.getHdsProviderIdentifierType3())) {
			CollegeIdentifierDto identifier3 = new CollegeIdentifierDto();
			identifier3.setIdentifier(data.getHdsProviderIdentifier3());
			identifier3.setType(data.getHdsProviderIdentifierType3());
			identifier3.setEffectiveStartDate(data.getCreatedAt());
			identifierList.add(identifier3);
		}
		
		if (identifierList.isEmpty()) {
			return null;
		}
		return identifierList;
	}
	
	private JurisdictionNameCodeDto createJurisdictionDto() {
		JurisdictionNameCodeDto jursidictionDto = new JurisdictionNameCodeDto();
		jursidictionDto.setJurisdicationNameCode(data.getPhysicalProvince());
		
		return jursidictionDto;
	}
	
	private List<StatusDto> createStatusDtos() {
		List<StatusDto> statusList = new ArrayList<>();
		
		StatusDto status = new StatusDto();
		status.setTypeCode("ACTIVE");
		status.setClassCode("AE");
		status.setReasonCode("ORG");
		status.setEffectiveStartDate(data.getCreatedAt());
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
			hdsBusTelNumber.setTypeCode("T");
			hdsBusTelNumber.setCommunicationPurposeCode("BC");
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
			hdsBusCellNumber.setTypeCode("MB");
			hdsBusCellNumber.setCommunicationPurposeCode("BC");
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
			hdsBusFaxNumber.setTypeCode("FAX");
			hdsBusFaxNumber.setCommunicationPurposeCode("BC");
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
		
		if (StringUtils.hasText(data.getHdsEmail())) {
			ElectronicAddressDto hdsEmail = new ElectronicAddressDto();
			hdsEmail.setAddress(data.getHdsEmail());
			hdsEmail.setTypeCode("E");
			hdsEmail.setCommunicationPurposeCode("BC");
			hdsEmail.setCreatedDate(data.getCreatedAt());
			hdsEmail.setEffectiveStartDate(data.getCreatedAt());
			electronicAddressList.add(hdsEmail);
		}
		
		if (StringUtils.hasText(data.getHdsWebsite())) {
			ElectronicAddressDto hdsWebsite = new ElectronicAddressDto();
			hdsWebsite.setAddress(data.getHdsWebsite());
			hdsWebsite.setTypeCode("H");
			hdsWebsite.setCommunicationPurposeCode("BC");
			hdsWebsite.setCreatedDate(data.getCreatedAt());
			hdsWebsite.setEffectiveStartDate(data.getCreatedAt());
			electronicAddressList.add(hdsWebsite);
		}
		
		return electronicAddressList;
	}
}
