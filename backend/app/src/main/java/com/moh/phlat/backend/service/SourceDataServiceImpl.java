package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.SourceDataFilterParams;
import com.moh.phlat.backend.controller.ControlController;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.SourceDataFilterSpecification;
import com.moh.phlat.backend.repository.SourceDataFilterSpecificationImpl;
import com.moh.phlat.backend.repository.SourceDataRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class SourceDataServiceImpl implements SourceDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(SourceDataServiceImpl.class);

	@Autowired
	private SourceDataRepository sourceDataRepository;
	
	@Autowired
	private EntityManager entityManager;
    
	private SourceDataFilterSpecification specificationService = new SourceDataFilterSpecificationImpl();
	
	@Override
	public List<SourceData> getSourceData(Long controlId, SourceDataFilterParams filterSource, Pageable pageable) {
		
		try {
			return sourceDataRepository.findAll(buildSpecification(controlId, filterSource), pageable);
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			
			return new ArrayList();
		}
	}
	
	@Override
	public Long countSourceData(Long controlId,  SourceDataFilterParams filterSource) {
		try{
			return sourceDataRepository.count(buildSpecification(controlId, filterSource));
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			
			return null;
		}
	}
	
	private Specification<SourceData> buildSpecification(Long controlId, SourceDataFilterParams filterSource) throws Exception{
		
		Specification<SourceData> combinedSpecification;
		
		try {
			combinedSpecification = specificationService.buildSpecificationWhereEqual("controlTableId", controlId.toString());
		} catch (Exception e) {
			throw new Exception(e);
		}

		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", filterSource.getId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoadFlag", filterSource.getDoNotLoadFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", filterSource.getStakeholder());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholderId", filterSource.getStakeholderId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIpcId", filterSource.getHdsIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", filterSource.getHdsCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier1", filterSource.getHdsProviderIdentifier1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier2", filterSource.getHdsProviderIdentifier2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifier3", filterSource.getHdsProviderIdentifier3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType1", filterSource.getHdsProviderIdentifierType1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType2", filterSource.getHdsProviderIdentifierType2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdentifierType3", filterSource.getHdsProviderIdentifierType3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", filterSource.getHdsHibcFacId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", filterSource.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsSubType", filterSource.getHdsSubType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", filterSource.getHdsName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPreferredNameFlag", filterSource.getHdsPreferredNameFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", filterSource.getHdsEmail());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", filterSource.getHdsWebsite());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", filterSource.getHdsBusTelAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNumber", filterSource.getHdsBusTelNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExtension", filterSource.getHdsTelExtension());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", filterSource.getHdsCellAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNumber", filterSource.getHdsCellNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", filterSource.getHdsFaxAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNumber", filterSource.getHdsFaxNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnServiceDeliveryType", filterSource.getPcnServiceDeliveryType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicType", filterSource.getPcnClinicType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", filterSource.getPcnPciFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", filterSource.getHdsHoursOfOp());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", filterSource.getHdsContactName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", filterSource.getHdsIsForProfitFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", filterSource.getSourceStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", filterSource.getHdsParentIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", filterSource.getBusIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", filterSource.getBusCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", filterSource.getBusName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", filterSource.getBusLegalName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", filterSource.getBusPayeeNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", filterSource.getBusOwnerName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", filterSource.getBusOwnerType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", filterSource.getBusOwnerTypeOther());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", filterSource.getFacBuildingName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", filterSource.getFacHdsDetailAddInfo());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr1", filterSource.getPhysicalAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr2", filterSource.getPhysicalAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr3", filterSource.getPhysicalAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddr4", filterSource.getPhysicalAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalCity", filterSource.getPhysicalCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalProv", filterSource.getPhysicalProv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalPcode", filterSource.getPhysicalPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalCountry", filterSource.getPhysicalCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physicalAddrPrpsTypeCd", filterSource.getPhysicalAddrPrpsTypeCd());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", filterSource.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", filterSource.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", filterSource.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", filterSource.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", filterSource.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", filterSource.getMailBc());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", filterSource.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", filterSource.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", filterSource.getMailAddrIsPriv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsMspFacilityNumber", filterSource.getHdsMspFacilityNumber());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnClinicStatus", filterSource.getPcnClinicStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEffectiveStartDate", filterSource.getHdsEffectiveStartDate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facAddressUnit", filterSource.getFacAddressUnit());
		
		return combinedSpecification;
	}

	@Override
	public List<String> getUniqueColumnValues(Long controlTableId, String columnKey) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);
        
        Root<SourceData> root = query.from(SourceData.class);
	        
	    query.select(root.get(columnKey)).distinct(true);
	    query.where(cb.equal(root.get("controlTableId"), controlTableId));
	    query.orderBy(cb.asc(root.get(columnKey)));
        
        return entityManager.createQuery(query).getResultList();
		
	}

}
