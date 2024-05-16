package com.moh.phlat.backend.model;



import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "source_data")
public class SourceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID")
    private Long id;
    
    @Column(name="control_id")
    private Long controlTableId;    
    
    
    @Column(name="do_not_load_flag")
    private String doNotLoadFlag;


    @Column(name="stakeholder")
    private String stakeholder;

    @Column(name="stakeholder_id")
    private String stakeholderId;
    
    @Column(name="hds_ipc_id")
    private String hdsIpcId;

    @Column(name="hds_cpn_id")
    private String hdsCpnId;
    
    @Column(name="hds_provider_identifier1")
    private String hdsProviderIdentifier1;

    @Column(name="hds_provider_identifier2")
    private String hdsProviderIdentifier2;
    
    @Column(name="hds_provider_identifier3")
    private String hdsProviderIdentifier3;

    @Column(name="hds_provider_identifier_type1")
    private String hdsProviderIdentifierType1;

    @Column(name="hds_provider_identifier_type2")
    private String hdsProviderIdentifierType2;
    
    @Column(name="hds_provider_identifier_type3")
    private String hdsProviderIdentifierType3;
    
    @Column(name="hds_msp_facility_number")
    private String hdsMspFacilityNumber;

    @Column(name="hds_type")
    private String hdsType;

    @Column(name="hds_sub_type")
    private String hdsSubType;

    @Column(name="hds_name")
    private String hdsName;

    @Column(name="hds_preferred_name_flag")
    private String hdsPreferredNameFlag;
    
    @Column(name="hds_email")
    private String hdsEmail;
    
    @Column(name="hds_website")
    private String hdsWebsite;
    
    @Column(name="hds_bus_tel_area_code")
    private String hdsBusTelAreaCode;
    
    @Column(name="hds_bus_tel_number")
    private String hdsBusTelNumber;

    @Column(name="hds_tel_extension")
    private String hdsTelExtension;
    
    @Column(name="hds_cell_area_code")
    private String hdsCellAreaCode;   
    
    @Column(name="hds_cell_number")
    private String hdsCellNumber;
    
    @Column(name="hds_fax_area_code")
    private String hdsFaxAreaCode;
      
    @Column(name="hds_fax_number")
    private String hdsFaxNumber;   
    
    @Column(name="pcn_service_delivery_type")
    private String pcnServiceDeliveryType;   
    
    @Column(name="pcn_clinic_type")
    private String pcnClinicType;  
    
    @Column(name="pcn_pci_flag")
    private String pcnPciFlag;  
    
    @Column(name="source_status")
    private String sourceStatus;  

    @Column(name="pcn_clinic_status")
    private String pcnClinicStatus;  

    @Column(name="hds_effective_start_date")
    private String hdsEffectiveStartDate;  

    @Column(name="hds_effective_end_date")
    private String hdsEffectiveEndDate;  

    @Column(name="unit_by_civic_address")
    private String unitByCivicAddress;  
    
    @Column(name="civic_address")
    private String civicAddress;  
    
    @Column(name="fac_reln_type")
    private String facRelnType;  
    
    @Column(name="fac_type_code")
    private String facTypeCode;  
    
    @Column(name="fac_address_unit")
    private String facAddressUnit;  
    
    @Column(name="fac_building_name")
    private String facBuildingName;
    
    @Column(name="fac_full_address")
    private String facFullAddress;
    
    @Column(name="fac_latitude")
    private String facLatitude;
    
    @Column(name="fac_longitude")
    private String facLongitude;
    
    @Column(name="fac_street_direction")
    private String facStreetDirection;
    
    @Column(name="street_direction_prefix")
    private String streetDirectionPrefix;
    
    @Column(name="street_type_prefix")
    private String streetTypePrefix;
    
    @Column(name="fac_civic_number")
    private String facCivicNumber;

    @Column(name="fac_street_name")
    private String facStreetName;
    
    @Column(name="fac_street_type")
    private String facStreetType;

    @Column(name="fac_locality_name")
    private String facLocalityName;

    @Column(name="fac_province_code")
    private String facProvinceCode;

    @Column(name="fac_site_id")
    private String facSiteId;

    @Column(name="fac_score")
    private String facScore;

    @Column(name="fac_match_precision")
    private String facMatchPrecision;

    @Column(name="fac_precisionPoints")
    private String facPrecisionPoints;
    
    @Column(name="fac_hsda_name")
    private String facHsdaName;

    @Column(name="fac_databc_results")
    private String facDatabcResults;

    @Column(name="fac_pcn_code")
    private String facPcnCode;

    @Column(name="fac_pcn_name")
    private String facPcnName;

    @Column(name="fac_chsa_status")
    private String facChsaStatus;

    @Column(name="fac_pcn_status")
    private String facPcnStatus;

    @Column(name="fac_chsa_code")
    private String facChsaCode;

    @Column(name="fac_chsa_name")
    private String facChsaName;

    @Column(name="fac_lha_name")
    private String facLhaName;

    @Column(name="fac_ha_name")
    private String facHaName;

    @Column(name="physical_addr1")
    private String physicalAddr1;  
    
    @Column(name="physical_addr2")
    private String physicalAddr2;  
    
    @Column(name="physical_addr3")
    private String physicalAddr3;  
    
    @Column(name="physical_addr4")
    private String physicalAddr4;  
     
    @Column(name="physical_city")
    private String physicalCity;  
    
    @Column(name="physical_province")
    private String physicalProvince;  

    @Column(name="physical_pcode")
    private String physicalPcode;  
    
    @Column(name="physical_country")
    private String physicalCountry;  
    
    @Column(name="physical_addr_prps_type_cd")
    private String physicalAddrPrpsTypeCd;  
    
    @Column(name="physical_address_validation_status")
    private String physicalAddressValidationStatus;  

    @Column(name="mail_addr1")
    private String mailAddr1;  
    
    @Column(name="mail_addr2")
    private String mailAddr2;  

    @Column(name="mail_addr3")
    private String mailAddr3;
    
    @Column(name="mail_addr4")
    private String mailAddr4;  
    
    @Column(name="mail_city")
    private String mailCity;  
    
    @Column(name="mail_bc")
    private String mailBc;
    
    @Column(name="mail_pcode")
    private String mailPcode;
    
    @Column(name="mail_country")
    private String mailCountry;  
    
    @Column(name="mail_adderss_validation_status")
    private String mailAddressValidationStatus;  

 	@Column(name="created_at")
    private Date createdAt;

	@Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_at")
    private Date updatedAt;

	@Column(name="updated_by")
    private String updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getControlTableId() {
		return controlTableId;
	}

	public void setControlTableId(Long controlTableId) {
		this.controlTableId = controlTableId;
	}

	public String getDoNotLoadFlag() {
		return doNotLoadFlag;
	}

	public void setDoNotLoadFlag(String doNotLoadFlag) {
		this.doNotLoadFlag = doNotLoadFlag;
	}

	public String getStakeholder() {
		return stakeholder;
	}

	public void setStakeholder(String stakeholder) {
		this.stakeholder = stakeholder;
	}

	public String getStakeholderId() {
		return stakeholderId;
	}

	public void setStakeholderId(String stakeholderId) {
		this.stakeholderId = stakeholderId;
	}

	public String getHdsIpcId() {
		return hdsIpcId;
	}

	public void setHdsIpcId(String hdsIpcId) {
		this.hdsIpcId = hdsIpcId;
	}

	public String getHdsCpnId() {
		return hdsCpnId;
	}

	public void setHdsCpnId(String hdsCpnId) {
		this.hdsCpnId = hdsCpnId;
	}

	public String getHdsProviderIdentifier1() {
		return hdsProviderIdentifier1;
	}

	public void setHdsProviderIdentifier1(String hdsProviderIdentifier1) {
		this.hdsProviderIdentifier1 = hdsProviderIdentifier1;
	}

	public String getHdsProviderIdentifier2() {
		return hdsProviderIdentifier2;
	}

	public void setHdsProviderIdentifier2(String hdsProviderIdentifier2) {
		this.hdsProviderIdentifier2 = hdsProviderIdentifier2;
	}

	public String getHdsProviderIdentifier3() {
		return hdsProviderIdentifier3;
	}

	public void setHdsProviderIdentifier3(String hdsProviderIdentifier3) {
		this.hdsProviderIdentifier3 = hdsProviderIdentifier3;
	}

	public String getHdsProviderIdentifierType1() {
		return hdsProviderIdentifierType1;
	}

	public void setHdsProviderIdentifierType1(String hdsProviderIdentifierType1) {
		this.hdsProviderIdentifierType1 = hdsProviderIdentifierType1;
	}

	public String getHdsProviderIdentifierType2() {
		return hdsProviderIdentifierType2;
	}

	public void setHdsProviderIdentifierType2(String hdsProviderIdentifierType2) {
		this.hdsProviderIdentifierType2 = hdsProviderIdentifierType2;
	}

	public String getHdsProviderIdentifierType3() {
		return hdsProviderIdentifierType3;
	}

	public void setHdsProviderIdentifierType3(String hdsProviderIdentifierType3) {
		this.hdsProviderIdentifierType3 = hdsProviderIdentifierType3;
	}

	public String getHdsMspFacilityNumber() {
		return hdsMspFacilityNumber;
	}

	public void setHdsMspFacilityNumber(String hdsMspFacilityNumber) {
		this.hdsMspFacilityNumber = hdsMspFacilityNumber;
	}

	public String getHdsType() {
		return hdsType;
	}

	public void setHdsType(String hdsType) {
		this.hdsType = hdsType;
	}

	public String getHdsSubType() {
		return hdsSubType;
	}

	public void setHdsSubType(String hdsSubType) {
		this.hdsSubType = hdsSubType;
	}

	public String getHdsName() {
		return hdsName;
	}

	public void setHdsName(String hdsName) {
		this.hdsName = hdsName;
	}

	public String getHdsPreferredNameFlag() {
		return hdsPreferredNameFlag;
	}

	public void setHdsPreferredNameFlag(String hdsPreferredNameFlag) {
		this.hdsPreferredNameFlag = hdsPreferredNameFlag;
	}

	public String getHdsEmail() {
		return hdsEmail;
	}

	public void setHdsEmail(String hdsEmail) {
		this.hdsEmail = hdsEmail;
	}

	public String getHdsWebsite() {
		return hdsWebsite;
	}

	public void setHdsWebsite(String hdsWebsite) {
		this.hdsWebsite = hdsWebsite;
	}

	public String getHdsBusTelAreaCode() {
		return hdsBusTelAreaCode;
	}

	public void setHdsBusTelAreaCode(String hdsBusTelAreaCode) {
		this.hdsBusTelAreaCode = hdsBusTelAreaCode;
	}

	public String getHdsBusTelNumber() {
		return hdsBusTelNumber;
	}

	public void setHdsBusTelNumber(String hdsBusTelNumber) {
		this.hdsBusTelNumber = hdsBusTelNumber;
	}

	public String getHdsTelExtension() {
		return hdsTelExtension;
	}

	public void setHdsTelExtension(String hdsTelExtension) {
		this.hdsTelExtension = hdsTelExtension;
	}

	public String getHdsCellAreaCode() {
		return hdsCellAreaCode;
	}

	public void setHdsCellAreaCode(String hdsCellAreaCode) {
		this.hdsCellAreaCode = hdsCellAreaCode;
	}

	public String getHdsCellNumber() {
		return hdsCellNumber;
	}

	public void setHdsCellNumber(String hdsCellNumber) {
		this.hdsCellNumber = hdsCellNumber;
	}

	public String getHdsFaxAreaCode() {
		return hdsFaxAreaCode;
	}

	public void setHdsFaxAreaCode(String hdsFaxAreaCode) {
		this.hdsFaxAreaCode = hdsFaxAreaCode;
	}

	public String getHdsFaxNumber() {
		return hdsFaxNumber;
	}

	public void setHdsFaxNumber(String hdsFaxNumber) {
		this.hdsFaxNumber = hdsFaxNumber;
	}

	public String getPcnServiceDeliveryType() {
		return pcnServiceDeliveryType;
	}

	public void setPcnServiceDeliveryType(String pcnServiceDeliveryType) {
		this.pcnServiceDeliveryType = pcnServiceDeliveryType;
	}

	public String getPcnClinicType() {
		return pcnClinicType;
	}

	public void setPcnClinicType(String pcnClinicType) {
		this.pcnClinicType = pcnClinicType;
	}

	public String getPcnPciFlag() {
		return pcnPciFlag;
	}

	public void setPcnPciFlag(String pcnPciFlag) {
		this.pcnPciFlag = pcnPciFlag;
	}

	public String getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(String sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

	public String getPcnClinicStatus() {
		return pcnClinicStatus;
	}

	public void setPcnClinicStatus(String pcnClinicStatus) {
		this.pcnClinicStatus = pcnClinicStatus;
	}

	public String getHdsEffectiveStartDate() {
		return hdsEffectiveStartDate;
	}

	public void setHdsEffectiveStartDate(String hdsEffectiveStartDate) {
		this.hdsEffectiveStartDate = hdsEffectiveStartDate;
	}

	public String getHdsEffectiveEndDate() {
		return hdsEffectiveEndDate;
	}

	public void setHdsEffectiveEndDate(String hdsEffectiveEndDate) {
		this.hdsEffectiveEndDate = hdsEffectiveEndDate;
	}

	public String getUnitByCivicAddress() {
		return unitByCivicAddress;
	}

	public void setUnitByCivicAddress(String unitByCivicAddress) {
		this.unitByCivicAddress = unitByCivicAddress;
	}

	public String getCivicAddress() {
		return civicAddress;
	}

	public void setCivicAddress(String civicAddress) {
		this.civicAddress = civicAddress;
	}

	public String getFacRelnType() {
		return facRelnType;
	}

	public void setFacRelnType(String facRelnType) {
		this.facRelnType = facRelnType;
	}

	public String getFacTypeCode() {
		return facTypeCode;
	}

	public void setFacTypeCode(String facTypeCode) {
		this.facTypeCode = facTypeCode;
	}

	public String getFacAddressUnit() {
		return facAddressUnit;
	}

	public void setFacAddressUnit(String facAddressUnit) {
		this.facAddressUnit = facAddressUnit;
	}

	public String getFacBuildingName() {
		return facBuildingName;
	}

	public void setFacBuildingName(String facBuildingName) {
		this.facBuildingName = facBuildingName;
	}

	public String getFacFullAddress() {
		return facFullAddress;
	}

	public void setFacFullAddress(String facFullAddress) {
		this.facFullAddress = facFullAddress;
	}

	public String getFacLatitude() {
		return facLatitude;
	}

	public void setFacLatitude(String facLatitude) {
		this.facLatitude = facLatitude;
	}

	public String getFacLongitude() {
		return facLongitude;
	}

	public void setFacLongitude(String facLongitude) {
		this.facLongitude = facLongitude;
	}

	public String getFacStreetDirection() {
		return facStreetDirection;
	}

	public void setFacStreetDirection(String facStreetDirection) {
		this.facStreetDirection = facStreetDirection;
	}

	public String getStreetDirectionPrefix() {
		return streetDirectionPrefix;
	}

	public void setStreetDirectionPrefix(String streetDirectionPrefix) {
		this.streetDirectionPrefix = streetDirectionPrefix;
	}

	public String getStreetTypePrefix() {
		return streetTypePrefix;
	}

	public void setStreetTypePrefix(String streetTypePrefix) {
		this.streetTypePrefix = streetTypePrefix;
	}

	public String getFacCivicNumber() {
		return facCivicNumber;
	}

	public void setFacCivicNumber(String facCivicNumber) {
		this.facCivicNumber = facCivicNumber;
	}

	public String getFacStreetName() {
		return facStreetName;
	}

	public void setFacStreetName(String facStreetName) {
		this.facStreetName = facStreetName;
	}

	public String getFacStreetType() {
		return facStreetType;
	}

	public void setFacStreetType(String facStreetType) {
		this.facStreetType = facStreetType;
	}

	public String getFacLocalityName() {
		return facLocalityName;
	}

	public void setFacLocalityName(String facLocalityName) {
		this.facLocalityName = facLocalityName;
	}

	public String getFacProvinceCode() {
		return facProvinceCode;
	}

	public void setFacProvinceCode(String facProvinceCode) {
		this.facProvinceCode = facProvinceCode;
	}

	public String getFacSiteId() {
		return facSiteId;
	}

	public void setFacSiteId(String facSiteId) {
		this.facSiteId = facSiteId;
	}

	public String getFacScore() {
		return facScore;
	}

	public void setFacScore(String facScore) {
		this.facScore = facScore;
	}

	public String getFacMatchPrecision() {
		return facMatchPrecision;
	}

	public void setFacMatchPrecision(String facMatchPrecision) {
		this.facMatchPrecision = facMatchPrecision;
	}

	public String getFacPrecisionPoints() {
		return facPrecisionPoints;
	}

	public void setFacPrecisionPoints(String facPrecisionPoints) {
		this.facPrecisionPoints = facPrecisionPoints;
	}

	public String getFacHsdaName() {
		return facHsdaName;
	}

	public void setFacHsdaName(String facHsdaName) {
		this.facHsdaName = facHsdaName;
	}

	public String getFacDatabcResults() {
		return facDatabcResults;
	}

	public void setFacDatabcResults(String facDatabcResults) {
		this.facDatabcResults = facDatabcResults;
	}

	public String getFacPcnCode() {
		return facPcnCode;
	}

	public void setFacPcnCode(String facPcnCode) {
		this.facPcnCode = facPcnCode;
	}

	public String getFacPcnName() {
		return facPcnName;
	}

	public void setFacPcnName(String facPcnName) {
		this.facPcnName = facPcnName;
	}

	public String getFacChsaStatus() {
		return facChsaStatus;
	}

	public void setFacChsaStatus(String facChsaStatus) {
		this.facChsaStatus = facChsaStatus;
	}

	public String getFacPcnStatus() {
		return facPcnStatus;
	}

	public void setFacPcnStatus(String facPcnStatus) {
		this.facPcnStatus = facPcnStatus;
	}

	public String getFacChsaCode() {
		return facChsaCode;
	}

	public void setFacChsaCode(String facChsaCode) {
		this.facChsaCode = facChsaCode;
	}

	public String getFacChsaName() {
		return facChsaName;
	}

	public void setFacChsaName(String facChsaName) {
		this.facChsaName = facChsaName;
	}

	public String getFacLhaName() {
		return facLhaName;
	}

	public void setFacLhaName(String facLhaName) {
		this.facLhaName = facLhaName;
	}

	public String getFacHaName() {
		return facHaName;
	}

	public void setFacHaName(String facHaName) {
		this.facHaName = facHaName;
	}

	public String getPhysicalAddr1() {
		return physicalAddr1;
	}

	public void setPhysicalAddr1(String physicalAddr1) {
		this.physicalAddr1 = physicalAddr1;
	}

	public String getPhysicalAddr2() {
		return physicalAddr2;
	}

	public void setPhysicalAddr2(String physicalAddr2) {
		this.physicalAddr2 = physicalAddr2;
	}

	public String getPhysicalAddr3() {
		return physicalAddr3;
	}

	public void setPhysicalAddr3(String physicalAddr3) {
		this.physicalAddr3 = physicalAddr3;
	}

	public String getPhysicalAddr4() {
		return physicalAddr4;
	}

	public void setPhysicalAddr4(String physicalAddr4) {
		this.physicalAddr4 = physicalAddr4;
	}

	public String getPhysicalCity() {
		return physicalCity;
	}

	public void setPhysicalCity(String physicalCity) {
		this.physicalCity = physicalCity;
	}

	public String getPhysicalProvince() {
		return physicalProvince;
	}

	public void setPhysicalProvince(String physicalProvince) {
		this.physicalProvince = physicalProvince;
	}

	public String getPhysicalPcode() {
		return physicalPcode;
	}

	public void setPhysicalPcode(String physicalPcode) {
		this.physicalPcode = physicalPcode;
	}

	public String getPhysicalCountry() {
		return physicalCountry;
	}

	public void setPhysicalCountry(String physicalCountry) {
		this.physicalCountry = physicalCountry;
	}

	public String getPhysicalAddrPrpsTypeCd() {
		return physicalAddrPrpsTypeCd;
	}

	public void setPhysicalAddrPrpsTypeCd(String physicalAddrPrpsTypeCd) {
		this.physicalAddrPrpsTypeCd = physicalAddrPrpsTypeCd;
	}

	public String getPhysicalAddressValidationStatus() {
		return physicalAddressValidationStatus;
	}

	public void setPhysicalAddressValidationStatus(String physicalAddressValidationStatus) {
		this.physicalAddressValidationStatus = physicalAddressValidationStatus;
	}

	public String getMailAddr1() {
		return mailAddr1;
	}

	public void setMailAddr1(String mailAddr1) {
		this.mailAddr1 = mailAddr1;
	}

	public String getMailAddr2() {
		return mailAddr2;
	}

	public void setMailAddr2(String mailAddr2) {
		this.mailAddr2 = mailAddr2;
	}

	public String getMailAddr3() {
		return mailAddr3;
	}

	public void setMailAddr3(String mailAddr3) {
		this.mailAddr3 = mailAddr3;
	}

	public String getMailAddr4() {
		return mailAddr4;
	}

	public void setMailAddr4(String mailAddr4) {
		this.mailAddr4 = mailAddr4;
	}

	public String getMailCity() {
		return mailCity;
	}

	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	public String getMailBc() {
		return mailBc;
	}

	public void setMailBc(String mailBc) {
		this.mailBc = mailBc;
	}

	public String getMailPcode() {
		return mailPcode;
	}

	public void setMailPcode(String mailPcode) {
		this.mailPcode = mailPcode;
	}

	public String getMailCountry() {
		return mailCountry;
	}

	public void setMailCountry(String mailCountry) {
		this.mailCountry = mailCountry;
	}

	public String getMailAddressValidationStatus() {
		return mailAddressValidationStatus;
	}

	public void setMailAddressValidationStatus(String mailAddressValidationStatus) {
		this.mailAddressValidationStatus = mailAddressValidationStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public SourceData() {
	}
	
	public SourceData(Long controlTableId, String doNotLoadFlag, String stakeholder, String stakeholderId,
			String hdsIpcId, String hdsCpnId, String hdsProviderIdentifier1, String hdsProviderIdentifier2,
			String hdsProviderIdentifier3, String hdsProviderIdentifierType1, String hdsProviderIdentifierType2,
			String hdsProviderIdentifierType3, String hdsMspFacilityNumber, String hdsType, String hdsSubType,
			String hdsName, String hdsPreferredNameFlag, String hdsEmail, String hdsWebsite, String hdsBusTelAreaCode,
			String hdsBusTelNumber, String hdsTelExtension, String hdsCellAreaCode, String hdsCellNumber,
			String hdsFaxAreaCode, String hdsFaxNumber, String pcnServiceDeliveryType, String pcnClinicType,
			String pcnPciFlag, String sourceStatus, String pcnClinicStatus, String hdsEffectiveStartDate,
			String hdsEffectiveEndDate, String unitByCivicAddress, String civicAddress, String facRelnType,
			String facTypeCode, String facAddressUnit, String facBuildingName, String facFullAddress,
			String facLatitude, String facLongitude, String facStreetDirection, String streetDirectionPrefix,
			String streetTypePrefix, String facCivicNumber, String facStreetName, String facStreetType,
			String facLocalityName, String facProvinceCode, String facSiteId, String facScore, String facMatchPrecision,
			String facPrecisionPoints, String facHsdaName, String facDatabcResults, String facPcnCode,
			String facPcnName, String facChsaStatus, String facPcnStatus, String facChsaCode, String facChsaName,
			String facLhaName, String facHaName, String physicalAddr1, String physicalAddr2, String physicalAddr3,
			String physicalAddr4, String physicalCity, String physicalProvince, String physicalPcode,
			String physicalCountry, String physicalAddrPrpsTypeCd, String physicalAddressValidationStatus,
			String mailAddr1, String mailAddr2, String mailAddr3, String mailAddr4, String mailCity, String mailBc,
			String mailPcode, String mailCountry, String mailAddressValidationStatus, Date createdAt, String createdBy,
			Date updatedAt, String updatedBy) {
		super();
		this.controlTableId = controlTableId;
		this.doNotLoadFlag = doNotLoadFlag;
		this.stakeholder = stakeholder;
		this.stakeholderId = stakeholderId;
		this.hdsIpcId = hdsIpcId;
		this.hdsCpnId = hdsCpnId;
		this.hdsProviderIdentifier1 = hdsProviderIdentifier1;
		this.hdsProviderIdentifier2 = hdsProviderIdentifier2;
		this.hdsProviderIdentifier3 = hdsProviderIdentifier3;
		this.hdsProviderIdentifierType1 = hdsProviderIdentifierType1;
		this.hdsProviderIdentifierType2 = hdsProviderIdentifierType2;
		this.hdsProviderIdentifierType3 = hdsProviderIdentifierType3;
		this.hdsMspFacilityNumber = hdsMspFacilityNumber;
		this.hdsType = hdsType;
		this.hdsSubType = hdsSubType;
		this.hdsName = hdsName;
		this.hdsPreferredNameFlag = hdsPreferredNameFlag;
		this.hdsEmail = hdsEmail;
		this.hdsWebsite = hdsWebsite;
		this.hdsBusTelAreaCode = hdsBusTelAreaCode;
		this.hdsBusTelNumber = hdsBusTelNumber;
		this.hdsTelExtension = hdsTelExtension;
		this.hdsCellAreaCode = hdsCellAreaCode;
		this.hdsCellNumber = hdsCellNumber;
		this.hdsFaxAreaCode = hdsFaxAreaCode;
		this.hdsFaxNumber = hdsFaxNumber;
		this.pcnServiceDeliveryType = pcnServiceDeliveryType;
		this.pcnClinicType = pcnClinicType;
		this.pcnPciFlag = pcnPciFlag;
		this.sourceStatus = sourceStatus;
		this.pcnClinicStatus = pcnClinicStatus;
		this.hdsEffectiveStartDate = hdsEffectiveStartDate;
		this.hdsEffectiveEndDate = hdsEffectiveEndDate;
		this.unitByCivicAddress = unitByCivicAddress;
		this.civicAddress = civicAddress;
		this.facRelnType = facRelnType;
		this.facTypeCode = facTypeCode;
		this.facAddressUnit = facAddressUnit;
		this.facBuildingName = facBuildingName;
		this.facFullAddress = facFullAddress;
		this.facLatitude = facLatitude;
		this.facLongitude = facLongitude;
		this.facStreetDirection = facStreetDirection;
		this.streetDirectionPrefix = streetDirectionPrefix;
		this.streetTypePrefix = streetTypePrefix;
		this.facCivicNumber = facCivicNumber;
		this.facStreetName = facStreetName;
		this.facStreetType = facStreetType;
		this.facLocalityName = facLocalityName;
		this.facProvinceCode = facProvinceCode;
		this.facSiteId = facSiteId;
		this.facScore = facScore;
		this.facMatchPrecision = facMatchPrecision;
		this.facPrecisionPoints = facPrecisionPoints;
		this.facHsdaName = facHsdaName;
		this.facDatabcResults = facDatabcResults;
		this.facPcnCode = facPcnCode;
		this.facPcnName = facPcnName;
		this.facChsaStatus = facChsaStatus;
		this.facPcnStatus = facPcnStatus;
		this.facChsaCode = facChsaCode;
		this.facChsaName = facChsaName;
		this.facLhaName = facLhaName;
		this.facHaName = facHaName;
		this.physicalAddr1 = physicalAddr1;
		this.physicalAddr2 = physicalAddr2;
		this.physicalAddr3 = physicalAddr3;
		this.physicalAddr4 = physicalAddr4;
		this.physicalCity = physicalCity;
		this.physicalProvince = physicalProvince;
		this.physicalPcode = physicalPcode;
		this.physicalCountry = physicalCountry;
		this.physicalAddrPrpsTypeCd = physicalAddrPrpsTypeCd;
		this.physicalAddressValidationStatus = physicalAddressValidationStatus;
		this.mailAddr1 = mailAddr1;
		this.mailAddr2 = mailAddr2;
		this.mailAddr3 = mailAddr3;
		this.mailAddr4 = mailAddr4;
		this.mailCity = mailCity;
		this.mailBc = mailBc;
		this.mailPcode = mailPcode;
		this.mailCountry = mailCountry;
		this.mailAddressValidationStatus = mailAddressValidationStatus;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}	
}
