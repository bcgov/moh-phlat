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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moh.phlat.backend.model.ProcessData;

import ca.bc.gov.health.plr.dto.esb.MaintainProviderRequest;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityDto;
import ca.bc.gov.health.plr.dto.facility.esb.FacilityRelationshipDto;
import ca.bc.gov.health.plr.dto.provider.esb.ProviderDetails;

public class OFRelationshipRequest implements PlrRequest {
	
	private ProcessData data;
	
	public OFRelationshipRequest(ProcessData data) {
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
		ProviderDetails providerDetails = new ProviderDetails();
		providerDetails.setFacilityRelationships(createFacilityRelationshipDtos());
		return providerDetails;
	}
	
	private FacilityDto createFacilityDto() {
		FacilityDto facility = new FacilityDto();
		return facility;
	}
	
	private List<FacilityRelationshipDto> createFacilityRelationshipDtos() {
		List<FacilityRelationshipDto> relationshipList = new ArrayList<>();
		
		FacilityRelationshipDto relationship = new FacilityRelationshipDto();
		relationship.setFacilityIdentifier(data.getPlrFacilityId());
		relationship.setFacilityIdentifierTypeCode("IFC");
		relationship.setFacilityRelationshipTypeCode("LOCATION");
		relationship.setPauthId(Long.valueOf(data.getHdsPauthId()));
		relationship.setProviderRelationshipTypeCode("LOCATED");
		
		return relationshipList;
	}
}
