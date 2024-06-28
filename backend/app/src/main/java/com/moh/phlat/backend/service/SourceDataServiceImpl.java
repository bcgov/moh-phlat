package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.SourceDataRepository;

@Service
public class SourceDataServiceImpl implements SourceDataService {


	@Autowired
	private SourceDataRepository sourceDataRepository;
    
	@Autowired
	private SpecificationService specificationService;
	
	@Override
	public List<SourceData> findAll(Long controlId, List<String> ids, List<String> doNotLoad, List<String> stakeholder,
			List<String> hdsLpcId, List<String> hdsCpnId, List<String> hdsProviderId1, List<String> hdsProviderId2,
			List<String> hdsProviderId3, List<String> hdsProviderIdType1, List<String> hdsProviderIdType2,
			List<String> hdsProviderIdType3, List<String> hdsHibcFacId, List<String> hdsType, List<String> hdsName,
			List<String> hdsNameAlias, List<String> hdsPrefNameFlag, List<String> hdsEmail, List<String> hdsWebsite,
			List<String> hdsBusTelAreaCode, List<String> hdsBusTelNum, List<String> hdsTelExt,
			List<String> hdsCellAreaCode, List<String> hdsCellNum, List<String> hdsFaxAreaCode, List<String> hdsFaxNum,
			List<String> hdsServiceDelType, List<String> pcnCLinicType, List<String> pcnPciFlag,
			List<String> hdsHoursOfOp, List<String> hdsContactName, List<String> hdsIsForProfitFlag,
			List<String> sourceStatus, List<String> hdsParentIpcId, List<String> busIpcId, List<String> busCpnId,
			List<String> busName, List<String> busLegalName, List<String> busPayeeNum, List<String> busOwnerName,
			List<String> busOwnerType, List<String> busOwnerTypeOther, List<String> facBuildingName,
			List<String> facHdsDetailAddInfo, List<String> physAddr1, List<String> physAddr2, List<String> physAddr3,
			List<String> physAddr4, List<String> physCity, List<String> physProv, List<String> physPCode,
			List<String> physCountry, List<String> physAddrIsPrivate, List<String> mailAddr1, List<String> mailAddr2,
			List<String> mailAddr3, List<String> mailAddr4, List<String> mailCity, List<String> mailBc,
			List<String> mailPcode, List<String> mailCountry, List<String> mailAddrIsPriv) {
		
		Specification<SourceData> combinedSpecification = specificationService.buildSpecificationWhereEqual("controlTableId", controlId.toString());

		if(ids != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "id", ids);
		}
		if(doNotLoad != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "doNotLoad", doNotLoad);
		}
		if(stakeholder != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "stakeholder", stakeholder);
		}
		if(hdsLpcId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsLpcId", hdsLpcId);
		}
		if(hdsCpnId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCpnId", hdsCpnId);
		}
		if(hdsProviderId1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId1", hdsProviderId1);
		}
		if(hdsProviderId2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId2", hdsProviderId2);
		}
		if(hdsProviderId3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderId3", hdsProviderId3);
		}
		if(hdsProviderIdType1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType1", hdsProviderIdType1);
		}
		if(hdsProviderIdType2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType2", hdsProviderIdType2);
		}
		if(hdsProviderIdType3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsProviderIdType3", hdsProviderIdType3);
		}
		if(hdsHibcFacId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHibcFacId", hdsHibcFacId);
		}
		if(hdsType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsType", hdsType);
		}
		if(hdsName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsName", hdsName);
		}
		if(hdsNameAlias != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsNameAlias", hdsNameAlias);
		}
		if(hdsPrefNameFlag != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsPrefNameFlag", hdsPrefNameFlag);
		}
		if(hdsEmail != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsEmail", hdsEmail);
		}
		if(hdsWebsite != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsWebsite", hdsWebsite);
		}
		if(hdsBusTelAreaCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelAreaCode", hdsBusTelAreaCode);
		}
		if(hdsBusTelNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsBusTelNum", hdsBusTelNum);
		}
		if(hdsTelExt != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsTelExt", hdsTelExt);
		}
		if(hdsCellAreaCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellAreaCode", hdsCellAreaCode);
		}
		if(hdsCellNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsCellNum", hdsCellNum);
		}
		if(hdsFaxAreaCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxAreaCode", hdsFaxAreaCode);
		}
		if(hdsFaxNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsFaxNum", hdsFaxNum);
		}
		if(hdsServiceDelType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsServiceDelType", hdsServiceDelType);
		}
		if(pcnCLinicType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnCLinicType", pcnCLinicType);
		}
		if(pcnPciFlag != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "pcnPciFlag", pcnPciFlag);
		}
		if(hdsHoursOfOp != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsHoursOfOp", hdsHoursOfOp);
		}
		if(hdsContactName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsContactName", hdsContactName);
		}
		if(hdsIsForProfitFlag != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsIsForProfitFlag", hdsIsForProfitFlag);
		}
		if(sourceStatus != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "sourceStatus", sourceStatus);
		}
		if(hdsParentIpcId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "hdsParentIpcId", hdsParentIpcId);
		}
		if(busIpcId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busIpcId", busIpcId);
		}
		if(busCpnId != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busCpnId", busCpnId);
		}
		if(busName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busName", busName);
		}
		if(busLegalName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busLegalName", busLegalName);
		}
		if(busPayeeNum != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busPayeeNum", busPayeeNum);
		}
		if(busOwnerName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerName", busOwnerName);
		}
		if(busOwnerType != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerType", busOwnerType);
		}
		if(busOwnerTypeOther != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "busOwnerTypeOther", busOwnerTypeOther);
		}
		if(facBuildingName != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facBuildingName", facBuildingName);
		}
		if(facHdsDetailAddInfo != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "facHdsDetailAddInfo", facHdsDetailAddInfo);
		}
		if(physAddr1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr1", physAddr1);
		}
		if(physAddr2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr2", physAddr2);
		}
		if(physAddr3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr3", physAddr3);
		}
		if(physAddr4 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddr4", physAddr4);
		}
		if(physCity != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCity", physCity);
		}
		if(physProv != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physProv", physProv);
		}
		if(physPCode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physPCode", physPCode);
		}
		if(physCountry != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physCountry", physCountry);
		}
		if(physAddrIsPrivate != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "physAddrIsPrivate", physAddrIsPrivate);
		}
		if(mailAddr1 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr1", mailAddr1);
		}
		if(mailAddr2 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr2", mailAddr2);
		}
		if(mailAddr3 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr3", mailAddr3);
		}
		if(mailAddr4 != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddr4", mailAddr4);
		}
		if(mailCity != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCity", mailCity);
		}
		if(mailBc != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailBc", mailBc);
		}
		if(mailPcode != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailPcode", mailPcode);
		}
		if(mailCountry != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailCountry", mailCountry);
		}
		if(mailAddrIsPriv != null) {
			combinedSpecification = specificationService.buildSpecificationAnd(combinedSpecification, "mailAddrIsPriv", mailAddrIsPriv);
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
