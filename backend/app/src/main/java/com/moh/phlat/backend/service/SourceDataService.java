package com.moh.phlat.backend.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import com.moh.phlat.backend.model.SourceData;

public interface SourceDataService {
	
	public final List<String> SOURCE_DATA_COLUMNS = Arrays.asList("id", "control_id", "doNotLoadFlag", "stakeholder", "hdsIpcId", "hdsCpnId", "hdsProviderIdentifier1",
			"hdsProviderIdentifier2", "hdsProviderIdentifier3", "hdsProviderIdentifierType1", "hdsProviderIdentifierType2", "hdsProviderIdentifierType3", "hdsHibcFacilityId",
			"hdsType", "hdsName", "hdsNameAlias", "hdsPreferredNameFlag", "hdsEmail", "hdsWebsite", "hdsBusTelAreaCode", "hdsBusTelNumber", "hdsTelExtension", 
			"hdsCellAreaCode", "hdsCellNumber", "hdsFaxAreaCode", "hdsFaxNumber", "hdsServiceDeliveryType", "pcnClinicType", "pcnPciFlag", "hdsHoursOfOperation",
			"hdsContactName", "hdsIsForProfitFlag", "sourceStatus", "hdaParentIpcId", "busIpcId", "busCpnId", "busName",  "busLegalName", "busPayeeNumber", "busOwnerName",
			"busOwnerType", "busOwnerTypeOther", "facBuildingName", "facilityHdsDetailsAdditionalInfo", "physicalAddr1", "physicalAddr2", "physicalAddr3", "physicalAddr",
			"physicalCity", "physicalProvince", "physicalPcode", "physicalCountry", "physAddrIsPrivate", "mailAddr1", "mailAddr2", "mailAddr3", "mailAddr4", "nailCity",
			"mailBc", "mailPcode", "mailCountry", "mailAddrIsPrivate");

	public List<SourceData> findAll(Long controlId, List<String> ids, List<String> doNotLoad, List<String> stakeholder, 
			List<String> hdsLpcId, List<String> hdsCpnId, List<String> hdsProviderId1, List<String> hdsProviderId2, List<String> hdsProviderId3, 
			List<String> hdsProviderIdType1, List<String> hdsProviderIdType2, List<String> hdsProviderIdType3, List<String> hdsHibcFacId, 
			List<String> hdsType, List<String> hdsName, List<String> hdsNameAlias, List<String> hdsPrefNameFlag, List<String> hdsEmail, 
			List<String> hdsWebsite, List<String> hdsBusTelAreaCode, List<String> hdsBusTelNum, List<String> hdsTelExt, List<String> hdsCellAreaCode,
			List<String> hdsCellNum, List<String> hdsFaxAreaCode, List<String> hdsFaxNum, List<String> hdsServiceDelType, List<String> pcnCLinicType,
			List<String> pcnPciFlag, List<String> hdsHoursOfOp, List<String> hdsContactName, List<String> hdsIsForProfitFlag, List<String> sourceStatus,
			List<String> hdsParentIpcId, List<String> busIpcId, List<String> busCpnId, List<String> busName, List<String> busLegalName, 
			List<String> busPayeeNum, List<String> busOwnerName, List<String> busOwnerType, List<String> busOwnerTypeOther, 
			List<String> facBuildingName, List<String> facHdsDetailAddInfo, List<String> physAddr1, List<String> physAddr2, List<String> physAddr3,
			List<String> physAddr4, List<String> physCity, List<String> physProv, List<String> physPCode, List<String> physCountry, 
			List<String> physAddrIsPrivate, List<String> mailAddr1, List<String> mailAddr2, List<String> mailAddr3, List<String> mailAddr4, 
			List<String> mailCity, List<String> mailBc, List<String> mailPcode, List<String> mailCountry, List<String> mailAddrIsPriv);
	
	public List<String> getDistinctColumnValues(@PathVariable Long controlTableId, @PathVariable String columnKey);
}
