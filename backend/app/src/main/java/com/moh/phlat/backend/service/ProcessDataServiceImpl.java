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
import com.moh.phlat.backend.repository.ProcessDataFilterSpecification;
import com.moh.phlat.backend.repository.ProcessDataFilterSpecificationImpl;
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
	
	private ProcessDataFilterSpecification specificationService = new ProcessDataFilterSpecificationImpl();
    
    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlId, String reqRowStatusCode, ProcessDataFilterParams pProcess) {
    	
    	Specification<ProcessData> combinedSpecification = specificationService.getDataWithMessages(controlId);

		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", pProcess.getId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "actions", pProcess.getActions());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowStatusCode", pProcess.getRowStatusCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", pProcess.getDoNotLoad());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", pProcess.getStakeholder());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", pProcess.getHdsLpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", pProcess.getHdsCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", pProcess.getHdsProviderId1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", pProcess.getHdsProviderId2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", pProcess.getHdsProviderId3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", pProcess.getHdsProviderIdType1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", pProcess.getHdsProviderIdType2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", pProcess.getHdsProviderIdType3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", pProcess.getHdsHibcFacId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", pProcess.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", pProcess.getHdsName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", pProcess.getHdsNameAlias());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", pProcess.getHdsPrefNameFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", pProcess.getHdsEmail());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", pProcess.getHdsWebsite());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", pProcess.getHdsBusTelAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", pProcess.getHdsBusTelNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", pProcess.getHdsTelExt());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", pProcess.getHdsCellAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", pProcess.getHdsCellNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", pProcess.getHdsFaxAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", pProcess.getHdsFaxNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", pProcess.getHdsServiceDelType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", pProcess.getPcnClinicType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", pProcess.getPcnPciFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", pProcess.getHdsHoursOfOp());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", pProcess.getHdsContactName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", pProcess.getHdsIsForProfitFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", pProcess.getSourceStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", pProcess.getHdsParentIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", pProcess.getBusIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", pProcess.getBusCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", pProcess.getBusName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", pProcess.getBusLegalName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", pProcess.getBusPayeeNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", pProcess.getBusOwnerName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", pProcess.getBusOwnerType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", pProcess.getBusOwnerTypeOther());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", pProcess.getFacBuildingName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", pProcess.getFacHdsDetailAddInfo());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", pProcess.getPhysAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", pProcess.getPhysAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", pProcess.getPhysAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", pProcess.getPhysAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", pProcess.getPhysCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", pProcess.getPhysProv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", pProcess.getPhysPCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", pProcess.getPhysCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", pProcess.getPhysAddrIsPrivate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", pProcess.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", pProcess.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", pProcess.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", pProcess.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", pProcess.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", pProcess.getMailBc());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", pProcess.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", pProcess.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", pProcess.getMailAddrIsPriv());
		
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
