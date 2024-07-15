package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<SourceData> getSourceData(Long controlId, SourceDataFilterParams filterProcess) {
		
		Specification<SourceData> combinedSpecification;
		
		try {
			combinedSpecification = specificationService.buildSpecificationWhereEqual("controlTableId", controlId.toString());
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			
			return new ArrayList();
		}

		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", filterProcess.getId());
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
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", filterProcess.getPcnCLinicType());
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
		
		return sourceDataRepository.findAll(combinedSpecification);
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
