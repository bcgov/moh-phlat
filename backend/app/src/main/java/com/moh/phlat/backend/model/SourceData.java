package com.moh.phlat.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "source_data")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Column(name="hds_effective_start_date")
    private String hdsEffectiveStartDate;

    @Column(name="fac_civic_address")
    private String facCivicAddress;  

    @Column(name="fac_address_unit")
    private String facAddressUnit;  
    
    @Column(name="fac_building_name")
    private String facBuildingName;

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
    
    @Column(name="mail_province")
    private String mailProvince;
    
    @Column(name="mail_pcode")
    private String mailPcode;
    
    @Column(name="mail_country")
    private String mailCountry;  

 	@Column(name="created_at")
    private Date createdAt;

	@Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_at")
    private Date updatedAt;

	@Column(name="updated_by")
    private String updatedBy;

	// Group actions and effective dates

    @Column(name="primary_care_group_action")
    private String primaryCareGroupAction;
    
    @Column(name="primary_care_group_effective_start_date")
    private String primaryCareGroupEffectiveStartDate;
    
    @Column(name="primary_care_group_effective_end_date")
    private String primaryCareGroupEffectiveEndDate;

    @Column(name="hds_sub_type_group_action")
    private String hdsSubTypeGroupAction;
    
    @Column(name="hds_sub_type_group_effective_start_date")
    private String hdsSubTypeGroupEffectiveStartDate;
    
    @Column(name="hds_sub_type_group_effective_end_date")
    private String hdsSubTypeGroupEffectiveEndDate;

    @Column(name="hds_name_group_action")
    private String hdsNameGroupAction;
    
    @Column(name="hds_name_group_effective_start_date")
    private String hdsNameGroupEffectiveStartDate;
    
    @Column(name="hds_name_group_effective_end_date")
    private String hdsNameGroupEffectiveEndDate;
    
    @Column(name="hds_email_group_action")
    private String hdsEmailGroupAction;
    
    @Column(name="hds_email_group_effective_start_date")
    private String hdsEmailGroupEffectiveStartDate;
    
    @Column(name="hds_email_group_effective_end_date")
    private String hdsEmailGroupEffectiveEndDate;
    
    @Column(name="hds_website_group_action")
    private String hdsWebsiteGroupAction;
    
    @Column(name="hds_website_group_effective_start_date")
    private String hdsWebsiteGroupEffectiveStartDate;
    
    @Column(name="hds_website_group_effective_end_date")
    private String hdsWebsiteGroupEffectiveEndDate;
    
    @Column(name="business_phone_group_action")
    private String businessPhoneGroupAction;
    
    @Column(name="business_phone_group_effective_start_date")
    private String businessPhoneGroupEffectiveStartDate;
    
    @Column(name="business_phone_group_effective_end_date")
    private String businessPhoneGroupEffectiveEndDate;
    
    @Column(name="hds_fax_group_action")
    private String hdsFaxGroupAction;
    
    @Column(name="hds_fax_group_effective_start_date")
    private String hdsFaxGroupEffectiveStartDate;
    
    @Column(name="hds_fax_group_effective_end_date")
    private String hdsFaxGroupEffectiveEndDate;
    
    @Column(name="hds_cell_group_action")
    private String hdsCellGroupAction;
    
    @Column(name="hds_cell_group_effective_start_date")
    private String hdsCellGroupEffectiveStartDate;
    
    @Column(name="hds_cell_group_effective_end_date")
    private String hdsCellGroupEffectiveEndDate;
    
    @Column(name="status_group_action")
    private String statusGroupAction;
    
    @Column(name="status_group_effective_start_date")
    private String statusGroupEffectiveStartDate;
    
    @Column(name="status_group_effective_end_date")
    private String statusGroupEffectiveEndDate;
    
    @Column(name="physical_address_group_action")
    private String physicalAddressGroupAction;
    
    @Column(name="physical_address_group_effective_start_date")
    private String physicalAddressGroupEffectiveStartDate;
    
    @Column(name="physical_address_group_effective_end_date")
    private String physicalAddressGroupEffectiveEndDate;
    
    @Column(name="mailing_address_group_action")
    private String mailingAddressGroupAction;
    
    @Column(name="mailing_address_group_effective_start_date")
    private String mailingAddressGroupEffectiveStartDate;
    
    @Column(name="mailing_address_group_effective_end_date")
    private String mailingAddressGroupEffectiveEndDate;
    
    @Column(name="record_action")
    private String recordAction;
    
    // PLR storage only
    
    @Column(name="plr_hds_sub_type")
    private String plrHdsSubType;

    @Column(name="plr_hds_sub_type_effective_start_date")
    private String plrHdsSubTypeEffectiveStartDate;

    @Column(name="plr_hds_sub_type_effective_end_date")
    private String plrHdsSubTypeEffectiveEndDate;

    @Column(name="plr_hds_name")
    private String plrHdsName;

    @Column(name="plr_hds_name_effective_start_date")
    private String plrHdsNameEffectiveStartDate;

    @Column(name="plr_hds_name_effective_end_date")
    private String plrHdsNameEffectiveEndDate;

    @Column(name="plr_source_status")
    private String plrSourceStatus;

    @Column(name="plr_source_status_effective_start_date")
    private String plrSourceStatusEffectiveStartDate;

    @Column(name="plr_source_status_effective_end_date")
    private String plrSourceStatusEffectiveEndDate;

    @Column(name="plr_hds_email")
    private String plrHdsEmail;

    @Column(name="plr_hds_email_effective_start_date")
    private String plrHdsEmailEffectiveStartDate;

    @Column(name="plr_hds_email_effective_end_date")
    private String plrHdsEmailEffectiveEndDate;

    @Column(name="plr_hds_website")
    private String plrHdsWebsite;

    @Column(name="plr_hds_website_effective_start_date")
    private String plrHdsWebsiteEffectiveStartDate;

    @Column(name="plr_hds_website_effective_end_date")
    private String plrHdsWebsiteEffectiveEndDate;

    @Column(name="plr_hds_business_phone")
    private String plrHdsBusinessPhone;

    @Column(name="plr_business_phone_effective_start_date")
    private String plrBusinessPhoneEffectiveStartDate;

    @Column(name="plr_business_phone_effective_end_date")
    private String plrBusinessPhoneEffectiveEndDate;

    @Column(name="plr_hds_fax")
    private String plrHdsFax;

    @Column(name="plr_hds_fax_effective_start_date")
    private String plrHdsFaxEffectiveStartDate;

    @Column(name="plr_hds_fax_effective_end_date")
    private String plrHdsFaxEffectiveEndDate;

    @Column(name="plr_hds_cell")
    private String plrHdsCell;

    @Column(name="plr_hds_cell_effective_start_date")
    private String plrHdsCellEffectiveStartDate;

    @Column(name="plr_hds_cell_effective_end_date")
    private String plrHdsCellEffectiveEndDate;

    @Column(name="plr_physical_addr1")
    private String plrPhysicalAddr1;  
    
    @Column(name="plr_physical_addr2")
    private String plrPhysicalAddr2;  
    
    @Column(name="plr_physical_addr3")
    private String plrPhysicalAddr3;  
    
    @Column(name="plr_physical_addr4")
    private String plrPhysicalAddr4;  
     
    @Column(name="plr_physical_city")
    private String plrPhysicalCity;  
    
    @Column(name="plr_physical_province")
    private String plrPhysicalProvince;  

    @Column(name="plr_physical_pcode")
    private String plrPhysicalPcode;  
    
    @Column(name="plr_physical_country")
    private String plrPhysicalCountry;  
    
    @Column(name="plr_physical_addr_prps_type_cd")
    private String plrPhysicalAddrPrpsTypeCd;  

    @Column(name="plr_physical_address_effective_start_date")
    private String plrPhysicalAddressEffectiveStartDate;

    @Column(name="plr_physical_address_effective_end_date")
    private String plrPhysicalAddressEffectiveEndDate;
    
    @Column(name="plr_mail_addr1")
    private String plrMailAddr1;  
    
    @Column(name="plr_mail_addr2")
    private String plrMailAddr2;  

    @Column(name="plr_mail_addr3")
    private String plrMailAddr3;
    
    @Column(name="plr_mail_addr4")
    private String plrMailAddr4;  
    
    @Column(name="plr_mail_city")
    private String plrMailCity;  
    
    @Column(name="plr_mail_province")
    private String plrMailProvince;
    
    @Column(name="plr_mail_pcode")
    private String plrMailPcode;
    
    @Column(name="plr_mail_country")
    private String plrMailCountry;  

    @Column(name="plr_mailing_address_effective_start_date")
    private String plrMailingAddressEffectiveStartDate;

    @Column(name="plr_mailing_address_effective_end_date")
    private String plrMailingAddressEffectiveEndDate;
    
    // Property CHID fields
    
    @Column(name="hds_sub_type_property_chid")
    private String hdsSubTypePropertyChid;
    
    @Column(name="pcn_service_delivery_type_property_chid")
    private String pcnServiceDeliveryTypePropertyChid;
    
    @Column(name="pcn_clinic_type_property_chid")
    private String pcnClinicTypePropertyChid;
    
    @Column(name="pcn_pci_flag_property_chid")
    private String pcnPciFlagPropertyChid;
    
    @Column(name="fac_address_unit_property_chid")
    private String facAddressUnitPropertyChid;
}
