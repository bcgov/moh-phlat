package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.SourceDataFilterParams;
import com.moh.phlat.backend.model.ProcessData;
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


	@Autowired
	private SourceDataRepository sourceDataRepository;
	
	@Autowired
	private EntityManager entityManager;
    
	private SourceDataFilterSpecification specificationService = new SourceDataFilterSpecificationImpl();
	
	@Override
	public List<SourceData> getSourceData(Long controlId, SourceDataFilterParams pSource) {
		
		Specification<SourceData> combinedSpecification = specificationService.buildSpecificationWhereEqual("controlTableId", controlId.toString());

		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", pSource.getId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", pSource.getDoNotLoad());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", pSource.getStakeholder());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", pSource.getHdsLpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", pSource.getHdsCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", pSource.getHdsProviderId1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", pSource.getHdsProviderId2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", pSource.getHdsProviderId3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", pSource.getHdsProviderIdType1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", pSource.getHdsProviderIdType2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", pSource.getHdsProviderIdType3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", pSource.getHdsHibcFacId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", pSource.getHdsType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", pSource.getHdsName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", pSource.getHdsNameAlias());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", pSource.getHdsPrefNameFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", pSource.getHdsEmail());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", pSource.getHdsWebsite());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", pSource.getHdsBusTelAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", pSource.getHdsBusTelNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", pSource.getHdsTelExt());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", pSource.getHdsCellAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", pSource.getHdsCellNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", pSource.getHdsFaxAreaCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", pSource.getHdsFaxNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", pSource.getHdsServiceDelType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", pSource.getPcnCLinicType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", pSource.getPcnPciFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", pSource.getHdsHoursOfOp());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", pSource.getHdsContactName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", pSource.getHdsIsForProfitFlag());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", pSource.getSourceStatus());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", pSource.getHdsParentIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", pSource.getBusIpcId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", pSource.getBusCpnId());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", pSource.getBusName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", pSource.getBusLegalName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", pSource.getBusPayeeNum());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", pSource.getBusOwnerName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", pSource.getBusOwnerType());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", pSource.getBusOwnerTypeOther());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", pSource.getFacBuildingName());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", pSource.getFacHdsDetailAddInfo());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", pSource.getPhysAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", pSource.getPhysAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", pSource.getPhysAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", pSource.getPhysAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", pSource.getPhysCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", pSource.getPhysProv());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", pSource.getPhysPCode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", pSource.getPhysCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", pSource.getPhysAddrIsPrivate());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", pSource.getMailAddr1());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", pSource.getMailAddr2());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", pSource.getMailAddr3());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", pSource.getMailAddr4());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", pSource.getMailCity());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", pSource.getMailBc());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", pSource.getMailPcode());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", pSource.getMailCountry());
		combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", pSource.getMailAddrIsPriv());
		
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
