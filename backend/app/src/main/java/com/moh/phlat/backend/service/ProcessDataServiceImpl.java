package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import com.moh.phlat.backend.service.dto.ReportSummary;
import com.moh.phlat.backend.service.RowStatusService;
import com.moh.phlat.backend.model.ParamProcess;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ProcessData> getProcessDataWithMessages(Long controlId) {

		return getProcessDataWithMessages(controlId,null,new ParamProcess());

    }
    
    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlId, String reqRowStatusCode, ParamProcess pProcess) {
    	
    	Specification<ProcessData> combinedSpecification = specificationService.hasDataWithMessages(controlId);

		if(reqRowStatusCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		}
		if(pProcess.getId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", pProcess.getId());
		}
		if(pProcess.getActions() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "actions", pProcess.getActions());
		}
		if(pProcess.getRowStatusCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowStatusCode", pProcess.getRowStatusCode());
		}
		if(pProcess.getMessages() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "messages", pProcess.getMessages());
		}
		if(pProcess.getDoNotLoad() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", pProcess.getDoNotLoad());
		}
		if(pProcess.getStakeholder() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", pProcess.getStakeholder());
		}
		if(pProcess.getHdsLpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", pProcess.getHdsLpcId());
		}
		if(pProcess.getHdsCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", pProcess.getHdsCpnId());
		}
		if(pProcess.getHdsProviderId1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", pProcess.getHdsProviderId1());
		}
		if(pProcess.getHdsProviderId2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", pProcess.getHdsProviderId2());
		}
		if(pProcess.getHdsProviderId3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", pProcess.getHdsProviderId3());
		}
		if(pProcess.getHdsProviderIdType1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", pProcess.getHdsProviderIdType1());
		}
		if(pProcess.getHdsProviderIdType2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", pProcess.getHdsProviderIdType2());
		}
		if(pProcess.getHdsProviderIdType3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", pProcess.getHdsProviderIdType3());
		}
		if(pProcess.getHdsHibcFacId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", pProcess.getHdsHibcFacId());
		}
		if(pProcess.getHdsType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", pProcess.getHdsType());
		}
		if(pProcess.getHdsName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", pProcess.getHdsName());
		}
		if(pProcess.getHdsNameAlias() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", pProcess.getHdsNameAlias());
		}
		if(pProcess.getHdsPrefNameFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", pProcess.getHdsPrefNameFlag());
		}
		if(pProcess.getHdsEmail() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", pProcess.getHdsEmail());
		}
		if(pProcess.getHdsWebsite() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", pProcess.getHdsWebsite());
		}
		if(pProcess.getHdsBusTelAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", pProcess.getHdsBusTelAreaCode());
		}
		if(pProcess.getHdsBusTelNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", pProcess.getHdsBusTelNum());
		}
		if(pProcess.getHdsTelExt() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", pProcess.getHdsTelExt());
		}
		if(pProcess.getHdsCellAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", pProcess.getHdsCellAreaCode());
		}
		if(pProcess.getHdsCellNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", pProcess.getHdsCellNum());
		}
		if(pProcess.getHdsFaxAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", pProcess.getHdsFaxAreaCode());
		}
		if(pProcess.getHdsFaxNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", pProcess.getHdsFaxNum());
		}
		if(pProcess.getHdsServiceDelType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", pProcess.getHdsServiceDelType());
		}
		if(pProcess.getPcnClinicType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", pProcess.getPcnClinicType());
		}
		if(pProcess.getPcnPciFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", pProcess.getPcnPciFlag());
		}
		if(pProcess.getHdsHoursOfOp() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", pProcess.getHdsHoursOfOp());
		}
		if(pProcess.getHdsContactName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", pProcess.getHdsContactName());
		}
		if(pProcess.getHdsIsForProfitFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", pProcess.getHdsIsForProfitFlag());
		}
		if(pProcess.getSourceStatus() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", pProcess.getSourceStatus());
		}
		if(pProcess.getHdsParentIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", pProcess.getHdsParentIpcId());
		}
		if(pProcess.getBusIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", pProcess.getBusIpcId());
		}
		if(pProcess.getBusCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", pProcess.getBusCpnId());
		}
		if(pProcess.getBusName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", pProcess.getBusName());
		}
		if(pProcess.getBusLegalName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", pProcess.getBusLegalName());
		}
		if(pProcess.getBusPayeeNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", pProcess.getBusPayeeNum());
		}
		if(pProcess.getBusOwnerName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", pProcess.getBusOwnerName());
		}
		if(pProcess.getBusOwnerType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", pProcess.getBusOwnerType());
		}
		if(pProcess.getBusOwnerTypeOther() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", pProcess.getBusOwnerTypeOther());
		}
		if(pProcess.getFacBuildingName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", pProcess.getFacBuildingName());
		}
		if(pProcess.getFacHdsDetailAddInfo() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", pProcess.getFacHdsDetailAddInfo());
		}
		if(pProcess.getPhysAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", pProcess.getPhysAddr1());
		}
		if(pProcess.getPhysAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", pProcess.getPhysAddr2());
		}
		if(pProcess.getPhysAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", pProcess.getPhysAddr3());
		}
		if(pProcess.getPhysAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", pProcess.getPhysAddr4());
		}
		if(pProcess.getPhysCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", pProcess.getPhysCity());
		}
		if(pProcess.getPhysProv() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", pProcess.getPhysProv());
		}
		if(pProcess.getPhysPCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", pProcess.getPhysPCode());
		}
		if(pProcess.getPhysCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", pProcess.getPhysCountry());
		}
		if(pProcess.getPhysAddrIsPrivate() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", pProcess.getPhysAddrIsPrivate());
		}
		if(pProcess.getMailAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", pProcess.getMailAddr1());
		}
		if(pProcess.getMailAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", pProcess.getMailAddr2());
		}
		if(pProcess.getMailAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", pProcess.getMailAddr3());
		}
		if(pProcess.getMailAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", pProcess.getMailAddr4());
		}
		if(pProcess.getMailCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", pProcess.getMailCity());
		}
		if(pProcess.getMailBc() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", pProcess.getMailBc());
		}
		if(pProcess.getMailPcode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", pProcess.getMailPcode());
		}
		if(pProcess.getMailCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", pProcess.getMailCountry());
		}
		if(pProcess.getMailAddrIsPriv() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", pProcess.getMailAddrIsPriv());
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
