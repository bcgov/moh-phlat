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

    @Column(name="pcn_clinic_status")
    private String pcnClinicStatus;  

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
    
    @Column(name="mail_bc")
    private String mailBc;
    
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
       
    @Column(name="primary_care_specific_group_action")
    private String primaryCareSpecificGroupAction;
    
    @Column(name="contact_info_group_action")
    private String contactInfoGroupAction;	

    @Column(name="status_group_action")
    private String statusGroupAction;
    
    @Column(name="physical_address_group_action")
    private String physicalAddressGroupAction;
    
    @Column(name="mailing_address_group_action")
    private String mailingAddressGroupAction;	
}
