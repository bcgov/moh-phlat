package com.moh.phlat.backend.esb.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.esb.json.MaintainFacilityRequest;
import com.moh.phlat.backend.esb.json.MaintainFacilityResponse;
import com.moh.phlat.backend.esb.json.MaintainHdsRequest;
import com.moh.phlat.backend.esb.json.MaintainHdsResponse;
import com.moh.phlat.backend.esb.json.OFRelationshipRequest;
import com.moh.phlat.backend.esb.json.OFRelationshipResponse;
import com.moh.phlat.backend.esb.json.PlrLoadResults;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import lombok.Getter;

@Component
public class PlrDataLoad {
	
	@Autowired
	@Getter
	private PlrEsbBoundary plrEsbBoundary;
	
	public PlrLoadResults loadPlrViaEsb(Control control, ProcessData processData) {
		
		PlrLoadResults maintainResults = new PlrLoadResults();
		
		// Facility Load
		if (control.getLoadTypeFacility()) {
			if (!StringUtils.hasText(processData.getFacIfcId())) {
				MaintainFacilityResponse facilityResponse = createFacility(control, processData);
				maintainResults.setFacilityResult(facilityResponse);
			} else {
				// If this record already has a facility ID, mark it as loaded and skip
				maintainResults.setFacilityResult(new MaintainFacilityResponse(true));
			}
		}
		// HDS Load
		if (control.getLoadTypeHds()) {
			if (StringUtils.hasText(processData.getFacIfcId())
					&& !StringUtils.hasText(processData.getHdsPauthId())
					&& !StringUtils.hasText(processData.getHdsCpnId())
					&& !StringUtils.hasText(processData.getHdsIpcId())
					&& !StringUtils.hasText(processData.getRecordAction())) {
				// Create HDS
				MaintainHdsResponse hdsResponse = createHdsProvider(control, processData);
				maintainResults.setHdsResult(hdsResponse);
			} else if (StringUtils.hasText(processData.getRecordAction())) {
				// Update HDS
				MaintainHdsResponse hdsResponse = updateHdsProvider(control, processData);
				maintainResults.setHdsResult(hdsResponse);
			} else {
				// HDS Identifiers are present but there's no Record Action; mark as not loaded and skip
				maintainResults.setHdsResult(new MaintainHdsResponse(false));
			}
		}
		//OF Relationship Load
		if (control.getLoadTypeOFRelationship()
				&& StringUtils.hasText(processData.getFacIfcId())
				&& StringUtils.hasText(processData.getHdsPauthId())
				&& StringUtils.hasText(processData.getHdsCpnId())
				&& StringUtils.hasText(processData.getHdsIpcId())) {
			OFRelationshipResponse ofResponse = createOFRelationship(control, processData);
			maintainResults.setOFResult(ofResponse);
		} else {
			// Facility and/or HDS is missing; mark as not loaded and skip
			maintainResults.setOFResult(new OFRelationshipResponse(false));
		}
		return maintainResults;
	}
	
	private MaintainFacilityResponse createFacility(Control control, ProcessData processData) {
		
		MaintainFacilityRequest maintainFacilityRequest = new MaintainFacilityRequest(processData);
		MaintainFacilityResponse maintainFacilityResponse = new MaintainFacilityResponse(processData);
		
		plrEsbBoundary.maintainProvider(control, maintainFacilityRequest, maintainFacilityResponse);
		
		return maintainFacilityResponse;
	}
	
	private MaintainHdsResponse createHdsProvider(Control control, ProcessData processData) {
		MaintainHdsRequest maintainHdsRequest = new MaintainHdsRequest(processData);
		MaintainHdsResponse maintainHdsResponse = new MaintainHdsResponse(processData);
		
		plrEsbBoundary.maintainProvider(control, maintainHdsRequest, maintainHdsResponse);
		
		return maintainHdsResponse;
	}
	
	private MaintainHdsResponse updateHdsProvider(Control control, ProcessData processData) {
		MaintainHdsRequest maintainHdsRequest = new MaintainHdsRequest(processData, true);
		MaintainHdsResponse maintainHdsResponse = new MaintainHdsResponse(processData);
		
		plrEsbBoundary.maintainProvider(control, maintainHdsRequest, maintainHdsResponse);
		
		return maintainHdsResponse;
	}
	
	private OFRelationshipResponse createOFRelationship(Control control, ProcessData processData) {
		OFRelationshipRequest oFRelationshipRequest = new OFRelationshipRequest(processData);
		OFRelationshipResponse oFRelationshipResponse = new OFRelationshipResponse(processData);
		
		plrEsbBoundary.maintainProvider(control, oFRelationshipRequest, oFRelationshipResponse);
		
		return oFRelationshipResponse;
	}
}
