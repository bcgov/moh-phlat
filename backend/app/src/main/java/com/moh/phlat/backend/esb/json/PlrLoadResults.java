package com.moh.phlat.backend.esb.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlrLoadResults {

	private MaintainFacilityResponse facilityResult;
	private MaintainHdsResponse hdsResult;
	private OFRelationshipResponse oFResult;
	
	public boolean isAllLoaded() {
		if (facilityResult != null && !facilityResult.isLoaded()) {
			return false;
		}
		if (hdsResult != null && !hdsResult.isLoaded()) {
			return false;
		}
		if (oFResult != null && !oFResult.isLoaded()) {
			return false;
		}
		return true;
	}
	
	public boolean hasAnyDuplicates() {
		if (facilityResult != null) {
			return facilityResult.isDuplicate();
		}
		return false;
	}
}
