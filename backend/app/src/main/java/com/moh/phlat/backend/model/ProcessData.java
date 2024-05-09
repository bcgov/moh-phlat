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

import java.util.Date;
import java.util.List;

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
    
    @Column(name="created_at")
    private Date createdAt;

	@Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_at")
    private Date updatedAt;

	@Column(name="updated_by")
    private String updatedBy;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "processData")
    private List<Message> messages;


}
