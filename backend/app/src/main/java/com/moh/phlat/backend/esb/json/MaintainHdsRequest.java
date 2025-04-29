package com.moh.phlat.backend.esb.json;

import java.io.IOException;
import java.text.ParseException;
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
import ca.bc.gov.health.plr.dto.provider.esb.PropertyDto;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;
import ca.bc.gov.health.plr.dto.provider.esb.StatusDto;
import ca.bc.gov.health.plr.dto.provider.esb.TelecommunicationDto;

public class MaintainHdsRequest implements PlrRequest {
	
	private static final String COMMUNICATION_PURPOSE_CODE = "BC";
	private static final String HDS_SUB_TYPE = "HDS_SUB_TYPE";
	private static final String ADDRESS_UNIT = "ADDRESS_UNIT";
	
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
	private boolean isUpdate;
	
	public MaintainHdsRequest(ProcessData processData) {
		this(processData, false);
	}
	
	public MaintainHdsRequest(ProcessData processData, boolean isUpdate) {
		this.processData = processData;
		this.isUpdate = isUpdate;
	}
	
	@Override
	public String processDataToPlrJson() throws IOException, ParseException {
		return objectMapper.writeValueAsString(createMaintainProviderRequest());
	}
	
	private MaintainProviderRequest createMaintainProviderRequest() throws ParseException {
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
	
	private ProviderDetails createProviderDetails() throws ParseException {
		ProviderDetails providerDetails = new ProviderDetails();
		
		providerDetails.setType("HDS");
		providerDetails.setProviderType("ORG");
		providerDetails.setJurisdiction(createJurisdictionDto());
		providerDetails.setIdentifiers(createIdentifierDtos());
		providerDetails.setOrgNames(createOrgNameDtos());
		providerDetails.setHdsType(createHdsTypeDto());
		providerDetails.setProperties(createPropertyDtos());
		providerDetails.setStatuses(createStatusDtos());
		providerDetails.setAddresses(createAddressDtos());
		providerDetails.setTelecommunication(createTelecomunicationDtos());
		providerDetails.setElectronicAddresses(createElectronicAddressDtos());
		return providerDetails;
	}
	
	private List<OrgNameDto> createOrgNameDtos() throws ParseException {
		
		String nameAction = processData.getHdsNameGroupAction();
		
		if (isUpdate && !StringUtils.hasText(nameAction)) {
			return null;
		}
		List<OrgNameDto> orgNameList = new ArrayList<>();
		
		OrgNameDto orgName = new OrgNameDto();
		orgName.setName(processData.getHdsName());
		if (processData.getHdsPreferredNameFlag() != null) {
			orgName.setPreferred(Boolean.parseBoolean(processData.getHdsPreferredNameFlag()));
		}
		orgName.setTypeCode("CURR");
		orgName.setDataOwnerCode(processData.getStakeholder());
		orgName.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsNameGroupEffectiveStartDate()));
		if (processData.getHdsNameGroupEffectiveEndDate() != null) {
			orgName.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsNameGroupEffectiveEndDate()));
		}
		if (StringUtils.hasText(nameAction)) {
			orgName.setEndReasonCode(nameAction);
		}
		orgNameList.add(orgName);
		
		return orgNameList;
	}
	
	private HdsTypeDto createHdsTypeDto() {
		HdsTypeDto hdsType = new HdsTypeDto();
		hdsType.setHdsType(processData.getHdsType());
		hdsType.setDataOwnerCode(processData.getStakeholder());
		hdsType.setEffectiveStartDate(processData.getCreatedAt());
		
		return hdsType;
	}
	
	private List<PropertyDto> createPropertyDtos() {
		List<PropertyDto> propertyList = new ArrayList<>();
		
		if (StringUtils.hasText(processData.getHdsSubType()) && !isUpdate) {
			addHdsProperty(propertyList, processData.getHdsSubType(), HDS_SUB_TYPE);
		}
		if (StringUtils.hasText(processData.getFacAddressUnit()) && !isUpdate) {
			addHdsProperty(propertyList, processData.getFacAddressUnit(), ADDRESS_UNIT);
		}
		
		if (propertyList.isEmpty()) {
			return null;
		}
		return propertyList;
	}
	
	private void addHdsProperty(List<PropertyDto> propertyList, String propertyValue, String propertyType) {
		PropertyDto hdsProperty = new PropertyDto();
		hdsProperty.setPropertyValue(propertyValue);
		hdsProperty.setPropertyTypeCode(propertyType);
		hdsProperty.setDataOwnerCode(processData.getStakeholder());
		hdsProperty.setEffectiveStartDate(processData.getUpdatedAt());
		propertyList.add(hdsProperty);
	}
	
	private List<CollegeIdentifierDto> createIdentifierDtos() {
		List<CollegeIdentifierDto> identifierList = new ArrayList<>();
		
		addHdsProviderIdentifier(identifierList, processData.getHdsIpcId(), "IPC");
		addHdsProviderIdentifier(identifierList, processData.getHdsCpnId(), "CPN");
		addHdsProviderIdentifier(identifierList, processData.getHdsProviderIdentifier1(), processData.getHdsProviderIdentifierType1());
		addHdsProviderIdentifier(identifierList, processData.getHdsProviderIdentifier2(), processData.getHdsProviderIdentifierType2());
		addHdsProviderIdentifier(identifierList, processData.getHdsProviderIdentifier3(), processData.getHdsProviderIdentifierType3());
				
		if (identifierList.isEmpty()) {
			return null;
		}
		return identifierList;
	}
	
	private void addHdsProviderIdentifier(List<CollegeIdentifierDto> identifierList, String hdsId, String hdsIdType) {
		if (StringUtils.hasText(hdsId) && StringUtils.hasText(hdsIdType)) {
			CollegeIdentifierDto identifierDto = new CollegeIdentifierDto();
			identifierDto.setIdentifier(hdsId);
			identifierDto.setTypeCode(hdsIdType);
			if (!isUpdate) {
				identifierDto.setDataOwnerCode(processData.getStakeholder());
				identifierDto.setEffectiveStartDate(processData.getCreatedAt());
			}
			identifierList.add(identifierDto);
		}
	}
	
	private JurisdictionNameCodeDto createJurisdictionDto() {
		JurisdictionNameCodeDto jurisdictionDto = new JurisdictionNameCodeDto();
		jurisdictionDto.setJurisdicationNameCode(processData.getPhysicalProvince());
		
		return jurisdictionDto;
	}
	
	private List<StatusDto> createStatusDtos() throws ParseException {
		
		String statusAction = processData.getStatusGroupAction();
		
		if (isUpdate && !StringUtils.hasText(statusAction)) {
			return null;
		}
		
		List<StatusDto> statusList = new ArrayList<>();
		
		StatusDto status = new StatusDto();
		status.setTypeCode("ACTIVE");
		status.setClassCode("AE");
		status.setReasonCode("ORG");
		status.setDataOwnerCode(processData.getStakeholder());
		status.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getStatusGroupEffectiveStartDate()));
		if (processData.getStatusGroupEffectiveEndDate() != null) {
			status.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getStatusGroupEffectiveEndDate()));
		}
		if (StringUtils.hasText(statusAction)) {
			status.setEndReasonCode(statusAction);
		}
		statusList.add(status);
		
		return statusList;
	}
	
	private List<AddressDto> createAddressDtos() throws ParseException {
		if (isUpdate && !StringUtils.hasText(processData.getPhysicalAddressGroupAction() + processData.getMailingAddressGroupAction())) {
			return null;
		}
		
		List<AddressDto> addressList = new ArrayList<>();
		addressList.add(createPhysicalAddressDto());
		if (StringUtils.hasText(processData.getMailAddr1()) && StringUtils.hasText(processData.getMailBc())) {
			addressList.add(createMailingAddressDto());
		}
		return addressList;
	}
	
	private AddressDto createPhysicalAddressDto() throws ParseException {
		AddressDto address = new AddressDto();
		address.setActive(false);
		address.setAddressLineOne(processData.getPhysicalAddr1());
		if (StringUtils.hasText(processData.getPhysicalAddr2())) {
			address.setAddressLineTwo(processData.getPhysicalAddr2());
		}
		if (StringUtils.hasText(processData.getPhysicalAddr3())) {
			address.setAddressLineThree(processData.getPhysicalAddr3());
		}
		if (StringUtils.hasText(processData.getPhysicalAddr4())) {
			address.setAddressLineFour(processData.getPhysicalAddr4());
		}
		if (StringUtils.hasText(processData.getPhysicalCity())) {
			address.setCity(processData.getPhysicalCity());
		}
		address.setTypeCode("P");
		address.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
		if (StringUtils.hasText(processData.getPhysicalCountry())) {
			address.setCountry(processData.getPhysicalCountry());
		}
		address.setCountryCode("CA");
		address.setCreatedDate(processData.getCreatedAt());
		address.setDisplayActive(false);
		address.setDataOwnerCode(processData.getStakeholder());
		address.setNoChangeOnUpdate(false);
		if (StringUtils.hasText(processData.getPhysicalPcode())) {
			address.setPostalCode(processData.getPhysicalPcode());
		}
		if (StringUtils.hasText(processData.getPhysicalProvince())) {
			address.setProvinceOrStateTxt(processData.getPhysicalProvince());
		}
		address.setUpdatable(false);
		address.setValidCpc(true);
		address.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getPhysicalAddressGroupEffectiveStartDate()));
		if (processData.getPhysicalAddressGroupEffectiveEndDate() != null) {
			address.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getPhysicalAddressGroupEffectiveEndDate()));
		}
		if (StringUtils.hasText(processData.getPhysicalAddressGroupAction())) {
			address.setEndReasonCode(processData.getPhysicalAddressGroupAction());
		}
		
		return address;
	}
	
	private AddressDto createMailingAddressDto() throws ParseException {
		AddressDto address = new AddressDto();
		address.setActive(false);
		address.setAddressLineOne(processData.getMailAddr1());
		if (StringUtils.hasText(processData.getMailAddr2())) {
			address.setAddressLineTwo(processData.getMailAddr2());
		}
		if (StringUtils.hasText(processData.getMailAddr3())) {
			address.setAddressLineThree(processData.getMailAddr3());
		}
		if (StringUtils.hasText(processData.getMailAddr4())) {
			address.setAddressLineFour(processData.getMailAddr4());
		}
		if (StringUtils.hasText(processData.getMailCity())) {
			address.setCity(processData.getMailCity());
		}
		address.setTypeCode("M");
		address.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
		address.setCountry(processData.getMailCountry());
		address.setCountryCode("CA");
		address.setCreatedDate(processData.getCreatedAt());
		address.setDisplayActive(false);
		address.setDataOwnerCode(processData.getStakeholder());
		address.setNoChangeOnUpdate(false);
		if (StringUtils.hasText(processData.getMailPcode())) {
			address.setPostalCode(processData.getMailPcode());
		}
		address.setProvinceOrStateTxt(processData.getMailBc());
		address.setUpdatable(false);
		address.setValidCpc(true);
		address.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getMailingAddressGroupEffectiveStartDate()));
		if (processData.getMailingAddressGroupEffectiveEndDate() != null) {
			address.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getMailingAddressGroupEffectiveEndDate()));
		}
		if (StringUtils.hasText(processData.getMailingAddressGroupAction())) {
			address.setEndReasonCode(processData.getMailingAddressGroupAction());
		}
		
		return address;
	}
	
	private List<TelecommunicationDto> createTelecomunicationDtos() throws ParseException {
		
		String busAction = processData.getBusinessPhoneGroupAction();
		String cellAction = processData.getHdsCellGroupAction();
		String faxAction = processData.getHdsFaxGroupAction();
		
		if ((isUpdate && !StringUtils.hasText(busAction + cellAction + faxAction))
				|| !StringUtils.hasText(processData.getHdsBusTelNumber() + processData.getHdsCellNumber() + processData.getHdsFaxNumber())) {
			return null;
		}
		
		List<TelecommunicationDto> telecomList = new ArrayList<>();
		
		if (StringUtils.hasText(processData.getHdsBusTelNumber())
				|| (isUpdate && StringUtils.hasText(busAction))) {
			TelecommunicationDto hdsBusTelNumber = new TelecommunicationDto();
			hdsBusTelNumber.setNumber(processData.getHdsBusTelNumber());
			hdsBusTelNumber.setTypeCode("T");
			hdsBusTelNumber.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
			if (StringUtils.hasText(processData.getHdsTelExtension())) {
				hdsBusTelNumber.setExtension(processData.getHdsTelExtension());
			}
			if (StringUtils.hasText(processData.getHdsBusTelAreaCode())) {
				hdsBusTelNumber.setAreaCode(processData.getHdsBusTelAreaCode());
			}
			hdsBusTelNumber.setCreatedDate(processData.getCreatedAt());
			hdsBusTelNumber.setDataOwnerCode(processData.getStakeholder());
			hdsBusTelNumber.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getBusinessPhoneGroupEffectiveStartDate()));
			if (processData.getBusinessPhoneGroupEffectiveEndDate() != null) {
				hdsBusTelNumber.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getBusinessPhoneGroupEffectiveEndDate()));
			}
			if (StringUtils.hasText(busAction)) {
				hdsBusTelNumber.setEndReasonCode(busAction);
			}
			telecomList.add(hdsBusTelNumber);
		}
		if (StringUtils.hasText(processData.getHdsCellNumber())
				|| (isUpdate && StringUtils.hasText(cellAction))) {
			TelecommunicationDto hdsBusCellNumber = new TelecommunicationDto();
			hdsBusCellNumber.setNumber(processData.getHdsCellNumber());
			hdsBusCellNumber.setTypeCode("MB");
			hdsBusCellNumber.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
			if (StringUtils.hasText(processData.getHdsCellAreaCode())) {
				hdsBusCellNumber.setAreaCode(processData.getHdsCellAreaCode());
			}
			hdsBusCellNumber.setCreatedDate(processData.getCreatedAt());
			hdsBusCellNumber.setDataOwnerCode(processData.getStakeholder());
			hdsBusCellNumber.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsCellGroupEffectiveStartDate()));
			if (processData.getHdsCellGroupEffectiveEndDate() != null) {
				hdsBusCellNumber.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsCellGroupEffectiveEndDate()));
			}
			if (StringUtils.hasText(cellAction)) {
				hdsBusCellNumber.setEndReasonCode(cellAction);
			}
			telecomList.add(hdsBusCellNumber);
		}
		if (StringUtils.hasText(processData.getHdsFaxNumber())
				|| (isUpdate && StringUtils.hasText(faxAction))) {
			TelecommunicationDto hdsBusFaxNumber = new TelecommunicationDto();
			hdsBusFaxNumber.setNumber(processData.getHdsFaxNumber());
			hdsBusFaxNumber.setTypeCode("FAX");
			hdsBusFaxNumber.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
			if (StringUtils.hasText(processData.getHdsFaxAreaCode())) {
				hdsBusFaxNumber.setAreaCode(processData.getHdsFaxAreaCode());
			}
			hdsBusFaxNumber.setCreatedDate(processData.getCreatedAt());
			hdsBusFaxNumber.setDataOwnerCode(processData.getStakeholder());
			hdsBusFaxNumber.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsFaxGroupEffectiveStartDate()));
			if (processData.getHdsFaxGroupEffectiveEndDate() != null) {
				hdsBusFaxNumber.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsFaxGroupEffectiveEndDate()));
			}
			if (StringUtils.hasText(faxAction)) {
				hdsBusFaxNumber.setEndReasonCode(faxAction);
			}
			telecomList.add(hdsBusFaxNumber);
		}
		return telecomList;
	}
	
	private List<ElectronicAddressDto> createElectronicAddressDtos() throws ParseException {
		
		String emailAction = processData.getHdsEmailGroupAction();
		String webAction = processData.getHdsWebsiteGroupAction();
		
		if ((isUpdate && !StringUtils.hasText(emailAction + webAction))
				|| !StringUtils.hasText(processData.getHdsEmail() + processData.getHdsWebsite())) {
			return null;
		}
		
		List<ElectronicAddressDto> electronicAddressList = new ArrayList<>();
		
		if (StringUtils.hasText(processData.getHdsEmail())
				|| (isUpdate && StringUtils.hasText(emailAction))) {
			ElectronicAddressDto hdsEmail = new ElectronicAddressDto();
			hdsEmail.setAddress(processData.getHdsEmail());
			hdsEmail.setTypeCode("E");
			hdsEmail.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
			hdsEmail.setCreatedDate(processData.getCreatedAt());
			hdsEmail.setDataOwnerCode(processData.getStakeholder());
			hdsEmail.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsEmailGroupEffectiveStartDate()));
			if (processData.getHdsEmailGroupEffectiveEndDate() != null) {
				hdsEmail.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsEmailGroupEffectiveEndDate()));
			}
			if (StringUtils.hasText(emailAction)) {
				hdsEmail.setEndReasonCode(emailAction);
			}
			electronicAddressList.add(hdsEmail);
		}
		
		if (StringUtils.hasText(processData.getHdsWebsite())
				|| (isUpdate && StringUtils.hasText(webAction))) {
			ElectronicAddressDto hdsWebsite = new ElectronicAddressDto();
			hdsWebsite.setAddress(processData.getHdsWebsite());
			hdsWebsite.setTypeCode("H");
			hdsWebsite.setCommunicationPurposeCode(COMMUNICATION_PURPOSE_CODE);
			hdsWebsite.setCreatedDate(processData.getCreatedAt());
			hdsWebsite.setDataOwnerCode(processData.getStakeholder());
			hdsWebsite.setEffectiveStartDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsWebsiteGroupEffectiveStartDate()));
			if (processData.getHdsWebsiteGroupEffectiveEndDate() != null) {
				hdsWebsite.setEffectiveEndDate(EFFECTIVE_DATE_FORMAT.parse(processData.getHdsWebsiteGroupEffectiveEndDate()));
			}
			if (StringUtils.hasText(webAction)) {
				hdsWebsite.setEndReasonCode(webAction);
			}
			electronicAddressList.add(hdsWebsite);
		}
		
		return electronicAddressList;
	}
}
