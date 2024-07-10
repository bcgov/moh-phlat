package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.ParamSource;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.SourceDataRepository;

@Service
public class SourceDataServiceImpl implements SourceDataService {


	@Autowired
	private SourceDataRepository sourceDataRepository;
    
	@Autowired
	private SpecificationService specificationService;
	
	@Override
	public List<SourceData> findAll(Long controlId, ParamSource pSource) {
		
		Specification<SourceData> combinedSpecification = specificationService.buildSpecificationWhereEqual("controlTableId", controlId.toString());

		if(pSource.getId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", pSource.getId());
		}
		if(pSource.getDoNotLoad() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", pSource.getDoNotLoad());
		}
		if(pSource.getStakeholder() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", pSource.getStakeholder());
		}
		if(pSource.getHdsLpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", pSource.getHdsLpcId());
		}
		if(pSource.getHdsCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", pSource.getHdsCpnId());
		}
		if(pSource.getHdsProviderId1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", pSource.getHdsProviderId1());
		}
		if(pSource.getHdsProviderId2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", pSource.getHdsProviderId2());
		}
		if(pSource.getHdsProviderId3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", pSource.getHdsProviderId3());
		}
		if(pSource.getHdsProviderIdType1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", pSource.getHdsProviderIdType1());
		}
		if(pSource.getHdsProviderIdType2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", pSource.getHdsProviderIdType2());
		}
		if(pSource.getHdsProviderIdType3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", pSource.getHdsProviderIdType3());
		}
		if(pSource.getHdsHibcFacId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", pSource.getHdsHibcFacId());
		}
		if(pSource.getHdsType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", pSource.getHdsType());
		}
		if(pSource.getHdsName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", pSource.getHdsName());
		}
		if(pSource.getHdsNameAlias() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", pSource.getHdsNameAlias());
		}
		if(pSource.getHdsPrefNameFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", pSource.getHdsPrefNameFlag());
		}
		if(pSource.getHdsEmail() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", pSource.getHdsEmail());
		}
		if(pSource.getHdsWebsite() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", pSource.getHdsWebsite());
		}
		if(pSource.getHdsBusTelAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", pSource.getHdsBusTelAreaCode());
		}
		if(pSource.getHdsBusTelNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", pSource.getHdsBusTelNum());
		}
		if(pSource.getHdsTelExt() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", pSource.getHdsTelExt());
		}
		if(pSource.getHdsCellAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", pSource.getHdsCellAreaCode());
		}
		if(pSource.getHdsCellNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", pSource.getHdsCellNum());
		}
		if(pSource.getHdsFaxAreaCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", pSource.getHdsFaxAreaCode());
		}
		if(pSource.getHdsFaxNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", pSource.getHdsFaxNum());
		}
		if(pSource.getHdsServiceDelType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", pSource.getHdsServiceDelType());
		}
		if(pSource.getPcnCLinicType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", pSource.getPcnCLinicType());
		}
		if(pSource.getPcnPciFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", pSource.getPcnPciFlag());
		}
		if(pSource.getHdsHoursOfOp() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", pSource.getHdsHoursOfOp());
		}
		if(pSource.getHdsContactName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", pSource.getHdsContactName());
		}
		if(pSource.getHdsIsForProfitFlag() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", pSource.getHdsIsForProfitFlag());
		}
		if(pSource.getSourceStatus() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", pSource.getSourceStatus());
		}
		if(pSource.getHdsParentIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", pSource.getHdsParentIpcId());
		}
		if(pSource.getBusIpcId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", pSource.getBusIpcId());
		}
		if(pSource.getBusCpnId() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", pSource.getBusCpnId());
		}
		if(pSource.getBusName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", pSource.getBusName());
		}
		if(pSource.getBusLegalName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", pSource.getBusLegalName());
		}
		if(pSource.getBusPayeeNum() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", pSource.getBusPayeeNum());
		}
		if(pSource.getBusOwnerName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", pSource.getBusOwnerName());
		}
		if(pSource.getBusOwnerType() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", pSource.getBusOwnerType());
		}
		if(pSource.getBusOwnerTypeOther() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", pSource.getBusOwnerTypeOther());
		}
		if(pSource.getFacBuildingName() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", pSource.getFacBuildingName());
		}
		if(pSource.getFacHdsDetailAddInfo() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", pSource.getFacHdsDetailAddInfo());
		}
		if(pSource.getPhysAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", pSource.getPhysAddr1());
		}
		if(pSource.getPhysAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", pSource.getPhysAddr2());
		}
		if(pSource.getPhysAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", pSource.getPhysAddr3());
		}
		if(pSource.getPhysAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", pSource.getPhysAddr4());
		}
		if(pSource.getPhysCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", pSource.getPhysCity());
		}
		if(pSource.getPhysProv() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", pSource.getPhysProv());
		}
		if(pSource.getPhysPCode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", pSource.getPhysPCode());
		}
		if(pSource.getPhysCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", pSource.getPhysCountry());
		}
		if(pSource.getPhysAddrIsPrivate() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", pSource.getPhysAddrIsPrivate());
		}
		if(pSource.getMailAddr1() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", pSource.getMailAddr1());
		}
		if(pSource.getMailAddr2() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", pSource.getMailAddr2());
		}
		if(pSource.getMailAddr3() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", pSource.getMailAddr3());
		}
		if(pSource.getMailAddr4() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", pSource.getMailAddr4());
		}
		if(pSource.getMailCity() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", pSource.getMailCity());
		}
		if(pSource.getMailBc() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", pSource.getMailBc());
		}
		if(pSource.getMailPcode() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", pSource.getMailPcode());
		}
		if(pSource.getMailCountry() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", pSource.getMailCountry());
		}
		if(pSource.getMailAddrIsPriv() != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", pSource.getMailAddrIsPriv());
		}
		
		return sourceDataRepository.findAll(combinedSpecification);
	}

