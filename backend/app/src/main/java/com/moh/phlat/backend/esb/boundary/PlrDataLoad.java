package com.moh.phlat.backend.esb.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moh.phlat.backend.esb.json.MaintainFacilityRequest;
import com.moh.phlat.backend.esb.json.MaintainFacilityResponse;
import com.moh.phlat.backend.esb.json.MaintainHdsRequest;
import com.moh.phlat.backend.esb.json.MaintainHdsResponse;
import com.moh.phlat.backend.esb.json.MaintainResults;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import lombok.Getter;

@Component
public class PlrDataLoad {
	
	@Autowired
	@Getter
	private PlrEsbBoundary plrEsbBoundary;
	
	public MaintainResults loadPlrViaEsb(Control control, ProcessData processData) {
		MaintainResults maintainResults = new MaintainResults();
		if (control.getLoadTypeFacility()) {
			MaintainFacilityResponse facilityResponse = createFacility(control, processData);
			maintainResults.setFacilityResult(facilityResponse);
		}
		if (maintainResults.getFacilityResult().verifyStatus()
				&& control.getLoadTypeHds()) {
			//*** WILL IMPLEMENT THIS AFTER FACILITY LOAD IS COMPLETE ***
			//MaintainHdsResponse hdsResponse = createHdsProvider(control, processData);
			//maintainResults.setHdsResult(hdsResponse);
			
			if (maintainResults.getHdsResult().verifyStatus()
					&& control.getLoadTypeOFRelationship()) {
				//*** WILL IMPLEMENT THIS AFTER HDS LOAD IS COMPLETE ***
				//OFRelationshipResponse ofResponse = createOFRelationship(control, processData);
				//maintainResults.setOFResult(ofResponse);
			}
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