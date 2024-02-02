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
import org.springframework.web.multipart.MultipartFile;

import com.moh.phlat.backend.repository.SourceDataRepository;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.model.User;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.exception.HandleNotFoundException;
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
	public boolean hasCsvFormat(MultipartFile file,String tableName) {
//		String expectedHeaderLine = "DO_NOT_LOAD,INTERNAL_ID,HDS_SURV_INTERNAL_ID,FACILITY_SURV_INTERNAL_ID,HDS_SOURCE,HDS_TYPE_CONCAT,HDS_NAME,CIVIC_ADDRESS_CLEAN,BUS_NAME,FACILITY_DETAILS_ADDITIONAL_INFO,HDS_TEL_AREA_CODE,HDS_TEL_NUMBER,HDS_CELL_AREA_CODE,HDS_CELL_NUMBER,HDS_FAX_AREA_CODE,HDS_FAX_NUMBER,PHYS_PROCESS_STATUS,PHYS_MAILABILITY_SCORE,PHYS_CITY,PHYS_PROVINCE,PHYS_PCODE,PHYS_COUNTRY,PHYS_ADDR_1,PHYS_ADDR_2,PHYS_ADDR_3,PHYS_ADDR_4,BUILDING,UNIT,CIVIC_ADDRESS_CLEAN_OLD,MAIL_PROCESS_STATUS,MAIL_MAILABILITY_SCORE,MAIL_CITY,MAIL_PROVINCE,MAIL_PCODE,MAIL_COUNTRY,MAIL_ADDR_1,MAIL_ADDR_2,MAIL_ADDR_3,MAIL_ADDR_4,DATABC_LATITUDE,DATABC_LONGITUDE,DATABC_UNIT_NO,DATABC_CIVIC_NUMBER,DATABC_STREET_NAME,DATABC_STREET_TYPE,DATABC_LOCALITY_NAME,DATABC_PROVINCE_CODE,DATABC_SITE_ID,DATABC_SCORE,DATABC_MATCH_PRECISION,DATABC_PRECISION_POINTS,DATABC_CHSA_CODE,DATABC_CHSA_NAME,DATABC_LHA_NAME,DATABC_HSDA_NAME,DATABC_HA_NAME,DATABC_USER_CHID,DATABC_PCN_CODE,DATABC_PCN_NAME,DATABC_STATUS";
		String expectedHeaderLine = dbUtilityService.getHeadersByTableNameSortedById(tableName.toUpperCase()).trim();
		
		String type = "text/csv";
		if (!type.equals(file.getContentType()))
			return false;

		InputStream stream = null;
		try {
			stream = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String headerLine = null;
		try {
			headerLine = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Objects.isNull(headerLine))
			return false; // empty file
		
//		logger.info("act:" + headerLine + "(" + expectedHeaderLine.length() + ")");
//		logger.info("exp:" + expectedHeaderLine + "(" + expectedHeaderLine.length() + ")");
		

		if (!expectedHeaderLine.trim().equals(headerLine.trim())) {
//			logger.info("HEADER ARE NOT EQUAL");
			return false; // headers not match expected values
		}
		
		logger.info("HEADER CHECKED SUCCESFFUL");
		return true;
	}

	@Override
    @Async
	public void processAndSaveData(MultipartFile file, Long controlTableId) {
		try {
			logger.info("Parsing CSV file starts...");
			List<SourceData> sourceData = csvToSourceData(file.getInputStream(), controlTableId);

			sourceDataRepository.saveAll(sourceData);


			Control control = controlRepository.findById(controlTableId)
					.orElseThrow(() -> new HandleNotFoundException("Control not found with id: " + controlTableId));

			control.setStatusCode("UPLOAD_COMPLETED");
			control.setUpdatedBy("SYSTEM");
			control.setUpdatedAt(new Date());
			
			controlRepository.save(control);
			logger.info("Parsing CSV file completed successfully.");

			logger.info("Loading process data table starts...");
			copyInputSourceDataToProcessData(controlTableId);
			logger.info("Loading process data table completed successfully.");

		} catch (IOException | HandleNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

	}

	private List<SourceData> csvToSourceData(InputStream inputStream, Long controlTableId) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<SourceData> sourceDatas = new ArrayList<SourceData>();
			List<CSVRecord> records = csvParser.getRecords();
			for (CSVRecord csvRecords : records) {
				SourceData sourceData = new SourceData(controlTableId, 
						csvRecords.get("DO_NOT_LOAD"), 
						csvRecords.get("INTERNAL_ID"),
						csvRecords.get("HDS_SURV_INTERNAL_ID"),	
						csvRecords.get("FACILITY_SURV_INTERNAL_ID"),
						csvRecords.get("HDS_SOURCE"),
						csvRecords.get("HDS_TYPE_CONCAT"),
						csvRecords.get("HDS_NAME"),						
						csvRecords.get("CIVIC_ADDRESS_CLEAN"),
						csvRecords.get("BUS_NAME"),
						csvRecords.get("FACILITY_DETAILS_ADDITIONAL_INFO"),
						csvRecords.get("HDS_TEL_AREA_CODE"),
						csvRecords.get("HDS_TEL_NUMBER"),
						csvRecords.get("HDS_CELL_AREA_CODE"),
						csvRecords.get("HDS_CELL_NUMBER"),
						csvRecords.get("HDS_FAX_AREA_CODE"),
						csvRecords.get("HDS_FAX_NUMBER"),
						csvRecords.get("PHYS_PROCESS_STATUS"),
						csvRecords.get("PHYS_MAILABILITY_SCORE"),
						csvRecords.get("PHYS_CITY"),
						csvRecords.get("PHYS_PROVINCE"),
						csvRecords.get("PHYS_PCODE"),
						csvRecords.get("PHYS_COUNTRY"),
						csvRecords.get("PHYS_ADDR_1"),
						csvRecords.get("PHYS_ADDR_2"),
						csvRecords.get("PHYS_ADDR_3"),
						csvRecords.get("PHYS_ADDR_4"),
						csvRecords.get("BUILDING"),
						csvRecords.get("UNIT"),
						csvRecords.get("CIVIC_ADDRESS_CLEAN_OLD"),
						csvRecords.get("MAIL_PROCESS_STATUS"),
						csvRecords.get("MAIL_MAILABILITY_SCORE"),
						csvRecords.get("MAIL_CITY"),
						csvRecords.get("MAIL_PROVINCE"),
						csvRecords.get("MAIL_PCODE"),
						csvRecords.get("MAIL_COUNTRY"),					
						csvRecords.get("MAIL_ADDR_1"),
						csvRecords.get("MAIL_ADDR_2"),
						csvRecords.get("MAIL_ADDR_3"),
						csvRecords.get("MAIL_ADDR_4"),
						csvRecords.get("DATABC_LATITUDE"),
						csvRecords.get("DATABC_LONGITUDE"),
						csvRecords.get("DATABC_UNIT_NO"),
						csvRecords.get("DATABC_CIVIC_NUMBER"),
						csvRecords.get("DATABC_STREET_NAME"),
						csvRecords.get("DATABC_STREET_TYPE"),
						csvRecords.get("DATABC_LOCALITY_NAME"),
						csvRecords.get("DATABC_PROVINCE_CODE"),
						csvRecords.get("DATABC_SITE_ID"),
						csvRecords.get("DATABC_SCORE"),
						csvRecords.get("DATABC_MATCH_PRECISION"),
						csvRecords.get("DATABC_PRECISION_POINTS"),
						csvRecords.get("DATABC_CHSA_CODE"),
						csvRecords.get("DATABC_CHSA_NAME"),
						csvRecords.get("DATABC_LHA_NAME"),
						csvRecords.get("DATABC_HSDA_NAME"),
						csvRecords.get("DATABC_HA_NAME"),
						csvRecords.get("DATABC_USER_CHID"),
						csvRecords.get("DATABC_PCN_CODE"),
						csvRecords.get("DATABC_PCN_NAME"),
						csvRecords.get("DATABC_STATUS"),
						new Date(), // created_at
						"SYSTEM", // created_by
						null, // updated_at
						null // updated_by
						);
				sourceDatas.add(sourceData);
			}
			return sourceDatas;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			//e.printStackTrace();
		}
		return null;
	}
	
    
	private void copyInputSourceDataToProcessData(Long controlTableId) {

   	
		// processDataRepository.deleteById(controlTableId);
		
    	Iterable<SourceData> inputSourceData = sourceDataRepository.getAllSourceDataByControlTableId(controlTableId);
    	
    	for (SourceData  s : inputSourceData ) {
	        ProcessData processData = new ProcessData();
	        processData.setId(s.getId());
	        processData.setControlTableId(s.getControlTableId());
	        processData.setDoNotLoad(s.getDoNotLoad());
	        processData.setInternalId(s.getInternalId());
	        processData.setHdsSurvInternalId(s.getHdsSurvInternalId());
	        processData.setFacilitySurvInternalId(s.getFacilitySurvInternalId());
	        processData.setHdsSource(s.getHdsSource());
	        processData.setHdsTypeConcat(s.getHdsTypeConcat());
	        processData.setHdsName(s.getHdsName());
	        processData.setCivicAddressClean(s.getCivicAddressClean());
	        processData.setBusName(s.getBusName());        
	        processData.setFacilityDetailsAdditionalInfo(s.getFacilityDetailsAdditionalInfo());
	        processData.setHdsTelAreaCode(s.getHdsTelAreaCode()); 
	        processData.setHdsTelNumber(s.getHdsTelNumber());  	        
	        processData.setHdsCellAreaCode(s.getHdsCellAreaCode());	
	        processData.setHdsCellNumber(s.getHdsCellNumber());  	        
	        processData.setHdsFaxAreaCode(s.getHdsFaxAreaCode());	
	        processData.setHdsFaxNumber(s.getHdsFaxNumber());  	        
	        processData.setPhysProcessStatus(s.getPhysProcessStatus());  	
	        processData.setPhysMailabilityScore(s.getPhysProcessStatus());  
	        processData.setPhysCity(s.getPhysCity());  	        
	        processData.setPhysProvince(s.getPhysProvince());          
	        processData.setPhysPcode(s.getPhysPcode());
	        processData.setPhysCountry(s.getPhysCountry());
	        processData.setPhysAddress1(s.getPhysAddress1());
	        processData.setPhysAddress2(s.getPhysAddress2());
	        processData.setPhysAddress3(s.getPhysAddress3());
	        processData.setPhysAddress4(s.getPhysAddress4());
	        processData.setBuilding(s.getBuilding());	        
	        processData.setUnit(s.getUnit());
	        processData.setCivicAddressCleanOld(s.getCivicAddressCleanOld());
	        processData.setMailProcessStatus(s.getMailProcessStatus());
	        processData.setMailMailabilityScore(s.getMailMailabilityScore());
	        
	        processData.setMailCity(s.getMailCity());  	        
	        processData.setMailProvince(s.getMailProvince());          
	        processData.setMailPcode(s.getMailPcode());
	        processData.setMailCountry(s.getMailCountry());
	        processData.setMailAddress1(s.getMailAddress1());
	        processData.setMailAddress2(s.getMailAddress2());
	        processData.setMailAddress3(s.getMailAddress3());
	        processData.setMailAddress4(s.getMailAddress4());
	        
	        processData.setDatabcLatitude(s.getDatabcLatitude()); 
	        processData.setDatabcLongitude(s.getDatabcLongitude());   
	        processData.setDatabcUnitNo(s.getDatabcUnitNo()); 
	        processData.setDatabcCivicNumber(s.getDatabcCivicNumber()); 	 
	        processData.setDatabcStreetName(s.getDatabcStreetName()); 
	        processData.setDatabcStreetType(s.getDatabcStreetType()); 	 
	        processData.setDatabcLocalityName(s.getDatabcLocalityName());
	        processData.setDatabcProvinceCode(s.getDatabcProvinceCode()); 	 
	        processData.setDatabcSiteId(s.getDatabcSiteId());
	        processData.setDatabcScore(s.getDatabcScore()); 	 
	        processData.setDatabcMatchPrecision(s.getDatabcMatchPrecision()); 	 
	        processData.setDatabcPrecisionPoints(s.getDatabcMatchPrecision()); 	 
	        processData.setDatabcChsaCode(s.getDatabcChsaCode()); 	 
	        processData.setDatabcChsaName(s.getDatabcChsaName()); 	       
	        processData.setDatabcLhaName(s.getDatabcLhaName()); 	  	        
	        processData.setDatabcHsdaName(s.getDatabcHsdaName()); 	  
	        processData.setDatabcHaName(s.getDatabcHaName()); 	  
	        
	        processData.setDatabcUserChid(s.getDatabcUserChid()); 		
	        processData.setDatabcPcnCode(s.getDatabcPcnCode()); 	
	        processData.setDatabcPcnName(s.getDatabcPcnName());
	        processData.setDatabcStatus(s.getDatabcStatus()); 	

	        processData.setStatusCode("INITIAL"); 
	        processData.setCreatedAt(new Date());
	        processData.setCreatedBy("SYSTEM");
	        processDataRepository.save(processData);



    	}

		
	}

}
