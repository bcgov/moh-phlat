package com.moh.phlat.backend.controller;

import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moh.phlat.backend.exception.HandleInternalException;
import com.moh.phlat.backend.exception.HandleNotFoundException;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.SourceDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.FileService;

@RestController
@RequestMapping("/sourcedata")
@CrossOrigin(origins = "*")
public class SourceDataController {
	private static final Logger logger = LoggerFactory.getLogger(SourceDataController.class);

	// @Value("${column.headers}")
	// private String columnHeaders;

	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private SourceDataRepository sourceDataRepository;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private DbUtilityService dbUtilityService;

	// create input data row
	@PostMapping("/add")
	public SourceData addSourceData(@RequestBody SourceData newSourceData) {
		SourceData sourceData = new SourceData();
		sourceData.setControlTableId(newSourceData.getControlTableId());
		sourceData.setDoNotLoad(newSourceData.getDoNotLoad());
		sourceData.setInternalId(newSourceData.getInternalId());
		sourceData.setHdsSurvInternalId(newSourceData.getHdsSurvInternalId());
		sourceData.setFacilitySurvInternalId(newSourceData.getFacilitySurvInternalId());
		sourceData.setHdsSource(newSourceData.getHdsSource());
		sourceData.setHdsTypeConcat(newSourceData.getHdsTypeConcat());
		sourceData.setHdsName(newSourceData.getHdsName());
		sourceData.setCivicAddressClean(newSourceData.getCivicAddressClean());
		sourceData.setBusName(newSourceData.getBusName());
		sourceData.setFacilityDetailsAdditionalInfo(newSourceData.getFacilityDetailsAdditionalInfo());
		sourceData.setHdsTelAreaCode(newSourceData.getHdsTelAreaCode());
		sourceData.setHdsTelNumber(newSourceData.getHdsTelNumber());
		sourceData.setHdsCellAreaCode(newSourceData.getHdsCellAreaCode());
		sourceData.setHdsCellNumber(newSourceData.getHdsCellNumber());
		sourceData.setHdsFaxAreaCode(newSourceData.getHdsFaxAreaCode());
		sourceData.setHdsFaxNumber(newSourceData.getHdsFaxNumber());
		sourceData.setPhysProcessStatus(newSourceData.getPhysProcessStatus());
		sourceData.setPhysMailabilityScore(newSourceData.getPhysProcessStatus());
		sourceData.setPhysCity(newSourceData.getPhysCity());
		sourceData.setPhysProvince(newSourceData.getPhysProvince());
		sourceData.setPhysPcode(newSourceData.getPhysPcode());
		sourceData.setPhysCountry(newSourceData.getPhysCountry());
		sourceData.setPhysAddress1(newSourceData.getPhysAddress1());
		sourceData.setPhysAddress2(newSourceData.getPhysAddress2());
		sourceData.setPhysAddress3(newSourceData.getPhysAddress3());
		sourceData.setPhysAddress4(newSourceData.getPhysAddress4());
		sourceData.setBuilding(newSourceData.getBuilding());
		sourceData.setUnit(newSourceData.getUnit());
		sourceData.setCivicAddressCleanOld(newSourceData.getCivicAddressCleanOld());
		sourceData.setMailProcessStatus(newSourceData.getMailProcessStatus());
		sourceData.setMailMailabilityScore(newSourceData.getMailMailabilityScore());

		sourceData.setMailCity(newSourceData.getMailCity());
		sourceData.setMailProvince(newSourceData.getMailProvince());
		sourceData.setMailPcode(newSourceData.getMailPcode());
		sourceData.setMailCountry(newSourceData.getMailCountry());
		sourceData.setMailAddress1(newSourceData.getMailAddress1());
		sourceData.setMailAddress2(newSourceData.getMailAddress2());
		sourceData.setMailAddress3(newSourceData.getMailAddress3());
		sourceData.setMailAddress4(newSourceData.getMailAddress4());

		sourceData.setDatabcLatitude(newSourceData.getDatabcLatitude());
		sourceData.setDatabcLongitude(newSourceData.getDatabcLongitude());
		sourceData.setDatabcUnitNo(newSourceData.getDatabcUnitNo());
		sourceData.setDatabcCivicNumber(newSourceData.getDatabcCivicNumber());
		sourceData.setDatabcStreetName(newSourceData.getDatabcStreetName());
		sourceData.setDatabcStreetType(newSourceData.getDatabcStreetType());
		sourceData.setDatabcLocalityName(newSourceData.getDatabcLocalityName());
		sourceData.setDatabcProvinceCode(newSourceData.getDatabcProvinceCode());
		sourceData.setDatabcSiteId(newSourceData.getDatabcSiteId());
		sourceData.setDatabcScore(newSourceData.getDatabcScore());
		sourceData.setDatabcMatchPrecision(newSourceData.getDatabcMatchPrecision());
		sourceData.setDatabcPrecisionPoints(newSourceData.getDatabcMatchPrecision());
		sourceData.setDatabcChsaCode(newSourceData.getDatabcChsaCode());
		sourceData.setDatabcChsaName(newSourceData.getDatabcChsaName());
		sourceData.setDatabcLhaName(newSourceData.getDatabcLhaName());
		sourceData.setDatabcHsdaName(newSourceData.getDatabcHsdaName());
		sourceData.setDatabcHaName(newSourceData.getDatabcHaName());

		sourceData.setDatabcUserChid(newSourceData.getDatabcUserChid());
		sourceData.setDatabcPcnCode(newSourceData.getDatabcPcnCode());
		sourceData.setDatabcPcnName(newSourceData.getDatabcPcnName());
		sourceData.setDatabcStatus(newSourceData.getDatabcStatus());

		sourceData.setCreatedBy(newSourceData.getCreatedBy());
		sourceData.setCreatedAt(new Date());

		sourceDataRepository.save(sourceData);
		return sourceData;
	}

