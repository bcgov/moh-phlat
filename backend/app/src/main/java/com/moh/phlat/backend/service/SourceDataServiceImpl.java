package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.SourceDataFilterParams;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;
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
    
	private SpecificationService specificationService = new SpecificationServiceImpl();
	
	@Override
	public List<SourceData> getSourceData(Long controlId, SourceDataFilterParams pSource) {
		
		Specification<SourceData> combinedSpecification = specificationService.buildSpecificationWhereEqualForSourceData("controlTableId", controlId.toString());

		if(pSource.getId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "id", pSource.getId());
		}
		if(pSource.getDoNotLoad() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "doNotLoad", pSource.getDoNotLoad());
		}
		if(pSource.getStakeholder() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "stakeholder", pSource.getStakeholder());
		}
		if(pSource.getHdsLpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsLpcId", pSource.getHdsLpcId());
		}
		if(pSource.getHdsCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsCpnId", pSource.getHdsCpnId());
		}
		if(pSource.getHdsProviderId1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderId1", pSource.getHdsProviderId1());
		}
		if(pSource.getHdsProviderId2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderId2", pSource.getHdsProviderId2());
		}
		if(pSource.getHdsProviderId3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderId3", pSource.getHdsProviderId3());
		}
		if(pSource.getHdsProviderIdType1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderIdType1", pSource.getHdsProviderIdType1());
		}
		if(pSource.getHdsProviderIdType2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderIdType2", pSource.getHdsProviderIdType2());
		}
		if(pSource.getHdsProviderIdType3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderIdType3", pSource.getHdsProviderIdType3());
		}
		if(pSource.getHdsHibcFacId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsHibcFacId", pSource.getHdsHibcFacId());
		}
		if(pSource.getHdsType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsType", pSource.getHdsType());
		}
		if(pSource.getHdsName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsName", pSource.getHdsName());
		}
		if(pSource.getHdsNameAlias() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsNameAlias", pSource.getHdsNameAlias());
		}
		if(pSource.getHdsPrefNameFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsPrefNameFlag", pSource.getHdsPrefNameFlag());
		}
		if(pSource.getHdsEmail() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsEmail", pSource.getHdsEmail());
		}
		if(pSource.getHdsWebsite() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsWebsite", pSource.getHdsWebsite());
		}
		if(pSource.getHdsBusTelAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsBusTelAreaCode", pSource.getHdsBusTelAreaCode());
		}
		if(pSource.getHdsBusTelNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsBusTelNum", pSource.getHdsBusTelNum());
		}
		if(pSource.getHdsTelExt() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsTelExt", pSource.getHdsTelExt());
		}
		if(pSource.getHdsCellAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsCellAreaCode", pSource.getHdsCellAreaCode());
		}
		if(pSource.getHdsCellNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsCellNum", pSource.getHdsCellNum());
		}
		if(pSource.getHdsFaxAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsFaxAreaCode", pSource.getHdsFaxAreaCode());
		}
		if(pSource.getHdsFaxNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsFaxNum", pSource.getHdsFaxNum());
		}
		if(pSource.getHdsServiceDelType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsServiceDelType", pSource.getHdsServiceDelType());
		}
		if(pSource.getPcnCLinicType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "pcnCLinicType", pSource.getPcnCLinicType());
		}
		if(pSource.getPcnPciFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "pcnPciFlag", pSource.getPcnPciFlag());
		}
		if(pSource.getHdsHoursOfOp() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsHoursOfOp", pSource.getHdsHoursOfOp());
		}
		if(pSource.getHdsContactName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsContactName", pSource.getHdsContactName());
		}
		if(pSource.getHdsIsForProfitFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsIsForProfitFlag", pSource.getHdsIsForProfitFlag());
		}
		if(pSource.getSourceStatus() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "sourceStatus", pSource.getSourceStatus());
		}
		if(pSource.getHdsParentIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsParentIpcId", pSource.getHdsParentIpcId());
		}
		if(pSource.getBusIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busIpcId", pSource.getBusIpcId());
		}
		if(pSource.getBusCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busCpnId", pSource.getBusCpnId());
		}
		if(pSource.getBusName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busName", pSource.getBusName());
		}
		if(pSource.getBusLegalName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busLegalName", pSource.getBusLegalName());
		}
		if(pSource.getBusPayeeNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busPayeeNum", pSource.getBusPayeeNum());
		}
		if(pSource.getBusOwnerName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busOwnerName", pSource.getBusOwnerName());
		}
		if(pSource.getBusOwnerType() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busOwnerType", pSource.getBusOwnerType());
		}
		if(pSource.getBusOwnerTypeOther() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busOwnerTypeOther", pSource.getBusOwnerTypeOther());
		}
		if(pSource.getFacBuildingName() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "facBuildingName", pSource.getFacBuildingName());
		}
		if(pSource.getFacHdsDetailAddInfo() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "facHdsDetailAddInfo", pSource.getFacHdsDetailAddInfo());
		}
		if(pSource.getPhysAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr1", pSource.getPhysAddr1());
		}
		if(pSource.getPhysAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr2", pSource.getPhysAddr2());
		}
		if(pSource.getPhysAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr3", pSource.getPhysAddr3());
		}
		if(pSource.getPhysAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr4", pSource.getPhysAddr4());
		}
		if(pSource.getPhysCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physCity", pSource.getPhysCity());
		}
		if(pSource.getPhysProv() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physProv", pSource.getPhysProv());
		}
		if(pSource.getPhysPCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physPCode", pSource.getPhysPCode());
		}
		if(pSource.getPhysCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physCountry", pSource.getPhysCountry());
		}
		if(pSource.getPhysAddrIsPrivate() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddrIsPrivate", pSource.getPhysAddrIsPrivate());
		}
		if(pSource.getMailAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr1", pSource.getMailAddr1());
		}
		if(pSource.getMailAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr2", pSource.getMailAddr2());
		}
		if(pSource.getMailAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr3", pSource.getMailAddr3());
		}
		if(pSource.getMailAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr4", pSource.getMailAddr4());
		}
		if(pSource.getMailCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailCity", pSource.getMailCity());
		}
		if(pSource.getMailBc() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailBc", pSource.getMailBc());
		}
		if(pSource.getMailPcode() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailPcode", pSource.getMailPcode());
		}
		if(pSource.getMailCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailCountry", pSource.getMailCountry());
		}
		if(pSource.getMailAddrIsPriv() != null) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddrIsPriv", pSource.getMailAddrIsPriv());
		}
		
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
