package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.SourceData;

@Repository
public interface SourceDataRepository extends CrudRepository<SourceData, Long> {
	public List<SourceData> getAllSourceDataByControlTableId(Long controlTableId);
	public List<SourceData> findAll(Specification<SourceData> spec);

	@Query(value="Select distinct id FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctId(Long controlTableId);
	@Query(value="Select distinct control_id FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctControlId(Long controlTableId);
	@Query(value="Select distinct do_not_load FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctDoNotLoad(Long controlTableId);
	@Query(value="Select distinct stakeholder FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctStakeholder(Long controlTableId);
	@Query(value="Select distinct hds_ipc_id FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsIpcId(Long controlTableId);
	@Query(value="Select distinct hds_cpn_id FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsCpnId(Long controlTableId);
	@Query(value="Select distinct hds_provider_identifier1 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifier1(Long controlTableId);
	@Query(value="Select distinct hds_provider_identifier2 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifier2(Long controlTableId);
	@Query(value="Select distinct hds_provider_identifier3 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifier3(Long controlTableId);
	@Query(value="Select distinct hds_provider_identifier_type1 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifierType1(Long controlTableId);
	@Query(value="Select distinct hds_provider_identifier_type2 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifierType2(Long controlTableId);
	@Query(value="Select distinct hds_provider_identifier_type3 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifierType3(Long controlTableId);
	@Query(value="Select distinct hds_hibc_facility_id FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsHibcFacilityId(Long controlTableId);
	@Query(value="Select distinct hds_type FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsType(Long controlTableId);
	@Query(value="Select distinct hds_name FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsName(Long controlTableId);
	@Query(value="Select distinct hds_name_alias FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsNameAlias(Long controlTableId);
	@Query(value="Select distinct hds_preferred_name_flag FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsPreferredNameFlag(Long controlTableId);
	@Query(value="Select distinct hds_email FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsEmail(Long controlTableId);
	@Query(value="Select distinct hds_website FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsWebsite(Long controlTableId);
	@Query(value="Select distinct HDS_BUS_TEL_AREA_CODE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsBusTelAreaCode(Long controlTableId);
	@Query(value="Select distinct HDS_BUS_TEL_NUMBER FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsBusTelNumber(Long controlTableId);
	@Query(value="Select distinct HDS_TEL_EXTENSION FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsTelExtension(Long controlTableId);
	@Query(value="Select distinct HDS_CELL_AREA_CODE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsCellAreaCode(Long controlTableId);
	@Query(value="Select distinct HDS_CELL_NUMBER FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsCellNumber(Long controlTableId);
	@Query(value="Select distinct HDS_FAX_AREA_CODE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsFaxAreaCode(Long controlTableId);
	@Query(value="Select distinct HDS_FAX_NUMBER FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsFaxNumber(Long controlTableId);
	@Query(value="Select distinct HDS_SERVICE_DELIVERY_TYPE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsServiceDeliveryType(Long controlTableId);
	@Query(value="Select distinct PCN_CLINIC_TYPE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPcnClinicType(Long controlTableId);
	@Query(value="Select distinct PCN_PCI_FLAG FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPcnPciFlag(Long controlTableId);
	@Query(value="Select distinct HDS_HOURS_OF_OPERATION FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsHoursOfOperation(Long controlTableId);
	@Query(value="Select distinct HDS_CONTACT_NAME FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsContactName(Long controlTableId);
	@Query(value="Select distinct HDS_IS_FOR_PROFIT_FLAG FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsIsForProfitFlag(Long controlTableId);
	@Query(value="Select distinct SOURCE_STATUS FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctSourceStatus(Long controlTableId);
	@Query(value="Select distinct HDS_PARENT_IPC_ID FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctHdsParentIpcId(Long controlTableId);
	@Query(value="Select distinct BUS_IPC_ID FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusIpcId(Long controlTableId);
	@Query(value="Select distinct BUS_CPN_ID FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusCpnId(Long controlTableId);
	@Query(value="Select distinct BUS_NAME FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusName(Long controlTableId);
	@Query(value="Select distinct BUS_LEGAL_NAME FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusLegalName(Long controlTableId);
	@Query(value="Select distinct BUS_PAYEE_NUMBER FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusPayeeNumber(Long controlTableId);
	@Query(value="Select distinct BUS_OWNER_NAME FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusOwnerName(Long controlTableId);
	@Query(value="Select distinct BUS_OWNER_TYPE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusOwnerType(Long controlTableId);
	@Query(value="Select distinct BUS_OWNER_TYPE_OTHER FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctBusOwnerTypeOther(Long controlTableId);
	@Query(value="Select distinct FAC_BUILDING_NAME FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctFacBuildingName(Long controlTableId);
	@Query(value="Select distinct FACILITY_HDS_DETAILS_ADDITIONAL_INFO FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctFacilityHdsDetailsAdditionalInfo(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_ADDR1 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr1(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_ADDR2 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr2(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_ADDR3 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr3(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_ADDR4 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr4(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_CITY FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalCity(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_PROVINCE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalProvince(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_PCODE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalPcode(Long controlTableId);
	@Query(value="Select distinct PHYSICAL_COUNTRY FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysicalCountry(Long controlTableId);
	@Query(value="Select distinct PHYS_ADDR_IS_PRIVATE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctPhysAddrIsPrivate(Long controlTableId);
	@Query(value="Select distinct MAIL_ADDR1 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailAddr1(Long controlTableId);
	@Query(value="Select distinct MAIL_ADDR2 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailAddr2(Long controlTableId);
	@Query(value="Select distinct MAIL_ADDR3 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailAddr3(Long controlTableId);
	@Query(value="Select distinct MAIL_ADDR4 FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailAddr4(Long controlTableId);
	@Query(value="Select distinct MAIL_CITY FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailCity(Long controlTableId);
	@Query(value="Select distinct MAIL_BC FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailBc(Long controlTableId);
	@Query(value="Select distinct MAIL_PCODE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailPcode(Long controlTableId);
	@Query(value="Select distinct MAIL_COUNTRY FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailCountry(Long controlTableId);
	@Query(value="Select distinct MAIL_ADDR_IS_PRIVATE FROM source_data WHERE control_id = :controlTableId",nativeQuery=true)
	public List<String> findAllDistinctMailAddrIsPrivate(Long controlTableId);
	
	public static Specification<SourceData> buildSpecificationIn(Long controlId, List<String> ids, List<String> doNotLoad, List<String> stakeholder, 
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
}
