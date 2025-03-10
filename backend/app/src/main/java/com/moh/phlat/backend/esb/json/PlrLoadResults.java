package com.moh.phlat.backend.esb.json;

import com.moh.phlat.backend.model.Control;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlrLoadResults {

	private MaintainFacilityResponse facilityResult;
	private MaintainHdsResponse hdsResult;
	private OFRelationshipResponse oFResult;
	
	public boolean isAllLoaded(Control control) {
		if (control.getLoadTypeFacility() && (facilityResult == null || !facilityResult.isLoaded())) {
			return false;
		}
		if (control.getLoadTypeHds() && (hdsResult == null || !hdsResult.isLoaded())) {
			return false;
		}
		if (control.getLoadTypeOFRelationship() && (oFResult == null || !oFResult.isLoaded())) {
			return false;
		}
		return true;
	}
}
