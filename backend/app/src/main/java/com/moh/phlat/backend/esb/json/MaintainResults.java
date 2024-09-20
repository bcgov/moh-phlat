package com.moh.phlat.backend.esb.json;

import java.util.ArrayList;
import java.util.List;

import com.moh.phlat.backend.esb.boundary.PlrError;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintainResults {

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
		if (facilityResult != null && facilityResult.isDuplicate()) {
			return true;
		}
		if (hdsResult != null && hdsResult.isDuplicate()) {
			return true;
		}
		return false;
	}
	
	public List<PlrError> getPlrErrors() {
		
		List<PlrError> plrErrors = new ArrayList<>();
		if (facilityResult != null) {
			plrErrors.addAll(facilityResult.getPlrErrors());
		}
		if (hdsResult != null) {
			plrErrors.addAll(hdsResult.getPlrErrors());
		}
		if (oFResult != null) {
			plrErrors.addAll(oFResult.getPlrErrors());
		}
		
		return plrErrors;
	}
}
