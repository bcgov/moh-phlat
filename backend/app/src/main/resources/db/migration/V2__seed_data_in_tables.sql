INSERT INTO row_status (code, description) VALUES
('INITIAL', 'The Source file upload has completed. The PHLAT Tool assigns a starting row status for each record in the PHLAT Process File.'),
('DO_NOT_LOAD', 'Indicates a user has excluded the record from loading into PLR. This status may be either set by the PHLAT Tool or PHLAT User.'),
('ON_HOLD', 'Implies deferred loading of the record into PLR. The record is not to be loaded at this time.'),
('VALID', 'Indicates the PHLAT Tool Source file validation has completed with no errors. The record is ready to be loaded into PLR. This is the only record ROWSTATUS value that allows loading into PLR.'),
('INVALID', 'Outcomes of record validations indicates one or more errors that require user correction.'),
('WARNING', 'Outcomes of record validations indicates one or more warnings that require user review to identify any invalid information. The record must be either updated and re-validated or the user may override the record status and manually change the status to either VALID, ON_HOLD, DO_NOT_LOAD, or POTENTIAL_DUPLICATE.'),
('POTENTIAL_DUPLICATE', 'Outcomes of record validations indicates potential duplicate record and requires user review to verify duplicate entity. If the record has 1 or more potential duplicate validation codes and no associated Error Codes, the ROWSTATUS is set to POTENTIAL_DUPLICATE. Records flagged as Potential Duplicate must have 2 or more potential duplicate records and may have one or more WARNING codes assigned to it. The user may override the record status and manually change the status to either VALID, ON_HOLD, DO_NOT_LOAD.'),
('COMPLETED', 'The System has completed the data load into PLR. This status can not be manually altered.'),
('LOAD_ERROR', 'Data load into PLR was not able to complete. The user may override the record status and manually change the status to either ON_HOLD, or DO_NOT_LOAD.');

-- seeded data for table and column info
INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'ID', '', 'id', 'Rcd ID'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'DO_NOT_LOAD_FLAG', 'DO_NOT_LOAD_FLAG', 'doNotLoadFlag','Do Not Load Flag'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'STAKEHOLDER', 'STAKEHOLDER', 'stakeholder','Stakeholder'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'STAKEHOLDER_ID', 'STAKEHOLDER_ID', 'stakeholderId', 'Stakeholder ID'
);


INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_IPC_ID', 'HDS_IPC_ID', 'hdsIpcId', 'HDS IPC'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_CPN_ID', 'HDS_CPN_ID', 'hdsCpnId', 'HDS CPN'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PROVIDER_IDENTIFIER1', 'HDS_PROVIDER_IDENTIFIER1', 'hdsProviderIdentifier1', 'HDS Provider ID 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PROVIDER_IDENTIFIER2', 'HDS_PROVIDER_IDENTIFIER2', 'hdsProviderIdentifier2', 'HDS Provider ID 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PROVIDER_IDENTIFIER3', 'HDS_PROVIDER_IDENTIFIER3', 'hdsProviderIdentifier3', 'HDS Provider ID 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PROVIDER_IDENTIFIER_TYPE1', 'HDS_PROVIDER_IDENTIFIER_TYPE1', 'hdsProviderIdentifierType1', 'HDS Provider Type 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PROVIDER_IDENTIFIER_TYPE2', 'HDS_PROVIDER_IDENTIFIER_TYPE2', 'hdsProviderIdentifierType2', 'HDS Provider Type 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PROVIDER_IDENTIFIER_TYPE3', 'HDS_PROVIDER_IDENTIFIER_TYPE3', 'hdsProviderIdentifierType3', 'HDS Provider Type 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_MSP_FACILITY_NUMBER', 'HDS_MSP_FACILITY_NUMBER', 'hdsMspFacilityNumber', 'HDS MSP Facility #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_TYPE', 'HDS_TYPE', 'hdsType', 'HDS Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_SUB_TYPE', 'HDS_SUB_TYPE', 'hdsSubType', 'HDS Sub Type'
);


INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_NAME', 'HDS_NAME', 'hdsName', 'HDS Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_PREFERRED_NAME_FLAG', 'HDS_PREFERRED_NAME_FLAG', 'hdsPreferredNameFlag', 'HDS Pref Name Flag'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_EMAIL', 'HDS_EMAIL', 'hdsEmail', 'HDS Email'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_WEBSITE', 'HDS_WEBSITE', 'hdsWebsite', 'HDS Web Site'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_BUS_TEL_AREA_CODE', 'HDS_BUS_TEL_AREA_CODE', 'hdsBusTelAreaCode', 'HDS Tel Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_BUS_TEL_NUMBER', 'HDS_BUS_TEL_NUMBER', 'hdsBusTelNumber', 'HDS Tel #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_TEL_EXTENSION', 'HDS_TEL_EXTENSION', 'hdsTelExtension', 'HDS Tel Ext.'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_CELL_AREA_CODE', 'HDS_CELL_AREA_CODE', 'hdsCellAreaCode', 'HDS Cell # Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_CELL_NUMBER', 'HDS_CELL_NUMBER', 'hdsCellNumber', 'HDS Cell #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_FAX_AREA_CODE', 'HDS_FAX_AREA_CODE', 'hdsFaxAreaCode', 'HDS Fax Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_FAX_NUMBER', 'HDS_FAX_NUMBER', 'hdsFaxNumber', 'HDS Fa Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PCN_SERVICE_DELIVERY_TYPE', 'PCN_SERVICE_DELIVERY_TYPE', 'pcnServiceDeliveryType', 'PCN Srve Delivery Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PCN_CLINIC_TYPE', 'PCN_CLINIC_TYPE', 'pcnClinicType', 'PCN Clinic Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (

    
'SOURCE_DATA', 'PCN_PCI_FLAG', 'PCN_PCI_FLAG', 'pcnPciFlag', 'PCN PCI Flag'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'SOURCE_STATUS', 'SOURCE_STATUS', 'sourceStatus', 'Source Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PCN_CLINIC_STATUS', 'PCN_CLINIC_STATUS', 'pcnClinicStatus', 'PCN Clinic Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'HDS_EFFECTIVE_START_DATE', 'HDS_EFFECTIVE_START_DATE', 'hdsEffectiveStartDate', 'HDS Operation Start Date'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'FAC_ADDRESS_UNIT', 'FAC_ADDRESS_UNIT', 'facAddressUnit', 'Facility Addr Unit #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'FAC_BUILDING_NAME', 'FAC_BUILDING_NAME', 'facBuildingName', 'Facility Building Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_ADDR1', 'PHYSICAL_ADDR1', 'physicalAddr1', 'Physical Addr 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_ADDR2', 'PHYSICAL_ADDR2', 'physicalAddr2', 'Physical Addr 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_ADDR3', 'PHYSICAL_ADDR3', 'physicalAddr3', 'Physical Addr 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_ADDR4', 'PHYSICAL_ADDR4', 'physicalAddr4', 'Physical Addr 4'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_CITY', 'PHYSICAL_CITY', 'physicalCity', 'Physical Addr City'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_PROVINCE', 'PHYSICAL_PROVINCE', 'physicalProvince', 'Physical Addr Prov'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_PCODE', 'PHYSICAL_PCODE', 'physicalPcode', 'Physical Postal'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_COUNTRY', 'PHYSICAL_COUNTRY', 'physicalCountry', 'Physical Cntry'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'PHYSICAL_ADDR_PRPS_TYPE_CD', 'PHYSICAL_ADDR_PRPS_TYPE_CD', 'physicalAddrPrpsTypeCd', 'Physical Purpose Type CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_ADDR1', 'MAIL_ADDR1', 'mailAddr1', 'Mailing Addr 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_ADDR2', 'MAIL_ADDR2', 'mailAddr2', 'Mailing Addr 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_ADDR3', 'MAIL_ADDR3', 'mailAddr3', 'Mailing Addr 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_ADDR4', 'MAIL_ADDR4', 'mailAddr4', 'Mailing Addr 4'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_CITY', 'MAIL_CITY', 'mailCity', 'Mailing City'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_BC', 'MAIL_BC', 'mailBc', 'Mailing Prov'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_PCODE', 'MAIL_PCODE', 'mailPcode', 'Mailing Postal'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'SOURCE_DATA', 'MAIL_COUNTRY', 'MAIL_COUNTRY', 'mailCountry', 'Mailing Cntry'
);

