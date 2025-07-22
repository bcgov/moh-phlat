package com.moh.phlat.backend.esb.json;

import java.io.IOException;
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
import ca.bc.gov.health.plr.dto.facility.esb.FacilityRelationshipDto;
import ca.bc.gov.health.plr.dto.provider.esb.CollegeIdentifierDto;
import ca.bc.gov.health.plr.dto.provider.esb.JurisdictionNameCodeDto;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;
import ca.bc.gov.health.plr.dto.provider.esb.StatusDto;

public class OFRelationshipRequest implements PlrRequest {
	
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
	private Long pauthId;
	
	public OFRelationshipRequest(ProcessData processData) {
		this.processData = processData;
		if (this.processData.getHdsPauthId() != null) {
			pauthId = Long.valueOf(this.processData.getHdsPauthId());
		}
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
		return maintainProviderRequest;
	}

	private Map<String,Object> createEsbMetadata() {
		Map<String,Object> esbMetaData = new HashMap<String,Object>();
		esbMetaData.put("isLRA", false);
		return esbMetaData;
	}
	
	private ProviderDetails createProviderDetails() {
		ProviderDetails providerDetails = new ProviderDetails();
		providerDetails.setPauthId(pauthId);
		providerDetails.setType("HDS");
		providerDetails.setProviderType("ORG");
		providerDetails.setIdentifiers(createIdentifierDtos());
		providerDetails.setJurisdiction(createJurisdictionDto());
		providerDetails.setStatuses(createStatusDtos());
		providerDetails.setFacilityRelationships(createFacilityRelationshipDtos());
		return providerDetails;
	}
	
	private List<CollegeIdentifierDto> createIdentifierDtos() {
		List<CollegeIdentifierDto> identifierList = new ArrayList<>();
		
		addHdsProviderIdentifier(identifierList, processData.getHdsIpcId(), "IPC");
		addHdsProviderIdentifier(identifierList, processData.getHdsCpnId(), "CPN");
				
		if (identifierList.isEmpty()) {
			return null;
		}
		return identifierList;
	}
	
	private void addHdsProviderIdentifier(List<CollegeIdentifierDto> identifierList, String hdsId, String hdsIdType) {
		if (StringUtils.hasText(hdsId) && StringUtils.hasText(hdsIdType)) {
			CollegeIdentifierDto identifierDto = new CollegeIdentifierDto();
			identifierDto.setPauthId(pauthId);
			identifierDto.setIdentifier(hdsId);
			identifierDto.setTypeCode(hdsIdType);
			identifierDto.setEffectiveStartDate(processData.getCreatedAt());
			identifierList.add(identifierDto);
		}
	}
	
	private JurisdictionNameCodeDto createJurisdictionDto() {
		JurisdictionNameCodeDto jurisdictionDto = new JurisdictionNameCodeDto();
		jurisdictionDto.setJurisdicationNameCode(processData.getPhysicalProvince());
		
		return jurisdictionDto;
	}
	
	private List<StatusDto> createStatusDtos() {
		List<StatusDto> statusList = new ArrayList<>();
		
		StatusDto status = new StatusDto();
		status.setEndReasonCode("CHG");
		status.setTypeCode("ACTIVE");
		status.setClassCode("AE");
		status.setReasonCode("ORG");
		status.setDataOwnerCode(processData.getStakeholder());
		status.setEffectiveStartDate(processData.getCreatedAt());
		statusList.add(status);
		
		return statusList;
	}
	
	private List<FacilityRelationshipDto> createFacilityRelationshipDtos() {
		List<FacilityRelationshipDto> relationshipList = new ArrayList<>();
		
		FacilityRelationshipDto relationship = new FacilityRelationshipDto();
		relationship.setFacilityIdentifier(processData.getFacIfcId());
		relationship.setFacilityIdentifierTypeCode("IFC");
		relationship.setPauthId(pauthId);
		relationship.setProviderIdentifier(processData.getHdsIpcId());
		relationship.setProviderIdentifierTypeCode("IPC");
		relationship.setProviderRelationshipTypeCode("LOCATED");
		relationship.setEffectiveStartDate(processData.getCreatedAt());
		relationship.setDataOwnerCode(processData.getStakeholder());
		relationshipList.add(relationship);
		
		return relationshipList;
	}
}
