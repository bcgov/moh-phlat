package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import com.moh.phlat.backend.service.dto.ReportSummary;
import com.moh.phlat.backend.service.RowStatusService;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;
    
	@Autowired
	private SpecificationService specificationService;

    @Override
	public List<ProcessData> getProcessDataWithMessages(Long controlId) {

		return getProcessDataWithMessages(controlId,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);

    }
    
    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlId, String reqRowStatusCode, List<String> ids, List<String> actions, 
    		List<String> rowStatusCode, List<String> messages, List<String> doNotLoad, List<String> stakeholder, List<String> hdsLpcId, 
    		List<String> hdsCpnId, List<String> hdsProviderId1, List<String> hdsProviderId2, List<String> hdsProviderId3, 
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
    		List<String> mailCity, List<String> mailBc, List<String> mailPcode, List<String> mailCountry, List<String> mailAddrIsPriv) {

    	return processDataRepository.findAll(specificationService.buildSpecificationInProcessData(controlId, reqRowStatusCode, ids, actions,  rowStatusCode, 
    			messages, doNotLoad, stakeholder, hdsLpcId, hdsCpnId, hdsProviderId1, hdsProviderId2, hdsProviderId3, hdsProviderIdType1, 
    			hdsProviderIdType2, hdsProviderIdType3, hdsHibcFacId, hdsType, hdsName, hdsNameAlias, hdsPrefNameFlag, hdsEmail,
    			hdsWebsite, hdsBusTelAreaCode, hdsBusTelNum, hdsTelExt, hdsCellAreaCode, hdsCellNum, hdsFaxAreaCode, hdsFaxNum,
    			hdsServiceDelType, pcnCLinicType, pcnPciFlag, hdsHoursOfOp, hdsContactName, hdsIsForProfitFlag,
    			sourceStatus, hdsParentIpcId, busIpcId, busCpnId, busName, busLegalName, busPayeeNum, busOwnerName,
    			busOwnerType, busOwnerTypeOther, facBuildingName, facHdsDetailAddInfo, physAddr1, physAddr2,
    			physAddr3, physAddr4, physCity, physProv, physPCode, physCountry, physAddrIsPrivate, mailAddr1,
    			mailAddr2, mailAddr3, mailAddr4, mailCity, mailBc, mailPcode, mailCountry, mailAddrIsPriv, true));
    }

    private static ReportSummary createReportSummaryData(String reportAttributeName, Long reportAttributeValue) {
        return ReportSummary.builder()
						  .attribute(reportAttributeName)
                          .count(reportAttributeValue)
                          .build();
    }

    public List<ReportSummary> getReportSummary(Long controlTableId) {
		String reportAttributeName;
		Long reportAttributeValue;

		List<ReportSummary> items = new ArrayList<ReportSummary>();

		reportAttributeName ="TOTAL INPUT RECORDS";
		reportAttributeValue = processDataRepository.countByControlTableId(controlTableId);
     	ReportSummary rs1 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs1);
		
		reportAttributeName = "TOTAL INITIAL ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.INITIAL);
     	ReportSummary rs2 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs2);

		reportAttributeName = "TOTAL DO_NOT_LOAD ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.DO_NOT_LOAD);
     	ReportSummary rs3 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs3);		
		
		reportAttributeName ="TOTAL INVALID ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.INVALID);
     	ReportSummary rs4 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs4);	

		reportAttributeName ="TOTAL VALID ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.VALID);
		ReportSummary rs5 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs5);	
		
		reportAttributeName ="TOTAL WARNING ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.WARNING);
     	ReportSummary rs6 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs6);	

		reportAttributeName ="TOTAL COMPLETED ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.COMPLETED);
     	ReportSummary rs7 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs7);

		reportAttributeName = "TOTAL POTENTIAL_DUPLICATE ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.POTENTIAL_DUPLICATE);
     	ReportSummary rs8 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs8);
		
		reportAttributeName = "TOTAL LOAD_ERROR ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.LOAD_ERROR);
     	ReportSummary rs9 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs9);			
		
		// adding message code and desc to the list

		List<Object[]> listMsg = processDataRepository.getProcessDataWithMessageCodeCount(controlTableId);

		for (Object[] msg : listMsg){
			String code = (String) msg[1];
			if (StringUtils.hasText(code)) {
				reportAttributeName = (String) msg[0] + " " + (String) msg[1] + " " + (String) msg[2];
				reportAttributeValue = (Long) msg[3];
				ReportSummary rsMessage = createReportSummaryData(reportAttributeName, reportAttributeValue);
				items.add(rsMessage);	
			}	
		}   
		return items;
	}

	@Override
	public List<String> getDistinctColumnValues(Long controlTableId, String columnKey) {
		switch(columnKey) {
			case "id":
				return processDataRepository.findAllDistinctId(controlTableId);
			case "controlTableId":
				return processDataRepository.findAllDistinctControlId(controlTableId);
			case "do_not_load":
				return processDataRepository.findAllDistinctDoNotLoad(controlTableId);
			case "stakeholder":
				return processDataRepository.findAllDistinctStakeholder(controlTableId);
			case "hdsIpcId":
				return processDataRepository.findAllDistinctHdsIpcId(controlTableId);
			case "hdsCpnId":
				return processDataRepository.findAllDistinctHdsCpnId(controlTableId);
			case "hdsProviderIdentifier1":
				return processDataRepository.findAllDistinctHdsProviderIdentifier1(controlTableId);
			case "hdsProviderIdentifier2":
				return processDataRepository.findAllDistinctHdsProviderIdentifier2(controlTableId);
			case "hdsProviderIdentifier3":
				return processDataRepository.findAllDistinctHdsProviderIdentifier3(controlTableId);
			case "hdsProviderIdentifierType1":
				return processDataRepository.findAllDistinctHdsProviderIdentifierType1(controlTableId);
			case "hdsProviderIdentifierType2":
				return processDataRepository.findAllDistinctHdsProviderIdentifierType2(controlTableId);
			case "hdsProviderIdentifierType3":
				return processDataRepository.findAllDistinctHdsProviderIdentifierType3(controlTableId);
			case "hdsMspFacilityNumber":
				return processDataRepository.findAllDistinctHdsHibcFacilityId(controlTableId);
			case "hdsType":
				return processDataRepository.findAllDistinctHdsType(controlTableId);
			case "hdsName":
				return processDataRepository.findAllDistinctHdsName(controlTableId);
			case "hds_name_alias":
				//return processDataRepository.findAllDistinctHdsNameAlias(controlTableId);
				break;
			case "hdsPreferredNameFlag":
				return processDataRepository.findAllDistinctHdsPreferredNameFlag(controlTableId);
			case "hdsEmail":
				return processDataRepository.findAllDistinctHdsEmail(controlTableId);
			case "hdsWebsite":
				return processDataRepository.findAllDistinctHdsWebsite(controlTableId);
			case "hdsBusTelAreaCode":
				return processDataRepository.findAllDistinctHdsBusTelAreaCode(controlTableId);
			case "hdsBusTelNumber":
				return processDataRepository.findAllDistinctHdsBusTelNumber(controlTableId);
			case "hdsTelExtension":
				return processDataRepository.findAllDistinctHdsTelExtension(controlTableId);
			case "hdsCellAreaCode":
				return processDataRepository.findAllDistinctHdsCellAreaCode(controlTableId);
			case "hdsCellNumber":
				return processDataRepository.findAllDistinctHdsCellNumber(controlTableId);
			case "hdsFaxAreaCode":
				return processDataRepository.findAllDistinctHdsFaxAreaCode(controlTableId);
			case "hdsFaxNumber":
				return processDataRepository.findAllDistinctHdsFaxNumber(controlTableId);
			case "pcnServiceDeliveryType":
				return processDataRepository.findAllDistinctHdsServiceDeliveryType(controlTableId);
			case "pcnClinicType":
				return processDataRepository.findAllDistinctPcnClinicType(controlTableId);
			case "pcnPciFlag":
				return processDataRepository.findAllDistinctPcnPciFlag(controlTableId);
			case "hdsHoursOfOperation":
				//return processDataRepository.findAllDistinctHdsHoursOfOperation(controlTableId);
				break;
			case "hdsContactName":
				//return processDataRepository.findAllDistinctHdsContactName(controlTableId);
				break;
			case "hdsIsForProfitFlag":
				//return processDataRepository.findAllDistinctHdsIsForProfitFlag(controlTableId);
				break;
			case "sourceStatus":
				return processDataRepository.findAllDistinctSourceStatus(controlTableId);
			case "hdsParentIpcId":
				//return processDataRepository.findAllDistinctHdsParentIpcId(controlTableId);
				break;
			case "busIpcId":
				//return processDataRepository.findAllDistinctBusIpcId(controlTableId);
				break;
			case "busCpnId":
				//return processDataRepository.findAllDistinctBusCpnId(controlTableId);
				break;
			case "busName":
				//return processDataRepository.findAllDistinctBusName(controlTableId);
				break;
			case "busLegalName":
				//return processDataRepository.findAllDistinctBusLegalName(controlTableId);
				break;
			case "busPayeeNumber":
				//return processDataRepository.findAllDistinctBusPayeeNumber(controlTableId);
				break;
			case "busOwnerName":
				//return processDataRepository.findAllDistinctBusOwnerName(controlTableId);
				break;
			case "busOwnerType":
				//return processDataRepository.findAllDistinctBusOwnerType(controlTableId);
				break;
			case "busOwnerTypeOther":
				//return processDataRepository.findAllDistinctBusOwnerTypeOther(controlTableId);
				break;
			case "facBuildingName":
				return processDataRepository.findAllDistinctFacBuildingName(controlTableId);
			case "facilityHdsDetailsAdditionalInfo":
				//return processDataRepository.findAllDistinctFacilityHdsDetailsAdditionalInfo(controlTableId);
				break;
			case "physicalAddr1":
				return processDataRepository.findAllDistinctPhysicalAddr1(controlTableId);
			case "physicalAddr2":
				return processDataRepository.findAllDistinctPhysicalAddr2(controlTableId);
			case "physicalAddr3":
				return processDataRepository.findAllDistinctPhysicalAddr3(controlTableId);
			case "physicalAddr":
				return processDataRepository.findAllDistinctPhysicalAddr4(controlTableId);
			case "physicalCity":
				return processDataRepository.findAllDistinctPhysicalCity(controlTableId);
			case "physicalProvince":
				return processDataRepository.findAllDistinctPhysicalProvince(controlTableId);
			case "physicalPcode":
				return processDataRepository.findAllDistinctPhysicalPcode(controlTableId);
			case "physicalCountry":
				return processDataRepository.findAllDistinctPhysicalCountry(controlTableId);
			case "physAddrIsPrivate":
				//return processDataRepository.findAllDistinctPhysAddrIsPrivate(controlTableId);
				break;
			case "mailAddr1":
				return processDataRepository.findAllDistinctMailAddr1(controlTableId);
			case "mailAddr2":
				return processDataRepository.findAllDistinctMailAddr2(controlTableId);
			case "mailAddr3":
				return processDataRepository.findAllDistinctMailAddr3(controlTableId);
			case "mailAddr4":
				return processDataRepository.findAllDistinctMailAddr4(controlTableId);
			case "mailCity":
				return processDataRepository.findAllDistinctMailCity(controlTableId);
			case "mailBc":
				return processDataRepository.findAllDistinctMailBc(controlTableId);
			case "mailPcode":
				return processDataRepository.findAllDistinctMailPcode(controlTableId);
			case "mailCountry":
				return processDataRepository.findAllDistinctMailCountry(controlTableId);
			case "mailAddrIsPrivate":
				//return processDataRepository.findAllDistinctMailAddrIsPrivate(controlTableId);
				break;
		}	
			
		return null;
	}
}
