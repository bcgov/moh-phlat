package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import com.moh.phlat.backend.service.dto.ReportSummary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import com.moh.phlat.backend.service.RowStatusService;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.model.SourceData;
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
	EntityManager entityManager;
	
	private SpecificationService specificationService = new SpecificationServiceImpl();
    
    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlId, String reqRowStatusCode, ProcessDataFilterParams pProcess) {
    	
    	Specification<ProcessData> combinedSpecification = specificationService.getDataWithMessagesForProcessData(controlId);

		if(reqRowStatusCode != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		}
		if(pProcess.getId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "id", pProcess.getId());
		}
		if(pProcess.getActions() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "actions", pProcess.getActions());
		}
		if(pProcess.getRowStatusCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "rowStatusCode", pProcess.getRowStatusCode());
		}
		if(pProcess.getDoNotLoad() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "doNotLoad", pProcess.getDoNotLoad());
		}
		if(pProcess.getStakeholder() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "stakeholder", pProcess.getStakeholder());
		}
		if(pProcess.getHdsLpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsLpcId", pProcess.getHdsLpcId());
		}
		if(pProcess.getHdsCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsCpnId", pProcess.getHdsCpnId());
		}
		if(pProcess.getHdsProviderId1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderId1", pProcess.getHdsProviderId1());
		}
		if(pProcess.getHdsProviderId2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderId2", pProcess.getHdsProviderId2());
		}
		if(pProcess.getHdsProviderId3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderId3", pProcess.getHdsProviderId3());
		}
		if(pProcess.getHdsProviderIdType1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderIdType1", pProcess.getHdsProviderIdType1());
		}
		if(pProcess.getHdsProviderIdType2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderIdType2", pProcess.getHdsProviderIdType2());
		}
		if(pProcess.getHdsProviderIdType3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderIdType3", pProcess.getHdsProviderIdType3());
		}
		if(pProcess.getHdsHibcFacId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsHibcFacId", pProcess.getHdsHibcFacId());
		}
		if(pProcess.getHdsType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsType", pProcess.getHdsType());
		}
		if(pProcess.getHdsName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsName", pProcess.getHdsName());
		}
		if(pProcess.getHdsNameAlias() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsNameAlias", pProcess.getHdsNameAlias());
		}
		if(pProcess.getHdsPrefNameFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsPrefNameFlag", pProcess.getHdsPrefNameFlag());
		}
		if(pProcess.getHdsEmail() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsEmail", pProcess.getHdsEmail());
		}
		if(pProcess.getHdsWebsite() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsWebsite", pProcess.getHdsWebsite());
		}
		if(pProcess.getHdsBusTelAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsBusTelAreaCode", pProcess.getHdsBusTelAreaCode());
		}
		if(pProcess.getHdsBusTelNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsBusTelNum", pProcess.getHdsBusTelNum());
		}
		if(pProcess.getHdsTelExt() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsTelExt", pProcess.getHdsTelExt());
		}
		if(pProcess.getHdsCellAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsCellAreaCode", pProcess.getHdsCellAreaCode());
		}
		if(pProcess.getHdsCellNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsCellNum", pProcess.getHdsCellNum());
		}
		if(pProcess.getHdsFaxAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsFaxAreaCode", pProcess.getHdsFaxAreaCode());
		}
		if(pProcess.getHdsFaxNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsFaxNum", pProcess.getHdsFaxNum());
		}
		if(pProcess.getHdsServiceDelType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsServiceDelType", pProcess.getHdsServiceDelType());
		}
		if(pProcess.getPcnClinicType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "pcnCLinicType", pProcess.getPcnClinicType());
		}
		if(pProcess.getPcnPciFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "pcnPciFlag", pProcess.getPcnPciFlag());
		}
		if(pProcess.getHdsHoursOfOp() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsHoursOfOp", pProcess.getHdsHoursOfOp());
		}
		if(pProcess.getHdsContactName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsContactName", pProcess.getHdsContactName());
		}
		if(pProcess.getHdsIsForProfitFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsIsForProfitFlag", pProcess.getHdsIsForProfitFlag());
		}
		if(pProcess.getSourceStatus() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "sourceStatus", pProcess.getSourceStatus());
		}
		if(pProcess.getHdsParentIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsParentIpcId", pProcess.getHdsParentIpcId());
		}
		if(pProcess.getBusIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busIpcId", pProcess.getBusIpcId());
		}
		if(pProcess.getBusCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busCpnId", pProcess.getBusCpnId());
		}
		if(pProcess.getBusName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busName", pProcess.getBusName());
		}
		if(pProcess.getBusLegalName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busLegalName", pProcess.getBusLegalName());
		}
		if(pProcess.getBusPayeeNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busPayeeNum", pProcess.getBusPayeeNum());
		}
		if(pProcess.getBusOwnerName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busOwnerName", pProcess.getBusOwnerName());
		}
		if(pProcess.getBusOwnerType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busOwnerType", pProcess.getBusOwnerType());
		}
		if(pProcess.getBusOwnerTypeOther() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busOwnerTypeOther", pProcess.getBusOwnerTypeOther());
		}
		if(pProcess.getFacBuildingName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "facBuildingName", pProcess.getFacBuildingName());
		}
		if(pProcess.getFacHdsDetailAddInfo() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "facHdsDetailAddInfo", pProcess.getFacHdsDetailAddInfo());
		}
		if(pProcess.getPhysAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr1", pProcess.getPhysAddr1());
		}
		if(pProcess.getPhysAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr2", pProcess.getPhysAddr2());
		}
		if(pProcess.getPhysAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr3", pProcess.getPhysAddr3());
		}
		if(pProcess.getPhysAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr4", pProcess.getPhysAddr4());
		}
		if(pProcess.getPhysCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physCity", pProcess.getPhysCity());
		}
		if(pProcess.getPhysProv() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physProv", pProcess.getPhysProv());
		}
		if(pProcess.getPhysPCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physPCode", pProcess.getPhysPCode());
		}
		if(pProcess.getPhysCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physCountry", pProcess.getPhysCountry());
		}
		if(pProcess.getPhysAddrIsPrivate() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddrIsPrivate", pProcess.getPhysAddrIsPrivate());
		}
		if(pProcess.getMailAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr1", pProcess.getMailAddr1());
		}
		if(pProcess.getMailAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr2", pProcess.getMailAddr2());
		}
		if(pProcess.getMailAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr3", pProcess.getMailAddr3());
		}
		if(pProcess.getMailAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr4", pProcess.getMailAddr4());
		}
		if(pProcess.getMailCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailCity", pProcess.getMailCity());
		}
		if(pProcess.getMailBc() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailBc", pProcess.getMailBc());
		}
		if(pProcess.getMailPcode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailPcode", pProcess.getMailPcode());
		}
		if(pProcess.getMailCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailCountry", pProcess.getMailCountry());
		}
		if(pProcess.getMailAddrIsPriv() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddrIsPriv", pProcess.getMailAddrIsPriv());
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
	public List<String> getUniqueColumnValues(Long controlTableId, String columnKey) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);
        
        Root<ProcessData> root = query.from(ProcessData.class);
	        
	    query.select(root.get(columnKey)).distinct(true);
	    query.where(cb.equal(root.get("controlTableId"), controlTableId));
	    query.orderBy(cb.asc(root.get(columnKey)));
        
        return entityManager.createQuery(query).getResultList();
		
	}
}
