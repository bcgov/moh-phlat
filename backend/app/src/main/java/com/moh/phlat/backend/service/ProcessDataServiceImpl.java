package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.repository.ProcessDataFilterSpecification;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.service.dto.ReportSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {

    @Autowired
    ProcessDataRepository processDataRepository;

    @Autowired
    ProcessDataFilterSpecification specificationService;

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
        Specification<ProcessData> combinedSpecification = specificationService.getDataWithMessages(controlTableId);
        combinedSpecification = buildSpecification(combinedSpecification, rowStatus, filterProcess);

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

    private Specification<ProcessData> buildSpecification( Specification<ProcessData> combinedSpecification, String reqRowStatusCode, ProcessDataFilterParams filterProcess) {
        combinedSpecification = StringUtils.hasText(reqRowStatusCode) ? specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", reqRowStatusCode) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getId()) ? specificationService.buildSpecificationAnd(combinedSpecification, "id", filterProcess.getId()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getRowstatusCode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "rowstatusCode", filterProcess.getRowstatusCode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getStakeholder()) ? specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", filterProcess.getStakeholder()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsIpcId()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsIpcId", filterProcess.getHdsIpcId()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsCpnId()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", filterProcess.getHdsCpnId()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsProviderIdentifier1()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier1", filterProcess.getHdsProviderIdentifier1()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsProviderIdentifier2()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier2", filterProcess.getHdsProviderIdentifier2()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsProviderIdentifier3()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier3", filterProcess.getHdsProviderIdentifier3()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsProviderIdentifierType1()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType1", filterProcess.getHdsProviderIdentifierType1()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsProviderIdentifierType2()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType2", filterProcess.getHdsProviderIdentifierType2()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsProviderIdentifierType3()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType3", filterProcess.getHdsProviderIdentifierType3()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsMspFacilityNumber()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsMspFacilityNumber", filterProcess.getHdsMspFacilityNumber()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsType()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", filterProcess.getHdsType()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsSubType()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubType", filterProcess.getHdsSubType()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", filterProcess.getHdsName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsPreferredNameFlag()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsPreferredNameFlag", filterProcess.getHdsPreferredNameFlag()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsEmail()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", filterProcess.getHdsEmail()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsWebsite()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", filterProcess.getHdsWebsite()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsBusTelAreaCode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", filterProcess.getHdsBusTelAreaCode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsBusTelNumber()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNumber", filterProcess.getHdsBusTelNumber()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsTelExtension()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExtension", filterProcess.getHdsTelExtension()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsCellAreaCode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", filterProcess.getHdsCellAreaCode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsCellNumber()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNumber", filterProcess.getHdsCellNumber()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsFaxAreaCode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", filterProcess.getHdsFaxAreaCode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsFaxNumber()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNumber", filterProcess.getHdsFaxNumber()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPcnServiceDeliveryType()) ? specificationService.buildSpecificationAnd(combinedSpecification, "pcnServiceDeliveryType", filterProcess.getPcnServiceDeliveryType()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPcnClinicType()) ? specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicType", filterProcess.getPcnClinicType()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPcnPciFlag()) ? specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", filterProcess.getPcnPciFlag()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getSourceStatus()) ? specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", filterProcess.getSourceStatus()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPcnClinicStatus()) ? specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicStatus", filterProcess.getPcnClinicStatus()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsEffectiveStartDate", filterProcess.getHdsEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacBuildingName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", filterProcess.getFacBuildingName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddr1()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr1", filterProcess.getPhysicalAddr1()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddr2()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr2", filterProcess.getPhysicalAddr2()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddr3()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr3", filterProcess.getPhysicalAddr3()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddr4()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr4", filterProcess.getPhysicalAddr4()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalCity()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalCity", filterProcess.getPhysicalCity()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalProvince()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalProvince", filterProcess.getPhysicalProvince()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalPcode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalPcode", filterProcess.getPhysicalPcode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalCountry()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalCountry", filterProcess.getPhysicalCountry()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddrPrpsTypeCd()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrPrpsTypeCd", filterProcess.getPhysicalAddrPrpsTypeCd()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddrValidationStatus()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrValidationStatus", filterProcess.getPhysicalAddrValidationStatus()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddrMailabilityScore()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrMailabilityScore", filterProcess.getPhysicalAddrMailabilityScore()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddr1()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", filterProcess.getMailAddr1()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddr2()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", filterProcess.getMailAddr2()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddr3()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", filterProcess.getMailAddr3()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddr4()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", filterProcess.getMailAddr4()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailCity()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", filterProcess.getMailCity()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailProvince()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailProvince", filterProcess.getMailProvince()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailPcode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", filterProcess.getMailPcode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailCountry()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", filterProcess.getMailCountry()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddrPrpsTypeCd()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrPrpsTypeCd", filterProcess.getMailAddrPrpsTypeCd()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddrValidationStatus()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrValidationStatus", filterProcess.getMailAddrValidationStatus()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailAddrMailabilityScore()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrMailabilityScore", filterProcess.getMailAddrMailabilityScore()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPlrFacilityId()) ? specificationService.buildSpecificationAnd(combinedSpecification, "plrFacilityId", filterProcess.getPlrFacilityId()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacRelnType()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facRelnType", filterProcess.getFacRelnType()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacAddressUnit()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facAddressUnit", filterProcess.getFacAddressUnit()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacCivicAddr()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facCivicAddr", filterProcess.getFacCivicAddr()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacLatitude()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facLatitude", filterProcess.getFacLatitude()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacLongitude()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facLongitude", filterProcess.getFacLongitude()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacSiteId()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facSiteId", filterProcess.getFacSiteId()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacScore()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facScore", filterProcess.getFacScore()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacMatchPrecision()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facMatchPrecision", filterProcess.getFacMatchPrecision()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacHsdaName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facHsdaName", filterProcess.getFacHsdaName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacChsaStatus()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facChsaStatus", filterProcess.getFacChsaStatus()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacPcnStatus()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facPcnStatus", filterProcess.getFacPcnStatus()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacChsaCode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facChsaCode", filterProcess.getFacChsaCode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacChsaName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facChsaName", filterProcess.getFacChsaName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacLhaName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facLhaName", filterProcess.getFacLhaName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacHaName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facHaName", filterProcess.getFacHaName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacPcnCode()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facPcnCode", filterProcess.getFacPcnCode()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacPcnName()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facPcnName", filterProcess.getFacPcnName()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getFacIfcId()) ? specificationService.buildSpecificationAnd(combinedSpecification, "facIfcId", filterProcess.getFacIfcId()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddrMailabilityScore()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrMailabilityScore", filterProcess.getPhysicalAddrMailabilityScore()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPrimaryCareGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "primaryCareGroupAction", filterProcess.getPrimaryCareGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPrimaryCareGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "primaryCareGroupEffectiveStartDate", filterProcess.getPrimaryCareGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPrimaryCareGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "primaryCareGroupEffectiveEndDate", filterProcess.getPrimaryCareGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsSubTypeGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubTypeGroupAction", filterProcess.getHdsSubTypeGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsSubTypeGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubTypeGroupEffectiveStartDate", filterProcess.getHdsSubTypeGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsSubTypeGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubTypeGroupEffectiveEndDate", filterProcess.getHdsSubTypeGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsNameGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupAction", filterProcess.getHdsNameGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsNameGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupEffectiveStartDate", filterProcess.getHdsNameGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsNameGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameGroupEffectiveEndDate", filterProcess.getHdsNameGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getStatusGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "statusGroupAction", filterProcess.getStatusGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getStatusGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "statusGroupEffectiveStartDate", filterProcess.getStatusGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getStatusGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "statusGroupEffectiveEndDate", filterProcess.getStatusGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsEmailGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupAction", filterProcess.getHdsEmailGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsEmailGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupEffectiveStartDate", filterProcess.getHdsEmailGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsEmailGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmailGroupEffectiveEndDate", filterProcess.getHdsEmailGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsWebsiteGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsiteGroupAction", filterProcess.getHdsWebsiteGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsWebsiteGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsiteGroupEffectiveStartDate", filterProcess.getHdsWebsiteGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsWebsiteGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsiteGroupEffectiveEndDate", filterProcess.getHdsWebsiteGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getBusinessPhoneGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "businessPhoneGroupAction", filterProcess.getBusinessPhoneGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getBusinessPhoneGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "businessPhoneGroupEffectiveStartDate", filterProcess.getBusinessPhoneGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getBusinessPhoneGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "businessPhoneGroupEffectiveEndDate", filterProcess.getBusinessPhoneGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsFaxGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxGroupAction", filterProcess.getHdsFaxGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsFaxGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxGroupEffectiveStartDate", filterProcess.getHdsFaxGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsFaxGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxGroupEffectiveEndDate", filterProcess.getHdsFaxGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsCellGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupAction", filterProcess.getHdsCellGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsCellGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupEffectiveStartDate", filterProcess.getHdsCellGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getHdsCellGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellGroupEffectiveEndDate", filterProcess.getHdsCellGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddressGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddressGroupAction", filterProcess.getPhysicalAddressGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddressGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddressGroupEffectiveStartDate", filterProcess.getPhysicalAddressGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getPhysicalAddressGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddressGroupEffectiveEndDate", filterProcess.getPhysicalAddressGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailingAddressGroupAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailingAddressGroupAction", filterProcess.getMailingAddressGroupAction()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailingAddressGroupEffectiveStartDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailingAddressGroupEffectiveStartDate", filterProcess.getMailingAddressGroupEffectiveStartDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getMailingAddressGroupEffectiveEndDate()) ? specificationService.buildSpecificationAnd(combinedSpecification, "mailingAddressGroupEffectiveEndDate", filterProcess.getMailingAddressGroupEffectiveEndDate()) : combinedSpecification;
        combinedSpecification = !CollectionUtils.isEmpty(filterProcess.getRecordAction()) ? specificationService.buildSpecificationAnd(combinedSpecification, "recordAction", filterProcess.getRecordAction()) : combinedSpecification;

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
        return processDataRepository.getUniqueColumnValues(controlTableId, columnKey);
    }
}
