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

		if(StringUtils.hasText(reqRowStatusCode)) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		}
		if(StringUtils.hasText(pProcess.getId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "id", pProcess.getId());
		}
		if(StringUtils.hasText(pProcess.getActions())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "actions", pProcess.getActions());
		}
		if(StringUtils.hasText(pProcess.getRowStatusCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "rowStatusCode", pProcess.getRowStatusCode());
		}
		if(StringUtils.hasText(pProcess.getDoNotLoad())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "doNotLoad", pProcess.getDoNotLoad());
		}
		if(StringUtils.hasText(pProcess.getStakeholder())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "stakeholder", pProcess.getStakeholder());
		}
		if(StringUtils.hasText(pProcess.getHdsLpcId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsLpcId", pProcess.getHdsLpcId());
		}
		if(StringUtils.hasText(pProcess.getHdsCpnId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsCpnId", pProcess.getHdsCpnId());
		}
		if(StringUtils.hasText(pProcess.getHdsProviderId1())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderId1", pProcess.getHdsProviderId1());
		}
		if(StringUtils.hasText(pProcess.getHdsProviderId2())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderId2", pProcess.getHdsProviderId2());
		}
		if(StringUtils.hasText(pProcess.getHdsProviderId3())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderId3", pProcess.getHdsProviderId3());
		}
		if(StringUtils.hasText(pProcess.getHdsProviderIdType1())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderIdType1", pProcess.getHdsProviderIdType1());
		}
		if(StringUtils.hasText(pProcess.getHdsProviderIdType2())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderIdType2", pProcess.getHdsProviderIdType2());
		}
		if(StringUtils.hasText(pProcess.getHdsProviderIdType3())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsProviderIdType3", pProcess.getHdsProviderIdType3());
		}
		if(StringUtils.hasText(pProcess.getHdsHibcFacId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsHibcFacId", pProcess.getHdsHibcFacId());
		}
		if(StringUtils.hasText(pProcess.getHdsType())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsType", pProcess.getHdsType());
		}
		if(StringUtils.hasText(pProcess.getHdsName())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsName", pProcess.getHdsName());
		}
		if(StringUtils.hasText(pProcess.getHdsNameAlias())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsNameAlias", pProcess.getHdsNameAlias());
		}
		if(StringUtils.hasText(pProcess.getHdsPrefNameFlag())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsPrefNameFlag", pProcess.getHdsPrefNameFlag());
		}
		if(StringUtils.hasText(pProcess.getHdsEmail())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsEmail", pProcess.getHdsEmail());
		}
		if(StringUtils.hasText(pProcess.getHdsWebsite())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsWebsite", pProcess.getHdsWebsite());
		}
		if(StringUtils.hasText(pProcess.getHdsBusTelAreaCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsBusTelAreaCode", pProcess.getHdsBusTelAreaCode());
		}
		if(StringUtils.hasText(pProcess.getHdsBusTelNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsBusTelNum", pProcess.getHdsBusTelNum());
		}
		if(StringUtils.hasText(pProcess.getHdsTelExt())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsTelExt", pProcess.getHdsTelExt());
		}
		if(StringUtils.hasText(pProcess.getHdsCellAreaCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsCellAreaCode", pProcess.getHdsCellAreaCode());
		}
		if(StringUtils.hasText(pProcess.getHdsCellNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsCellNum", pProcess.getHdsCellNum());
		}
		if(StringUtils.hasText(pProcess.getHdsFaxAreaCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsFaxAreaCode", pProcess.getHdsFaxAreaCode());
		}
		if(StringUtils.hasText(pProcess.getHdsFaxNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsFaxNum", pProcess.getHdsFaxNum());
		}
		if(StringUtils.hasText(pProcess.getHdsServiceDelType())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsServiceDelType", pProcess.getHdsServiceDelType());
		}
		if(StringUtils.hasText(pProcess.getPcnClinicType())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "pcnCLinicType", pProcess.getPcnClinicType());
		}
		if(StringUtils.hasText(pProcess.getPcnPciFlag())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "pcnPciFlag", pProcess.getPcnPciFlag());
		}
		if(StringUtils.hasText(pProcess.getHdsHoursOfOp())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsHoursOfOp", pProcess.getHdsHoursOfOp());
		}
		if(StringUtils.hasText(pProcess.getHdsContactName())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsContactName", pProcess.getHdsContactName());
		}
		if(StringUtils.hasText(pProcess.getHdsIsForProfitFlag())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsIsForProfitFlag", pProcess.getHdsIsForProfitFlag());
		}
		if(StringUtils.hasText(pProcess.getSourceStatus())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "sourceStatus", pProcess.getSourceStatus());
		}
		if(StringUtils.hasText(pProcess.getHdsParentIpcId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "hdsParentIpcId", pProcess.getHdsParentIpcId());
		}
		if(StringUtils.hasText(pProcess.getBusIpcId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busIpcId", pProcess.getBusIpcId());
		}
		if(StringUtils.hasText(pProcess.getBusCpnId())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busCpnId", pProcess.getBusCpnId());
		}
		if(StringUtils.hasText(pProcess.getBusName())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busName", pProcess.getBusName());
		}
		if(StringUtils.hasText(pProcess.getBusLegalName())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busLegalName", pProcess.getBusLegalName());
		}
		if(StringUtils.hasText(pProcess.getBusPayeeNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busPayeeNum", pProcess.getBusPayeeNum());
		}
		if(StringUtils.hasText(pProcess.getBusOwnerName())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busOwnerName", pProcess.getBusOwnerName());
		}
		if(StringUtils.hasText(pProcess.getBusOwnerType())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busOwnerType", pProcess.getBusOwnerType());
		}
		if(StringUtils.hasText(pProcess.getBusOwnerTypeOther())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "busOwnerTypeOther", pProcess.getBusOwnerTypeOther());
		}
		if(StringUtils.hasText(pProcess.getFacBuildingName())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "facBuildingName", pProcess.getFacBuildingName());
		}
		if(StringUtils.hasText(pProcess.getFacHdsDetailAddInfo())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "facHdsDetailAddInfo", pProcess.getFacHdsDetailAddInfo());
		}
		if(StringUtils.hasText(pProcess.getPhysAddr1())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr1", pProcess.getPhysAddr1());
		}
		if(StringUtils.hasText(pProcess.getPhysAddr2())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr2", pProcess.getPhysAddr2());
		}
		if(StringUtils.hasText(pProcess.getPhysAddr3())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr3", pProcess.getPhysAddr3());
		}
		if(StringUtils.hasText(pProcess.getPhysAddr4())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddr4", pProcess.getPhysAddr4());
		}
		if(StringUtils.hasText(pProcess.getPhysCity())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physCity", pProcess.getPhysCity());
		}
		if(StringUtils.hasText(pProcess.getPhysProv())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physProv", pProcess.getPhysProv());
		}
		if(StringUtils.hasText(pProcess.getPhysPCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physPCode", pProcess.getPhysPCode());
		}
		if(StringUtils.hasText(pProcess.getPhysCountry())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physCountry", pProcess.getPhysCountry());
		}
		if(StringUtils.hasText(pProcess.getPhysAddrIsPrivate())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "physAddrIsPrivate", pProcess.getPhysAddrIsPrivate());
		}
		if(StringUtils.hasText(pProcess.getMailAddr1())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr1", pProcess.getMailAddr1());
		}
		if(StringUtils.hasText(pProcess.getMailAddr2())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr2", pProcess.getMailAddr2());
		}
		if(StringUtils.hasText(pProcess.getMailAddr3())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr3", pProcess.getMailAddr3());
		}
		if(StringUtils.hasText(pProcess.getMailAddr4())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailAddr4", pProcess.getMailAddr4());
		}
		if(StringUtils.hasText(pProcess.getMailCity())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailCity", pProcess.getMailCity());
		}
		if(StringUtils.hasText(pProcess.getMailBc())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailBc", pProcess.getMailBc());
		}
		if(StringUtils.hasText(pProcess.getMailPcode())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailPcode", pProcess.getMailPcode());
		}
		if(StringUtils.hasText(pProcess.getMailCountry())) {
			combinedSpecification = specificationService.buildSpecificationAndForProcessData(combinedSpecification, "mailCountry", pProcess.getMailCountry());
		}
		if(StringUtils.hasText(pProcess.getMailAddrIsPriv())) {
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
