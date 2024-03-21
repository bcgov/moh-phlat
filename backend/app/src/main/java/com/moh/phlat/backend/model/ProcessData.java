package com.moh.phlat.backend.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROCESS_DATA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessData {
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID")
	private Long id;

    @Column(name="control_id")
    private Long controlTableId;    
    
    
    @Column(name="do_not_load")
    private String doNotLoad;


    @Column(name="stakeholder")
    private String stakeholder;
    
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
    
    @Column(name="hds_hibc_facility_id")
    private String hdsHibcFacilityId;

    @Column(name="hds_type")
    private String hdsType;

    @Column(name="hds_name")
    private String hdsName;

    @Column(name="hds_name_alias")
    private String hdsNameAlias;
    
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
    
    @Column(name="hds_service_delivery_type")
    private String hdsServiceDeliveryType;   
    
    @Column(name="pcn_clinic_type")
    private String pcnClinicType;  
    
    @Column(name="pcn_pci_flag")
    private String pcnPciFlag;  
    
    @Column(name="hds_hours_of_operation")
    private String hdsHoursOfOperation;  

    @Column(name="hds_contact_name")
    private String hdsContactName;  
    
    @Column(name="hds_is_for_profit_flag")
    private String hdsIsForProfitFlag;  
    
    @Column(name="source_status")
    private String sourceStatus;  
    
    @Column(name="hds_parent_ipc_id")
    private String hdsParentIpcId;  
    
    @Column(name="bus_ipc_id")
    private String busIpcId;  
    
    @Column(name="bus_cpn_id")
    private String busCpnId;  
    
    @Column(name="bus_name")
    private String busName;
    
    @Column(name="bus_legal_name")
    private String busLegalName;
    
    @Column(name="bus_payee_number")
    private String busPayeeNumber;
    
    @Column(name="bus_owner_name")
    private String busOwnerName;
    
    @Column(name="bus_owner_type")
    private String busOwnerType;
    
    @Column(name="bus_owner_type_other")
    private String busOwnerTypeOther;
    
    @Column(name="fac_building_name")
    private String facBuildingName;
    
    @Column(name="facility_hds_details_additional_info")
    private String facilityHdsDetailsAdditionalInfo;
    
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
    
    @Column(name="phys_addr_is_private")
    private String physAddrIsPrivate;  
    
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
    
    @Column(name="mail_addr_is_private")
    private String mailAddrIsPrivate;  
    
    @Column(name="facility_id")
    private Long facility_id;
    
    @Column(name="rowstatus_code")
    private String rowstatusCode;  
    
    @Column(name="error_msg")
    private String errorMsg;  
    
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

	public String getDoNotLoad() {
		return doNotLoad;
	}

	public void setDoNotLoad(String doNotLoad) {
		this.doNotLoad = doNotLoad;
	}

	public String getStakeholder() {
		return stakeholder;
	}

	public void setStakeholder(String stakeholder) {
		this.stakeholder = stakeholder;
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

	public String getHdsHibcFacilityId() {
		return hdsHibcFacilityId;
	}

	public void setHdsHibcFacilityId(String hdsHibcFacilityId) {
		this.hdsHibcFacilityId = hdsHibcFacilityId;
	}

	public String getHdsType() {
		return hdsType;
	}

	public void setHdsType(String hdsType) {
		this.hdsType = hdsType;
	}

	public String getHdsName() {
		return hdsName;
	}

	public void setHdsName(String hdsName) {
		this.hdsName = hdsName;
	}

	public String getHdsNameAlias() {
		return hdsNameAlias;
	}

	public void setHdsNameAlias(String hdsNameAlias) {
		this.hdsNameAlias = hdsNameAlias;
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

	public String getHdsServiceDeliveryType() {
		return hdsServiceDeliveryType;
	}

	public void setHdsServiceDeliveryType(String hdsServiceDeliveryType) {
		this.hdsServiceDeliveryType = hdsServiceDeliveryType;
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

	public String getHdsHoursOfOperation() {
		return hdsHoursOfOperation;
	}

	public void setHdsHoursOfOperation(String hdsHoursOfOperation) {
		this.hdsHoursOfOperation = hdsHoursOfOperation;
	}

	public String getHdsContactName() {
		return hdsContactName;
	}

	public void setHdsContactName(String hdsContactName) {
		this.hdsContactName = hdsContactName;
	}

	public String getHdsIsForProfitFlag() {
		return hdsIsForProfitFlag;
	}

	public void setHdsIsForProfitFlag(String hdsIsForProfitFlag) {
		this.hdsIsForProfitFlag = hdsIsForProfitFlag;
	}

	public String getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(String sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

	public String getHdsParentIpcId() {
		return hdsParentIpcId;
	}

	public void setHdsParentIpcId(String hdsParentIpcId) {
		this.hdsParentIpcId = hdsParentIpcId;
	}

	public String getBusIpcId() {
		return busIpcId;
	}

	public void setBusIpcId(String busIpcId) {
		this.busIpcId = busIpcId;
	}

	public String getBusCpnId() {
		return busCpnId;
	}

	public void setBusCpnId(String busCpnId) {
		this.busCpnId = busCpnId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusLegalName() {
		return busLegalName;
	}

	public void setBusLegalName(String busLegalName) {
		this.busLegalName = busLegalName;
	}

	public String getBusPayeeNumber() {
		return busPayeeNumber;
	}

	public void setBusPayeeNumber(String busPayeeNumber) {
		this.busPayeeNumber = busPayeeNumber;
	}

	public String getBusOwnerName() {
		return busOwnerName;
	}

	public void setBusOwnerName(String busOwnerName) {
		this.busOwnerName = busOwnerName;
	}

	public String getBusOwnerType() {
		return busOwnerType;
	}

	public void setBusOwnerType(String busOwnerType) {
		this.busOwnerType = busOwnerType;
	}

	public String getBusOwnerTypeOther() {
		return busOwnerTypeOther;
	}

	public void setBusOwnerTypeOther(String busOwnerTypeOther) {
		this.busOwnerTypeOther = busOwnerTypeOther;
	}

	public String getFacBuildingName() {
		return facBuildingName;
	}

	public void setFacBuildingName(String facBuildingName) {
		this.facBuildingName = facBuildingName;
	}

	public String getFacilityHdsDetailsAdditionalInfo() {
		return facilityHdsDetailsAdditionalInfo;
	}

	public void setFacilityHdsDetailsAdditionalInfo(String facilityHdsDetailsAdditionalInfo) {
		this.facilityHdsDetailsAdditionalInfo = facilityHdsDetailsAdditionalInfo;
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

	public String getPhysAddrIsPrivate() {
		return physAddrIsPrivate;
	}

	public void setPhysAddrIsPrivate(String physAddrIsPrivate) {
		this.physAddrIsPrivate = physAddrIsPrivate;
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

	public String getMailAddrIsPrivate() {
		return mailAddrIsPrivate;
	}

	public void setMailAddrIsPrivate(String mailAddrIsPrivate) {
		this.mailAddrIsPrivate = mailAddrIsPrivate;
	}

	public Long getFacility_id() {
		return facility_id;
	}

	public void setFacility_id(Long facility_id) {
		this.facility_id = facility_id;
	}

	public String getRowstatusCode() {
		return rowstatusCode;
	}

	public void setRowstatusCode(String rowstatusCode) {
		this.rowstatusCode = rowstatusCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	
	
}