-- process data table
INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'ID', 'ID', 'id', 'Rcd ID'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'CONTROL_ID', 'CONTROL_ID', 'controlTableId', 'File ID'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'STAKEHOLDER', 'STAKEHOLDER', 'stakeholder', 'Stakeholder'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_IPC_ID', 'HDS_IPC_ID', 'hdsIpcId', 'HDS IPC'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_CPN_ID', 'HDS_CPN_ID', 'hdsCpnId', 'HDS CPN'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PROVIDER_IDENTIFIER1', 'HDS_PROVIDER_IDENTIFIER1', 'hdsProviderIdentifier1', 'HDS Provider ID 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PROVIDER_IDENTIFIER2', 'HDS_PROVIDER_IDENTIFIER2', 'hdsProviderIdentifier2', 'HDS Provider ID 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PROVIDER_IDENTIFIER3', 'HDS_PROVIDER_IDENTIFIER3', 'hdsProviderIdentifier3', 'HDS Provider ID 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PROVIDER_IDENTIFIER_TYPE1', 'HDS_PROVIDER_IDENTIFIER_TYPE1', 'hdsProviderIdentifierType1', 'HDS Provider Type 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PROVIDER_IDENTIFIER_TYPE2', 'HDS_PROVIDER_IDENTIFIER_TYPE2', 'hdsProviderIdentifierType2', 'HDS Provider Type 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PROVIDER_IDENTIFIER_TYPE3', 'HDS_PROVIDER_IDENTIFIER_TYPE3', 'hdsProviderIdentifierType3', 'HDS Provider Type 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_MSP_FACILITY_NUMBER', 'HDS_MSP_FACILITY_NUMBER', 'hdsMspFacilityNumber', 'HDS MSP Facility #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_TYPE', 'HDS_TYPE', 'hdsType', 'HDS Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_SUB_TYPE', 'HDS_SUB_TYPE', 'hdsSubType', 'HDS Sub Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_NAME', 'HDS_NAME', 'hdsName', 'HDS Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_PREFERRED_NAME_FLAG', 'HDS_PREFERRED_NAME_FLAG', 'hdsPreferredNameFlag', 'HDS Pref Name Flag'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_EMAIL', 'HDS_EMAIL', 'hdsEmail', 'HDS Email'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_WEBSITE', 'HDS_WEBSITE', 'hdsWebsite', 'HDS Web Site'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_BUS_TEL_AREA_CODE', 'HDS_BUS_TEL_AREA_CODE', 'hdsBusTelAreaCode', 'HDS Tel Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_BUS_TEL_NUMBER', 'HDS_BUS_TEL_NUMBER', 'hdsBusTelNumber', 'HDS Tel #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_TEL_EXTENSION', 'HDS_TEL_EXTENSION', 'hdsTelExtension', 'HDS Tel Ext.'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_CELL_AREA_CODE', 'HDS_CELL_AREA_CODE', 'hdsCellAreaCode', 'HDS Cell # Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_CELL_NUMBER', 'HDS_CELL_NUMBER', 'hdsCellNumber', 'HDS Cell #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_FAX_AREA_CODE', 'HDS_FAX_AREA_CODE', 'hdsFaxAreaCode', 'HDS Fax Area CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_FAX_NUMBER', 'HDS_FAX_NUMBER', 'hdsFaxNumber', 'HDS Fax #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PCN_SERVICE_DELIVERY_TYPE', 'PCN_SERVICE_DELIVERY_TYPE', 'pcnServiceDeliveryType', 'PCN Srvc Delivery Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PCN_CLINIC_TYPE', 'PCN_CLINIC_TYPE', 'pcnClinicType', 'PCN Clinic Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PCN_PCI_FLAG', 'PCN_PCI_FLAG', 'pcnPciFlag', 'PCN PCI Flag'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'SOURCE_STATUS', 'SOURCE_STATUS', 'sourceStatus', 'Source Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PCN_CLINIC_STATUS', 'PCN_CLINIC_STATUS', 'pcnClinicStatus', 'FCN PCI Flag'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'HDS_EFFECTIVE_START_DATE', 'HDS_EFFECTIVE_START_DATE', 'hdsEffectiveStartDate', 'HDS Operation Start Date'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PLR_FACILITY_ID', 'PLR_FACILITY_ID', 'plrFacilityId', 'PLR Facility ID'
);


INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_RELN_TYPE', 'FAC_RELN_TYPE', 'facRelnType', 'Facility Relationship Type'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_ADDRESS_UNIT', 'FAC_ADDRESS_UNIT', 'facAddressUnit', 'Facility Addr Unit #'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_BUILDING_NAME', 'FAC_BUILDING_NAME', 'facBuildingName', 'Facility Building Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_CIVIC_ADDR', 'FAC_CIVIC_ADDR', 'facCivicAddr', 'Facility Civic Addr'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_LATITUDE', 'FAC_LATITUDE', 'facLatitude', 'Facility Civic Addr Latitude'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_LONGITUDE', 'FAC_LONGITUDE', 'facLongitude', 'Facility Civic Addr Longitude'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_SITE_ID', 'FAC_SITE_ID', 'facSiteId', 'Facility Civic Addr Site'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_SCORE', 'FAC_SCORE', 'facScore', 'Facility Civic Addr Score'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_MATCH_PRECISION', 'FAC_MATCH_PRECISION', 'facMatchPrecision', 'Facility Civic Addr Match Precision'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_PRECISION_POINTS', 'FAC_PRECISION_POINTS', 'facPrecisionPoints', 'Facility Civic Precision Pts'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_HSDA_NAME', 'FAC_HSDA_NAME', 'facHsdaName', 'HDS Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_CHSA_STATUS', 'FAC_CHSA_STATUS', 'facChsaStatus', 'Facility CHSA Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_PCN_STATUS', 'FAC_PCN_STATUS', 'facPcnStatus', 'Facility PCN Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_CHSA_CODE', 'FAC_CHSA_CODE', 'facChsaCode', 'Facility CHSA CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_CHSA_NAME', 'FAC_CHSA_NAME', 'facChsaName', 'Facility CHSA Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_LHA_NAME', 'FAC_LHA_NAME', 'facLhaName', 'Facility LHA Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_HA_NAME', 'FAC_HA_NAME', 'facHaName' , 'Facility HA Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_PCN_CODE', 'FAC_PCN_CODE', 'facPcnCode', 'Facility PCN Code'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'FAC_PCN_NAME', 'FAC_PCN_NAME', 'facPcnName', 'Facility PCN Name'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_ADDR1', 'PHYSICAL_ADDR1', 'physicalAddr1', 'Physical Addr 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_ADDR2', 'PHYSICAL_ADDR2', 'physicalAddr2', 'Physical Addr 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_ADDR3', 'PHYSICAL_ADDR3', 'physicalAddr3' , 'Physical Addr 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_ADDR4', 'PHYSICAL_ADDR4', 'physicalAddr4' , 'Physical Addr 4'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_CITY', 'PHYSICAL_CITY', 'physicalCity', 'Physical Addr City'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_PROVINCE', 'PHYSICAL_PROVINCE', 'physicalProvince', 'Physical Addr Prov'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_PCODE', 'PHYSICAL_PCODE', 'physicalPcode', 'Physical Postal'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_COUNTRY', 'PHYSICAL_COUNTRY', 'physicalCountry', 'Physical Cntry'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_ADDR_PRPS_TYPE_CD', 'PHYSICAL_ADDR_PRPS_TYPE_CD', 'physicalAddrPrpsTypeCd', 'Physical Purpose Type CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'PHYSICAL_ADDR_VALIDATION_STATUS', 'PHYSICAL_ADDR_VALIDATION_STATUS', 'physicalAddrValidationStatus', 'Physical Addr Val Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_ADDR1', 'MAIL_ADDR1', 'mailAddr1', 'Mailing Addr 1'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_ADDR2', 'MAIL_ADDR2', 'mailAddr2', 'Mailing Addr 2'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_ADDR3', 'MAIL_ADDR3', 'mailAddr3', 'Mailing Addr 3'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_ADDR4', 'MAIL_ADDR4', 'mailAddr4', 'Mailing Addr 4'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_CITY', 'MAIL_CITY', 'mailCity',  'Mailing City'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_BC', 'MAIL_BC', 'mailBc', 'Mailing Prov'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_PCODE', 'MAIL_PCODE', 'mailPcode', 'Mailing Postal'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_COUNTRY', 'MAIL_COUNTRY', 'mailCountry', 'Mailing Cntry'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_ADDR_PRPS_TYPE_CD', 'MAIL_ADDR_PRPS_TYPE_CD', 'mailAddrPrpsTypeCd', 'Mailing Purpose Type CD'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'MAIL_ADDR_VALIDATION_STATUS', 'MAIL_ADDR_VALIDATION_STATUS', 'mailAddrValidationStatus', 'Mailing Val Status'
);

INSERT INTO table_column_info(table_name, column_name, header_name,variable_name, title) VALUES (
'PROCESS_DATA', 'ROWSTATUS_CODE', 'ROWSTATUS_CODE', 'rowstatusCode', 'Rcd Status'
);