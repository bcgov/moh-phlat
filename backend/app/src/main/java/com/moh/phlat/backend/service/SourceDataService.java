package com.moh.phlat.backend.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

import com.moh.phlat.backend.model.ParamSource;
import com.moh.phlat.backend.model.SourceData;

public interface SourceDataService {
	
	List<String> SOURCE_DATA_COLUMNS = Arrays.asList("id", "control_id", "doNotLoadFlag", "stakeholder", "hdsIpcId", "hdsCpnId", "hdsProviderIdentifier1",
			"hdsProviderIdentifier2", "hdsProviderIdentifier3", "hdsProviderIdentifierType1", "hdsProviderIdentifierType2", "hdsProviderIdentifierType3", "hdsHibcFacilityId",
			"hdsType", "hdsName", "hdsNameAlias", "hdsPreferredNameFlag", "hdsEmail", "hdsWebsite", "hdsBusTelAreaCode", "hdsBusTelNumber", "hdsTelExtension", 
			"hdsCellAreaCode", "hdsCellNumber", "hdsFaxAreaCode", "hdsFaxNumber", "hdsServiceDeliveryType", "pcnClinicType", "pcnPciFlag", "hdsHoursOfOperation",
			"hdsContactName", "hdsIsForProfitFlag", "sourceStatus", "hdaParentIpcId", "busIpcId", "busCpnId", "busName",  "busLegalName", "busPayeeNumber", "busOwnerName",
			"busOwnerType", "busOwnerTypeOther", "facBuildingName", "facilityHdsDetailsAdditionalInfo", "physicalAddr1", "physicalAddr2", "physicalAddr3", "physicalAddr",
			"physicalCity", "physicalProvince", "physicalPcode", "physicalCountry", "physAddrIsPrivate", "mailAddr1", "mailAddr2", "mailAddr3", "mailAddr4", "nailCity",
			"mailBc", "mailPcode", "mailCountry", "mailAddrIsPrivate");

	public List<SourceData> getSourceData(Long controlId,  ParamSource pSource);
	
	public List<String> getUniqueColumnValues(@PathVariable Long controlTableId, @PathVariable String columnKey);
}
