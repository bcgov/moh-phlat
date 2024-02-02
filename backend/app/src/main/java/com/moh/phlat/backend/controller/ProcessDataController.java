package com.moh.phlat.backend.controller;

import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.moh.phlat.backend.exception.HandleInternalException;
import com.moh.phlat.backend.exception.HandleNotFoundException;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import jakarta.validation.Valid;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;

@RestController
@RequestMapping("/processdata")
@CrossOrigin(origins = "*")
public class ProcessDataController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private ProcessDataRepository processDataRepository;

	// create input data row
	@PostMapping("/add")
	public ResponseEntity<ProcessData> addProcessData(@RequestBody ProcessData newProcessData)
			throws HandleInternalException {
		ProcessData processData = new ProcessData();
		processData.setControlTableId(newProcessData.getControlTableId());
		processData.setDoNotLoad(newProcessData.getDoNotLoad());
		processData.setInternalId(newProcessData.getInternalId());
		processData.setHdsSurvInternalId(newProcessData.getHdsSurvInternalId());
		processData.setFacilitySurvInternalId(newProcessData.getFacilitySurvInternalId());
		processData.setHdsSource(newProcessData.getHdsSource());
		processData.setHdsTypeConcat(newProcessData.getHdsTypeConcat());
		processData.setHdsName(newProcessData.getHdsName());
		processData.setCivicAddressClean(newProcessData.getCivicAddressClean());
		processData.setBusName(newProcessData.getBusName());
		processData.setFacilityDetailsAdditionalInfo(newProcessData.getFacilityDetailsAdditionalInfo());
		processData.setHdsTelAreaCode(newProcessData.getHdsTelAreaCode());
		processData.setHdsTelNumber(newProcessData.getHdsTelNumber());
		processData.setHdsCellAreaCode(newProcessData.getHdsCellAreaCode());
		processData.setHdsCellNumber(newProcessData.getHdsCellNumber());
		processData.setHdsFaxAreaCode(newProcessData.getHdsFaxAreaCode());
		processData.setHdsFaxNumber(newProcessData.getHdsFaxNumber());
		processData.setPhysProcessStatus(newProcessData.getPhysProcessStatus());
		processData.setPhysMailabilityScore(newProcessData.getPhysProcessStatus());
		processData.setPhysCity(newProcessData.getPhysCity());
		processData.setPhysProvince(newProcessData.getPhysProvince());
		processData.setPhysPcode(newProcessData.getPhysPcode());
		processData.setPhysCountry(newProcessData.getPhysCountry());
		processData.setPhysAddress1(newProcessData.getPhysAddress1());
		processData.setPhysAddress2(newProcessData.getPhysAddress2());
		processData.setPhysAddress3(newProcessData.getPhysAddress3());
		processData.setPhysAddress4(newProcessData.getPhysAddress4());
		processData.setBuilding(newProcessData.getBuilding());
		processData.setUnit(newProcessData.getUnit());
		processData.setCivicAddressCleanOld(newProcessData.getCivicAddressCleanOld());
		processData.setMailProcessStatus(newProcessData.getMailProcessStatus());
		processData.setMailMailabilityScore(newProcessData.getMailMailabilityScore());

		processData.setMailCity(newProcessData.getMailCity());
		processData.setMailProvince(newProcessData.getMailProvince());
		processData.setMailPcode(newProcessData.getMailPcode());
		processData.setMailCountry(newProcessData.getMailCountry());
		processData.setMailAddress1(newProcessData.getMailAddress1());
		processData.setMailAddress2(newProcessData.getMailAddress2());
		processData.setMailAddress3(newProcessData.getMailAddress3());
		processData.setMailAddress4(newProcessData.getMailAddress4());

		processData.setDatabcLatitude(newProcessData.getDatabcLatitude());
		processData.setDatabcLongitude(newProcessData.getDatabcLongitude());
		processData.setDatabcUnitNo(newProcessData.getDatabcUnitNo());
		processData.setDatabcCivicNumber(newProcessData.getDatabcCivicNumber());
		processData.setDatabcStreetName(newProcessData.getDatabcStreetName());
		processData.setDatabcStreetType(newProcessData.getDatabcStreetType());
		processData.setDatabcLocalityName(newProcessData.getDatabcLocalityName());
		processData.setDatabcProvinceCode(newProcessData.getDatabcProvinceCode());
		processData.setDatabcSiteId(newProcessData.getDatabcSiteId());
		processData.setDatabcScore(newProcessData.getDatabcScore());
		processData.setDatabcMatchPrecision(newProcessData.getDatabcMatchPrecision());
		processData.setDatabcPrecisionPoints(newProcessData.getDatabcMatchPrecision());
		processData.setDatabcChsaCode(newProcessData.getDatabcChsaCode());
		processData.setDatabcChsaName(newProcessData.getDatabcChsaName());
		processData.setDatabcLhaName(newProcessData.getDatabcLhaName());
		processData.setDatabcHsdaName(newProcessData.getDatabcHsdaName());
		processData.setDatabcHaName(newProcessData.getDatabcHaName());

		processData.setDatabcUserChid(newProcessData.getDatabcUserChid());
		processData.setDatabcPcnCode(newProcessData.getDatabcPcnCode());
		processData.setDatabcPcnName(newProcessData.getDatabcPcnName());
		processData.setDatabcStatus(newProcessData.getDatabcStatus());

		processData.setStatusCode(newProcessData.getStatusCode());

		try {
			processDataRepository.save(processData);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new HandleInternalException(
					"Cannot add a record to the process data table. Please report this to your system administrator");
		}
		return new ResponseEntity<>(processData, HttpStatus.CREATED);
	}

	@GetMapping("/view/all")
	public @ResponseBody Iterable<ProcessData> getAllProcessDatas() {
		return processDataRepository.findAll();
	}

	// get process data by control id
	@GetMapping("/view/controltableid/{controlTableId}")
	public @ResponseBody Iterable<ProcessData> getAllProcessDataByControlTableId(@PathVariable Long controlTableId)
			throws HandleNotFoundException {
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isPresent()) {
			return processDataRepository.getAllProcessDataByControlTableId(controlTableId);
		} else {
			throw new HandleNotFoundException("Control Table does not have id: " + controlTableId);
		}

	}

	// get specific row by id
	@GetMapping("/view/{id}")
	public ProcessData getProcessDataById(@PathVariable Long id) throws HandleNotFoundException {
		return processDataRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Process data not found with id: " + id));
	}

	// update an existing input source data record
	@PutMapping("/update/{id}")
	public ResponseEntity<ProcessData> updateProcessDataById(@RequestBody @Valid ProcessData requestProcessData,
			@PathVariable Long id) throws HandleNotFoundException, HandleInternalException {
		ProcessData processData = processDataRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Process data not found with id: " + id));

		// processData.setControlTableId(requestProcessData.getControlTableId());
		processData.setDoNotLoad(requestProcessData.getDoNotLoad());
		processData.setInternalId(requestProcessData.getInternalId());
		processData.setHdsSurvInternalId(requestProcessData.getHdsSurvInternalId());
		processData.setFacilitySurvInternalId(requestProcessData.getFacilitySurvInternalId());
		processData.setHdsSource(requestProcessData.getHdsSource());
		processData.setHdsTypeConcat(requestProcessData.getHdsTypeConcat());
		processData.setHdsName(requestProcessData.getHdsName());
		processData.setCivicAddressClean(requestProcessData.getCivicAddressClean());
		processData.setBusName(requestProcessData.getBusName());
		processData.setFacilityDetailsAdditionalInfo(requestProcessData.getFacilityDetailsAdditionalInfo());
		processData.setHdsTelAreaCode(requestProcessData.getHdsTelAreaCode());
		processData.setHdsTelNumber(requestProcessData.getHdsTelNumber());
		processData.setHdsCellAreaCode(requestProcessData.getHdsCellAreaCode());
		processData.setHdsCellNumber(requestProcessData.getHdsCellNumber());
		processData.setHdsFaxAreaCode(requestProcessData.getHdsFaxAreaCode());
		processData.setHdsFaxNumber(requestProcessData.getHdsFaxNumber());
		processData.setPhysProcessStatus(requestProcessData.getPhysProcessStatus());
		processData.setPhysMailabilityScore(requestProcessData.getPhysProcessStatus());
		processData.setPhysCity(requestProcessData.getPhysCity());
		processData.setPhysProvince(requestProcessData.getPhysProvince());
		processData.setPhysPcode(requestProcessData.getPhysPcode());
		processData.setPhysCountry(requestProcessData.getPhysCountry());
		processData.setPhysAddress1(requestProcessData.getPhysAddress1());
		processData.setPhysAddress2(requestProcessData.getPhysAddress2());
		processData.setPhysAddress3(requestProcessData.getPhysAddress3());
		processData.setPhysAddress4(requestProcessData.getPhysAddress4());
		processData.setBuilding(requestProcessData.getBuilding());
		processData.setUnit(requestProcessData.getUnit());
		processData.setCivicAddressCleanOld(requestProcessData.getCivicAddressCleanOld());
		processData.setMailProcessStatus(requestProcessData.getMailProcessStatus());
		processData.setMailMailabilityScore(requestProcessData.getMailMailabilityScore());

		processData.setMailCity(requestProcessData.getMailCity());
		processData.setMailProvince(requestProcessData.getMailProvince());
		processData.setMailPcode(requestProcessData.getMailPcode());
		processData.setMailCountry(requestProcessData.getMailCountry());
		processData.setMailAddress1(requestProcessData.getMailAddress1());
		processData.setMailAddress2(requestProcessData.getMailAddress2());
		processData.setMailAddress3(requestProcessData.getMailAddress3());
		processData.setMailAddress4(requestProcessData.getMailAddress4());

		processData.setDatabcLatitude(requestProcessData.getDatabcLatitude());
		processData.setDatabcLongitude(requestProcessData.getDatabcLongitude());
		processData.setDatabcUnitNo(requestProcessData.getDatabcUnitNo());
		processData.setDatabcCivicNumber(requestProcessData.getDatabcCivicNumber());
		processData.setDatabcStreetName(requestProcessData.getDatabcStreetName());
		processData.setDatabcStreetType(requestProcessData.getDatabcStreetType());
		processData.setDatabcLocalityName(requestProcessData.getDatabcLocalityName());
		processData.setDatabcProvinceCode(requestProcessData.getDatabcProvinceCode());
		processData.setDatabcSiteId(requestProcessData.getDatabcSiteId());
		processData.setDatabcScore(requestProcessData.getDatabcScore());
		processData.setDatabcMatchPrecision(requestProcessData.getDatabcMatchPrecision());
		processData.setDatabcPrecisionPoints(requestProcessData.getDatabcMatchPrecision());
		processData.setDatabcChsaCode(requestProcessData.getDatabcChsaCode());
		processData.setDatabcChsaName(requestProcessData.getDatabcChsaName());
		processData.setDatabcLhaName(requestProcessData.getDatabcLhaName());
		processData.setDatabcHsdaName(requestProcessData.getDatabcHsdaName());
		processData.setDatabcHaName(requestProcessData.getDatabcHaName());

		processData.setDatabcUserChid(requestProcessData.getDatabcUserChid());
		processData.setDatabcPcnCode(requestProcessData.getDatabcPcnCode());
		processData.setDatabcPcnName(requestProcessData.getDatabcPcnName());
		processData.setDatabcStatus(requestProcessData.getDatabcStatus());

		processData.setStatusCode(requestProcessData.getStatusCode());
		processData.setUpdatedBy(requestProcessData.getUpdatedBy());
		processData.setUpdatedAt(new Date());

		try {
			processDataRepository.save(processData);
		} catch (Exception e) {
			if (e.getMessage().contains("foreign key constraint")) {
				throw new HandleInternalException(
						"Invalid row status code found. Cannot update process data with id: " + id);
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException("Cannot update process data with id: " + id
						+ ". Please report this to your system administrator.");
			}
		}
		return new ResponseEntity<>(processData, HttpStatus.OK);
	}

	
}
