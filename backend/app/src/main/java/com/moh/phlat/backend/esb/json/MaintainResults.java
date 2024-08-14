package com.moh.phlat.backend.esb.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintainResults {

	private MaintainFacilityResponse facilityResult;
	private MaintainHdsResponse hdsResult;
	private OFRelationshipResponse oFResult;
	
}
