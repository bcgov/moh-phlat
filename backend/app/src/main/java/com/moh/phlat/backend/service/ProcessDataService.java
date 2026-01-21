package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.service.dto.ReportSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;

import java.util.Arrays;
import java.util.List;

public interface ProcessDataService {
	
	List<String> PROCESS_DATA_COLUMNS = Arrays.asList("controlTableId", "doNotLoad", "stakeholder", "stakeholderId", "hdsIpcId", "hdsCpnId", "hdsProviderIdentifier1",
			"hdsProviderIdentifier2", "hdsProviderIdentifier3", "hdsProviderIdentifierType1", "hdsProviderIdentifierType2", "hdsProviderIdentifierType3", 
			"hdsMspFacilityNumber", "hdsPauthId", "hdsCategoryCode", "hdsRoleTypeCode", "hdsType", "hdsSubType", "hdsUserChid", "hdsCreatedDts", "hdsInvalidatedDts",
			"hdsName", "hdsPreferredNameFlag", "hdsEmail", "hdsWebsite", "hdsBusTelAreaCode", "hdsBusTelNumber", "hdsTelExtension", "hdsCellAreaCode", "hdsCellNumber", 
			"hdsFaxAreaCode", "hdsFaxNumber", "pcnServiceDeliveryType", "pcnClinicType", "pcnPciFlag", "sourceStatus", "pcnClinicStatus", "hdsEffectiveStartDate",
			"hdsEffectiveEndDate", "facAddressUnit", "facBuildingName", "facCivicAddrId", "facCivicAddr", "facLatitude", "facLongitude",
			"facStreetDirection", "streetDirectionPrefix", "streetTypePrefix", "facCivicNumber", "facStreetName", "facStreetType",
			"facLocalityName", "facProvinceCode", "facSiteId", "facScore", "facMatchPrecision", "facPrecisionPoints",    "facHsdaName",
			"facDatabcResults", "facPcnCode", "facPcnName", "facChsaStatus", "facPcnStatus", "facChsaCode", "facChsaName", "facLhaName", "facHaName",
			"facRelnType", "facTypeCode", "physicalAddr1", "physicalAddr2", "physicalAddr3", "physicalAddr4", "physicalCity", "physicalProvince", "physicalPcode", 
			"physicalCountry", "physicalAddrPrpsTypeCd", "physicalAddrValidationStatus", "physicalAddrMailabilityScore", 
			"mailAddr1", "mailAddr2", "mailAddr3", "mailAddr4", "mailCity", "mailBc", "mailPcode", "mailCountry", "mailAddrPrpsTypeCd", "mailAddrValidationStatus", 
			"mailAddrMailabilityScore", "plrFacilityId", "facRelnType", "facIfcId", "rowstatusCode", "createdAt", "updatedAt",
			"primaryCareGroupAction","primaryCareGroupEffectiveStartDate", "primaryCareGroupEffectiveEndDate", 
			"hdsSubTypeGroupAction","hdsSubTypeGroupEffectiveStartDate", "hdsSubTypeGroupEffectiveEndDate", 
			"hdsNameGroupAction","hdsNameGroupEffectiveStartDate", "hdsNameGroupEffectiveEndDate", 
			"statusGroupAction","statusGroupEffectiveStartDate", "statusGroupEffectiveEndDate",
			"hdsEmailGroupAction","hdsEmailGroupEffectiveStartDate", "hdsEmailGroupEffectiveEndDate", 
			"hdsWebsiteGroupAction","hdsWebsiteGroupEffectiveStartDate", "hdsWebsiteGroupEffectiveEndDate",
			"businessPhoneGroupAction","businessPhoneGroupEffectiveStartDate", "businessPhoneGroupEffectiveEndDate",
			"hdsFaxGroupAction","hdsFaxGroupEffectiveStartDate", "hdsFaxGroupEffectiveEndDate",	
			"hdsCellGroupAction","hdsCellGroupEffectiveStartDate", "hdsCellGroupEffectiveEndDate",
			"physicalAddressGroupAction","physicalAddressGroupEffectiveStartDate", "physicalAddressGroupEffectiveEndDate",					
			"mailingAddressGroupAction","mailingAddressGroupEffectiveStartDate", "mailingAddressGroupEffectiveEndDate",							 			
			"recordAction"
			);

    List<ReportSummary> getReportSummary(Long controlTableId);
	
	List<String> getUniqueColumnValues(Long controlTableId, String columnKey);
	
	Page<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus, int page, int itemsPerPage, ProcessDataFilterParams filterProcess, 
			List<Order> sortOrders);
}
