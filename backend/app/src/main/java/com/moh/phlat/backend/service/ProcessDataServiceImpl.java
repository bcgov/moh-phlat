package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import com.moh.phlat.backend.service.dto.ReportSummary;
import com.moh.phlat.backend.service.RowStatusService;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;
    
	@Autowired
	private SpecificationService specificationService;

    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus, Pageable pageable) {

        /*if (StringUtils.hasText(rowStatus)) {
            return processDataRepository.getProcessDataWithMessages(controlTableId, rowStatus, pageable);
        } else {
            return processDataRepository.getProcessDataWithMessages(controlTableId, pageable);
        }*/

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
    	
    	Specification<ProcessData> combinedSpecification = specificationService.hasDataWithMessages(controlId);

		if(reqRowStatusCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		}
		if(ids != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", ids);
		}
		if(actions != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "actions", actions);
		}
		if(rowStatusCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowStatusCode", rowStatusCode);
		}
		if(messages != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "messages", messages);
		}
		if(doNotLoad != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", doNotLoad);
		}
		if(stakeholder != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", stakeholder);
		}
		if(hdsLpcId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", hdsLpcId);
		}
		if(hdsCpnId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", hdsCpnId);
		}
		if(hdsProviderId1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", hdsProviderId1);
		}
		if(hdsProviderId2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", hdsProviderId2);
		}
		if(hdsProviderId3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", hdsProviderId3);
		}
		if(hdsProviderIdType1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", hdsProviderIdType1);
		}
		if(hdsProviderIdType2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", hdsProviderIdType2);
		}
		if(hdsProviderIdType3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", hdsProviderIdType3);
		}
		if(hdsHibcFacId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", hdsHibcFacId);
		}
		if(hdsType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", hdsType);
		}
		if(hdsName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", hdsName);
		}
		if(hdsNameAlias != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", hdsNameAlias);
		}
		if(hdsPrefNameFlag != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", hdsPrefNameFlag);
		}
		if(hdsEmail != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", hdsEmail);
		}
		if(hdsWebsite != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", hdsWebsite);
		}
		if(hdsBusTelAreaCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", hdsBusTelAreaCode);
		}
		if(hdsBusTelNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", hdsBusTelNum);
		}
		if(hdsTelExt != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", hdsTelExt);
		}
		if(hdsCellAreaCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", hdsCellAreaCode);
		}
		if(hdsCellNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", hdsCellNum);
		}
		if(hdsFaxAreaCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", hdsFaxAreaCode);
		}
		if(hdsFaxNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", hdsFaxNum);
		}
		if(hdsServiceDelType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", hdsServiceDelType);
		}
		if(pcnCLinicType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", pcnCLinicType);
		}
		if(pcnPciFlag != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", pcnPciFlag);
		}
		if(hdsHoursOfOp != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", hdsHoursOfOp);
		}
		if(hdsContactName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", hdsContactName);
		}
		if(hdsIsForProfitFlag != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", hdsIsForProfitFlag);
		}
		if(sourceStatus != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", sourceStatus);
		}
		if(hdsParentIpcId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", hdsParentIpcId);
		}
		if(busIpcId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", busIpcId);
		}
		if(busCpnId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", busCpnId);
		}
		if(busName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", busName);
		}
		if(busLegalName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", busLegalName);
		}
		if(busPayeeNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", busPayeeNum);
		}
		if(busOwnerName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", busOwnerName);
		}
		if(busOwnerType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", busOwnerType);
		}
		if(busOwnerTypeOther != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", busOwnerTypeOther);
		}
		if(facBuildingName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", facBuildingName);
		}
		if(facHdsDetailAddInfo != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", facHdsDetailAddInfo);
		}
		if(physAddr1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", physAddr1);
		}
		if(physAddr2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", physAddr2);
		}
		if(physAddr3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", physAddr3);
		}
		if(physAddr4 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", physAddr4);
		}
		if(physCity != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", physCity);
		}
		if(physProv != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", physProv);
		}
		if(physPCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", physPCode);
		}
		if(physCountry != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", physCountry);
		}
		if(physAddrIsPrivate != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", physAddrIsPrivate);
		}
		if(mailAddr1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", mailAddr1);
		}
		if(mailAddr2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", mailAddr2);
		}
		if(mailAddr3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", mailAddr3);
		}
		if(mailAddr4 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", mailAddr4);
		}
		if(mailCity != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", mailCity);
		}
		if(mailBc != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", mailBc);
		}
		if(mailPcode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", mailPcode);
		}
		if(mailCountry != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", mailCountry);
		}
		if(mailAddrIsPriv != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", mailAddrIsPriv);
		}
		
		return processDataRepository.findAll(combinedSpecification);
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
		// Can't use specification to get a list of strings. 
		// https://stackoverflow.com/questions/68710246/distinct-on-specific-column-using-jpa-specification
		
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
