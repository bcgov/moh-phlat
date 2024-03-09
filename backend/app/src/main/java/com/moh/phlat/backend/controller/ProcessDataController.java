package com.moh.phlat.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDatas() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", processDataRepository.findAll()));
	}

	// get process data by control id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/controltableid/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDataByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required =false) String filterStatus) {
		
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Process Data not found for control_id: " + controlTableId, "[]"));
		}

		if (filterStatus == null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
				processDataRepository.getAllProcessDataByControlTableId(controlTableId)));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
					processDataRepository.findByControlTableIdAndRowstatusCode(controlTableId, filterStatus)));
		}
		
	}

	// get specific row by id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
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

		
	// update an existing record
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateProcessDataById(@RequestBody @Valid ProcessData reqProcessData,
			@PathVariable Long id) {

		Optional<ProcessData> _processData = processDataRepository.findById(id);

		if (_processData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Process data not found with id: " + id, "[]"));
		}

		ProcessData processData = _processData.get();

		if (reqProcessData.getDoNotLoad() != null)
			processData.setDoNotLoad(reqProcessData.getDoNotLoad());
		
		if (reqProcessData.getStakeholder() != null)
			processData.setStakeholder(reqProcessData.getStakeholder());
		
		if (reqProcessData.getHdsIpcId() != null)
			processData.setHdsIpcId(reqProcessData.getHdsIpcId());

		if (reqProcessData.getHdsCpnId()!= null)
			processData.setHdsCpnId(reqProcessData.getHdsCpnId());

		if (reqProcessData.getHdsProviderIdentifier1() != null)
			processData.setHdsProviderIdentifier1(reqProcessData.getHdsProviderIdentifier1());
		
		if (reqProcessData.getHdsProviderIdentifier2() != null)
			processData.setHdsProviderIdentifier2(reqProcessData.getHdsProviderIdentifier2());

		if (reqProcessData.getHdsProviderIdentifier3() != null)
			processData.setHdsProviderIdentifier3(reqProcessData.getHdsProviderIdentifier3());
		
		if (reqProcessData.getHdsProviderIdentifierType1() != null)
			processData.setHdsProviderIdentifierType1(reqProcessData.getHdsProviderIdentifierType1());
		
		if (reqProcessData.getHdsProviderIdentifierType2() != null)
			processData.setHdsProviderIdentifierType2(reqProcessData.getHdsProviderIdentifierType2());
		
		if (reqProcessData.getHdsProviderIdentifierType3() != null)
			processData.setHdsProviderIdentifierType3(reqProcessData.getHdsProviderIdentifierType3());
		
		if (reqProcessData.getHdsType() != null)
			processData.setHdsType(reqProcessData.getHdsType());
		
		if (reqProcessData.getHdsName() != null)
			processData.setHdsName(reqProcessData.getHdsName());
		
		if (reqProcessData.getHdsNameAlias() != null)
			processData.setHdsNameAlias(reqProcessData.getHdsNameAlias());
		
		if (reqProcessData.getHdsPreferredNameFlag() != null)
			processData.setHdsPreferredNameFlag(reqProcessData.getHdsPreferredNameFlag());
		
		if (reqProcessData.getHdsWebsite() != null)
			processData.setHdsEmail(reqProcessData.getHdsEmail());
		
		if (reqProcessData.getHdsWebsite() != null)
			processData.setHdsWebsite(reqProcessData.getHdsWebsite());

		if (reqProcessData.getHdsBusTelAreaCode() != null)
			processData.setHdsBusTelAreaCode(reqProcessData.getHdsBusTelAreaCode());
		
		if (reqProcessData.getHdsBusTelNumber() != null)
			processData.setHdsBusTelNumber(reqProcessData.getHdsBusTelNumber());
		
		if (reqProcessData.getHdsTelExtension() != null)
			processData.setHdsTelExtension(reqProcessData.getHdsTelExtension());
		
		if (reqProcessData.getHdsCellAreaCode() != null)
			processData.setHdsCellAreaCode(reqProcessData.getHdsCellAreaCode());
		
		if (reqProcessData.getHdsCellNumber() != null)
			processData.setHdsCellNumber(reqProcessData.getHdsCellNumber());
		
		if (reqProcessData.getHdsFaxAreaCode() != null)
			processData.setHdsFaxAreaCode(reqProcessData.getHdsFaxAreaCode());
		
		if (reqProcessData.getHdsFaxNumber() != null)
			processData.setHdsFaxNumber(reqProcessData.getHdsFaxNumber());
		
		if (reqProcessData.getHdsServiceDeliveryType() != null)
			processData.setHdsServiceDeliveryType(reqProcessData.getHdsServiceDeliveryType());
		
		if (reqProcessData.getPcnClinicType() != null)
			processData.setPcnClinicType(reqProcessData.getPcnClinicType());
		
		if (reqProcessData.getPcnPciFlag() != null)
			processData.setPcnPciFlag(reqProcessData.getPcnPciFlag());
		
		if (reqProcessData.getHdsHoursOfOperation() != null)
			processData.setHdsHoursOfOperation(reqProcessData.getHdsHoursOfOperation());
		
		if (reqProcessData.getHdsContactName() != null)
			processData.setHdsContactName(reqProcessData.getHdsContactName());
		
		if (reqProcessData.getHdsIsForProfitFlag() != null)
			processData.setHdsIsForProfitFlag(reqProcessData.getHdsIsForProfitFlag());
		
		if (reqProcessData.getSourceStatus() != null)
			processData.setSourceStatus(reqProcessData.getSourceStatus());
		
		if (reqProcessData.getHdsParentIpcId() != null)
			processData.setHdsParentIpcId(reqProcessData.getHdsParentIpcId());
		
		if (reqProcessData.getBusIpcId() != null)
			processData.setBusIpcId(reqProcessData.getBusIpcId());
		
		if (reqProcessData.getBusCpnId() != null)
			processData.setBusCpnId(reqProcessData.getBusCpnId());
		
		if (reqProcessData.getBusName() != null)
			processData.setBusName(reqProcessData.getBusName());
		
		if (reqProcessData.getBusLegalName() != null)
			processData.setBusLegalName(reqProcessData.getBusLegalName());
		
		if (reqProcessData.getBusPayeeNumber() != null)
			processData.setBusPayeeNumber(reqProcessData.getBusPayeeNumber());
		
		if (reqProcessData.getBusOwnerName() != null)
			processData.setBusOwnerName(reqProcessData.getBusOwnerName());
		
		if (reqProcessData.getBusOwnerType() != null)
			processData.setBusOwnerType(reqProcessData.getBusOwnerType());
		
		if (reqProcessData.getBusOwnerTypeOther() != null)
			processData.setBusOwnerTypeOther(reqProcessData.getBusOwnerTypeOther());
		
		if (reqProcessData.getFacBuildingName() != null)
			processData.setFacBuildingName(reqProcessData.getFacBuildingName());
		
		if (reqProcessData.getFacilityHdsDetailsAdditionalInfo() != null)
			processData.setFacilityHdsDetailsAdditionalInfo(reqProcessData.getFacilityHdsDetailsAdditionalInfo());
		
		if (reqProcessData.getPhysicalAddr1() != null)
			processData.setPhysicalAddr1(reqProcessData.getPhysicalAddr1());
		
		if (reqProcessData.getPhysicalAddr2() != null)
			processData.setPhysicalAddr2(reqProcessData.getPhysicalAddr2());
		
		if (reqProcessData.getPhysicalAddr3() != null)
			processData.setPhysicalAddr3(reqProcessData.getPhysicalAddr3());
		
		if (reqProcessData.getPhysicalAddr4() != null)
			processData.setPhysicalAddr4(reqProcessData.getPhysicalAddr4());
		
		if (reqProcessData.getPhysicalCity() != null)
			processData.setPhysicalCity(reqProcessData.getPhysicalCity());

		if (reqProcessData.getPhysicalProvince() != null)
			processData.setPhysicalProvince(reqProcessData.getPhysicalProvince());

		if (reqProcessData.getPhysicalPcode() != null)
			processData.setPhysicalPcode(reqProcessData.getPhysicalPcode());

		if (reqProcessData.getPhysicalCountry() != null)
			processData.setPhysicalCountry(reqProcessData.getPhysicalCountry());
		
		if (reqProcessData.getPhysAddrIsPrivate() != null)
			processData.setPhysAddrIsPrivate(reqProcessData.getPhysAddrIsPrivate());

		if (reqProcessData.getMailAddr1() != null)
			processData.setMailAddr1(reqProcessData.getMailAddr1());

		if (reqProcessData.getMailAddr2() != null)
			processData.setMailAddr2(reqProcessData.getMailAddr2());

		
		if (reqProcessData.getMailAddr3() != null)
			processData.setMailAddr3(reqProcessData.getMailAddr3());
		
		if (reqProcessData.getMailAddr4() != null)
			processData.setMailAddr4(reqProcessData.getMailAddr4());

		if (reqProcessData.getMailCity() != null)
			processData.setMailCity(reqProcessData.getMailCity());
		
		if (reqProcessData.getMailBc() != null)
			processData.setMailBc(reqProcessData.getMailBc());
		
		if (reqProcessData.getMailPcode() != null)
			processData.setMailPcode(reqProcessData.getMailPcode());
		
		if (reqProcessData.getMailCountry() != null)
			processData.setMailCountry(reqProcessData.getMailCountry());
		
		if (reqProcessData.getMailAddrIsPrivate() != null)
			processData.setMailAddrIsPrivate(reqProcessData.getMailAddrIsPrivate());
		
		if (reqProcessData.getRowstatusCode() != null)
			processData.setRowstatusCode(reqProcessData.getRowstatusCode());

		processData.setUpdatedBy(AuthenticationUtils.getAuthenticatedUserId());
		processData.setUpdatedAt(new Date());

		try {
			processDataRepository.save(processData);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Record updated sucessfully.", processData));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while updating Process Data with id: " + id, "[]"));
		}
	}


	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getformfields/header")
	public String getAllHeader() {
		return dbUtilityService.getVariablesByTableNameSortedById("PROCESS_DATA");
	}

	// get specific row by id
	@PutMapping("/validate/{id}")
	public ResponseEntity<ResponseMessage> validateProcessDataById(@PathVariable Long id) {

		Optional<ProcessData> _processData = processDataRepository.findById(id);
		String authenticatedUserId=AuthenticationUtils.getAuthenticatedUserId();
		if (_processData.isPresent()) {
			ProcessData processData = _processData.get();

			Optional<Control> _control = controlRepository.findById(processData.getControlTableId());
			if (_control.isPresent()) {
				Control control = _control.get();
				
				dbUtilityService.setControlStatus(processData.getControlTableId(), "PRE-VALIDATION_IN_PROGRESS",authenticatedUserId );
				
				if ((!processData.getDoNotLoad().equals("Y")) && (!processData.getRowstatusCode().equals("DO_NOT_LOAD")) && (!processData.getRowstatusCode().equals("COMPLETE"))) {
					logger.info("validate process data with id: " + id);
				    // run asyn process
					dbUtilityService.validateProcessData(control, processData,authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: " + id);
				}

				dbUtilityService.setControlStatus(processData.getControlTableId(), "PRE-VALIDATION_COMPLETED",
												  authenticatedUserId);
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
						controlRepository.findById(processData.getControlTableId())));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage("error", 404, "Control Id not found.", "[]"));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/validateallbycontroltableid/{id}")
	public ResponseEntity<ResponseMessage> validateAllProcessData(@PathVariable Long id) {

		
		List<ProcessData> _processDataList = processDataRepository.getAllProcessDataByControlTableId(id);
		
		if (_processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Nothing to validate.", "[]"));
			
		}
		String authenticatedUserId= AuthenticationUtils.getAuthenticatedUserId();
		dbUtilityService.setControlStatus(id, "PRE-VALIDATION_IN_PROGRESS",
										  authenticatedUserId);
		// asynchronous operation
		dbUtilityService.validateProcessDataByControlTableId(id,authenticatedUserId);

		Optional<Control> _control = controlRepository.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Validation process started!",_control));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
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
		String authenticatedUserId= AuthenticationUtils.getAuthenticatedUserId();
		dbUtilityService.setControlStatus(controlTableId, "PLR_LOAD_IN_PROGRESS",
										  authenticatedUserId);
		// asynchronous operation
		dbUtilityService.loadProcessDataToPlr(controlTableId,authenticatedUserId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "PLR load process started!",_control));
	}
}
