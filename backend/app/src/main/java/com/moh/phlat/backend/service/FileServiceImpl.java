package com.moh.phlat.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
												 String  primaryCareSpecificGroupAction, String contactInfoGroupAction,
												 String statusGroupAction, String physicalAddressGroupAction, String mailingAddressGroupAction) {
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
						  .primaryCareSpecificGroupAction(primaryCareSpecificGroupAction)
						  .contactInfoGroupAction(contactInfoGroupAction)
						  .statusGroupAction(statusGroupAction)			
						  .physicalAddressGroupAction(physicalAddressGroupAction)		
						  .mailingAddressGroupAction(mailingAddressGroupAction)									  							  			  
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
						csvRecords.get("PRIMARY_CARE_SPECIFIC_GROUP_ACTION"),
						csvRecords.get("CONTACT_INFO_GROUP_ACTION"),
						csvRecords.get("STATUS_GROUP_ACTION"),
						csvRecords.get("PHYSICAL_ADDRESS_GROUP_ACTION"),
						csvRecords.get("MAILING_ADDRESS_GROUP_ACTION")
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
			processData.setPrimaryCareSpecificGroupAction(s.getPrimaryCareSpecificGroupAction());
			processData.setContactInfoGroupAction(s.getContactInfoGroupAction());
			processData.setStatusGroupAction(s.getStatusGroupAction());
			processData.setPhysicalAddressGroupAction(s.getPhysicalAddressGroupAction());
			processData.setMailingAddressGroupAction(s.getMailingAddressGroupAction());
	        
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
