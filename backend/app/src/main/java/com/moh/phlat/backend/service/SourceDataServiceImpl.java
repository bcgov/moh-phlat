package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
