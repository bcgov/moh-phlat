package com.moh.phlat.backend.esb.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.esb.json.MaintainFacilityRequest;
import com.moh.phlat.backend.esb.json.MaintainFacilityResponse;
import com.moh.phlat.backend.esb.json.MaintainHdsRequest;
import com.moh.phlat.backend.esb.json.MaintainHdsResponse;
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
			if (!StringUtils.hasText(processData.getPlrFacilityId())) {
				MaintainFacilityResponse facilityResponse = createFacility(control, processData);
				maintainResults.setFacilityResult(facilityResponse);
			} else {
				// If this record already has a facility ID, mark it as loaded and skip
				maintainResults.setFacilityResult(new MaintainFacilityResponse(true));
			}
		}
		// HDS Load
		if (control.getLoadTypeHds() && StringUtils.hasText(processData.getPlrFacilityId())) {
			if (!StringUtils.hasText(processData.getHdsPauthId())
					&& !StringUtils.hasText(processData.getHdsCpnId())
					&& !StringUtils.hasText(processData.getHdsIpcId())) {
				MaintainHdsResponse hdsResponse = createHdsProvider(control, processData);
				maintainResults.setHdsResult(hdsResponse);
			} else {
				// If this record already has HDS IDs, mark it as loaded and skip
				maintainResults.setHdsResult(new MaintainHdsResponse(true));
			}
		}
		//OF Relationship Load
		if (control.getLoadTypeOFRelationship()
				&& StringUtils.hasText(processData.getPlrFacilityId())
				&& StringUtils.hasText(processData.getHdsPauthId())
				&& StringUtils.hasText(processData.getHdsCpnId())
				&& StringUtils.hasText(processData.getHdsIpcId())) {
			//*** WILL IMPLEMENT THIS AFTER HDS LOAD IS COMPLETE ***
			//OFRelationshipResponse ofResponse = createOFRelationship(control, processData);
			//maintainResults.setOFResult(ofResponse);
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
}
