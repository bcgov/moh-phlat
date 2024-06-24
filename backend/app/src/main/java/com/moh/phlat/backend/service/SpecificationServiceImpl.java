package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

import jakarta.persistence.criteria.JoinType;

@Service
public class SpecificationServiceImpl<T> implements SpecificationService {

	@Override
	public Specification<ProcessData> buildSpecificationInProcessData(Long controlId, boolean hasMessages) {
		return buildSpecificationInProcessData(controlId, null, hasMessages);
	}
	
	@Override
	public Specification<ProcessData> buildSpecificationInProcessData(Long controlId, String reqRowStatusCode, boolean hasMessages){
		return buildSpecificationInProcessData(controlId,reqRowStatusCode,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, hasMessages);
	}

	@Override
	public Specification<ProcessData> buildSpecificationInProcessData(Long controlId, String reqRowStatusCode, List<String> ids, List<String> actions, 
			List<String> rowStatusCode, List<String> messages, List<String> doNotLoad, List<String> stakeholder, List<String> hdsLpcId, 
			List<String> hdsCpnId, List<String> hdsProviderId1, List<String> hdsProviderId2, List<String> hdsProviderId3, 
			List<String> hdsProviderIdType1, List<String> hdsProviderIdType2, List<String> hdsProviderIdType3, List<String> hdsHibcFacId, 
			List<String> hdsType, List<String> hdsName, List<String> hdsNameAlias, List<String> hdsPrefNameFlag, List<String> hdsEmail, 
			List<String> hdsWebsite, List<String> hdsBusTelAreaCode, List<String> hdsBusTelNum, List<String> hdsTelExt, List<String> hdsCellAreaCode,
			List<String> hdsCellNum, List<String> hdsFaxAreaCode, List<String> hdsFaxNum, List<String> hdsServiceDelType, List<String> pcnCLinicType,
			List<String> pcnPciFlag, List<String> hdsHoursOfOp, List<String> hdsContactName, List<String> hdsIsForProfitFlag, List<String> sourceStatus,
			List<String> hdsParentIpcId, List<String> busIpcId, List<String> busCpnId, List<String> busName, List<String> busLegalName, 
			List<String> busPayeeNum, List<String> busOwnerName, List<String> busOwnerType, List<String> busOwnerTypeOther, 
			List<String> facBuildingName, List<String> facHdsDetailAddInfo, List<String> physAddr1, List<String> physAddr2, List<String> physAddr3,
			List<String> physAddr4, List<String> physCity, List<String> physProv, List<String> physPCode, List<String> physCountry, 
			List<String> physAddrIsPrivate, List<String> mailAddr1, List<String> mailAddr2, List<String> mailAddr3, List<String> mailAddr4, 
			List<String> mailCity, List<String> mailBc, List<String> mailPcode, List<String> mailCountry, List<String> mailAddrIsPriv, boolean hasMessages) {
		
		Specification<ProcessData> combinedSpecification;
		
		if(hasMessages) {
			combinedSpecification = hasDatWithMessages(controlId);
		} else {
			combinedSpecification = Specification.where((root, query, builder) -> 
				builder.equal(root.get("controlTableId"), controlId));
		}

		if(reqRowStatusCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
				builder.equal(root.get("rowstatusCode"), reqRowStatusCode));
		}
		if(ids != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("id").in(ids));
		}
		if(actions != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("actions").in(actions));
		}
		if(rowStatusCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("rowStatusCode").in(rowStatusCode));
		}
		if(messages != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("messages").in(messages));
		}
		if(doNotLoad != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("doNotLoad").in(doNotLoad));
		}
		if(stakeholder != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("stakeholder").in(stakeholder));
		}
		if(hdsLpcId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsLpcId").in(hdsLpcId));
		}
		if(hdsCpnId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsCpnId").in(hdsCpnId));
		}
		if(hdsProviderId1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderId1").in(hdsProviderId1));
		}
		if(hdsProviderId2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderId2").in(hdsProviderId2));
		}
		if(hdsProviderId3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderId3").in(hdsProviderId3));
		}
		if(hdsProviderIdType1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderIdType1").in(hdsProviderIdType1));
		}
		if(hdsProviderIdType2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderIdType2").in(hdsProviderIdType2));
		}
		if(hdsProviderIdType3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderIdType3").in(hdsProviderIdType3));
		}
		if(hdsHibcFacId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsHibcFacId").in(hdsHibcFacId));
		}
		if(hdsType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsType").in(hdsType));
		}
		if(hdsName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsName").in(hdsName));
		}
		if(hdsNameAlias != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsNameAlias").in(hdsNameAlias));
		}
		if(hdsPrefNameFlag != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsPrefNameFlag").in(hdsPrefNameFlag));
		}
		if(hdsEmail != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsEmail").in(hdsEmail));
		}
		if(hdsWebsite != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsWebsite").in(hdsWebsite));
		}
		if(hdsBusTelAreaCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsBusTelAreaCode").in(hdsBusTelAreaCode));
		}
		if(hdsBusTelNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsBusTelNum").in(hdsBusTelNum));
		}
		if(hdsTelExt != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsTelExt").in(hdsTelExt));
		}
		if(hdsCellAreaCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsCellAreaCode").in(hdsCellAreaCode));
		}
		if(hdsCellNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsCellNum").in(hdsCellNum));
		}
		if(hdsFaxAreaCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsFaxAreaCode").in(hdsFaxAreaCode));
		}
		if(hdsFaxNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsFaxNum").in(hdsFaxNum));
		}
		if(hdsServiceDelType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsServiceDelType").in(hdsServiceDelType));
		}
		if(pcnCLinicType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("pcnCLinicType").in(pcnCLinicType));
		}
		if(pcnPciFlag != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("pcnPciFlag").in(pcnPciFlag));
		}
		if(hdsHoursOfOp != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsHoursOfOp").in(hdsHoursOfOp));
		}
		if(hdsContactName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsContactName").in(hdsContactName));
		}
		if(hdsIsForProfitFlag != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsIsForProfitFlag").in(hdsIsForProfitFlag));
		}
		if(sourceStatus != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("sourceStatus").in(sourceStatus));
		}
		if(hdsParentIpcId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsParentIpcId").in(hdsParentIpcId));
		}
		if(busIpcId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busIpcId").in(busIpcId));
		}
		if(busCpnId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busCpnId").in(busCpnId));
		}
		if(busName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busName").in(busName));
		}
		if(busLegalName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busLegalName").in(busLegalName));
		}
		if(busPayeeNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busPayeeNum").in(busPayeeNum));
		}
		if(busOwnerName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busOwnerName").in(busOwnerName));
		}
		if(busOwnerType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busOwnerType").in(busOwnerType));
		}
		if(busOwnerTypeOther != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busOwnerTypeOther").in(busOwnerTypeOther));
		}
		if(facBuildingName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("facBuildingName").in(facBuildingName));
		}
		if(facHdsDetailAddInfo != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("facHdsDetailAddInfo").in(facHdsDetailAddInfo));
		}
		if(physAddr1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr1").in(physAddr1));
		}
		if(physAddr2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr2").in(physAddr2));
		}
		if(physAddr3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr3").in(physAddr3));
		}
		if(physAddr4 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr4").in(physAddr4));
		}
		if(physCity != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physCity").in(physCity));
		}
		if(physProv != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physProv").in(physProv));
		}
		if(physPCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physPCode").in(physPCode));
		}
		if(physCountry != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physCountry").in(physCountry));
		}
		if(physAddrIsPrivate != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddrIsPrivate").in(physAddrIsPrivate));
		}
		if(mailAddr1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr1").in(mailAddr1));
		}
		if(mailAddr2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr2").in(mailAddr2));
		}
		if(mailAddr3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr3").in(mailAddr3));
		}
		if(mailAddr4 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr4").in(mailAddr4));
		}
		if(mailCity != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailCity").in(mailCity));
		}
		if(mailBc != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailBc").in(mailBc));
		}
		if(mailPcode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailPcode").in(mailPcode));
		}
		if(mailCountry != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailCountry").in(mailCountry));
		}
		if(mailAddrIsPriv != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddrIsPriv").in(mailAddrIsPriv));
		}
		
		return combinedSpecification;
	}
	
	private static Specification<ProcessData> hasDatWithMessages(Long controlId){
		return (root, query, builder) -> {
			//Join<ProcessData,Message> joinMessage = root.join("messages");
			root.fetch("messages",JoinType.LEFT);
			//return builder.equal(joinMessage.get("controlTableId"), controlId);
			return builder.equal(root.get("controlTableId"), controlId);
		};
	}

	@Override
	public Specification<SourceData> buildSpecificationInSourceData(Long controlId) {
		return buildSpecificationInSourceData(controlId,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
	}

	@Override
	public Specification<SourceData> buildSpecificationInSourceData(Long controlId, List<String> ids, List<String> doNotLoad, List<String> stakeholder, 
			List<String> hdsLpcId, List<String> hdsCpnId, List<String> hdsProviderId1, List<String> hdsProviderId2, List<String> hdsProviderId3, 
			List<String> hdsProviderIdType1, List<String> hdsProviderIdType2, List<String> hdsProviderIdType3, List<String> hdsHibcFacId, 
			List<String> hdsType, List<String> hdsName, List<String> hdsNameAlias, List<String> hdsPrefNameFlag, List<String> hdsEmail, 
			List<String> hdsWebsite, List<String> hdsBusTelAreaCode, List<String> hdsBusTelNum, List<String> hdsTelExt, List<String> hdsCellAreaCode,
			List<String> hdsCellNum, List<String> hdsFaxAreaCode, List<String> hdsFaxNum, List<String> hdsServiceDelType, List<String> pcnCLinicType,
			List<String> pcnPciFlag, List<String> hdsHoursOfOp, List<String> hdsContactName, List<String> hdsIsForProfitFlag, List<String> sourceStatus,
			List<String> hdsParentIpcId, List<String> busIpcId, List<String> busCpnId, List<String> busName, List<String> busLegalName, 
			List<String> busPayeeNum, List<String> busOwnerName, List<String> busOwnerType, List<String> busOwnerTypeOther, 
			List<String> facBuildingName, List<String> facHdsDetailAddInfo, List<String> physAddr1, List<String> physAddr2, List<String> physAddr3,
			List<String> physAddr4, List<String> physCity, List<String> physProv, List<String> physPCode, List<String> physCountry, 
			List<String> physAddrIsPrivate, List<String> mailAddr1, List<String> mailAddr2, List<String> mailAddr3, List<String> mailAddr4, 
			List<String> mailCity, List<String> mailBc, List<String> mailPcode, List<String> mailCountry, List<String> mailAddrIsPriv) {
			
		Specification<SourceData> combinedSpecification = Specification.where((root, query, builder) -> 
				builder.equal(root.get("controlTableId"), controlId));

		if(ids != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("id").in(ids));
		}
		if(doNotLoad != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("doNotLoad").in(doNotLoad));
		}
		if(stakeholder != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("stakeholder").in(stakeholder));
		}
		if(hdsLpcId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsLpcId").in(hdsLpcId));
		}
		if(hdsCpnId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsCpnId").in(hdsCpnId));
		}
		if(hdsProviderId1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderId1").in(hdsProviderId1));
		}
		if(hdsProviderId2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderId2").in(hdsProviderId2));
		}
		if(hdsProviderId3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderId3").in(hdsProviderId3));
		}
		if(hdsProviderIdType1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderIdType1").in(hdsProviderIdType1));
		}
		if(hdsProviderIdType2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderIdType2").in(hdsProviderIdType2));
		}
		if(hdsProviderIdType3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsProviderIdType3").in(hdsProviderIdType3));
		}
		if(hdsHibcFacId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsHibcFacId").in(hdsHibcFacId));
		}
		if(hdsType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsType").in(hdsType));
		}
		if(hdsName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsName").in(hdsName));
		}
		if(hdsNameAlias != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsNameAlias").in(hdsNameAlias));
		}
		if(hdsPrefNameFlag != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsPrefNameFlag").in(hdsPrefNameFlag));
		}
		if(hdsEmail != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsEmail").in(hdsEmail));
		}
		if(hdsWebsite != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsWebsite").in(hdsWebsite));
		}
		if(hdsBusTelAreaCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsBusTelAreaCode").in(hdsBusTelAreaCode));
		}
		if(hdsBusTelNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsBusTelNum").in(hdsBusTelNum));
		}
		if(hdsTelExt != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsTelExt").in(hdsTelExt));
		}
		if(hdsCellAreaCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsCellAreaCode").in(hdsCellAreaCode));
		}
		if(hdsCellNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsCellNum").in(hdsCellNum));
		}
		if(hdsFaxAreaCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsFaxAreaCode").in(hdsFaxAreaCode));
		}
		if(hdsFaxNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsFaxNum").in(hdsFaxNum));
		}
		if(hdsServiceDelType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsServiceDelType").in(hdsServiceDelType));
		}
		if(pcnCLinicType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("pcnCLinicType").in(pcnCLinicType));
		}
		if(pcnPciFlag != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("pcnPciFlag").in(pcnPciFlag));
		}
		if(hdsHoursOfOp != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsHoursOfOp").in(hdsHoursOfOp));
		}
		if(hdsContactName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsContactName").in(hdsContactName));
		}
		if(hdsIsForProfitFlag != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsIsForProfitFlag").in(hdsIsForProfitFlag));
		}
		if(sourceStatus != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("sourceStatus").in(sourceStatus));
		}
		if(hdsParentIpcId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("hdsParentIpcId").in(hdsParentIpcId));
		}
		if(busIpcId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busIpcId").in(busIpcId));
		}
		if(busCpnId != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busCpnId").in(busCpnId));
		}
		if(busName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busName").in(busName));
		}
		if(busLegalName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busLegalName").in(busLegalName));
		}
		if(busPayeeNum != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busPayeeNum").in(busPayeeNum));
		}
		if(busOwnerName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busOwnerName").in(busOwnerName));
		}
		if(busOwnerType != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busOwnerType").in(busOwnerType));
		}
		if(busOwnerTypeOther != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("busOwnerTypeOther").in(busOwnerTypeOther));
		}
		if(facBuildingName != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("facBuildingName").in(facBuildingName));
		}
		if(facHdsDetailAddInfo != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("facHdsDetailAddInfo").in(facHdsDetailAddInfo));
		}
		if(physAddr1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr1").in(physAddr1));
		}
		if(physAddr2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr2").in(physAddr2));
		}
		if(physAddr3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr3").in(physAddr3));
		}
		if(physAddr4 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddr4").in(physAddr4));
		}
		if(physCity != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physCity").in(physCity));
		}
		if(physProv != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physProv").in(physProv));
		}
		if(physPCode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physPCode").in(physPCode));
		}
		if(physCountry != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physCountry").in(physCountry));
		}
		if(physAddrIsPrivate != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("physAddrIsPrivate").in(physAddrIsPrivate));
		}
		if(mailAddr1 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr1").in(mailAddr1));
		}
		if(mailAddr2 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr2").in(mailAddr2));
		}
		if(mailAddr3 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr3").in(mailAddr3));
		}
		if(mailAddr4 != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddr4").in(mailAddr4));
		}
		if(mailCity != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailCity").in(mailCity));
		}
		if(mailBc != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailBc").in(mailBc));
		}
		if(mailPcode != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailPcode").in(mailPcode));
		}
		if(mailCountry != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailCountry").in(mailCountry));
		}
		if(mailAddrIsPriv != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("mailAddrIsPriv").in(mailAddrIsPriv));
		}
		
		return combinedSpecification;
	}
	
	@Override
	public Specification<Control> buildSpecificationInControl(List<String> ids, List<String> fileName, List<String> userIds, 
			List<String> fileExtractedDates, List<String> batchLabelNames, List<String> loadTypeFacilitys, List<String> loadTypeHds, 
			List<String> loadTypeBusOrgs, List<String> loadTypeOFRelationships, List<String> loadTypeOORelationships, 
			List<String> loadTypeIORelationships, List<String> loadTypeWlOrgXrefs, List<String> loadTypeWlPracIdentXrefs, List<String> processStartDates, 
			List<String> processEndDates, List<String> statusCodes, List<String> createdBy, List<String> createdAt, List<String> updatedBy, 
			List<String> updatedAt) {

		Specification<Control> combinedSpecification = null;

		if(ids != null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
					root.get("id").in(ids));
		}
		if(fileName != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("fileName").in(fileName));
		} else if (fileName != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("fileName").in(fileName));
		}
		if(userIds != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("userIds").in(userIds));
		} else if (userIds != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("userId").in(userIds));
		}
		if(fileExtractedDates != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("fileExtractedDate").in(fileExtractedDates));
		} else if (fileExtractedDates != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("fileExtractedDate").in(fileExtractedDates));
		}
		if(batchLabelNames != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("batchLabelName").in(batchLabelNames));
		} else if (batchLabelNames != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("batchLabelName").in(batchLabelNames));
		}
		if(loadTypeFacilitys != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeFacility").in(loadTypeFacilitys));
		} else if (loadTypeFacilitys != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeFacility").in(loadTypeFacilitys));
		}
		if(loadTypeHds != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeHd").in(loadTypeHds));
		} else if (loadTypeHds != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeHd").in(loadTypeHds));
		}
		if(loadTypeBusOrgs != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeBusOrg").in(loadTypeBusOrgs));
		} else if (loadTypeBusOrgs != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeBusOrg").in(loadTypeBusOrgs));
		}
		if(loadTypeOFRelationships != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeOFRelationship").in(loadTypeOFRelationships));
		} else if (loadTypeOFRelationships != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeOFRelationship").in(loadTypeOFRelationships));
		}
		if(loadTypeOORelationships != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeOORelationship").in(loadTypeOORelationships));
		} else if (loadTypeOORelationships != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeOORelationship").in(loadTypeOORelationships));
		}
		if(loadTypeIORelationships != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeIORelationship").in(loadTypeIORelationships));
		} else if (loadTypeIORelationships != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeIORelationship").in(loadTypeIORelationships));
		}
		if(loadTypeWlOrgXrefs != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeWlOrgXref").in(loadTypeWlOrgXrefs));
		} else if (loadTypeWlOrgXrefs != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeWlOrgXref").in(loadTypeWlOrgXrefs));
		}
		if(loadTypeWlPracIdentXrefs != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("loadTypeWlPracIdentXref").in(loadTypeWlPracIdentXrefs));
		} else if (loadTypeWlPracIdentXrefs != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("loadTypeWlPracIdentXref").in(loadTypeWlPracIdentXrefs));
		}
		if(processStartDates != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("processStartDate").in(processStartDates));
		} else if (processStartDates != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("processStartDate").in(processStartDates));
		}
		if(processEndDates != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("processEndDate").in(processEndDates));
		} else if (processEndDates != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("processEndDate").in(processEndDates));
		}
		if(statusCodes != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("statusCode").in(statusCodes));
		} else if (statusCodes != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("statusCode").in(statusCodes));
		}
		if(createdBy != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("createdBy").in(createdBy));
		} else if (createdBy != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("createdBy").in(createdBy));
		}
		if(createdAt != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("createdAt").in(createdAt));
		} else if (createdAt != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("createdAt").in(createdAt));
		}
		if(updatedBy != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("updatedBy").in(updatedBy));
		} else if (updatedBy != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("updatedBy").in(updatedBy));
		}
		if(updatedAt != null && combinedSpecification != null) {
			combinedSpecification = combinedSpecification.and((root, query, builder) -> 
					root.get("updatedAt").in(updatedAt));
		} else if (updatedAt != null && combinedSpecification == null) {
			combinedSpecification = Specification.where((root, query, builder) -> 
			root.get("updatedAt").in(updatedAt));
		}

		return combinedSpecification;
	}
}