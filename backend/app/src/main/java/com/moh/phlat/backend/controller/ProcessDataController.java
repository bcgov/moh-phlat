package com.moh.phlat.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import jakarta.validation.Valid;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.DbUtilityService;

@RestController
@RequestMapping("/processdata")
@CrossOrigin(origins = "*")
public class ProcessDataController {
	private static final Logger logger = LoggerFactory.getLogger(ProcessDataController.class);

	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private ProcessDataRepository processDataRepository;

	@Autowired
	private DbUtilityService dbUtilityService;

	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDatas() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", processDataRepository.findAll()));
	}

	// get process data by control id
	@GetMapping("/view/controltableid/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDataByControlTableId(
			@PathVariable Long controlTableId) {
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Process Data not found for control_id: " + controlTableId, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
				processDataRepository.getAllProcessDataByControlTableId(controlTableId)));

	}

	// get specific row by id
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getProcessDataById(@PathVariable Long id) {
		Optional<ProcessData> processData = processDataRepository.findById(id);
		if (processData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Process Data not found for id: " + id, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", processDataRepository.findById(id)));

	}

	// update an existing input source data record
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateProcessDataById(@RequestBody @Valid ProcessData requestProcessData,
			@PathVariable Long id) {

		Optional<ProcessData> _processData = processDataRepository.findById(id);

		if (_processData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Process data not found with id: " + id, "[]"));
		}

		ProcessData processData = _processData.get();
		processData.setDoNotLoad(requestProcessData.getDoNotLoad());
		processData.setStakeholder(requestProcessData.getStakeholder());
		processData.setHdsIpcId(requestProcessData.getHdsIpcId());
		processData.setHdsCpnId(requestProcessData.getHdsCpnId());
		processData.setHdsProviderIdentifier1(requestProcessData.getHdsProviderIdentifier1());
		processData.setHdsProviderIdentifier2(requestProcessData.getHdsProviderIdentifier2());
		processData.setHdsProviderIdentifier3(requestProcessData.getHdsProviderIdentifier3());
		processData.setHdsProviderIdentifierType1(requestProcessData.getHdsProviderIdentifierType1());
		processData.setHdsProviderIdentifierType2(requestProcessData.getHdsProviderIdentifierType2());
		processData.setHdsProviderIdentifierType3(requestProcessData.getHdsProviderIdentifierType3());
		processData.setHdsType(requestProcessData.getHdsType());
		processData.setHdsName(requestProcessData.getHdsName());
		processData.setHdsNameAlias(requestProcessData.getHdsNameAlias());
		processData.setHdsPreferredNameFlag(requestProcessData.getHdsPreferredNameFlag());
		processData.setHdsEmail(requestProcessData.getHdsEmail());
		processData.setHdsWebsite(requestProcessData.getHdsWebsite());
		processData.setHdsBusTelAreaCode(requestProcessData.getHdsBusTelAreaCode());
		processData.setHdsBusTelNumber(requestProcessData.getHdsBusTelNumber());
		processData.setHdsTelExtension(requestProcessData.getHdsTelExtension());
		processData.setHdsCellAreaCode(requestProcessData.getHdsCellAreaCode());
		processData.setHdsCellNumber(requestProcessData.getHdsCellNumber());
		processData.setHdsFaxAreaCode(requestProcessData.getHdsFaxAreaCode());
		processData.setHdsFaxNumber(requestProcessData.getHdsFaxNumber());
		processData.setHdsServiceDeliveryType(requestProcessData.getHdsServiceDeliveryType());
		processData.setPcnClinicType(requestProcessData.getPcnClinicType());
		processData.setPcnPciFlag(requestProcessData.getPcnPciFlag());
		processData.setHdsHoursOfOperation(requestProcessData.getHdsHoursOfOperation());
		processData.setHdsContactName(requestProcessData.getHdsContactName());
		processData.setHdsIsForProfitFlag(requestProcessData.getHdsIsForProfitFlag());
		processData.setSourceStatus(requestProcessData.getSourceStatus());
		processData.setHdsParentIpcId(requestProcessData.getHdsParentIpcId());
		processData.setBusIpcId(requestProcessData.getBusIpcId());
		processData.setBusCpnId(requestProcessData.getBusCpnId());
		processData.setBusName(requestProcessData.getBusName());
		processData.setBusLegalName(requestProcessData.getBusLegalName());
		processData.setBusPayeeNumber(requestProcessData.getBusPayeeNumber());
		processData.setBusOwnerName(requestProcessData.getBusOwnerName());
		processData.setBusOwnerType(requestProcessData.getBusOwnerType());
		processData.setBusOwnerTypeOther(requestProcessData.getBusOwnerTypeOther());
		processData.setFacBuildingName(requestProcessData.getFacBuildingName());
		processData.setFacilityHdsDetailsAdditionalInfo(requestProcessData.getFacilityHdsDetailsAdditionalInfo());
		processData.setPhysicalAddr1(requestProcessData.getPhysicalAddr1());
		processData.setPhysicalAddr2(requestProcessData.getPhysicalAddr2());
		processData.setPhysicalAddr3(requestProcessData.getPhysicalAddr3());
		processData.setPhysicalAddr4(requestProcessData.getPhysicalAddr4());
		processData.setPhysicalCity(requestProcessData.getPhysicalCity());
		processData.setPhysicalProvince(requestProcessData.getPhysicalProvince());
		processData.setPhysicalPcode(requestProcessData.getPhysicalPcode());
		processData.setPhysicalCountry(requestProcessData.getPhysicalCountry());
		processData.setPhysAddrIsPrivate(requestProcessData.getPhysAddrIsPrivate());
		processData.setMailAddr1(requestProcessData.getMailAddr1());
		processData.setMailAddr2(requestProcessData.getMailAddr2());
		processData.setMailAddr3(requestProcessData.getMailAddr3());
		processData.setMailAddr4(requestProcessData.getMailAddr4());
		processData.setMailCity(requestProcessData.getMailCity());
		processData.setMailBc(requestProcessData.getMailBc());
		processData.setMailPcode(requestProcessData.getMailPcode());
		processData.setMailCountry(requestProcessData.getMailCountry());
		processData.setMailAddrIsPrivate(requestProcessData.getMailAddrIsPrivate());
		processData.setRowstatusCode(requestProcessData.getRowstatusCode());
		processData.setUpdatedBy(requestProcessData.getUpdatedBy());
		processData.setUpdatedAt(new Date());

		try {
			processDataRepository.save(processData);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", processData));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while updating Process Data with id: " + id, "[]"));
		}
	}

	@GetMapping("/getformfields/header")
	public String getAllHeader() {
		return dbUtilityService.getVariablesByTableNameSortedById("PROCESS_DATA");
	}

	// get specific row by id
	@PutMapping("/validate/{id}")
	public ResponseEntity<ResponseMessage> validateProcessDataById(@PathVariable Long id) {

		Optional<ProcessData> _processData = processDataRepository.findById(id);
		if (_processData.isPresent()) {
			ProcessData processData = _processData.get();

			Optional<Control> _control = controlRepository.findById(processData.getControlTableId());
			if (_control.isPresent()) {
				Control control = _control.get();
				
				dbUtilityService.setControlStatus(processData.getControlTableId(), "PRE-VALIDATION_IN_PROGRESS");
				
				if ((!processData.getDoNotLoad().equals("Y")) && (!processData.getRowstatusCode().equals("DO_NOT_LOAD")) && (!processData.getRowstatusCode().equals("COMPLETE"))) {
					logger.info("validate process data with id: " + id);
				    // run asyn process
					dbUtilityService.validateProcessData(control, processData);
				} else {
					logger.info("skip validating process data with id: " + id);
				}

				dbUtilityService.setControlStatus(processData.getControlTableId(), "PRE-VALIDATION_COMPLETED");
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
						controlRepository.findById(processData.getControlTableId())));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage("error", 404, "Control Id not found.", "[]"));

	}


	@PutMapping("/validateallbycontroltableid/{id}")
	public ResponseEntity<ResponseMessage> validateAllProcessData(@PathVariable Long id) {

		
		List<ProcessData> _processDataList = processDataRepository.getAllProcessDataByControlTableId(id);
		
		if (_processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Nothing to validate.", "[]"));
			
		}
		dbUtilityService.setControlStatus(id, "PRE-VALIDATION_IN_PROGRESS");
		// asynchronous operation
		dbUtilityService.validateProcessDataByControlTableId(id);

		Optional<Control> _control = controlRepository.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Validation process started!",_control));
	}

	@PutMapping("/plrload/{controlTableId}")
	public ResponseEntity<ResponseMessage> plrLoad(@PathVariable Long controlTableId) {

		Optional<Control> _control = controlRepository.findById(controlTableId);
		if (_control.isPresent()) {
			Control control = _control.get();
			if (!control.getStatusCode().equals("APPROVED")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("error", 404, "Missing approval from Reg Admin to load to PLR.", "[]"));
			}
		}
		
		List<ProcessData> _processDataList = processDataRepository.getAllProcessDataByControlTableId(controlTableId);
		
		if (_processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("error", 404, "Nothing to load to PLR.", "[]"));
			
		}
		dbUtilityService.setControlStatus(controlTableId, "PLR_LOAD_IN_PROGRESS");
		// asynchronous operation
		dbUtilityService.loadProcessDataToPlr(controlTableId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "PLR load process started!",_control));
	}
}
