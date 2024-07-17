package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.service.dto.ReportSummary;

import java.util.Arrays;
import java.util.List;

public interface ProcessDataService {
	
	List<String> PROCESS_DATA_COLUMNS = Arrays.asList("id", "controlTableId", "do_not_load", "stakeholder", "hdsIpcId", "hdsCpnId", "hdsProviderIdentifier1",
			"hdsProviderIdentifier2", "hdsProviderIdentifier3", "hdsProviderIdentifierType1", "hdsProviderIdentifierType2", "hdsProviderIdentifierType3", 
			"hdsMspFacilityNumber", "hdsType", "hdsName", "hds_name_alias", "hdsPreferredNameFlag", "hdsEmail", "hdsWebsite", "hdsBusTelAreaCode", "hdsBusTelNumber",
			"hdsTelExtension", "hdsCellAreaCode", "hdsCellNumber", "hdsFaxAreaCode", "hdsFaxNumber", "pcnServiceDeliveryType", "pcnClinicType", "pcnPciFlag",
			"hdsHoursOfOperation", "hdsContactName", "hdsIsForProfitFlag", "sourceStatus", "hdsParentIpcId", "busIpcId", "busCpnId", "busName", "busLegalName",
			"busPayeeNumber", "busOwnerName", "busOwnerType", "busOwnerTypeOther", "facBuildingName", "facilityHdsDetailsAdditionalInfo", "physicalAddr1",
			"physicalAddr2", "physicalAddr3", "physicalAddr", "physicalCity", "physicalProvince", "physicalPcode", "physicalCountry", "physAddrIsPrivate",
			"mailAddr1", "mailAddr2", "mailAddr3", "mailAddr4", "mailCity", "mailBc", "mailPcode", "mailCountry", "mailAddrIsPrivate","rowstatusCode","hdsSubType");

    List<ReportSummary> getReportSummary(Long controlTableId);
	
	List<ProcessData> getProcessDataWithMessages(Long controlId, String reqRowStatusCode, ProcessDataFilterParams filterProcess);
	
	List<String> getUniqueColumnValues(Long controlTableId, String columnKey);
}
