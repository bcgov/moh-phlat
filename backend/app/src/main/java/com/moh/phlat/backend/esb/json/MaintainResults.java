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
