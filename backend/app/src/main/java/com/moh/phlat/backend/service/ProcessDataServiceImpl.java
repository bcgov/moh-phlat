package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.repository.ProcessDataFilterSpecification;
import com.moh.phlat.backend.repository.ProcessDataFilterSpecificationImpl;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.service.dto.ReportSummary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;
    
	@Autowired
	EntityManager entityManager;

    @Override
    public Page<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus, int page, int itemsPerPage, ProcessDataFilterParams filterProcess, 
			List<Order> sortOrders){
    	
    	Pageable pageRequest;
    	
		if (!sortOrders.isEmpty()) {
			pageRequest = PageRequest.of(page - 1, itemsPerPage, Sort.by(sortOrders));
		} else {
			pageRequest = PageRequest.of(page - 1, itemsPerPage);
		}
		
		return processDataRepository.findAll(buildSpecification(controlTableId, rowStatus, filterProcess), pageRequest);
    }
    
    private Specification<ProcessData> buildSpecification(Long controlId, String reqRowStatusCode, ProcessDataFilterParams filterProcess) {
		ProcessDataFilterSpecification specificationService = new ProcessDataFilterSpecificationImpl();

		Specification<ProcessData> combinedSpecification = specificationService.getDataWithMessages(controlId);

		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", filterProcess.getId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "actions", filterProcess.getActions());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", filterProcess.getRowstatusCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", filterProcess.getDoNotLoad());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", filterProcess.getStakeholder());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIpcId", filterProcess.getHdsIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", filterProcess.getHdsCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier1", filterProcess.getHdsProviderIdentifier1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier2", filterProcess.getHdsProviderIdentifier2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier3", filterProcess.getHdsProviderIdentifier3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType1", filterProcess.getHdsProviderIdentifierType1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType2", filterProcess.getHdsProviderIdentifierType2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType3", filterProcess.getHdsProviderIdentifierType3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsMspFacilityNumber", filterProcess.getHdsMspFacilityNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", filterProcess.getHdsHibcFacId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", filterProcess.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubType", filterProcess.getHdsSubType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", filterProcess.getHdsName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", filterProcess.getHdsNameAlias());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPreferredNameFlag", filterProcess.getHdsPreferredNameFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", filterProcess.getHdsEmail());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", filterProcess.getHdsWebsite());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", filterProcess.getHdsBusTelAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNumber", filterProcess.getHdsBusTelNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExtension", filterProcess.getHdsTelExtension());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", filterProcess.getHdsCellAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNumber", filterProcess.getHdsCellNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", filterProcess.getHdsFaxAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNumber", filterProcess.getHdsFaxNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnServiceDeliveryType", filterProcess.getPcnServiceDeliveryType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicType", filterProcess.getPcnClinicType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", filterProcess.getPcnPciFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", filterProcess.getHdsHoursOfOp());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", filterProcess.getHdsContactName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", filterProcess.getHdsIsForProfitFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", filterProcess.getSourceStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicStatus", filterProcess.getPcnClinicStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEffectiveStartDate", filterProcess.getHdsEffectiveStartDate());
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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr1", filterProcess.getPhysicalAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr2", filterProcess.getPhysicalAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr3", filterProcess.getPhysicalAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr4", filterProcess.getPhysicalAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalCity", filterProcess.getPhysicalCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalProvince", filterProcess.getPhysicalProvince());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalPcode", filterProcess.getPhysicalPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalCountry", filterProcess.getPhysicalCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", filterProcess.getPhysAddrIsPrivate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrPrpsTypeCd", filterProcess.getPhysicalAddrPrpsTypeCd());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrValidationStatus", filterProcess.getPhysicalAddrValidationStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", filterProcess.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", filterProcess.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", filterProcess.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", filterProcess.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", filterProcess.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", filterProcess.getMailBc());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", filterProcess.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", filterProcess.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", filterProcess.getMailAddrIsPriv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrPrpsTypeCd", filterProcess.getMailAddrPrpsTypeCd());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrValidationStatus", filterProcess.getMailAddrValidationStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "plrFacilityId", filterProcess.getPlrFacilityId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facAddressUnit", filterProcess.getFacAddressUnit());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facCivicAddr", filterProcess.getFacCivicAddr());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facLatitude", filterProcess.getFacLatitude());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facLongitude", filterProcess.getFacLongitude());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facSiteId", filterProcess.getFacSiteId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facScore", filterProcess.getFacScore());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facMatchPrecision", filterProcess.getFacMatchPrecision());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHsdaName", filterProcess.getFacHsdaName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facChsaStatus", filterProcess.getFacChsaStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facPcnStatus", filterProcess.getFacPcnStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facChsaCode", filterProcess.getFacChsaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facChsaName", filterProcess.getFacChsaName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facLhaName", filterProcess.getFacPrecisionPoints());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHaName", filterProcess.getFacPrecisionPoints());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facPcnCode", filterProcess.getFacPrecisionPoints());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facPcnName", filterProcess.getFacPrecisionPoints());
		
		return combinedSpecification;
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

		List<ReportSummary> items = new ArrayList<>();

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
				reportAttributeName = msg[0] + " " +  msg[1] + " " + msg[2];
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
