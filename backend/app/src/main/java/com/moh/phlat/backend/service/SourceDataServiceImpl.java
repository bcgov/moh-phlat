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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", filterSource.getDoNotLoad());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", filterSource.getStakeholder());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", filterSource.getHdsLpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", filterSource.getHdsCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", filterSource.getHdsProviderId1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", filterSource.getHdsProviderId2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", filterSource.getHdsProviderId3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", filterSource.getHdsProviderIdType1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", filterSource.getHdsProviderIdType2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", filterSource.getHdsProviderIdType3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", filterSource.getHdsHibcFacId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", filterSource.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", filterSource.getHdsName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", filterSource.getHdsNameAlias());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", filterSource.getHdsPrefNameFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", filterSource.getHdsEmail());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", filterSource.getHdsWebsite());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", filterSource.getHdsBusTelAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", filterSource.getHdsBusTelNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", filterSource.getHdsTelExt());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", filterSource.getHdsCellAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", filterSource.getHdsCellNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", filterSource.getHdsFaxAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", filterSource.getHdsFaxNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", filterSource.getHdsServiceDelType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", filterSource.getPcnCLinicType());
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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", filterSource.getPhysAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", filterSource.getPhysAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", filterSource.getPhysAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", filterSource.getPhysAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", filterSource.getPhysCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", filterSource.getPhysProv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", filterSource.getPhysPCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", filterSource.getPhysCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", filterSource.getPhysAddrIsPrivate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", filterSource.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", filterSource.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", filterSource.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", filterSource.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", filterSource.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", filterSource.getMailBc());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", filterSource.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", filterSource.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", filterSource.getMailAddrIsPriv());
		
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
