package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.SourceDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;

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
		
		return sourceDataRepository.findAll(specificationService.buildSpecificationInSourceData(
				controlId, ids,
				doNotLoad, stakeholder, hdsLpcId, hdsCpnId, hdsProviderId1, hdsProviderId2, hdsProviderId3, hdsProviderIdType1, 
				hdsProviderIdType2, hdsProviderIdType3, hdsHibcFacId, hdsType, hdsName, hdsNameAlias, hdsPrefNameFlag, hdsEmail,
				hdsWebsite, hdsBusTelAreaCode, hdsBusTelNum, hdsTelExt, hdsCellAreaCode, hdsCellNum, hdsFaxAreaCode, hdsFaxNum,
				hdsServiceDelType, pcnCLinicType, pcnPciFlag, hdsHoursOfOp, hdsContactName, hdsIsForProfitFlag,
				sourceStatus, hdsParentIpcId, busIpcId, busCpnId, busName, busLegalName, busPayeeNum, busOwnerName,
				busOwnerType, busOwnerTypeOther, facBuildingName, facHdsDetailAddInfo, physAddr1, physAddr2,
				physAddr3, physAddr4, physCity, physProv, physPCode, physCountry, physAddrIsPrivate, mailAddr1,
				mailAddr2, mailAddr3, mailAddr4, mailCity, mailBc, mailPcode, mailCountry, mailAddrIsPriv));
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
