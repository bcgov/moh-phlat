package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.Message;
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
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.moh.phlat.backend.databc.util.Constants.COLON;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;
    
	@Autowired
	EntityManager entityManager;

    @Override
    public Page<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus, int page, int itemsPerPage, ProcessDataFilterParams filterProcess, 
			List<Order> sortOrders) {

        Pageable pageRequest;

        if (!sortOrders.isEmpty()) {
            pageRequest = PageRequest.of(page - 1, itemsPerPage, Sort.by(sortOrders));
        } else {
            pageRequest = PageRequest.of(page - 1, itemsPerPage);
        }

        Page<ProcessData> processData;
        ProcessDataFilterSpecification specificationService = new ProcessDataFilterSpecificationImpl();
        Specification<ProcessData> combinedSpecification = specificationService.getDataWithMessages(controlTableId);
        combinedSpecification = buildSpecification(combinedSpecification, rowStatus, filterProcess, specificationService);

        if (CollectionUtils.isEmpty(filterProcess.getMessages())) {
            processData = processDataRepository.findAll(combinedSpecification, pageRequest);
        } else {
            List<ProcessData> processDataMsg = new ArrayList<>();
            for (String messageCriteria : filterProcess.getMessages()) {
                // Create new specification for each message criteria (+ along with other filters)
                // and add them all to create a page.
                Specification<ProcessData> combinedSpecificationMsg = specificationService
                        .getProcessDataWithFilterMessages(combinedSpecification, messageCriteria);

                if (combinedSpecificationMsg != null) {
                    // Find all records with combined filters for Process_data and Messages table
                    processDataMsg.addAll(processDataRepository.findAll(combinedSpecificationMsg));
                }
            }
            // Convert all records to Page (empty Page when no matching result).
            processData = getProcessDataPage(pageRequest, processDataMsg, sortOrders);
        }

        return processData;
    }

    /**
     * Converts List ProcessData to Page and applies sorting.
     *
     * @param pageRequest - pageRequest containing required criteria
     * @param processDataMsg - List of ProcessData
     * @param sortOrders - List of required sort Order
     * @return - Modified Page<ProcessData>
     */
    private Page<ProcessData> getProcessDataPage(Pageable pageRequest, List<ProcessData> processDataMsg, List<Order> sortOrders) {
        Page<ProcessData> processData;
        Comparator<ProcessData> comparator = Comparator.comparing(ProcessData::getId); // Default Sorting - no sortOrders
        for (Order order : sortOrders) {
            if (sortOrders.indexOf(order) == 0) {
                // First sort criteria for .comparing()
                comparator = StringUtils.hasText(order.getProperty()) && order.getProperty().equals("hdsName")
                        ? Comparator.comparing(ProcessData::getHdsName) : comparator;
                comparator = updateComparatorDirection(order.isAscending(), comparator);
            }
            if (sortOrders.size() > 1 && sortOrders.indexOf(order) != 0) {
                // Chained sort criteria for .thenComparing()
                comparator = StringUtils.hasText(order.getProperty()) && order.getProperty().equals("facCivicAddr")
                        ? comparator.thenComparing(ProcessData::getFacCivicAddr) : comparator;
                comparator = updateComparatorDirection(order.isAscending(), comparator);
            }
        }
        processDataMsg = processDataMsg.stream().distinct().sorted(comparator).toList();
        final int startOfPage = (int) pageRequest.getOffset();
        final int endOfPage = Math.min((startOfPage + pageRequest.getPageSize()), processDataMsg.size());
        // Convert list to page
        processData = new PageImpl<>(endOfPage >= startOfPage ? processDataMsg.subList(startOfPage, endOfPage)
                : processDataMsg.subList(0, Math.min(pageRequest.getPageSize(), processDataMsg.size())), pageRequest, processDataMsg.size());
        return processData;
    }

    /**
     * Updates Comparator Direction Asc-Dsc
     *
     * @param isDirectionAsc - boolean is Direction Asc
     * @param comparator - Comparator
     * @return - Modified Comparator<ProcessData>
     */
    private Comparator<ProcessData> updateComparatorDirection(boolean isDirectionAsc, Comparator<ProcessData> comparator) {
        if (!isDirectionAsc) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    private Specification<ProcessData> buildSpecification( Specification<ProcessData> combinedSpecification, String reqRowStatusCode, ProcessDataFilterParams filterProcess,  ProcessDataFilterSpecification specificationService) {
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode);
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", filterProcess.getId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", filterProcess.getRowstatusCode());
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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", filterProcess.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubType", filterProcess.getHdsSubType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", filterProcess.getHdsName());
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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", filterProcess.getSourceStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicStatus", filterProcess.getPcnClinicStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEffectiveStartDate", filterProcess.getHdsEffectiveStartDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", filterProcess.getFacBuildingName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr1", filterProcess.getPhysicalAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr2", filterProcess.getPhysicalAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr3", filterProcess.getPhysicalAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr4", filterProcess.getPhysicalAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalCity", filterProcess.getPhysicalCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalProvince", filterProcess.getPhysicalProvince());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalPcode", filterProcess.getPhysicalPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalCountry", filterProcess.getPhysicalCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrPrpsTypeCd", filterProcess.getPhysicalAddrPrpsTypeCd());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrValidationStatus", filterProcess.getPhysicalAddrValidationStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrMailabilityScore", filterProcess.getPhysicalAddrMailabilityScore());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", filterProcess.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", filterProcess.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", filterProcess.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", filterProcess.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", filterProcess.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailProvince", filterProcess.getMailProvince());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", filterProcess.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", filterProcess.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrPrpsTypeCd", filterProcess.getMailAddrPrpsTypeCd());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrValidationStatus", filterProcess.getMailAddrValidationStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrMailabilityScore", filterProcess.getMailAddrMailabilityScore());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "plrFacilityId", filterProcess.getPlrFacilityId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facRelnType", filterProcess.getFacRelnType());
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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facLhaName", filterProcess.getFacLhaName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHaName", filterProcess.getFacHaName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facPcnCode", filterProcess.getFacPcnCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facPcnName", filterProcess.getFacPcnName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facIfcId", filterProcess.getFacIfcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrMailabilityScore", filterProcess.getPhysicalAddrMailabilityScore());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrMailabilityScore", filterProcess.getMailAddrMailabilityScore());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "primaryCareGroupAction", filterProcess.getPrimaryCareGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "primaryCareGroupEffectiveStartDate", filterProcess.getPrimaryCareGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "primaryCareGroupEffectiveEndDate", filterProcess.getPrimaryCareGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubTypeGroupAction", filterProcess.getHdsSubTypeGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubTypeGroupEffectiveStartDate", filterProcess.getHdsSubTypeGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubTypeGroupEffectiveEndDate", filterProcess.getHdsSubTypeGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupAction", filterProcess.getHdsNameGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupEffectiveStartDate", filterProcess.getHdsNameGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupEffectiveEndDate", filterProcess.getHdsNameGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupEffectiveEndDate", filterProcess.getHdsNameGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "statusGroupAction", filterProcess.getStatusGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "statusGroupEffectiveStartDate", filterProcess.getStatusGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "statusGroupEffectiveEndDate", filterProcess.getStatusGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupAction", filterProcess.getHdsEmailGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupEffectiveStartDate", filterProcess.getHdsEmailGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupEffectiveEndDate", filterProcess.getHdsEmailGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupEffectiveEndDate", filterProcess.getHdsEmailGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsiteGroupAction", filterProcess.getHdsWebsiteGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsiteGroupEffectiveStartDate", filterProcess.getHdsWebsiteGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsiteGroupEffectiveEndDate", filterProcess.getHdsWebsiteGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "businessPhoneGroupAction", filterProcess.getBusinessPhoneGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "businessPhoneGroupEffectiveStartDate", filterProcess.getBusinessPhoneGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "businessPhoneGroupEffectiveEndDate", filterProcess.getBusinessPhoneGroupEffectiveEndDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxGroupAction", filterProcess.getHdsFaxGroupAction());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxGroupEffectiveStartDate", filterProcess.getHdsFaxGroupEffectiveStartDate());
	    combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxGroupEffectiveEndDate", filterProcess.getHdsFaxGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupAction", filterProcess.getHdsCellGroupAction());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupEffectiveStartDate", filterProcess.getHdsCellGroupEffectiveStartDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupEffectiveEndDate", filterProcess.getHdsCellGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupEffectiveEndDate", filterProcess.getHdsCellGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddressGroupAction", filterProcess.getPhysicalAddressGroupAction());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddressGroupEffectiveStartDate", filterProcess.getPhysicalAddressGroupEffectiveStartDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddressGroupEffectiveEndDate", filterProcess.getPhysicalAddressGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailingAddressGroupAction", filterProcess.getMailingAddressGroupAction());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailingAddressGroupEffectiveStartDate", filterProcess.getMailingAddressGroupEffectiveStartDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailingAddressGroupEffectiveEndDate", filterProcess.getMailingAddressGroupEffectiveEndDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "recordAction", filterProcess.getRecordAction());

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

		reportAttributeName = "TOTAL POTENTIAL_FAC_DUPLICATE ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.POTENTIAL_FAC_DUPLICATE);
     	ReportSummary rs8 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs8);

		reportAttributeName = "TOTAL POTENTIAL_HDS_DUPLICATE ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.POTENTIAL_HDS_DUPLICATE);
     	ReportSummary rs9 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs9);
		
		reportAttributeName = "TOTAL LOAD_ERROR ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.LOAD_ERROR);
     	ReportSummary rs10 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs10);			
		
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
        List<String> result;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        if (columnKey.equals("messages")) {
            // Fetch messages from DB and convert them to String criteria
            CriteriaQuery<Message> query = cb.createQuery(Message.class);
            Root<ProcessData> root = query.from(ProcessData.class);

            query.select(root.get(columnKey)).distinct(true);
            query.where(cb.equal(root.get("controlTableId"), controlTableId));
            query.orderBy(cb.asc(root.get(columnKey)));
            List<Message> message = entityManager.createQuery(query).getResultList();
            result = !CollectionUtils.isEmpty(message)
                    ? message.stream()
                    .filter(msg -> StringUtils.hasText(msg.getMessageType())
                            && StringUtils.hasText(msg.getMessageCode()) && StringUtils.hasText(msg.getMessageDesc()))
                    .sorted(Comparator.comparing(Message::getMessageType))
                    .map(msg -> msg.getMessageType() + COLON +
                            msg.getMessageCode() + COLON + msg.getMessageDesc())
                    .distinct()
                    .collect(Collectors.toList())
                    : new ArrayList<>();
        } else {
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<ProcessData> root = query.from(ProcessData.class);

            query.select(root.get(columnKey)).distinct(true);
            query.where(cb.equal(root.get("controlTableId"), controlTableId));
            query.orderBy(cb.asc(root.get(columnKey)));
            result = entityManager.createQuery(query).getResultList();
        }
        return result;
		
	}
}
