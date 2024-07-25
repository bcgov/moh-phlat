package com.moh.phlat.backend.esb.json;

import lombok.Getter;
import lombok.Setter;

public class MaintainResults implements PlrResults {

	@Getter
	@Setter
	private MaintainFacilityResponse facilityResult;
	@Getter
	@Setter
	private MaintainHdsResponse hdsResult;
	@Getter
	@Setter
	private OFRelationshipResponse oFResult;
	
}
