package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.SourceData;

@Repository
public interface SourceDataRepository extends CrudRepository<SourceData, Long> {
	public List<SourceData> getAllSourceDataByControlTableId(Long controlTableId);
	public List<SourceData> findAll(Specification<SourceData> spec, Pageable pageable);
	
	@Query(value="Select distinct sd.id FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctId(Long controlTableId);
	@Query(value="Select distinct sd.controlTableId FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctControlId(Long controlTableId);
	@Query(value="Select distinct sd.doNotLoadFlag FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctDoNotLoad(Long controlTableId);
	@Query(value="Select distinct sd.stakeholder FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctStakeholder(Long controlTableId);
	@Query(value="Select distinct sd.hdsIpcId FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsIpcId(Long controlTableId);
	@Query(value="Select distinct sd.hdsCpnId FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsCpnId(Long controlTableId);
	@Query(value="Select distinct sd.hdsProviderIdentifier1 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifier1(Long controlTableId);
	@Query(value="Select distinct sd.hdsProviderIdentifier2 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifier2(Long controlTableId);
	@Query(value="Select distinct sd.hdsProviderIdentifier3 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifier3(Long controlTableId);
	@Query(value="Select distinct sd.hdsProviderIdentifierType1 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifierType1(Long controlTableId);
	@Query(value="Select distinct sd.hdsProviderIdentifierType2 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifierType2(Long controlTableId);
	@Query(value="Select distinct sd.hdsProviderIdentifierType3 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifierType3(Long controlTableId);
	@Query(value="Select distinct sd.hdsMspFacilityNumber FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsHibcFacilityId(Long controlTableId);
	@Query(value="Select distinct sd.hdsType FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsType(Long controlTableId);
	@Query(value="Select distinct sd.hdsName FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsName(Long controlTableId);
	//@Query(value="Select distinct sd.hds_name_alias FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctHdsNameAlias(Long controlTableId);
	@Query(value="Select distinct sd.hdsPreferredNameFlag FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsPreferredNameFlag(Long controlTableId);
	@Query(value="Select distinct sd.hdsEmail FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsEmail(Long controlTableId);
	@Query(value="Select distinct sd.hdsWebsite FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsWebsite(Long controlTableId);
	@Query(value="Select distinct sd.hdsBusTelAreaCode FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsBusTelAreaCode(Long controlTableId);
	@Query(value="Select distinct sd.hdsBusTelNumber FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsBusTelNumber(Long controlTableId);
	@Query(value="Select distinct sd.hdsTelExtension FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsTelExtension(Long controlTableId);
	@Query(value="Select distinct sd.hdsCellAreaCode FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsCellAreaCode(Long controlTableId);
	@Query(value="Select distinct sd.hdsCellNumber FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsCellNumber(Long controlTableId);
	@Query(value="Select distinct sd.hdsFaxAreaCode FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsFaxAreaCode(Long controlTableId);
	@Query(value="Select distinct sd.hdsFaxNumber FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsFaxNumber(Long controlTableId);
	/*@Query(value="Select distinct sd.HDS_SERVICE_DELIVERY_TYPE FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsServiceDeliveryType(Long controlTableId);
	@Query(value="Select distinct sd.PCN_CLINIC_TYPE FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPcnClinicType(Long controlTableId);
	@Query(value="Select distinct sd.PCN_PCI_FLAG FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPcnPciFlag(Long controlTableId);
	@Query(value="Select distinct sd.HDS_HOURS_OF_OPERATION FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsHoursOfOperation(Long controlTableId);
	@Query(value="Select distinct sd.HDS_CONTACT_NAME FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsContactName(Long controlTableId);
	@Query(value="Select distinct sd.HDS_IS_FOR_PROFIT_FLAG FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsIsForProfitFlag(Long controlTableId);*/
	@Query(value="Select distinct sd.sourceStatus FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctSourceStatus(Long controlTableId);
	/*@Query(value="Select distinct sd.HDS_PARENT_IPC_ID FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsParentIpcId(Long controlTableId);
	@Query(value="Select distinct sd.BUS_IPC_ID FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusIpcId(Long controlTableId);
	@Query(value="Select distinct sd.BUS_CPN_ID FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusCpnId(Long controlTableId);
	@Query(value="Select distinct sd.BUS_NAME FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusName(Long controlTableId);
	@Query(value="Select distinct sd.BUS_LEGAL_NAME FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusLegalName(Long controlTableId);
	@Query(value="Select distinct sd.BUS_PAYEE_NUMBER FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusPayeeNumber(Long controlTableId);
	@Query(value="Select distinct sd.BUS_OWNER_NAME FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusOwnerName(Long controlTableId);
	@Query(value="Select distinct sd.BUS_OWNER_TYPE FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusOwnerType(Long controlTableId);
	@Query(value="Select distinct sd.BUS_OWNER_TYPE_OTHER FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusOwnerTypeOther(Long controlTableId);*/
	@Query(value="Select distinct sd.facBuildingName FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctFacBuildingName(Long controlTableId);
	//@Query(value="Select distinct sd.FACILITY_HDS_DETAILS_ADDITIONAL_INFO FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctFacilityHdsDetailsAdditionalInfo(Long controlTableId);
	@Query(value="Select distinct sd.physicalAddr1 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr1(Long controlTableId);
	@Query(value="Select distinct sd.physicalAddr2 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr2(Long controlTableId);
	@Query(value="Select distinct sd.physicalAddr3 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr3(Long controlTableId);
	@Query(value="Select distinct sd.physicalAddr4 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr4(Long controlTableId);
	@Query(value="Select distinct sd.physicalCity FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalCity(Long controlTableId);
	@Query(value="Select distinct sd.physicalProvince FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalProvince(Long controlTableId);
	@Query(value="Select distinct sd.physicalPcode FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalPcode(Long controlTableId);
	@Query(value="Select distinct sd.physicalCountry FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalCountry(Long controlTableId);
	//@Query(value="Select distinct sd.PHYS_ADDR_IS_PRIVATE FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctPhysAddrIsPrivate(Long controlTableId);
	@Query(value="Select distinct sd.mailAddr1 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr1(Long controlTableId);
	@Query(value="Select distinct sd.mailAddr2 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr2(Long controlTableId);
	@Query(value="Select distinct sd.mailAddr3 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr3(Long controlTableId);
	@Query(value="Select distinct sd.mailAddr4 FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr4(Long controlTableId);
	@Query(value="Select distinct sd.mailCity FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailCity(Long controlTableId);
	@Query(value="Select distinct sd.mailBc FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailBc(Long controlTableId);
	@Query(value="Select distinct sd.mailPcode FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailPcode(Long controlTableId);
	@Query(value="Select distinct sd.mailCountry FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailCountry(Long controlTableId);
	//@Query(value="Select distinct sd.MAIL_ADDR_IS_PRIVATE FROM SourceData sd WHERE sd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctMailAddrIsPrivate(Long controlTableId);

	//List<SourceData> findAll();
	//findAll(Specification<SourceData> spec, Pageable pageable);
	List<SourceData> findAll(Pageable pageable);
}