	@GetMapping("view/all")
	public @ResponseBody Iterable<SourceData> getAllSourceDatas() {
		return sourceDataRepository.findAll();

	}

	// get specific row by id
	@GetMapping("/view/{id}")
	public SourceData getSourceDataById(@PathVariable Long id) throws HandleNotFoundException {
		return sourceDataRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Source data not found with id: " + id));
	}

	// get source data by control id
	@GetMapping("/view/controltableid/{controlTableId}")
	public @ResponseBody Iterable<SourceData> getAllSourceDataByControlTableId(@PathVariable Long controlTableId)
			throws HandleNotFoundException {
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isPresent()) {
			return sourceDataRepository.getAllSourceDataByControlTableId(controlTableId);
		} else {
			throw new HandleNotFoundException("Control Table does not have id: " + controlTableId);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<SourceData> updateSourceDataById(@RequestBody SourceData requestSourceData,
			@PathVariable Long id) throws HandleNotFoundException, HandleInternalException {

		// logger.info("HERE AT sourcedata/update/id");
		SourceData sourceData = sourceDataRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Source data not found with id: " + id));

		// sourceData.setControlTableId(requestSourceData.getControlTableId());
		sourceData.setDoNotLoad(requestSourceData.getDoNotLoad());
		sourceData.setInternalId(requestSourceData.getInternalId());
		sourceData.setHdsSurvInternalId(requestSourceData.getHdsSurvInternalId());
		sourceData.setFacilitySurvInternalId(requestSourceData.getFacilitySurvInternalId());
		sourceData.setHdsSource(requestSourceData.getHdsSource());
		sourceData.setHdsTypeConcat(requestSourceData.getHdsTypeConcat());
		sourceData.setHdsName(requestSourceData.getHdsName());
		sourceData.setCivicAddressClean(requestSourceData.getCivicAddressClean());
		sourceData.setBusName(requestSourceData.getBusName());
		sourceData.setFacilityDetailsAdditionalInfo(requestSourceData.getFacilityDetailsAdditionalInfo());
		sourceData.setHdsTelAreaCode(requestSourceData.getHdsTelAreaCode());
		sourceData.setHdsTelNumber(requestSourceData.getHdsTelNumber());
		sourceData.setHdsCellAreaCode(requestSourceData.getHdsCellAreaCode());
		sourceData.setHdsCellNumber(requestSourceData.getHdsCellNumber());
		sourceData.setHdsFaxAreaCode(requestSourceData.getHdsFaxAreaCode());
		sourceData.setHdsFaxNumber(requestSourceData.getHdsFaxNumber());
		sourceData.setPhysProcessStatus(requestSourceData.getPhysProcessStatus());
		sourceData.setPhysMailabilityScore(requestSourceData.getPhysProcessStatus());
		sourceData.setPhysCity(requestSourceData.getPhysCity());
		sourceData.setPhysProvince(requestSourceData.getPhysProvince());
		sourceData.setPhysPcode(requestSourceData.getPhysPcode());
		sourceData.setPhysCountry(requestSourceData.getPhysCountry());
		sourceData.setPhysAddress1(requestSourceData.getPhysAddress1());
		sourceData.setPhysAddress2(requestSourceData.getPhysAddress2());
		sourceData.setPhysAddress3(requestSourceData.getPhysAddress3());
		sourceData.setPhysAddress4(requestSourceData.getPhysAddress4());
		sourceData.setBuilding(requestSourceData.getBuilding());
		sourceData.setUnit(requestSourceData.getUnit());
		sourceData.setCivicAddressCleanOld(requestSourceData.getCivicAddressCleanOld());
		sourceData.setMailProcessStatus(requestSourceData.getMailProcessStatus());
		sourceData.setMailMailabilityScore(requestSourceData.getMailMailabilityScore());

		sourceData.setMailCity(requestSourceData.getMailCity());
		sourceData.setMailProvince(requestSourceData.getMailProvince());
		sourceData.setMailPcode(requestSourceData.getMailPcode());
		sourceData.setMailCountry(requestSourceData.getMailCountry());
		sourceData.setMailAddress1(requestSourceData.getMailAddress1());
		sourceData.setMailAddress2(requestSourceData.getMailAddress2());
		sourceData.setMailAddress3(requestSourceData.getMailAddress3());
		sourceData.setMailAddress4(requestSourceData.getMailAddress4());

		sourceData.setDatabcLatitude(requestSourceData.getDatabcLatitude());
		sourceData.setDatabcLongitude(requestSourceData.getDatabcLongitude());
		sourceData.setDatabcUnitNo(requestSourceData.getDatabcUnitNo());
		sourceData.setDatabcCivicNumber(requestSourceData.getDatabcCivicNumber());
		sourceData.setDatabcStreetName(requestSourceData.getDatabcStreetName());
		sourceData.setDatabcStreetType(requestSourceData.getDatabcStreetType());
		sourceData.setDatabcLocalityName(requestSourceData.getDatabcLocalityName());
		sourceData.setDatabcProvinceCode(requestSourceData.getDatabcProvinceCode());
		sourceData.setDatabcSiteId(requestSourceData.getDatabcSiteId());
		sourceData.setDatabcScore(requestSourceData.getDatabcScore());
		sourceData.setDatabcMatchPrecision(requestSourceData.getDatabcMatchPrecision());
		sourceData.setDatabcPrecisionPoints(requestSourceData.getDatabcMatchPrecision());
		sourceData.setDatabcChsaCode(requestSourceData.getDatabcChsaCode());
		sourceData.setDatabcChsaName(requestSourceData.getDatabcChsaName());
		sourceData.setDatabcLhaName(requestSourceData.getDatabcLhaName());
		sourceData.setDatabcHsdaName(requestSourceData.getDatabcHsdaName());
		sourceData.setDatabcHaName(requestSourceData.getDatabcHaName());

		sourceData.setDatabcUserChid(requestSourceData.getDatabcUserChid());
		sourceData.setDatabcPcnCode(requestSourceData.getDatabcPcnCode());
		sourceData.setDatabcPcnName(requestSourceData.getDatabcPcnName());
		sourceData.setDatabcStatus(requestSourceData.getDatabcStatus());
		sourceData.setUpdatedBy(requestSourceData.getUpdatedBy());
		sourceData.setUpdatedAt(new Date());

		try {
			sourceDataRepository.save(sourceData);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new HandleInternalException(
					"Source data can not update id: " + id + ". Please report this to your system administrator");
		}

		return new ResponseEntity<>(sourceData, HttpStatus.OK);
	}

	@GetMapping("/getformfields/header")
	public String getAllHeader() {
//		return columnHeaders;
		return dbUtilityService.getVariablesByTableNameSortedById("TRUSTED_FILE");

	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			Control newControlTable) {
		String _oldFileName = null;
		String _newFileName = null;
		Boolean isFound = false;

		
        // table name: "TRUSTED_FILE", "SOURCE_DATA"
		if (fileService.hasCsvFormat(file, "TRUSTED_FILE")) {
			logger.info("File is valid");
			Control control = new Control();
			control.setFileName(newControlTable.getFileName());
			control.setUserId(newControlTable.getUserId());
			control.setFileExtractedDate(newControlTable.getFileExtractedDate());
			control.setBatchLabelName(newControlTable.getBatchLabelName());
			control.setLoadTypeFacility(newControlTable.getLoadTypeFacility());
			control.setLoadTypeHds(newControlTable.getLoadTypeHds());
			control.setLoadTypeOrg(newControlTable.getLoadTypeOrg());
			control.setLoadTypeOFRelationship(newControlTable.getLoadTypeOFRelationship());
			control.setLoadTypeOORelationship(newControlTable.getLoadTypeOORelationship());
			control.setLoadTypeIORelationship(newControlTable.getLoadTypeIORelationship());
			control.setLoadTypeWOXref(newControlTable.getLoadTypeWOXref());
			control.setLoadTypeWPIXref(newControlTable.getLoadTypeWPIXref());
			control.setProcessStartDate(new Date());
			control.setStatusCode("UPLOAD_IN_PROGRESS");
			control.setCreatedBy("SYSTEM");
			control.setCreatedAt(new Date());

			// check mandatory fields: FileName, UserId, BatchLabelName
			if (control.getFileName().trim().isEmpty()) {
				logger.info("File Name is required.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error",400,"File Name is required.", 0));
			}

			if (control.getBatchLabelName().trim().isEmpty()) {
				logger.info(_newFileName + " Batch Label Name is required.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error",400,"Batch Label Name is required.", 0));
			}

			if (control.getUserId().trim().isEmpty()) {
				logger.info("Userid is required.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error",400, "Userid is required.", 0));
			}

			// File Name must be unique

			Iterable<Control> _controlTable = controlRepository.findByFileName(newControlTable.getFileName());
			_newFileName = newControlTable.getFileName();
			for (Control s : _controlTable) {
				_oldFileName = s.getFileName();
				if (_oldFileName.contentEquals(_newFileName)) {
					isFound = true;
				}
			}

			if (isFound) {
				logger.info(_newFileName + " file has already been uploaded before.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error",400,
						_newFileName + " file has already been uploaded before. Please upload a different data file.",
						0));
			}

			controlRepository.save(control);

			// Async process
			fileService.processAndSaveData(file, control.getId());

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success",200,"File loaded successfully", control.getId()));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseMessage("error",400,"Please upload a non-empty CSV file with the standard format!", 0));
	}


}
