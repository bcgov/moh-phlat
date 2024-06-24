package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.ProcessData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

	@Query(value="Select distinct id FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctId();
	@Query(value="Select distinct control_id FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctControlId();
	@Query(value="Select distinct do_not_load FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctDoNotLoad();
	@Query(value="Select distinct stakeholder FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctStakeholder();
	@Query(value="Select distinct hds_ipc_id FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsIpcId();
	@Query(value="Select distinct hds_cpn_id FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsCpnId();
	@Query(value="Select distinct hds_provider_identifier1 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifier1();
	@Query(value="Select distinct hds_provider_identifier2 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifier2();
	@Query(value="Select distinct hds_provider_identifier3 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifier3();
	@Query(value="Select distinct hds_provider_identifier_type1 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifierType1();
	@Query(value="Select distinct hds_provider_identifier_type2 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifierType2();
	@Query(value="Select distinct hds_provider_identifier_type3 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsProviderIdentifierType3();
	@Query(value="Select distinct hds_hibc_facility_id FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsHibcFacilityId();
	@Query(value="Select distinct hds_type FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsType();
	@Query(value="Select distinct hds_name FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsName();
	@Query(value="Select distinct hds_name_alias FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsNameAlias();
	@Query(value="Select distinct hds_preferred_name_flag FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsPreferredNameFlag();
	@Query(value="Select distinct hds_email FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsEmail();
	@Query(value="Select distinct hds_website FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsWebsite();
	@Query(value="Select distinct HDS_BUS_TEL_AREA_CODE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsBusTelAreaCode();
	@Query(value="Select distinct HDS_BUS_TEL_NUMBER FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsBusTelNumber();
	@Query(value="Select distinct HDS_TEL_EXTENSION FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsTelExtension();
	@Query(value="Select distinct HDS_CELL_AREA_CODE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsCellAreaCode();
	@Query(value="Select distinct HDS_CELL_NUMBER FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsCellNumber();
	@Query(value="Select distinct HDS_FAX_AREA_CODE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsFaxAreaCode();
	@Query(value="Select distinct HDS_FAX_NUMBER FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsFaxNumber();
	@Query(value="Select distinct HDS_SERVICE_DELIVERY_TYPE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsServiceDeliveryType();
	@Query(value="Select distinct PCN_CLINIC_TYPE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPcnClinicType();
	@Query(value="Select distinct PCN_PCI_FLAG FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPcnPciFlag();
	@Query(value="Select distinct HDS_HOURS_OF_OPERATION FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsHoursOfOperation();
	@Query(value="Select distinct HDS_CONTACT_NAME FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsContactName();
	@Query(value="Select distinct HDS_IS_FOR_PROFIT_FLAG FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsIsForProfitFlag();
	@Query(value="Select distinct SOURCE_STATUS FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctSourceStatus();
	@Query(value="Select distinct HDS_PARENT_IPC_ID FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctHdsParentIpcId();
	@Query(value="Select distinct BUS_IPC_ID FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusIpcId();
	@Query(value="Select distinct BUS_CPN_ID FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusCpnId();
	@Query(value="Select distinct BUS_NAME FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusName();
	@Query(value="Select distinct BUS_LEGAL_NAME FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusLegalName();
	@Query(value="Select distinct BUS_PAYEE_NUMBER FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusPayeeNumber();
	@Query(value="Select distinct BUS_OWNER_NAME FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusOwnerName();
	@Query(value="Select distinct BUS_OWNER_TYPE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusOwnerType();
	@Query(value="Select distinct BUS_OWNER_TYPE_OTHER FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctBusOwnerTypeOther();
	@Query(value="Select distinct FAC_BUILDING_NAME FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctFacBuildingName();
	@Query(value="Select distinct FACILITY_HDS_DETAILS_ADDITIONAL_INFO FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctFacilityHdsDetailsAdditionalInfo();
	@Query(value="Select distinct PHYSICAL_ADDR1 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr1();
	@Query(value="Select distinct PHYSICAL_ADDR2 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr2();
	@Query(value="Select distinct PHYSICAL_ADDR3 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr3();
	@Query(value="Select distinct PHYSICAL_ADDR4 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalAddr4();
	@Query(value="Select distinct PHYSICAL_CITY FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalCity();
	@Query(value="Select distinct PHYSICAL_PROVINCE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalProvince();
	@Query(value="Select distinct PHYSICAL_PCODE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalPcode();
	@Query(value="Select distinct PHYSICAL_COUNTRY FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysicalCountry();
	@Query(value="Select distinct PHYS_ADDR_IS_PRIVATE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctPhysAddrIsPrivate();
	@Query(value="Select distinct MAIL_ADDR1 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailAddr1();
	@Query(value="Select distinct MAIL_ADDR2 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailAddr2();
	@Query(value="Select distinct MAIL_ADDR3 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailAddr3();
	@Query(value="Select distinct MAIL_ADDR4 FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailAddr4();
	@Query(value="Select distinct MAIL_CITY FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailCity();
	@Query(value="Select distinct MAIL_BC FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailBc();
	@Query(value="Select distinct MAIL_PCODE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailPcode();
	@Query(value="Select distinct MAIL_COUNTRY FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailCountry();
	@Query(value="Select distinct MAIL_ADDR_IS_PRIVATE FROM process_data",nativeQuery=true)
	public List<String> findAllDistinctMailAddrIsPrivate();

	
}