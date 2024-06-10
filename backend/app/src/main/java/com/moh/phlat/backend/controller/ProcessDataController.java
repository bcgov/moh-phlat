package com.moh.phlat.backend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.moh.phlat.backend.service.ProcessDataService;
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
import com.moh.phlat.backend.service.DbUtilityServiceImpl.ReportSummary;

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

	@Autowired
	private ProcessDataService processDataService;

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDatas() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", processDataRepository.findAll()));
	}


	// get process data by control id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/controltable/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDataByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required = false) String rowStatus, @RequestParam(required =false) List<String> id,
			@RequestParam(required =false) List<String> actions, @RequestParam(required =false) List<String> rowStatusCode,
			@RequestParam(required =false) List<String> doNotLoad, @RequestParam(required =false) List<String> stakeholder,
			@RequestParam(required =false) List<String> hdsLpcId, @RequestParam(required =false) List<String> hdsCpnId,
			@RequestParam(required =false) List<String> hdsProviderId1, @RequestParam(required =false) List<String> hdsProviderId2,
			@RequestParam(required =false) List<String> hdsProviderId3, @RequestParam(required =false) List<String> hdsProviderIdType1,
			@RequestParam(required =false) List<String> hdsProviderIdType2, @RequestParam(required =false) List<String> hdsProviderIdType3,
			@RequestParam(required =false) List<String> hdsHibcFacId, @RequestParam(required =false) List<String> hdsType,
			@RequestParam(required =false) List<String> hdsName, @RequestParam(required =false) List<String> hdsNameAlias,
			@RequestParam(required =false) List<String> hdsPrefNameFlag, @RequestParam(required =false) List<String> hdsEmail,
			@RequestParam(required =false) List<String> hdsWebsite, @RequestParam(required =false) List<String> hdsBusTelAreaCode,
			@RequestParam(required =false) List<String> hdsBusTelNum, @RequestParam(required =false) List<String> hdsTelExt,
			@RequestParam(required =false) List<String> hdsCellAreaCode, @RequestParam(required =false) List<String> hdsCellNum,
			@RequestParam(required =false) List<String> hdsFaxAreaCode, @RequestParam(required =false) List<String> hdsFaxNum,
			@RequestParam(required =false) List<String> hdsServiceDelType, @RequestParam(required =false) List<String> pcnCLinicType,
			@RequestParam(required =false) List<String> pcnPciFlag, @RequestParam(required =false) List<String> hdsHoursOfOp,
			@RequestParam(required =false) List<String> hdsContactName, @RequestParam(required =false) List<String> hdsIsForProfitFlag,
			@RequestParam(required =false) List<String> sourceStatus, @RequestParam(required =false) List<String> hdsParentIpcId,
			@RequestParam(required =false) List<String> busIpcId, @RequestParam(required =false) List<String> busCpnId,
			@RequestParam(required =false) List<String> busName, @RequestParam(required =false) List<String> busLegalName,
			@RequestParam(required =false) List<String> busPayeeNum, @RequestParam(required =false) List<String> busOwnerName,
			@RequestParam(required =false) List<String> busOwnerType, @RequestParam(required =false) List<String> busOwnerTypeOther,
			@RequestParam(required =false) List<String> facBuildingName, @RequestParam(required =false) List<String> facHdsDetailAddInfo,
			@RequestParam(required =false) List<String> physAddr1, @RequestParam(required =false) List<String> physAddr2,
			@RequestParam(required =false) List<String> physAddr3, @RequestParam(required =false) List<String> physAddr4,
			@RequestParam(required =false) List<String> physCity, @RequestParam(required =false) List<String> physProv,
			@RequestParam(required =false) List<String> physPCode, @RequestParam(required =false) List<String> physCountry,
			@RequestParam(required =false) List<String> physAddrIsPrivate, @RequestParam(required =false) List<String> mailAddr1,
			@RequestParam(required =false) List<String> mailAddr2, @RequestParam(required =false) List<String> mailAddr3,
			@RequestParam(required =false) List<String> mailAddr4, @RequestParam(required =false) List<String> mailCity,
			@RequestParam(required =false) List<String> mailBc, @RequestParam(required =false) List<String> mailPcode,
			@RequestParam(required =false) List<String> mailCountry, @RequestParam(required =false) List<String> mailAddrIsPriv,
			@RequestParam(required =false) List<String> messages) {

		//TODO this should be replaced by call to ControlService which is not yet introduced
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Process Data not found for control_id: " + controlTableId, "[]"));
		}

		if (rowStatus == null && id == null && actions == null && rowStatusCode == null && messages== null && doNotLoad == null && stakeholder == null && 
				hdsLpcId == null && hdsCpnId == null && hdsProviderId1 == null && hdsProviderId2 == null && hdsProviderId3 == null && 
				hdsProviderIdType1 == null && hdsProviderIdType2 == null && hdsProviderIdType3 == null && hdsHibcFacId == null && hdsType == null && 
				hdsName == null && hdsNameAlias == null && hdsPrefNameFlag == null && hdsEmail == null && hdsWebsite == null && 
				hdsBusTelAreaCode == null && hdsBusTelNum == null && hdsTelExt == null && hdsCellAreaCode == null && hdsCellNum == null && 
				hdsFaxAreaCode == null && hdsFaxNum == null && hdsServiceDelType == null && pcnCLinicType == null && pcnPciFlag == null && 
				hdsHoursOfOp == null && hdsContactName == null && hdsIsForProfitFlag == null && sourceStatus == null && hdsParentIpcId == null && 
				busIpcId == null && busCpnId == null && busName == null && busLegalName == null && busPayeeNum == null && busOwnerName == null && 
				busOwnerType == null && busOwnerTypeOther == null && facBuildingName == null && facHdsDetailAddInfo == null && physAddr1 == null && 
				physAddr2 == null && physAddr3 == null && physAddr4 == null && physCity == null && physProv == null && physPCode == null && 
				physCountry == null && physAddrIsPrivate == null && mailAddr1 == null && mailAddr2 == null && mailAddr3 == null && mailAddr4 == null && 
				mailCity == null && mailBc == null && mailPcode == null && mailCountry == null && mailAddrIsPriv == null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
				processDataRepository.getAllProcessDataByControlTableId(controlTableId)));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", 
					processDataRepository.findAll(ProcessDataRepository.buildSpecificationIn(controlTableId, rowStatus, id, actions,  rowStatusCode, 
							messages, doNotLoad, stakeholder, hdsLpcId, hdsCpnId, hdsProviderId1, hdsProviderId2, hdsProviderId3, hdsProviderIdType1, 
							hdsProviderIdType2, hdsProviderIdType3, hdsHibcFacId, hdsType, hdsName, hdsNameAlias, hdsPrefNameFlag, hdsEmail,
							hdsWebsite, hdsBusTelAreaCode, hdsBusTelNum, hdsTelExt, hdsCellAreaCode, hdsCellNum, hdsFaxAreaCode, hdsFaxNum,
							hdsServiceDelType, pcnCLinicType, pcnPciFlag, hdsHoursOfOp, hdsContactName, hdsIsForProfitFlag,
							sourceStatus, hdsParentIpcId, busIpcId, busCpnId, busName, busLegalName, busPayeeNum, busOwnerName,
							busOwnerType, busOwnerTypeOther, facBuildingName, facHdsDetailAddInfo, physAddr1, physAddr2,
							physAddr3, physAddr4, physCity, physProv, physPCode, physCountry, physAddrIsPrivate, mailAddr1,
							mailAddr2, mailAddr3, mailAddr4, mailCity, mailBc, mailPcode, mailCountry, mailAddrIsPriv))));
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
			logger.error("Error occured: {}", e.getMessage(), e);
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
					logger.info("validate process data with id: {}", id);
				    // run asyn process
					dbUtilityService.validateProcessData(control, processData,authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", id);
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

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/reportsummary/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getReportSummaryByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required = false) String rowStatus) {
		
		List<ReportSummary> _list = dbUtilityService.getReportSummary(controlTableId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", _list));
	}
	
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getColumnList/{controlTableId}")
	public ResponseEntity<ResponseMessage> getColumnLists(@PathVariable Long controlTableId, @RequestParam(required = false) String columnKey) {

		List<String> _processData = new ArrayList();

		switch(columnKey) {
			case "id":
				_processData = processDataRepository.findAllDistinctId(controlTableId);
				break;
			case "controlTableId":
				_processData = processDataRepository.findAllDistinctControlId(controlTableId);
				break;
			case "do_not_load":
				_processData = processDataRepository.findAllDistinctDoNotLoad(controlTableId);
				break;
			case "stakeholder":
				_processData = processDataRepository.findAllDistinctStakeholder(controlTableId);
				break;
			case "hdsIpcId":
				_processData = processDataRepository.findAllDistinctHdsIpcId(controlTableId);
				break;
			case "hdsCpnId":
				_processData = processDataRepository.findAllDistinctHdsCpnId(controlTableId);
				break;
			case "hdsProviderIdentifier1":
				_processData = processDataRepository.findAllDistinctHdsProviderIdentifier1(controlTableId);
				break;
			case "hdsProviderIdentifier2":
				_processData = processDataRepository.findAllDistinctHdsProviderIdentifier2(controlTableId);
				break;
			case "hdsProviderIdentifier3":
				_processData = processDataRepository.findAllDistinctHdsProviderIdentifier3(controlTableId);
				break;
			case "hdsProviderIdentifierType1":
				_processData = processDataRepository.findAllDistinctHdsProviderIdentifierType1(controlTableId);
				break;
			case "hdsProviderIdentifierType2":
				_processData = processDataRepository.findAllDistinctHdsProviderIdentifierType2(controlTableId);
				break;
			case "hdsProviderIdentifierType3":
				_processData = processDataRepository.findAllDistinctHdsProviderIdentifierType3(controlTableId);
				break;
			case "hdsMspFacilityNumber":
				_processData = processDataRepository.findAllDistinctHdsHibcFacilityId(controlTableId);
				break;
			case "hdsType":
				_processData = processDataRepository.findAllDistinctHdsType(controlTableId);
				break;
			case "hdsName":
				_processData = processDataRepository.findAllDistinctHdsName(controlTableId);
				break;
			case "hds_name_alias":
				_processData = processDataRepository.findAllDistinctHdsNameAlias(controlTableId);
				break;
			case "hdsPreferredNameFlag":
				_processData = processDataRepository.findAllDistinctHdsPreferredNameFlag(controlTableId);
				break;
			case "hdsEmail":
				_processData = processDataRepository.findAllDistinctHdsEmail(controlTableId);
				break;
			case "hdsWebsite":
				_processData = processDataRepository.findAllDistinctHdsWebsite(controlTableId);
				break;
			case "hdsBusTelAreaCode":
				_processData = processDataRepository.findAllDistinctHdsBusTelAreaCode(controlTableId);
				break;
			case "hdsBusTelNumber":
				_processData = processDataRepository.findAllDistinctHdsBusTelNumber(controlTableId);
				break;
			case "hdsTelExtension":
				_processData = processDataRepository.findAllDistinctHdsTelExtension(controlTableId);
				break;
			case "hdsCellAreaCode":
				_processData = processDataRepository.findAllDistinctHdsCellAreaCode(controlTableId);
				break;
			case "hdsCellNumber":
				_processData = processDataRepository.findAllDistinctHdsCellNumber(controlTableId);
				break;
			case "hdsFaxAreaCode":
				_processData = processDataRepository.findAllDistinctHdsFaxAreaCode(controlTableId);
				break;
			case "hdsFaxNumber":
				_processData = processDataRepository.findAllDistinctHdsFaxNumber(controlTableId);
				break;
			case "pcnServiceDeliveryType":
				_processData = processDataRepository.findAllDistinctHdsServiceDeliveryType(controlTableId);
				break;
			case "pcnClinicType":
				_processData = processDataRepository.findAllDistinctPcnClinicType(controlTableId);
				break;
			case "pcnPciFlag":
				_processData = processDataRepository.findAllDistinctPcnPciFlag(controlTableId);
				break;
			case "hdsHoursOfOperation":
				_processData = processDataRepository.findAllDistinctHdsHoursOfOperation(controlTableId);
				break;
			case "hdsContactName":
				_processData = processDataRepository.findAllDistinctHdsContactName(controlTableId);
				break;
			case "hdsIsForProfitFlag":
				_processData = processDataRepository.findAllDistinctHdsIsForProfitFlag(controlTableId);
				break;
			case "sourceStatus":
				_processData = processDataRepository.findAllDistinctSourceStatus(controlTableId);
				break;
			case "hdsParentIpcId":
				_processData = processDataRepository.findAllDistinctHdsParentIpcId(controlTableId);
				break;
			case "busIpcId":
				_processData = processDataRepository.findAllDistinctBusIpcId(controlTableId);
				break;
			case "busCpnId":
				_processData = processDataRepository.findAllDistinctBusCpnId(controlTableId);
				break;
			case "busName":
				_processData = processDataRepository.findAllDistinctBusName(controlTableId);
				break;
			case "busLegalName":
				_processData = processDataRepository.findAllDistinctBusLegalName(controlTableId);
				break;
			case "busPayeeNumber":
				_processData = processDataRepository.findAllDistinctBusPayeeNumber(controlTableId);
				break;
			case "busOwnerName":
				_processData = processDataRepository.findAllDistinctBusOwnerName(controlTableId);
				break;
			case "busOwnerType":
				_processData = processDataRepository.findAllDistinctBusOwnerType(controlTableId);
				break;
			case "busOwnerTypeOther":
				_processData = processDataRepository.findAllDistinctBusOwnerTypeOther(controlTableId);
				break;
			case "facBuildingName":
				_processData = processDataRepository.findAllDistinctFacBuildingName(controlTableId);
				break;
			case "facilityHdsDetailsAdditionalInfo":
				_processData = processDataRepository.findAllDistinctFacilityHdsDetailsAdditionalInfo(controlTableId);
				break;
			case "physicalAddr1":
				_processData = processDataRepository.findAllDistinctPhysicalAddr1(controlTableId);
				break;
			case "physicalAddr2":
				_processData = processDataRepository.findAllDistinctPhysicalAddr2(controlTableId);
				break;
			case "physicalAddr3":
				_processData = processDataRepository.findAllDistinctPhysicalAddr3(controlTableId);
				break;
			case "physicalAddr":
				_processData = processDataRepository.findAllDistinctPhysicalAddr4(controlTableId);
				break;
			case "physicalCity":
				_processData = processDataRepository.findAllDistinctPhysicalCity(controlTableId);
				break;
			case "physicalProvince":
				_processData = processDataRepository.findAllDistinctPhysicalProvince(controlTableId);
				break;
			case "physicalPcode":
				_processData = processDataRepository.findAllDistinctPhysicalPcode(controlTableId);
				break;
			case "physicalCountry":
				_processData = processDataRepository.findAllDistinctPhysicalCountry(controlTableId);
				break;
			case "physAddrIsPrivate":
				_processData = processDataRepository.findAllDistinctPhysAddrIsPrivate(controlTableId);
				break;
			case "mailAddr1":
				_processData = processDataRepository.findAllDistinctMailAddr1(controlTableId);
				break;
			case "mailAddr2":
				_processData = processDataRepository.findAllDistinctMailAddr2(controlTableId);
				break;
			case "mailAddr3":
				_processData = processDataRepository.findAllDistinctMailAddr3(controlTableId);
				break;
			case "mailAddr4":
				_processData = processDataRepository.findAllDistinctMailAddr4(controlTableId);
				break;
			case "mailCity":
				_processData = processDataRepository.findAllDistinctMailCity(controlTableId);
				break;
			case "mailBc":
				_processData = processDataRepository.findAllDistinctMailBc(controlTableId);
				break;
			case "mailPcode":
				_processData = processDataRepository.findAllDistinctMailPcode(controlTableId);
				break;
			case "mailCountry":
				_processData = processDataRepository.findAllDistinctMailCountry(controlTableId);
				break;
			case "mailAddrIsPrivate":
				_processData = processDataRepository.findAllDistinctMailAddrIsPrivate(controlTableId);
				break;
			default:
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error", 404, "Column not found.", 
						_processData));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Retrieved the items in the column.", 
				_processData));
	}
}
