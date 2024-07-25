package com.moh.phlat.backend.esb.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(PlrDataLoad.class);
	
	@Autowired
	@Getter
	private PlrEsbBoundary plrEsbBoundary;
	
	public MaintainResults loadPlrViaEsb(Control control, ProcessData processData) {
		MaintainResults maintainResults = new MaintainResults();
		if (control.getLoadTypeFacility()) {
			MaintainFacilityResponse facilityResponse = createFacility(control, processData);
			maintainResults.setFacilityResult(facilityResponse);
		}
		if (control.getLoadTypeHds()) {
			//MaintainHdsResponse hdsResponse = createHdsProvider(control, processData);
			//maintainResults.setHdsResult(hdsResponse);
		}
		if (control.getLoadTypeOFRelationship()) {
			//OFRelationshipResponse ofResponse = createOFRelationship(control, processData);
			//maintainResults.setOFResult(ofResponse);
		}
		return maintainResults;
	}
	
	private MaintainFacilityResponse createFacility(Control control, ProcessData processData) {
		
		MaintainFacilityRequest maintainFacilityRequest = new MaintainFacilityRequest(control, processData);
		MaintainFacilityResponse maintainFacilityResponse = new MaintainFacilityResponse(control, processData);
		
		plrEsbBoundary.maintainProvider(control, maintainFacilityRequest, maintainFacilityResponse);
		
		return maintainFacilityResponse;
	}
	
	private MaintainHdsResponse createHdsProvider(Control control, ProcessData processData) {
		MaintainHdsRequest maintainHdsRequest = new MaintainHdsRequest(control, processData);
		MaintainHdsResponse maintainHdsResponse = new MaintainHdsResponse(control, processData);
		
		plrEsbBoundary.maintainProvider(control, maintainHdsRequest, maintainHdsResponse);		
		return maintainHdsResponse;
	}
}
