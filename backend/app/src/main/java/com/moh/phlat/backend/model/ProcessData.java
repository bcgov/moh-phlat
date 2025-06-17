package com.moh.phlat.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "PROCESS_DATA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraph(name="ProcessData.withMessageDetails",attributeNodes = @NamedAttributeNode(value = "messages"))
public class ProcessData {
	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="hds_pauth_id")
    private String hdsPauthId;

    @Column(name="hds_category_code")
    private String hdsCategoryCode;

    @Column(name="hds_role_type_code")
    private String hdsRoleTypeCode;

    @Column(name="hds_type")
    private String hdsType;

    @Column(name="hds_sub_type")
    private String hdsSubType;

    @Column(name="hds_user_chid")
    private String hdsUserChid;

    @Column(name="hds_created_dts")
    private String hdsCreatedDts;

    @Column(name="hds_invalidated_dts")
    private String hdsInvalidatedDts ;

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

    @Column(name="hds_effective_end_date")
    private String hdsEffectiveEndDate;  

    @Column(name="fac_address_unit")
    private String facAddressUnit;  
    
    @Column(name="fac_building_name")
    private String facBuildingName;

    @Column(name="fac_civic_addr_id")
    private String facCivicAddrId;

    @Column(name="fac_civic_addr")
    private String facCivicAddr;

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

    @Column(name="fac_street_name ")
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

    @Column(name="fac_precision_points")
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

    @Column(name="fac_reln_type")
    private String facRelnType;

    @Column(name="fac_type_code")
    private String facTypeCode;

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

    @Column(name="physical_addr_validation_status")
    private String physicalAddrValidationStatus;  

    @Column(name="physical_addr_mailability_score")
    private String physicalAddrMailabilityScore;  

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

    @Column(name="mail_addr_prps_type_cd")
    private String mailAddrPrpsTypeCd;  

    @Column(name="mail_addr_validation_status")
    private String mailAddrValidationStatus;  

    @Column(name="mail_addr_mailability_score")
    private String mailAddrMailabilityScore;  

    @Column(name="plr_facility_id")
    private String plrFacilityId ; 

    @Column(name="fac_ifc_id")
    private String facIfcId ; 

    @Column(name="rowstatus_code")
    private String rowstatusCode; 

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

    @Column(name="plr_physical_address")
    private String plrPhysicalAddress;

    @Column(name="plr_physical_address_effective_start_date")
    private String plrPhysicalAddressEffectiveStartDate;

    @Column(name="plr_physical_address_effective_end_date")
    private String plrPhysicalAddressEffectiveEndDate;

    @Column(name="plr_mailing_address")
    private String plrMailingAddress;

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


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "processData")
    private List<Message> messages;
    
    public Map<String,String> mapOfHdsGroupActions() {
    	return Map.ofEntries(
		    new AbstractMap.SimpleEntry<>("Primary Care Specific", primaryCareGroupAction),
		    new AbstractMap.SimpleEntry<>("Hds Sub Type", hdsSubTypeGroupAction),
		    new AbstractMap.SimpleEntry<>("Hds Name", hdsNameGroupAction),
		    new AbstractMap.SimpleEntry<>("Hds Email", hdsEmailGroupAction),
		    new AbstractMap.SimpleEntry<>("Hds Website", hdsWebsiteGroupAction),
		    new AbstractMap.SimpleEntry<>("Business Phone", businessPhoneGroupAction),
		    new AbstractMap.SimpleEntry<>("Hds Cell", hdsCellGroupAction),
		    new AbstractMap.SimpleEntry<>("Hds Fax", hdsFaxGroupAction),
		    new AbstractMap.SimpleEntry<>("Status", statusGroupAction),
		    new AbstractMap.SimpleEntry<>("Physical Address", physicalAddressGroupAction),
		    new AbstractMap.SimpleEntry<>("Mailing Address", mailingAddressGroupAction)
    	);
    }
    
    public Map<String,String> mapOfHdsGroupEffectiveDates() {
    	return Map.ofEntries(
		    new AbstractMap.SimpleEntry<>("Primary Care Specific", primaryCareGroupEffectiveStartDate + "|" + primaryCareGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Hds Sub Type", hdsSubTypeGroupEffectiveStartDate + "|" + hdsSubTypeGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Hds Name", hdsNameGroupEffectiveStartDate + "|" + hdsNameGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Hds Email", hdsEmailGroupEffectiveStartDate + "|" + hdsEmailGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Hds Website", hdsWebsiteGroupEffectiveStartDate + "|" + hdsWebsiteGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Business Phone", businessPhoneGroupEffectiveStartDate + "|" + businessPhoneGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Hds Cell", hdsCellGroupEffectiveStartDate + "|" + hdsCellGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Hds Fax", hdsFaxGroupEffectiveStartDate + "|" + hdsFaxGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Status", statusGroupEffectiveStartDate + "|" + statusGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Physical Address", physicalAddressGroupEffectiveStartDate + "|" + physicalAddressGroupEffectiveEndDate),
		    new AbstractMap.SimpleEntry<>("Mailing Address", mailingAddressGroupEffectiveStartDate + "|" + mailingAddressGroupEffectiveEndDate)
    	);
    }
}