	@Override
	public List<String> getDistinctColumnValues(Long controlTableId, String columnKey) {
		switch(columnKey) {
			case "id":
				return sourceDataRepository.findAllDistinctId(controlTableId);
			case "control_id":
				return sourceDataRepository.findAllDistinctControlId(controlTableId);
			case "doNotLoadFlag":
				return sourceDataRepository.findAllDistinctDoNotLoad(controlTableId);
			case "stakeholder":
				return sourceDataRepository.findAllDistinctStakeholder(controlTableId);
			case "hdsIpcId":
				return sourceDataRepository.findAllDistinctHdsIpcId(controlTableId);
			case "hdsCpnId":
				return sourceDataRepository.findAllDistinctHdsCpnId(controlTableId);				
			case "hdsProviderIdentifier1":
				return sourceDataRepository.findAllDistinctHdsProviderIdentifier1(controlTableId);				
			case "hdsProviderIdentifier2":
				return sourceDataRepository.findAllDistinctHdsProviderIdentifier2(controlTableId);				
			case "hdsProviderIdentifier3":
				return sourceDataRepository.findAllDistinctHdsProviderIdentifier3(controlTableId);				
			case "hdsProviderIdentifierType1":
				return sourceDataRepository.findAllDistinctHdsProviderIdentifierType1(controlTableId);				
			case "hdsProviderIdentifierType2":
				return sourceDataRepository.findAllDistinctHdsProviderIdentifierType2(controlTableId);				
			case "hdsProviderIdentifierType3":
				return sourceDataRepository.findAllDistinctHdsProviderIdentifierType3(controlTableId);				
			case "hdsHibcFacilityId":
				return sourceDataRepository.findAllDistinctHdsHibcFacilityId(controlTableId);				
			case "hdsType":
				return sourceDataRepository.findAllDistinctHdsType(controlTableId);				
			case "hdsName":
				return sourceDataRepository.findAllDistinctHdsName(controlTableId);				
			case "hdsNameAlias":
				//return sourceDataRepository.findAllDistinctHdsNameAlias(controlTableId);
				break;
			case "hdsPreferredNameFlag":
				return sourceDataRepository.findAllDistinctHdsPreferredNameFlag(controlTableId);				
			case "hdsEmail":
				return sourceDataRepository.findAllDistinctHdsEmail(controlTableId);				
			case "hdsWebsite":
				return sourceDataRepository.findAllDistinctHdsWebsite(controlTableId);				
			case "hdsBusTelAreaCode":
				return sourceDataRepository.findAllDistinctHdsBusTelAreaCode(controlTableId);				
			case "hdsBusTelNumber":
				return sourceDataRepository.findAllDistinctHdsBusTelNumber(controlTableId);				
			case "hdsTelExtension":
				return sourceDataRepository.findAllDistinctHdsTelExtension(controlTableId);				
			case "hdsCellAreaCode":
				return sourceDataRepository.findAllDistinctHdsCellAreaCode(controlTableId);				
			case "hdsCellNumber":
				return sourceDataRepository.findAllDistinctHdsCellNumber(controlTableId);				
			case "hdsFaxAreaCode":
				return sourceDataRepository.findAllDistinctHdsFaxAreaCode(controlTableId);				
			case "hdsFaxNumber":
				return sourceDataRepository.findAllDistinctHdsFaxNumber(controlTableId);				
			case "hdsServiceDeliveryType":
				//return sourceDataRepository.findAllDistinctHdsServiceDeliveryType(controlTableId);				
				break;
			case "pcnClinicType":
				//return sourceDataRepository.findAllDistinctPcnClinicType(controlTableId);				
				break;			
			case "pcnPciFlag":
				//return sourceDataRepository.findAllDistinctPcnPciFlag(controlTableId);			
				break;				
			case "hdsHoursOfOperation":
				//return sourceDataRepository.findAllDistinctHdsHoursOfOperation(controlTableId);				
				break;			
			case "hdsContactName":
				//return sourceDataRepository.findAllDistinctHdsContactName(controlTableId);				
				break;			
			case "hdsIsForProfitFlag":
				//return sourceDataRepository.findAllDistinctHdsIsForProfitFlag(controlTableId);				
				break;			
			case "sourceStatus":
				return sourceDataRepository.findAllDistinctSourceStatus(controlTableId);				
			case "hdaParentIpcId":
				//return sourceDataRepository.findAllDistinctHdsParentIpcId(controlTableId);				
				break;			
			case "busIpcId":
				//return sourceDataRepository.findAllDistinctBusIpcId(controlTableId);				
				break;			
			case "busCpnId":
				//return sourceDataRepository.findAllDistinctBusCpnId(controlTableId);				
				break;			
			case "busName":
				//return sourceDataRepository.findAllDistinctBusName(controlTableId);					
				break;		
			case "busLegalName":
				//return sourceDataRepository.findAllDistinctBusLegalName(controlTableId);				
				break;			
			case "busPayeeNumber":
				//return sourceDataRepository.findAllDistinctBusPayeeNumber(controlTableId);				
				break;			
			case "busOwnerName":
				//return sourceDataRepository.findAllDistinctBusOwnerName(controlTableId);				
				break;			
			case "busOwnerType":
				//return sourceDataRepository.findAllDistinctBusOwnerType(controlTableId);				
				break;			
			case "busOwnerTypeOther":
				//return sourceDataRepository.findAllDistinctBusOwnerTypeOther(controlTableId);				
				break;			
			case "facBuildingName":
				return sourceDataRepository.findAllDistinctFacBuildingName(controlTableId);				
			case "facilityHdsDetailsAdditionalInfo":
				//return sourceDataRepository.findAllDistinctFacilityHdsDetailsAdditionalInfo(controlTableId);				
				break;			
			case "physicalAddr1":
				return sourceDataRepository.findAllDistinctPhysicalAddr1(controlTableId);				
			case "physicalAddr2":
				return sourceDataRepository.findAllDistinctPhysicalAddr2(controlTableId);				
			case "physicalAddr3":
				return sourceDataRepository.findAllDistinctPhysicalAddr3(controlTableId);				
			case "physicalAddr":
				return sourceDataRepository.findAllDistinctPhysicalAddr4(controlTableId);				
			case "physicalCity":
				return sourceDataRepository.findAllDistinctPhysicalCity(controlTableId);				
			case "physicalProvince":
				return sourceDataRepository.findAllDistinctPhysicalProvince(controlTableId);				
			case "physicalPcode":
				return sourceDataRepository.findAllDistinctPhysicalPcode(controlTableId);				
			case "physicalCountry":
				return sourceDataRepository.findAllDistinctPhysicalCountry(controlTableId);				
			case "physAddrIsPrivate":
				//return sourceDataRepository.findAllDistinctPhysAddrIsPrivate(controlTableId);					
				break;		
			case "mailAddr1":
				return sourceDataRepository.findAllDistinctMailAddr1(controlTableId);				
			case "mailAddr2":
				return sourceDataRepository.findAllDistinctMailAddr2(controlTableId);				
			case "mailAddr3":
				return sourceDataRepository.findAllDistinctMailAddr3(controlTableId);				
			case "mailAddr4":
				return sourceDataRepository.findAllDistinctMailAddr4(controlTableId);				
			case "nailCity":
				return sourceDataRepository.findAllDistinctMailCity(controlTableId);				
			case "mailBc":
				return sourceDataRepository.findAllDistinctMailBc(controlTableId);				
			case "mailPcode":
				return sourceDataRepository.findAllDistinctMailPcode(controlTableId);
			case "mailCountry":
				return sourceDataRepository.findAllDistinctMailCountry(controlTableId);
			case "mailAddrIsPrivate":
				//return sourceDataRepository.findAllDistinctMailAddrIsPrivate(controlTableId);			
				break;
		}
		
		return null;
	}

}
