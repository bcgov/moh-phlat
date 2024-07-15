package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

		if(StringUtils.hasText(pSource.getId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "id", pSource.getId());
		}
		if(StringUtils.hasText(pSource.getDoNotLoad())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "doNotLoad", pSource.getDoNotLoad());
		}
		if(StringUtils.hasText(pSource.getStakeholder())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "stakeholder", pSource.getStakeholder());
		}
		if(StringUtils.hasText(pSource.getHdsLpcId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsLpcId", pSource.getHdsLpcId());
		}
		if(StringUtils.hasText(pSource.getHdsCpnId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsCpnId", pSource.getHdsCpnId());
		}
		if(StringUtils.hasText(pSource.getHdsProviderId1())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderId1", pSource.getHdsProviderId1());
		}
		if(StringUtils.hasText(pSource.getHdsProviderId2())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderId2", pSource.getHdsProviderId2());
		}
		if(StringUtils.hasText(pSource.getHdsProviderId3())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderId3", pSource.getHdsProviderId3());
		}
		if(StringUtils.hasText(pSource.getHdsProviderIdType1())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderIdType1", pSource.getHdsProviderIdType1());
		}
		if(StringUtils.hasText(pSource.getHdsProviderIdType2())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderIdType2", pSource.getHdsProviderIdType2());
		}
		if(StringUtils.hasText(pSource.getHdsProviderIdType3())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsProviderIdType3", pSource.getHdsProviderIdType3());
		}
		if(StringUtils.hasText(pSource.getHdsHibcFacId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsHibcFacId", pSource.getHdsHibcFacId());
		}
		if(StringUtils.hasText(pSource.getHdsType())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsType", pSource.getHdsType());
		}
		if(StringUtils.hasText(pSource.getHdsName())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsName", pSource.getHdsName());
		}
		if(StringUtils.hasText(pSource.getHdsNameAlias())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsNameAlias", pSource.getHdsNameAlias());
		}
		if(StringUtils.hasText(pSource.getHdsPrefNameFlag())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsPrefNameFlag", pSource.getHdsPrefNameFlag());
		}
		if(StringUtils.hasText(pSource.getHdsEmail())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsEmail", pSource.getHdsEmail());
		}
		if(StringUtils.hasText(pSource.getHdsWebsite())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsWebsite", pSource.getHdsWebsite());
		}
		if(StringUtils.hasText(pSource.getHdsBusTelAreaCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsBusTelAreaCode", pSource.getHdsBusTelAreaCode());
		}
		if(StringUtils.hasText(pSource.getHdsBusTelNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsBusTelNum", pSource.getHdsBusTelNum());
		}
		if(StringUtils.hasText(pSource.getHdsTelExt())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsTelExt", pSource.getHdsTelExt());
		}
		if(StringUtils.hasText(pSource.getHdsCellAreaCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsCellAreaCode", pSource.getHdsCellAreaCode());
		}
		if(StringUtils.hasText(pSource.getHdsCellNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsCellNum", pSource.getHdsCellNum());
		}
		if(StringUtils.hasText(pSource.getHdsFaxAreaCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsFaxAreaCode", pSource.getHdsFaxAreaCode());
		}
		if(StringUtils.hasText(pSource.getHdsFaxNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsFaxNum", pSource.getHdsFaxNum());
		}
		if(StringUtils.hasText(pSource.getHdsServiceDelType())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsServiceDelType", pSource.getHdsServiceDelType());
		}
		if(StringUtils.hasText(pSource.getPcnCLinicType())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "pcnCLinicType", pSource.getPcnCLinicType());
		}
		if(StringUtils.hasText(pSource.getPcnPciFlag())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "pcnPciFlag", pSource.getPcnPciFlag());
		}
		if(StringUtils.hasText(pSource.getHdsHoursOfOp())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsHoursOfOp", pSource.getHdsHoursOfOp());
		}
		if(StringUtils.hasText(pSource.getHdsContactName())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsContactName", pSource.getHdsContactName());
		}
		if(StringUtils.hasText(pSource.getHdsIsForProfitFlag())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsIsForProfitFlag", pSource.getHdsIsForProfitFlag());
		}
		if(StringUtils.hasText(pSource.getSourceStatus())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "sourceStatus", pSource.getSourceStatus());
		}
		if(StringUtils.hasText(pSource.getHdsParentIpcId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "hdsParentIpcId", pSource.getHdsParentIpcId());
		}
		if(StringUtils.hasText(pSource.getBusIpcId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busIpcId", pSource.getBusIpcId());
		}
		if(StringUtils.hasText(pSource.getBusCpnId())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busCpnId", pSource.getBusCpnId());
		}
		if(StringUtils.hasText(pSource.getBusName())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busName", pSource.getBusName());
		}
		if(StringUtils.hasText(pSource.getBusLegalName())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busLegalName", pSource.getBusLegalName());
		}
		if(StringUtils.hasText(pSource.getBusPayeeNum())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busPayeeNum", pSource.getBusPayeeNum());
		}
		if(StringUtils.hasText(pSource.getBusOwnerName())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busOwnerName", pSource.getBusOwnerName());
		}
		if(StringUtils.hasText(pSource.getBusOwnerType())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busOwnerType", pSource.getBusOwnerType());
		}
		if(StringUtils.hasText(pSource.getBusOwnerTypeOther())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "busOwnerTypeOther", pSource.getBusOwnerTypeOther());
		}
		if(StringUtils.hasText(pSource.getFacBuildingName())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "facBuildingName", pSource.getFacBuildingName());
		}
		if(StringUtils.hasText(pSource.getFacHdsDetailAddInfo())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "facHdsDetailAddInfo", pSource.getFacHdsDetailAddInfo());
		}
		if(StringUtils.hasText(pSource.getPhysAddr1())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr1", pSource.getPhysAddr1());
		}
		if(StringUtils.hasText(pSource.getPhysAddr2())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr2", pSource.getPhysAddr2());
		}
		if(StringUtils.hasText(pSource.getPhysAddr3())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr3", pSource.getPhysAddr3());
		}
		if(StringUtils.hasText(pSource.getPhysAddr4())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddr4", pSource.getPhysAddr4());
		}
		if(StringUtils.hasText(pSource.getPhysCity())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physCity", pSource.getPhysCity());
		}
		if(StringUtils.hasText(pSource.getPhysProv())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physProv", pSource.getPhysProv());
		}
		if(StringUtils.hasText(pSource.getPhysPCode())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physPCode", pSource.getPhysPCode());
		}
		if(StringUtils.hasText(pSource.getPhysCountry())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physCountry", pSource.getPhysCountry());
		}
		if(StringUtils.hasText(pSource.getPhysAddrIsPrivate())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "physAddrIsPrivate", pSource.getPhysAddrIsPrivate());
		}
		if(StringUtils.hasText(pSource.getMailAddr1())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr1", pSource.getMailAddr1());
		}
		if(StringUtils.hasText(pSource.getMailAddr2())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr2", pSource.getMailAddr2());
		}
		if(StringUtils.hasText(pSource.getMailAddr3())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr3", pSource.getMailAddr3());
		}
		if(StringUtils.hasText(pSource.getMailAddr4())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailAddr4", pSource.getMailAddr4());
		}
		if(StringUtils.hasText(pSource.getMailCity())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailCity", pSource.getMailCity());
		}
		if(StringUtils.hasText(pSource.getMailBc())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailBc", pSource.getMailBc());
		}
		if(StringUtils.hasText(pSource.getMailPcode())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailPcode", pSource.getMailPcode());
		}
		if(StringUtils.hasText(pSource.getMailCountry())) {
			combinedSpecification = specificationService.buildSpecificationAndForSourceData(combinedSpecification, "mailCountry", pSource.getMailCountry());
		}
		if(StringUtils.hasText(pSource.getMailAddrIsPriv())) {
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
