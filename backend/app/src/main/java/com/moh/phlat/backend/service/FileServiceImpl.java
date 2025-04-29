package com.moh.phlat.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.moh.phlat.backend.repository.SourceDataRepository;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private ControlRepository controlRepository;
	
	@Autowired
	private SourceDataRepository sourceDataRepository;

	   
    @Autowired

	private ProcessDataRepository processDataRepository;	

	@Autowired
	private DbUtilityService dbUtilityService;
    
	@Override
	public boolean hasCsvFormat(MultipartFile file, String tableName) {
		// Check if the file is of type CSV
		if (!"text/csv".equals(file.getContentType())) {
			logger.warn("Content Type is not text/csv. Returning false...");
			return false;
		}

		// Get expected header line from DB
		String expectedHeaderLine = dbUtilityService.getHeadersByTableNameSortedById(tableName.toUpperCase()).trim();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			// Read the header line from the file
			String headerLine = reader.readLine();
			// Check if the file is empty
			if (!StringUtils.hasText(headerLine)) {
				logger.warn("The file appears to be empty as no header is present. Returnig false...");
				return false;
			}

			//check that length of file header and one received from DB matches.
			if (!expectedHeaderLine.equals(headerLine.trim())) {
				logger.warn(
						"Returning false. The header length of the uploaded file does not match the length obtained from the database.");
				logger.warn("Uploaded file header length: {}, Database file header length: {}", headerLine.length(),
							expectedHeaderLine.length());
				
				logger.warn("Column headers from uploading file: {}", headerLine);
				logger.warn("Expected column headers: {}", expectedHeaderLine);
				return false;
			}
		} catch (IOException e) {
			logger.error("Error occurred: {}", e.getMessage(), e);
			return false;
		}
		logger.info("Header check succesfful ...");
		return true;
	}

	@Override
    @Async
	public void processAndSaveData(MultipartFile file, Long controlTableId, String authenticateUserId) {
		try {
			logger.info("Parsing CSV file starts...");
			List<SourceData> sourceData = csvToSourceData(file.getInputStream(), controlTableId,authenticateUserId);

			sourceDataRepository.saveAll(sourceData);
			logger.info("Data saved in source table");


			Optional<Control> controlTable = controlRepository.findById(controlTableId);

			if (controlTable.isPresent()) {
				Control control = controlTable.get();
				
				control.setStatusCode(RowStatusService.UPLOAD_COMPLETED);
				control.setUpdatedBy(authenticateUserId);
				control.setUpdatedAt(new Date());
			
				controlRepository.save(control);

				logger.info("Starting copy of data from source to destination table...");

				dbUtilityService.setControlStatus(controlTableId, RowStatusService.PRE_VALIDATION_IN_PROGRESS,	authenticateUserId);
				copyInputSourceDataToProcessData(controlTableId,authenticateUserId);
				
				// asynchronous operation
				dbUtilityService.validateProcessDataByControlTableId(controlTableId,authenticateUserId);
				
			}
			logger.info("Loading of target table completed successfully");

		} catch (Exception e){
			logger.error("Error Encountered: {}", e.getMessage(), e);
			Optional<Control> controlTable = controlRepository.findById(controlTableId);
			
			Control control = controlTable.get();
			control.setStatusCode(RowStatusService.UPLOAD_ERROR);
			control.setUpdatedBy(authenticateUserId);
			control.setUpdatedAt(new Date());
		
			controlRepository.save(control);
		}

	}

 private static SourceData createSourceData(Long controlTableId, String doNotLoadFlag, String stakeholder, String stakeholderId,
                                                 String hdsIpcId, String hdsCpnId, 
                                                 String hdsProviderIdentifier1,
                                                 String hdsProviderIdentifier2, String hdsProviderIdentifier3,
                                                 String hdsProviderIdentifierType1, String hdsProviderIdentifierType2, 
                                                 String hdsProviderIdentifierType3, String hdsMspFacilityNumber,
                                                 String hdsType, String hdsSubType, 
                                                 String hdsName,
                                                 String hdsPreferredNameFlag, String hdsEmail, String hdsWebsite,
                                                 String hdsBusTelAreaCode, String hdsBusTelNumber, String hdsTelExtension,
                                                 String hdsCellAreaCode, String hdsCellNumber, String hdsFaxAreaCode, String hdsFaxNumber, 
                                                 String pcnServiceDeliveryType,
                                                 String pcnClinicType,
                                                 String pcnPciFlag, 
                                                 String sourceStatus, 
                                                 String pcnClinicStatus,
                                                 String hdsEffectiveStartDate,
                                                 String facCivicAddress, 
                                                 String facAddressUnit,
                                                 String facBuildingName,
                                                 String physicalAddr1, String physicalAddr2, String physicalAddr3,
                                                 String physicalAddr4,
                                                 String physicalCity, String physicalProvince, String physicalPcode,
                                                 String physicalCountry,
                                                 String physicalAddrPrpsTypeCd,
                                                 String mailAddr1, String mailAddr2,
                                                 String mailAddr3, String mailAddr4, String mailCity, String mailBc,
                                                 String mailPcode, String mailCountry, 
                                                 Date createdAt, String createdBy, Date updatedAt, String updatedBy,
                                                 // Group actions and effective dates
												 String primaryCareSpecificGroupAction, String primaryCareSpecificGroupEffectiveStartDate, String primaryCareSpecificGroupEffectiveEndDate,
												 String hdsSubTypeGroupAction, String hdsSubTypeGroupEffectiveStartDate, String hdsSubTypeGroupEffectiveEndDate,
												 String hdsNameGroupAction, String hdsNameGroupEffectiveStartDate, String hdsNameGroupEffectiveEndDate,
												 String hdsEmailGroupAction, String hdsEmailGroupEffectiveStartDate, String hdsEmailGroupEffectiveEndDate,
												 String hdsWebsiteGroupAction, String hdsWebsiteGroupEffectiveStartDate, String hdsWebsiteGroupEffectiveEndDate,
												 String businessPhoneGroupAction, String businessPhoneStartDate, String businessPhoneGroupEffectiveEndDate,
												 String hdsCellGroupAction, String hdsCellGroupEffectiveStartDate, String hdsCellGroupEffectiveEndDate,
												 String hdsFaxGroupAction, String hdsFaxGroupEffectiveStartDate, String hdsFaxGroupEffectiveEndDate,
												 String statusGroupAction, String statusGroupEffectiveStartDate, String statusGroupEffectiveEndDate,
												 String physicalAddressGroupAction, String physicalAddressGroupEffectiveStartDate, String physicalAddressGroupEffectiveEndDate,
												 String mailingAddressGroupAction, String mailingAddressGroupEffectiveStartDate, String mailingAddressGroupEffectiveEndDate,
												 String recordAction,
												 // PLR storage only
												 String plrHdsSubType, String plrHdsSubTypeEffectiveStartDate, String plrHdsSubTypeEffectiveEndDate,
												 String plrHdsName, String plrHdsNameEffectiveStartDate, String plrHdsNameEffectiveEndDate,
												 String plrSourceStatus, String plrSourceStatusEffectiveStartDate, String plrSourceStatusEffectiveEndDate,
												 String plrPcnClinicStatus, String plrPcnClinicStatusEffectiveStartDate, String plrPcnClinicStatusEffectiveEndDate,
												 String plrHdsEmail, String plrHdsEmailEffectiveStartDate, String plrHdsEmailEffectiveEndDate,
												 String plrHdsWebsite, String plrHdsWebsiteEffectiveStartDate, String plrHdsWebsiteEffectiveEndDate,
												 String plrHdsBusinessPhone, String plrBusinessPhoneEffectiveStartDate, String plrBusinessPhoneEffectiveEndDate,
												 String plrHdsFax, String plrHdsFaxEffectiveStartDate, String plrHdsFaxEffectiveEndDate,
												 String plrHdsCell, String plrHdsCellEffectiveStartDate, String plrHdsCellEffectiveEndDate,
												 String plrPhysicalAddress, String plrPhysicalAddressEffectiveStartDate, String plrPhysicalAddressEffectiveEndDate,
												 String plrMailingAddress, String plrMailingAddressEffectiveStartDate, String plrMailingAddressEffectiveEndDate) {
        return SourceData.builder()
                          .controlTableId(controlTableId)
                          .doNotLoadFlag(doNotLoadFlag)
                          .stakeholder(stakeholder)
                          .stakeholderId(stakeholderId)
                          .hdsIpcId(hdsIpcId)
                          .hdsCpnId(hdsCpnId)
                          .hdsProviderIdentifier1(hdsProviderIdentifier1)
                          .hdsProviderIdentifier2(hdsProviderIdentifier2)
                          .hdsProviderIdentifier3(hdsProviderIdentifier3)
                          .hdsProviderIdentifierType1(hdsProviderIdentifierType1)
                          .hdsProviderIdentifierType2(hdsProviderIdentifierType2)
                          .hdsProviderIdentifierType3(hdsProviderIdentifierType3)
                          .hdsMspFacilityNumber(hdsMspFacilityNumber)
                          .hdsType(hdsType)
                          .hdsSubType(hdsSubType)
                          .hdsName(hdsName)
                          .hdsPreferredNameFlag(hdsPreferredNameFlag)
                          .hdsEmail(hdsEmail)
                          .hdsWebsite(hdsWebsite)
                          .hdsBusTelAreaCode(hdsBusTelAreaCode)
                          .hdsBusTelNumber(hdsBusTelNumber)
                          .hdsTelExtension(hdsTelExtension)
                          .hdsCellAreaCode(hdsCellAreaCode)
                          .hdsCellNumber(hdsCellNumber)
                          .hdsFaxAreaCode(hdsFaxAreaCode)
                          .hdsFaxNumber(hdsFaxNumber)
                          .pcnServiceDeliveryType(pcnServiceDeliveryType)
                          .pcnClinicType(pcnClinicType)
                          .pcnPciFlag(pcnPciFlag)
                          .sourceStatus(sourceStatus)
                          .pcnClinicStatus(pcnClinicStatus)
                          .hdsEffectiveStartDate(hdsEffectiveStartDate)
                          .facCivicAddress(facCivicAddress)
                          .facAddressUnit(facAddressUnit)
                          .facBuildingName(facBuildingName)
                          .physicalAddr1(physicalAddr1)
                          .physicalAddr2(physicalAddr2)
                          .physicalAddr3(physicalAddr3)
                          .physicalAddr4(physicalAddr4)
                          .physicalCity(physicalCity)
                          .physicalProvince(physicalProvince)
                          .physicalPcode(physicalPcode)
                          .physicalCountry(physicalCountry)
                          .physicalAddrPrpsTypeCd(physicalAddrPrpsTypeCd)
                          .mailAddr1(mailAddr1)
                          .mailAddr2(mailAddr2)
                          .mailAddr3(mailAddr3)
                          .mailAddr4(mailAddr4)
                          .mailCity(mailCity)
                          .mailBc(mailBc)
                          .mailPcode(mailPcode)
                          .mailCountry(mailCountry)
                          .createdAt(createdAt)
                          .createdBy(createdBy)
                          .updatedAt(updatedAt)
                          .updatedBy(updatedBy)
                          // Group actions and effective dates
						  .primaryCareSpecificGroupAction(primaryCareSpecificGroupAction)
						  .primaryCareSpecificGroupEffectiveStartDate(primaryCareSpecificGroupEffectiveStartDate)
						  .primaryCareSpecificGroupEffectiveEndDate(primaryCareSpecificGroupEffectiveEndDate)
						  .hdsSubTypeGroupAction(hdsSubTypeGroupAction)
						  .hdsSubTypeGroupEffectiveStartDate(hdsSubTypeGroupEffectiveStartDate)
						  .hdsSubTypeGroupEffectiveEndDate(hdsSubTypeGroupEffectiveEndDate)
						  .hdsNameGroupAction(hdsNameGroupAction)
						  .hdsNameGroupEffectiveStartDate(hdsNameGroupEffectiveStartDate)
						  .hdsNameGroupEffectiveEndDate(hdsNameGroupEffectiveEndDate)
						  .hdsEmailGroupAction(hdsEmailGroupAction)
						  .hdsEmailGroupEffectiveStartDate(hdsEmailGroupEffectiveStartDate)
						  .hdsEmailGroupEffectiveEndDate(hdsEmailGroupEffectiveEndDate)
						  .hdsWebsiteGroupAction(hdsWebsiteGroupAction)
						  .hdsWebsiteGroupEffectiveStartDate(hdsWebsiteGroupEffectiveStartDate)
						  .hdsWebsiteGroupEffectiveEndDate(hdsWebsiteGroupEffectiveEndDate)
						  .businessPhoneGroupAction(businessPhoneGroupAction)
						  .businessPhoneGroupEffectiveStartDate(businessPhoneGroupEffectiveEndDate)
						  .businessPhoneGroupEffectiveEndDate(businessPhoneGroupEffectiveEndDate)
						  .hdsCellGroupAction(hdsCellGroupAction)
						  .hdsCellGroupEffectiveStartDate(hdsCellGroupEffectiveStartDate)
						  .hdsCellGroupEffectiveEndDate(hdsCellGroupEffectiveEndDate)
						  .hdsFaxGroupAction(hdsFaxGroupAction)
						  .hdsFaxGroupEffectiveStartDate(hdsFaxGroupEffectiveStartDate)
						  .hdsFaxGroupEffectiveEndDate(hdsFaxGroupEffectiveEndDate)
						  .statusGroupAction(statusGroupAction)
						  .statusGroupEffectiveStartDate(statusGroupEffectiveStartDate)
						  .statusGroupEffectiveEndDate(statusGroupEffectiveEndDate)
						  .physicalAddressGroupAction(physicalAddressGroupAction)
						  .physicalAddressGroupEffectiveStartDate(physicalAddressGroupEffectiveStartDate)
						  .physicalAddressGroupEffectiveEndDate(physicalAddressGroupEffectiveEndDate)
						  .mailingAddressGroupAction(mailingAddressGroupAction)
						  .mailingAddressGroupEffectiveStartDate(mailingAddressGroupEffectiveStartDate)
						  .mailingAddressGroupEffectiveEndDate(mailingAddressGroupEffectiveEndDate)
						  .recordAction(recordAction)
						  // PLR storage only
						  .plrHdsSubType(plrHdsSubType)
						  .plrHdsSubTypeEffectiveStartDate(plrHdsSubTypeEffectiveStartDate)
						  .plrHdsSubTypeEffectiveEndDate(plrHdsSubTypeEffectiveEndDate)
						  .plrHdsName(plrHdsName)
						  .plrHdsNameEffectiveStartDate(plrHdsNameEffectiveStartDate)
						  .plrHdsNameEffectiveEndDate(plrHdsNameEffectiveEndDate)
						  .plrSourceStatus(plrSourceStatus)
						  .plrSourceStatusEffectiveStartDate(plrSourceStatusEffectiveStartDate)
						  .plrSourceStatusEffectiveEndDate(plrSourceStatusEffectiveEndDate)
						  .plrPcnClinicStatus(plrPcnClinicStatus)
						  .plrPcnClinicStatusEffectiveStartDate(plrPcnClinicStatusEffectiveStartDate)
						  .plrPcnClinicStatusEffectiveEndDate(plrPcnClinicStatusEffectiveEndDate)
						  .plrHdsEmail(plrHdsEmail)
						  .plrHdsEmailEffectiveStartDate(plrHdsEmailEffectiveStartDate)
						  .plrHdsEmailEffectiveEndDate(plrHdsEmailEffectiveEndDate)
						  .plrHdsWebsite(plrHdsWebsite)
						  .plrHdsWebsiteEffectiveStartDate(plrHdsWebsiteEffectiveStartDate)
						  .plrHdsWebsiteEffectiveEndDate(plrHdsWebsiteEffectiveEndDate)
						  .plrHdsBusinessPhone(plrHdsBusinessPhone)
						  .plrBusinessPhoneEffectiveStartDate(plrBusinessPhoneEffectiveStartDate)
						  .plrBusinessPhoneEffectiveEndDate(plrBusinessPhoneEffectiveEndDate)
						  .plrHdsFax(plrHdsFax)
						  .plrHdsFaxEffectiveStartDate(plrHdsFaxEffectiveStartDate)
						  .plrHdsFaxEffectiveEndDate(plrHdsFaxEffectiveEndDate)
						  .plrHdsCell(plrHdsCell)
						  .plrHdsCellEffectiveStartDate(plrHdsCellEffectiveStartDate)
						  .plrHdsCellEffectiveEndDate(plrHdsCellEffectiveEndDate)
						  .plrPhysicalAddress(plrPhysicalAddress)
						  .plrPhysicalAddressEffectiveStartDate(plrPhysicalAddressEffectiveStartDate)
						  .plrPhysicalAddressEffectiveEndDate(plrPhysicalAddressEffectiveEndDate)
						  .plrMailingAddress(plrMailingAddress)
						  .plrMailingAddressEffectiveStartDate(plrMailingAddressEffectiveStartDate)
						  .plrMailingAddressEffectiveEndDate(plrMailingAddressEffectiveEndDate)
                          .build();
    }

	private List<SourceData> csvToSourceData(InputStream inputStream, Long controlTableId, String authenticateUserId) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<SourceData> sourceData = new ArrayList<SourceData>();
			List<CSVRecord> records = csvParser.getRecords();
			for (CSVRecord csvRecords : records) {
				sourceData.add(createSourceData(
					    controlTableId,
						csvRecords.get("DO_NOT_LOAD_FLAG"), 
						csvRecords.get("STAKEHOLDER"),
				 		csvRecords.get("STAKEHOLDER_ID"),
				 		csvRecords.get("HDS_IPC_ID"),	
				 		csvRecords.get("HDS_CPN_ID"),
				 		csvRecords.get("HDS_PROVIDER_IDENTIFIER1"),
				 		csvRecords.get("HDS_PROVIDER_IDENTIFIER2"),
				 		csvRecords.get("HDS_PROVIDER_IDENTIFIER3"),						
				 		csvRecords.get("HDS_PROVIDER_IDENTIFIER_TYPE1"),
				 		csvRecords.get("HDS_PROVIDER_IDENTIFIER_TYPE2"),
				 		csvRecords.get("HDS_PROVIDER_IDENTIFIER_TYPE3"),
				 		csvRecords.get("HDS_MSP_FACILITY_NUMBER"),
				 		csvRecords.get("HDS_TYPE"),
				 		csvRecords.get("HDS_SUB_TYPE"),
				 		csvRecords.get("HDS_NAME"),												
				 		csvRecords.get("HDS_PREFERRED_NAME_FLAG"),							
				 		csvRecords.get("HDS_EMAIL"),	
				 		csvRecords.get("HDS_WEBSITE"),	
				 		csvRecords.get("HDS_BUS_TEL_AREA_CODE"),
				 		csvRecords.get("HDS_BUS_TEL_NUMBER"),
				 		csvRecords.get("HDS_TEL_EXTENSION"),	
				 		csvRecords.get("HDS_CELL_AREA_CODE"),
				 		csvRecords.get("HDS_CELL_NUMBER"),
				 		csvRecords.get("HDS_FAX_AREA_CODE"),
				 		csvRecords.get("HDS_FAX_NUMBER"),
				 		csvRecords.get("PCN_SERVICE_DELIVERY_TYPE"),
				 		csvRecords.get("PCN_CLINIC_TYPE"),
				 		csvRecords.get("PCN_PCI_FLAG"),
				 		csvRecords.get("SOURCE_STATUS"),	
				 		csvRecords.get("PCN_CLINIC_STATUS"),	
						csvRecords.get("HDS_EFFECTIVE_START_DATE"),
				 		csvRecords.get("FAC_CIVIC_ADDRESS"),															
				 		csvRecords.get("FAC_ADDRESS_UNIT"),
				 		csvRecords.get("FAC_BUILDING_NAME"),
				 		csvRecords.get("PHYSICAL_ADDR1"),
				 		csvRecords.get("PHYSICAL_ADDR2"),
				 		csvRecords.get("PHYSICAL_ADDR3"),
				 		csvRecords.get("PHYSICAL_ADDR4"),
				 		csvRecords.get("PHYSICAL_CITY"),						
				 		csvRecords.get("PHYSICAL_PROVINCE"),
				 		csvRecords.get("PHYSICAL_PCODE"),
				 		csvRecords.get("PHYSICAL_COUNTRY"),
				 		csvRecords.get("PHYSICAL_ADDR_PRPS_TYPE_CD"),	
				 		csvRecords.get("MAIL_ADDR1"),
				 		csvRecords.get("MAIL_ADDR2"),
				 		csvRecords.get("MAIL_ADDR3"),
				 		csvRecords.get("MAIL_ADDR4"),
				 		csvRecords.get("MAIL_CITY"),						
				 		csvRecords.get("MAIL_BC"),
				 		csvRecords.get("MAIL_PCODE"),
				 		csvRecords.get("MAIL_COUNTRY"),
				 		new Date(), // created_at
			    		authenticateUserId,
				 		null, // updated_at
				 		null, // updated_by
				 		// Group actions and effective dates
						csvRecords.get("PRIMARY_CARE_SPECIFIC_GROUP_ACTION"),
						csvRecords.get("PRIMARY_CARE_SPECIFIC_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("PRIMARY_CARE_SPECIFIC_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("HDS_SUB_TYPE_GROUP_ACTION"),
						csvRecords.get("HDS_SUB_TYPE_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("HDS_SUB_TYPE_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("HDS_NAME_GROUP_ACTION"),
						csvRecords.get("HDS_NAME_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("HDS_NAME_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("HDS_EMAIL_GROUP_ACTION"),
						csvRecords.get("HDS_EMAIL_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("HDS_EMAIL_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("HDS_WEBSITE_GROUP_ACTION"),
						csvRecords.get("HDS_WEBSITE_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("HDS_WEBSITE_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("BUSINESS_PHONE_GROUP_ACTION"),
						csvRecords.get("BUSINESS_PHONE_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("BUSINESS_PHONE_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("HDS_CELL_GROUP_ACTION"),
						csvRecords.get("HDS_CELL_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("HDS_CELL_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("HDS_FAX_GROUP_ACTION"),
						csvRecords.get("HDS_FAX_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("HDS_FAX_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("STATUS_GROUP_ACTION"),
						csvRecords.get("STATUS_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("STATUS_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("PHYSICAL_ADDRESS_GROUP_ACTION"),
						csvRecords.get("PHYSICAL_ADDRESS_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("PHYSICAL_ADDRESS_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("MAILING_ADDRESS_GROUP_ACTION"),
						csvRecords.get("MAILING_ADDRESS_GROUP_EFFECTIVE_START_DATE"),
						csvRecords.get("MAILING_ADDRESS_GROUP_EFFECTIVE_END_DATE"),
						csvRecords.get("RECORD_ACTION"),
						// PLR storage only
						csvRecords.get("PLR_HDS_SUB_TYPE"),
						csvRecords.get("PLR_HDS_SUB_TYPE_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_HDS_SUB_TYPE_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_HDS_NAME"),
						csvRecords.get("PLR_HDS_NAME_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_HDS_NAME_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_SOURCE_STATUS"),
						csvRecords.get("PLR_SOURCE_STATUS_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_SOURCE_STATUS_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_PCN_CLINIC_STATUS"),
						csvRecords.get("PLR_PCN_CLINIC_STATUS_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_PCN_CLINIC_STATUS_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_HDS_EMAIL"),
						csvRecords.get("PLR_HDS_EMAIL_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_HDS_EMAIL_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_HDS_WEBSITE"),
						csvRecords.get("PLR_HDS_WEBSITE_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_HDS_WEBSITE_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_HDS_BUSINESS_PHONE"),
						csvRecords.get("PLR_BUSINESS_PHONE_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_BUSINESS_PHONE_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_HDS_FAX"),
						csvRecords.get("PLR_HDS_FAX_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_HDS_FAX_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_HDS_CELL"),
						csvRecords.get("PLR_HDS_CELL_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_HDS_CELL_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_PHYSICAL_ADDRESS"),
						csvRecords.get("PLR_PHYSICAL_ADDRESS_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_PHYSICAL_ADDRESS_EFFECTIVE_END_DATE"),
					    csvRecords.get("PLR_MAILING_ADDRESS"),
						csvRecords.get("PLR_MAILING_ADDRESS_EFFECTIVE_START_DATE"),
						csvRecords.get("PLR_MAILING_ADDRESS_EFFECTIVE_END_DATE")
				));
			}
			return sourceData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Unexpected error: {}", e.getMessage(), e);
			Optional<Control> controlTable = controlRepository.findById(controlTableId);
			
			Control control = controlTable.get();
			control.setStatusCode(RowStatusService.UPLOAD_ERROR);
			control.setUpdatedBy(authenticateUserId);
			control.setUpdatedAt(new Date());
		
			controlRepository.save(control);
		}
		return null;
	}
	
    
	private void copyInputSourceDataToProcessData(Long controlTableId, String authenticateUserId) {

		Optional<Control> control = controlRepository.findById(controlTableId);	
		Control controlTable = control.get();
		
    	Iterable<SourceData> inputSourceData = sourceDataRepository.getAllSourceDataByControlTableId(controlTableId);
    	
    	for (SourceData  s : inputSourceData ) {
            ProcessData processData = new ProcessData();
            processData.setId(s.getId());
            processData.setControlTableId(s.getControlTableId());
            processData.setDoNotLoadFlag(s.getDoNotLoadFlag());
            processData.setStakeholderId(s.getStakeholderId());
            processData.setStakeholder(s.getStakeholder());
            processData.setHdsIpcId(s.getHdsIpcId());
            processData.setHdsCpnId(s.getHdsCpnId());
            processData.setHdsProviderIdentifier1(s.getHdsProviderIdentifier1());
            processData.setHdsProviderIdentifier2(s.getHdsProviderIdentifier2());	        
            processData.setHdsProviderIdentifier3(s.getHdsProviderIdentifier3());
            processData.setHdsProviderIdentifierType1(s.getHdsProviderIdentifierType1());
            processData.setHdsProviderIdentifierType2(s.getHdsProviderIdentifierType2());	        
            processData.setHdsProviderIdentifierType3(s.getHdsProviderIdentifierType3());
            processData.setHdsMspFacilityNumber(s.getHdsMspFacilityNumber());
            processData.setHdsType(s.getHdsType());
            processData.setHdsSubType(s.getHdsSubType());
            processData.setHdsName(s.getHdsName());        
            processData.setHdsPreferredNameFlag(s.getHdsPreferredNameFlag());    
            processData.setHdsEmail(s.getHdsEmail());  
            processData.setHdsWebsite(s.getHdsWebsite());  
            processData.setHdsBusTelAreaCode(s.getHdsBusTelAreaCode()); 
            processData.setHdsBusTelNumber(s.getHdsBusTelNumber());  	     
            processData.setHdsTelExtension(s.getHdsTelExtension());  	
            processData.setHdsCellAreaCode(s.getHdsCellAreaCode());  	
            processData.setHdsCellNumber(s.getHdsCellNumber());          
            processData.setHdsFaxAreaCode(s.getHdsFaxAreaCode());  	
            processData.setHdsFaxNumber(s.getHdsFaxNumber());   
            processData.setPcnServiceDeliveryType(s.getPcnServiceDeliveryType());   
            processData.setPcnClinicType(s.getPcnClinicType());
            processData.setPcnPciFlag(s.getPcnPciFlag());
            processData.setSourceStatus(s.getSourceStatus()); 
            processData.setPcnClinicStatus(s.getPcnClinicStatus());
            processData.setHdsEffectiveStartDate(s.getHdsEffectiveStartDate());
            processData.setFacCivicAddr(s.getFacCivicAddress()); 	  
            processData.setFacAddressUnit(s.getFacAddressUnit()); 			    
            processData.setFacBuildingName(s.getFacBuildingName()); 	
            processData.setPhysicalAddr1(s.getPhysicalAddr1());
            processData.setPhysicalAddr2(s.getPhysicalAddr2());	  
			processData.setPhysicalAddr3(s.getPhysicalAddr3());
            processData.setPhysicalAddr4(s.getPhysicalAddr4());
            processData.setPhysicalCity(s.getPhysicalCity());        
            processData.setPhysicalProvince(s.getPhysicalProvince());      
            processData.setPhysicalPcode(s.getPhysicalPcode());      
            processData.setPhysicalCountry(s.getPhysicalCountry()); 
            processData.setPhysicalAddrPrpsTypeCd(s.getPhysicalAddrPrpsTypeCd()); 
            processData.setMailAddr1(s.getMailAddr1());
            processData.setMailAddr2(s.getMailAddr2());	        
            processData.setMailAddr3(s.getMailAddr3());
            processData.setMailAddr4(s.getMailAddr4());
            processData.setMailCity(s.getMailCity());        
            processData.setMailBc(s.getMailBc());      
            processData.setMailPcode(s.getMailPcode());      
            processData.setMailCountry(s.getMailCountry());
            processData.setMailAddrValidationStatus("");
            processData.setCreatedAt(s.getCreatedAt());
            processData.setCreatedBy(authenticateUserId);
            // Group actions and effective dates
			processData.setPrimaryCareSpecificGroupAction(s.getPrimaryCareSpecificGroupAction());
			processData.setPrimaryCareSpecificGroupEffectiveStartDate(s.getPrimaryCareSpecificGroupEffectiveStartDate());
			processData.setPrimaryCareSpecificGroupEffectiveEndDate(s.getPrimaryCareSpecificGroupEffectiveEndDate());
			processData.setHdsSubTypeGroupAction(s.getHdsSubTypeGroupAction());
			processData.setHdsSubTypeGroupEffectiveStartDate(s.getHdsSubTypeGroupEffectiveStartDate());
			processData.setHdsSubTypeGroupEffectiveEndDate(s.getHdsSubTypeGroupEffectiveEndDate());
			processData.setHdsNameGroupAction(s.getHdsNameGroupAction());
			processData.setHdsNameGroupEffectiveStartDate(s.getHdsNameGroupEffectiveStartDate());
			processData.setHdsNameGroupEffectiveEndDate(s.getHdsNameGroupEffectiveEndDate());
			processData.setHdsEmailGroupAction(s.getHdsEmailGroupAction());
			processData.setHdsEmailGroupEffectiveStartDate(s.getHdsEmailGroupEffectiveStartDate());
			processData.setHdsEmailGroupEffectiveEndDate(s.getHdsEmailGroupEffectiveEndDate());
			processData.setHdsWebsiteGroupAction(s.getHdsWebsiteGroupAction());
			processData.setHdsWebsiteGroupEffectiveStartDate(s.getHdsWebsiteGroupEffectiveStartDate());
			processData.setHdsWebsiteGroupEffectiveEndDate(s.getHdsWebsiteGroupEffectiveEndDate());
			processData.setBusinessPhoneGroupAction(s.getBusinessPhoneGroupAction());
			processData.setBusinessPhoneGroupEffectiveStartDate(s.getBusinessPhoneGroupEffectiveStartDate());
			processData.setBusinessPhoneGroupEffectiveEndDate(s.getBusinessPhoneGroupEffectiveEndDate());
			processData.setHdsCellGroupAction(s.getHdsCellGroupAction());
			processData.setHdsCellGroupEffectiveStartDate(s.getHdsCellGroupEffectiveStartDate());
			processData.setHdsCellGroupEffectiveEndDate(s.getHdsCellGroupEffectiveEndDate());
			processData.setHdsFaxGroupAction(s.getHdsFaxGroupAction());
			processData.setHdsFaxGroupEffectiveStartDate(s.getHdsFaxGroupEffectiveStartDate());
			processData.setHdsFaxGroupEffectiveEndDate(s.getHdsFaxGroupEffectiveEndDate());
			processData.setStatusGroupAction(s.getStatusGroupAction());
			processData.setStatusGroupEffectiveStartDate(s.getStatusGroupEffectiveStartDate());
			processData.setStatusGroupEffectiveEndDate(s.getStatusGroupEffectiveEndDate());
			processData.setPhysicalAddressGroupAction(s.getPhysicalAddressGroupAction());
			processData.setPhysicalAddressGroupEffectiveStartDate(s.getPhysicalAddressGroupEffectiveStartDate());
			processData.setPhysicalAddressGroupEffectiveEndDate(s.getPhysicalAddressGroupEffectiveEndDate());
			processData.setMailingAddressGroupAction(s.getMailingAddressGroupAction());
			processData.setMailingAddressGroupEffectiveStartDate(s.getMailingAddressGroupEffectiveStartDate());
			processData.setMailingAddressGroupEffectiveEndDate(s.getMailingAddressGroupEffectiveEndDate());
			processData.setRecordAction(s.getRecordAction());
			// PLR storage only
			processData.setPlrHdsSubType(s.getPlrHdsSubType());
			processData.setPlrHdsSubTypeEffectiveStartDate(s.getPlrHdsSubTypeEffectiveStartDate());
			processData.setPlrHdsSubTypeEffectiveEndDate(s.getPlrHdsSubTypeEffectiveEndDate());
			processData.setPlrHdsName(s.getPlrHdsName());
			processData.setPlrHdsNameEffectiveStartDate(s.getPlrHdsNameEffectiveStartDate());
			processData.setPlrHdsNameEffectiveEndDate(s.getPlrHdsNameEffectiveEndDate());
			processData.setPlrSourceStatus(s.getPlrSourceStatus());
			processData.setPlrSourceStatusEffectiveStartDate(s.getPlrSourceStatusEffectiveStartDate());
			processData.setPlrSourceStatusEffectiveEndDate(s.getPlrSourceStatusEffectiveEndDate());
			processData.setPlrPcnClinicStatus(s.getPlrPcnClinicStatus());
			processData.setPlrPcnClinicStatusEffectiveStartDate(s.getPlrPcnClinicStatusEffectiveStartDate());
			processData.setPlrPcnClinicStatusEffectiveEndDate(s.getPlrPcnClinicStatusEffectiveEndDate());
			processData.setPlrHdsEmail(s.getPlrHdsEmail());
			processData.setPlrHdsEmailEffectiveStartDate(s.getPlrHdsEmailEffectiveStartDate());
			processData.setPlrHdsEmailEffectiveEndDate(s.getPlrHdsEmailEffectiveEndDate());
			processData.setPlrHdsWebsite(s.getPlrHdsWebsite());
			processData.setPlrHdsWebsiteEffectiveStartDate(s.getPlrHdsWebsiteEffectiveStartDate());
			processData.setPlrHdsWebsiteEffectiveEndDate(s.getPlrHdsWebsiteEffectiveEndDate());
			processData.setPlrHdsBusinessPhone(s.getPlrHdsBusinessPhone());
			processData.setPlrBusinessPhoneEffectiveStartDate(s.getPlrBusinessPhoneEffectiveStartDate());
			processData.setPlrBusinessPhoneEffectiveEndDate(s.getPlrBusinessPhoneEffectiveEndDate());
			processData.setPlrHdsFax(s.getPlrHdsFax());
			processData.setPlrHdsFaxEffectiveStartDate(s.getPlrHdsFaxEffectiveStartDate());
			processData.setPlrHdsFaxEffectiveEndDate(s.getPlrHdsFaxEffectiveEndDate());
			processData.setPlrHdsCell(s.getPlrHdsCell());
			processData.setPlrHdsCellEffectiveStartDate(s.getPlrHdsCellEffectiveStartDate());
			processData.setPlrHdsCellEffectiveEndDate(s.getPlrHdsCellEffectiveEndDate());
			processData.setPlrPhysicalAddress(s.getPlrPhysicalAddress());
			processData.setPlrPhysicalAddressEffectiveStartDate(s.getPlrPhysicalAddressEffectiveStartDate());
			processData.setPlrPhysicalAddressEffectiveEndDate(s.getPlrPhysicalAddressEffectiveEndDate());
			processData.setPlrMailingAddress(s.getPlrMailingAddress());
			processData.setPlrMailingAddressEffectiveStartDate(s.getPlrMailingAddressEffectiveStartDate());
			processData.setPlrMailingAddressEffectiveEndDate(s.getPlrMailingAddressEffectiveEndDate());
	        
	        // set default values
		    if (controlTable.getLoadTypeHds()) {
				processData.setHdsUserChid(controlTable.getBatchLabelName());
				processData.setHdsInvalidatedDts("9999-12-30");	        
				processData.setHdsEffectiveEndDate("9999-12-30");  		    
				processData.setHdsCategoryCode("ORGANIZATION");
					
				if (s.getHdsPreferredNameFlag().isEmpty()) {
				    processData.setHdsPreferredNameFlag("Y");   
				}	        
		    }
		    
	        if (controlTable.getLoadTypeFacility()) {
		        processData.setFacRelnType("LOCATION OF"); 		
		        processData.setFacTypeCode("BUILDING"); 		
	        }
		    
	        
	        if ("Y".equals(s.getDoNotLoadFlag())) {
		        processData.setRowstatusCode(RowStatusService.DO_NOT_LOAD); 
	        } else {;
	            processData.setRowstatusCode(RowStatusService.INITIAL); 
	        }
	        
	        processDataRepository.save(processData);
    	}
	}

}
