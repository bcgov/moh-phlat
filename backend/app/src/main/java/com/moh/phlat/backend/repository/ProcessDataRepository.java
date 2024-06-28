package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.ProcessData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ProcessDataRepository extends JpaRepository<ProcessData, Long> {
    List<ProcessData> getAllProcessDataByControlTableId(Long controlTableId);

    List<ProcessData> findByControlTableIdAndRowstatusCode(Long controlTableId, String reqRowStatusCode);

    @Query("SELECT md.messageType, md.messageCode, md.messageDesc,count(md) from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId group by md.messageType, md.messageCode, md.messageDesc")
    List<Object[]> getProcessDataWithMessageCodeCount(Long controlTableId);

	long countAllByControlTableIdAndRowstatusCode(Long controlTableId, String string);

	long countByControlTableId(Long controlTableId);   
	
	public List<ProcessData> findAll(Specification<ProcessData> spec);

	@Query(value="Select distinct pd.id FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctId(Long controlTableId);
	@Query(value="Select distinct pd.controlTableId FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctControlId(Long controlTableId);
	@Query(value="Select distinct pd.doNotLoadFlag FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctDoNotLoad(Long controlTableId);
	@Query(value="Select distinct pd.stakeholder FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctStakeholder(Long controlTableId);
	@Query(value="Select distinct pd.hdsIpcId FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsIpcId(Long controlTableId);
	@Query(value="Select distinct pd.hdsCpnId FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsCpnId(Long controlTableId);
	@Query(value="Select distinct pd.hdsProviderIdentifier1 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifier1(Long controlTableId);
	@Query(value="Select distinct pd.hdsProviderIdentifier2 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifier2(Long controlTableId);
	@Query(value="Select distinct pd.hdsProviderIdentifier3 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifier3(Long controlTableId);
	@Query(value="Select distinct pd.hdsProviderIdentifierType1 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifierType1(Long controlTableId);
	@Query(value="Select distinct pd.hdsProviderIdentifierType2 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifierType2(Long controlTableId);
	@Query(value="Select distinct pd.hdsProviderIdentifierType3 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsProviderIdentifierType3(Long controlTableId);
	@Query(value="Select distinct pd.hdsMspFacilityNumber FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsHibcFacilityId(Long controlTableId);
	@Query(value="Select distinct pd.hdsType FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsType(Long controlTableId);
	@Query(value="Select distinct pd.hdsName FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsName(Long controlTableId);
	//@Query(value="Select distinct pd.hds_name_alias FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctHdsNameAlias(Long controlTableId);
	@Query(value="Select distinct pd.hdsPreferredNameFlag FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsPreferredNameFlag(Long controlTableId);
	@Query(value="Select distinct pd.hdsEmail FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsEmail(Long controlTableId);
	@Query(value="Select distinct pd.hdsWebsite FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsWebsite(Long controlTableId);
	@Query(value="Select distinct pd.hdsBusTelAreaCode FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsBusTelAreaCode(Long controlTableId);
	@Query(value="Select distinct pd.hdsBusTelNumber FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsBusTelNumber(Long controlTableId);
	@Query(value="Select distinct pd.hdsTelExtension FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsTelExtension(Long controlTableId);
	@Query(value="Select distinct pd.hdsCellAreaCode FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsCellAreaCode(Long controlTableId);
	@Query(value="Select distinct pd.hdsCellNumber FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsCellNumber(Long controlTableId);
	@Query(value="Select distinct pd.hdsFaxAreaCode FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsFaxAreaCode(Long controlTableId);
	@Query(value="Select distinct pd.hdsFaxNumber FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsFaxNumber(Long controlTableId);
	@Query(value="Select distinct pd.pcnServiceDeliveryType FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsServiceDeliveryType(Long controlTableId);
	@Query(value="Select distinct pd.pcnClinicType FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPcnClinicType(Long controlTableId);
	@Query(value="Select distinct pd.pcnPciFlag FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPcnPciFlag(Long controlTableId);
	/*@Query(value="Select distinct pd.HDS_HOURS_OF_OPERATION FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsHoursOfOperation(Long controlTableId);
	@Query(value="Select distinct pd.HDS_CONTACT_NAME FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsContactName(Long controlTableId);
	@Query(value="Select distinct pd.HDS_IS_FOR_PROFIT_FLAG FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsIsForProfitFlag(Long controlTableId);*/
	@Query(value="Select distinct pd.sourceStatus FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctSourceStatus(Long controlTableId);
	/*@Query(value="Select distinct pd.HDS_PARENT_IPC_ID FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctHdsParentIpcId(Long controlTableId);
	@Query(value="Select distinct pd.BUS_IPC_ID FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusIpcId(Long controlTableId);
	@Query(value="Select distinct pd.BUS_CPN_ID FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusCpnId(Long controlTableId);
	@Query(value="Select distinct pd.BUS_NAME FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusName(Long controlTableId);
	@Query(value="Select distinct pd.BUS_LEGAL_NAME FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusLegalName(Long controlTableId);
	@Query(value="Select distinct pd.BUS_PAYEE_NUMBER FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusPayeeNumber(Long controlTableId);
	@Query(value="Select distinct pd.BUS_OWNER_NAME FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusOwnerName(Long controlTableId);
	@Query(value="Select distinct pd.BUS_OWNER_TYPE FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusOwnerType(Long controlTableId);
	@Query(value="Select distinct pd.BUS_OWNER_TYPE_OTHER FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctBusOwnerTypeOther(Long controlTableId);*/
	@Query(value="Select distinct pd.facBuildingName FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctFacBuildingName(Long controlTableId);
	//@Query(value="Select distinct pd.FACILITY_HDS_DETAILS_ADDITIONAL_INFO FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctFacilityHdsDetailsAdditionalInfo(Long controlTableId);
	@Query(value="Select distinct pd.physicalAddr1 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr1(Long controlTableId);
	@Query(value="Select distinct pd.physicalAddr2 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr2(Long controlTableId);
	@Query(value="Select distinct pd.physicalAddr3 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr3(Long controlTableId);
	@Query(value="Select distinct pd.physicalAddr4 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalAddr4(Long controlTableId);
	@Query(value="Select distinct pd.physicalCity FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalCity(Long controlTableId);
	@Query(value="Select distinct pd.physicalProvince FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalProvince(Long controlTableId);
	@Query(value="Select distinct pd.physicalPcode FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalPcode(Long controlTableId);
	@Query(value="Select distinct pd.physicalCountry FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctPhysicalCountry(Long controlTableId);
	//@Query(value="Select distinct pd.PHYS_ADDR_IS_PRIVATE FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctPhysAddrIsPrivate(Long controlTableId);
	@Query(value="Select distinct pd.mailAddr1 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr1(Long controlTableId);
	@Query(value="Select distinct pd.mailAddr2 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr2(Long controlTableId);
	@Query(value="Select distinct pd.mailAddr3 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr3(Long controlTableId);
	@Query(value="Select distinct pd.mailAddr4 FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailAddr4(Long controlTableId);
	@Query(value="Select distinct pd.mailCity FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailCity(Long controlTableId);
	@Query(value="Select distinct pd.mailBc FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailBc(Long controlTableId);
	@Query(value="Select distinct pd.mailPcode FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailPcode(Long controlTableId);
	@Query(value="Select distinct pd.mailCountry FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	public List<String> findAllDistinctMailCountry(Long controlTableId);
	//@Query(value="Select distinct pd.MAIL_ADDR_IS_PRIVATE FROM ProcessData pd WHERE pd.controlTableId = :controlTableId")
	//public List<String> findAllDistinctMailAddrIsPrivate(Long controlTableId);

	
}