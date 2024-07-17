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
import org.springframework.data.domain.Pageable;
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
    public List<ProcessData> getProcessDataWithMessages(Long controlId, String reqRowStatusCode, ProcessDataFilterParams filterProcess, Pageable pageable) {
    	
    	Specification<ProcessData> combinedSpecification = specificationService.getDataWithMessages(controlId);

		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", filterProcess.getId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "actions", filterProcess.getActions());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowStatusCode", filterProcess.getRowStatusCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", filterProcess.getDoNotLoad());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", filterProcess.getStakeholder());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", filterProcess.getHdsLpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", filterProcess.getHdsCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", filterProcess.getHdsProviderId1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", filterProcess.getHdsProviderId2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", filterProcess.getHdsProviderId3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", filterProcess.getHdsProviderIdType1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", filterProcess.getHdsProviderIdType2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", filterProcess.getHdsProviderIdType3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", filterProcess.getHdsHibcFacId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", filterProcess.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", filterProcess.getHdsName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", filterProcess.getHdsNameAlias());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", filterProcess.getHdsPrefNameFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", filterProcess.getHdsEmail());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", filterProcess.getHdsWebsite());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", filterProcess.getHdsBusTelAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", filterProcess.getHdsBusTelNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", filterProcess.getHdsTelExt());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", filterProcess.getHdsCellAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", filterProcess.getHdsCellNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", filterProcess.getHdsFaxAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", filterProcess.getHdsFaxNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", filterProcess.getHdsServiceDelType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", filterProcess.getPcnClinicType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", filterProcess.getPcnPciFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", filterProcess.getHdsHoursOfOp());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", filterProcess.getHdsContactName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", filterProcess.getHdsIsForProfitFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", filterProcess.getSourceStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", filterProcess.getHdsParentIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", filterProcess.getBusIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", filterProcess.getBusCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", filterProcess.getBusName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", filterProcess.getBusLegalName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", filterProcess.getBusPayeeNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", filterProcess.getBusOwnerName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", filterProcess.getBusOwnerType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", filterProcess.getBusOwnerTypeOther());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", filterProcess.getFacBuildingName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", filterProcess.getFacHdsDetailAddInfo());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", filterProcess.getPhysAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", filterProcess.getPhysAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", filterProcess.getPhysAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", filterProcess.getPhysAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", filterProcess.getPhysCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", filterProcess.getPhysProv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", filterProcess.getPhysPCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", filterProcess.getPhysCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", filterProcess.getPhysAddrIsPrivate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", filterProcess.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", filterProcess.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", filterProcess.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", filterProcess.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", filterProcess.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", filterProcess.getMailBc());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", filterProcess.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", filterProcess.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", filterProcess.getMailAddrIsPriv());
		
		return processDataRepository.findAll(combinedSpecification, pageable);
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
