package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.ProcessDataFilterParams;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.ProcessDataService;
import com.moh.phlat.backend.service.RowStatusService;
import com.moh.phlat.backend.service.TableColumnInfoService;
import com.moh.phlat.backend.service.dto.ColumnDisplayName;
import com.moh.phlat.backend.service.dto.ReportSummary;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private TableColumnInfoService tableColumnInfoService;	

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDatas() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", null, processDataRepository.findAll()));
	}

	// get process data by control id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PostMapping("/controltable/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDataByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required = false) String rowStatus, @RequestParam(required = true) int page, 
			@RequestParam(required = true) int itemsPerPage, @RequestBody ProcessDataFilterParams filterProcess) {

		//TODO this should be replaced by call to ControlService which is not yet introduced
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Process Data not found for control_id: " + controlTableId, null, "[]"));
		} else if (page < 1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("success", 400,
					"Page needs to be larger than 0.", null, "[]"));
		}
		
		List<Order> sortOrders = new ArrayList<>();
		for(Map.Entry<String,String> entry:filterProcess.getSort().entrySet()) {
			if("asc".equals(entry.getValue()) || "desc".equals(entry.getValue())) {
				sortOrders.add(new Order(entry.getValue().equals("asc")?Sort.Direction.ASC:Sort.Direction.DESC, entry.getKey()));
			}
		}
		
		Page<ProcessData> processDataPage = processDataService.getProcessDataWithMessages(controlTableId, rowStatus, page, itemsPerPage, filterProcess, sortOrders);

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
				processDataPage.getTotalElements(), processDataPage.getContent()));
	}

	// get specific row by id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getProcessDataById(@PathVariable Long id) {
		Optional<ProcessData> processData = processDataRepository.findById(id);
		if (processData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Process Data not found for id: " + id, null, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", null, processDataRepository.findById(id)));

	}

		
	// update an existing record
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateProcessDataById(@RequestBody @Valid ProcessData reqProcessData,
			@PathVariable Long id) {

		String authenticatedUserId=AuthenticationUtils.getAuthenticatedUserId();
		Optional<ProcessData> processDataTable = processDataRepository.findById(id);

		if (processDataTable.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Process data not found with id: " + id, null, "[]"));
		}

		ProcessData processData = processDataTable.get();

		if (StringUtils.hasText(reqProcessData.getDoNotLoadFlag()))
			processData.setDoNotLoadFlag(reqProcessData.getDoNotLoadFlag());
		
		if (StringUtils.hasText(reqProcessData.getStakeholder()))
			processData.setStakeholder(reqProcessData.getStakeholder());
		
		if (StringUtils.hasText(reqProcessData.getStakeholderId()))
			processData.setStakeholderId(reqProcessData.getStakeholderId());

		if (StringUtils.hasText(reqProcessData.getHdsIpcId()))
			processData.setHdsIpcId(reqProcessData.getHdsIpcId());

		if (StringUtils.hasText(reqProcessData.getHdsCpnId()))
			processData.setHdsCpnId(reqProcessData.getHdsCpnId());

		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifier1()))
			processData.setHdsProviderIdentifier1(reqProcessData.getHdsProviderIdentifier1());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifier2()))
			processData.setHdsProviderIdentifier2(reqProcessData.getHdsProviderIdentifier2());

		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifier3()))
			processData.setHdsProviderIdentifier3(reqProcessData.getHdsProviderIdentifier3());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifierType1()))
			processData.setHdsProviderIdentifierType1(reqProcessData.getHdsProviderIdentifierType1());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifierType2()))
			processData.setHdsProviderIdentifierType2(reqProcessData.getHdsProviderIdentifierType2());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifierType3()))
			processData.setHdsProviderIdentifierType3(reqProcessData.getHdsProviderIdentifierType3());

		if (StringUtils.hasText(reqProcessData.getHdsMspFacilityNumber()))
			processData.setHdsMspFacilityNumber(reqProcessData.getHdsMspFacilityNumber());

		if (StringUtils.hasText(reqProcessData.getHdsPauthId()))
			processData.setHdsPauthId(reqProcessData.getHdsPauthId());

		if (StringUtils.hasText(reqProcessData.getHdsCategoryCode()))
			processData.setHdsCategoryCode(reqProcessData.getHdsCategoryCode());

		if (StringUtils.hasText(reqProcessData.getHdsRoleTypeCode()))
			processData.setHdsRoleTypeCode(reqProcessData.getHdsRoleTypeCode());

		if (StringUtils.hasText(reqProcessData.getHdsType()))
			processData.setHdsType(reqProcessData.getHdsType());

		if (StringUtils.hasText(reqProcessData.getHdsSubType()))
			processData.setHdsSubType(reqProcessData.getHdsSubType());			
		
		if (StringUtils.hasText(reqProcessData.getHdsUserChid()))
			processData.setHdsUserChid(reqProcessData.getHdsUserChid());		
		
		if (StringUtils.hasText(reqProcessData.getHdsCreatedDts()))
			processData.setHdsCreatedDts(reqProcessData.getHdsCreatedDts());	

		if (StringUtils.hasText(reqProcessData.getHdsInvalidatedDts()))
			processData.setHdsInvalidatedDts(reqProcessData.getHdsInvalidatedDts());	

		if (StringUtils.hasText(reqProcessData.getHdsName()))
			processData.setHdsName(reqProcessData.getHdsName());
	
		if (StringUtils.hasText(reqProcessData.getHdsPreferredNameFlag()))
			processData.setHdsPreferredNameFlag(reqProcessData.getHdsPreferredNameFlag());
		
		if (StringUtils.hasText(reqProcessData.getHdsEmail()))
			processData.setHdsEmail(reqProcessData.getHdsEmail());
		
		if (reqProcessData.getHdsWebsite() != null)
			processData.setHdsWebsite(reqProcessData.getHdsWebsite());

		if (StringUtils.hasText(reqProcessData.getHdsBusTelAreaCode()))
			processData.setHdsBusTelAreaCode(reqProcessData.getHdsBusTelAreaCode());
		
		if (StringUtils.hasText(reqProcessData.getHdsBusTelNumber()))
			processData.setHdsBusTelNumber(reqProcessData.getHdsBusTelNumber());
		
		if (StringUtils.hasText(reqProcessData.getHdsTelExtension()))
			processData.setHdsTelExtension(reqProcessData.getHdsTelExtension());
		
		if (StringUtils.hasText(reqProcessData.getHdsCellAreaCode()))
			processData.setHdsCellAreaCode(reqProcessData.getHdsCellAreaCode());
		
		if (StringUtils.hasText(reqProcessData.getHdsCellNumber()))
			processData.setHdsCellNumber(reqProcessData.getHdsCellNumber());
		
		if (StringUtils.hasText(reqProcessData.getHdsFaxAreaCode()))
			processData.setHdsFaxAreaCode(reqProcessData.getHdsFaxAreaCode());
		
		if (StringUtils.hasText(reqProcessData.getHdsFaxNumber()))
			processData.setHdsFaxNumber(reqProcessData.getHdsFaxNumber());
		
		if (StringUtils.hasText(reqProcessData.getPcnServiceDeliveryType()))
			processData.setPcnServiceDeliveryType(reqProcessData.getPcnServiceDeliveryType());	

		if (StringUtils.hasText(reqProcessData.getPcnClinicType()))
			processData.setPcnClinicType(reqProcessData.getPcnClinicType());
		
		if (StringUtils.hasText(reqProcessData.getPcnPciFlag()))
			processData.setPcnPciFlag(reqProcessData.getPcnPciFlag());
		
		if (StringUtils.hasText(reqProcessData.getSourceStatus()))
			processData.setSourceStatus(reqProcessData.getSourceStatus());
		
		if (StringUtils.hasText(reqProcessData.getPcnClinicStatus()))
			processData.setPcnClinicStatus(reqProcessData.getPcnClinicStatus());

		if (StringUtils.hasText(reqProcessData.getHdsEffectiveStartDate()))
			processData.setHdsEffectiveStartDate(reqProcessData.getHdsEffectiveStartDate());

		if (StringUtils.hasText(reqProcessData.getHdsEffectiveEndDate()))
			processData.setHdsEffectiveEndDate(reqProcessData.getHdsEffectiveEndDate());	

		if (StringUtils.hasText(reqProcessData.getFacAddressUnit()))
			processData.setFacAddressUnit(reqProcessData.getFacAddressUnit());	

		if (StringUtils.hasText(reqProcessData.getFacBuildingName()))
			processData.setFacBuildingName(reqProcessData.getFacBuildingName());	

		if (StringUtils.hasText(reqProcessData.getFacCivicAddrId()))
			processData.setFacCivicAddrId(reqProcessData.getFacCivicAddrId());	

		if (StringUtils.hasText(reqProcessData.getFacCivicAddr()))
			processData.setFacCivicAddr(reqProcessData.getFacCivicAddr());	

		if (StringUtils.hasText(reqProcessData.getFacLatitude()))
			processData.setFacLatitude(reqProcessData.getFacLatitude());	

		if (StringUtils.hasText(reqProcessData.getFacLongitude()))
			processData.setFacLongitude(reqProcessData.getFacLongitude());	

		if (StringUtils.hasText(reqProcessData.getFacStreetDirection()))
			processData.setFacStreetDirection(reqProcessData.getFacStreetDirection());	

		if (StringUtils.hasText(reqProcessData.getStreetDirectionPrefix()))
			processData.setStreetDirectionPrefix(reqProcessData.getStreetDirectionPrefix());	

		if (StringUtils.hasText(reqProcessData.getStreetTypePrefix()))
			processData.setStreetTypePrefix(reqProcessData.getStreetTypePrefix());	

		if (StringUtils.hasText(reqProcessData.getFacCivicNumber()))
			processData.setFacCivicNumber(reqProcessData.getFacCivicNumber());

		if (StringUtils.hasText(reqProcessData.getFacStreetName()))
			processData.setFacStreetName(reqProcessData.getFacStreetName());

		if (StringUtils.hasText(reqProcessData.getFacStreetType()))
			processData.setFacStreetType(reqProcessData.getFacStreetType());

		if (StringUtils.hasText(reqProcessData.getFacLocalityName()))
			processData.setFacLocalityName(reqProcessData.getFacLocalityName());

		if (StringUtils.hasText(reqProcessData.getFacProvinceCode()))
			processData.setFacProvinceCode(reqProcessData.getFacProvinceCode());

		if (StringUtils.hasText(reqProcessData.getFacSiteId()))
			processData.setFacSiteId(reqProcessData.getFacSiteId());

		if (StringUtils.hasText(reqProcessData.getFacScore()))
			processData.setFacScore(reqProcessData.getFacScore());

		if (StringUtils.hasText(reqProcessData.getFacMatchPrecision()))
			processData.setFacMatchPrecision(reqProcessData.getFacMatchPrecision());

		if (StringUtils.hasText(reqProcessData.getFacPrecisionPoints()))
			processData.setFacPrecisionPoints(reqProcessData.getFacPrecisionPoints());

		if (StringUtils.hasText(reqProcessData.getFacHsdaName()))
			processData.setFacHsdaName(reqProcessData.getFacHsdaName());

		if (StringUtils.hasText(reqProcessData.getFacDatabcResults()))
			processData.setFacDatabcResults(reqProcessData.getFacDatabcResults());

		if (StringUtils.hasText(reqProcessData.getFacPcnCode()))
			processData.setFacPcnCode(reqProcessData.getFacPcnCode());

		if (StringUtils.hasText(reqProcessData.getFacPcnName()))
			processData.setFacPcnName(reqProcessData.getFacPcnName());

		if (StringUtils.hasText(reqProcessData.getFacChsaStatus()))
			processData.setFacChsaStatus(reqProcessData.getFacChsaStatus());

		if (StringUtils.hasText(reqProcessData.getFacPcnStatus()))
			processData.setFacPcnStatus(reqProcessData.getFacPcnStatus());

		if (StringUtils.hasText(reqProcessData.getFacChsaCode()))
			processData.setFacChsaCode(reqProcessData.getFacChsaCode());

		if (StringUtils.hasText(reqProcessData.getFacChsaName()))
			processData.setFacChsaName(reqProcessData.getFacChsaName());

		if (StringUtils.hasText(reqProcessData.getFacLhaName()))
			processData.setFacLhaName(reqProcessData.getFacLhaName());

		if (StringUtils.hasText(reqProcessData.getFacHaName()))
			processData.setFacHaName(reqProcessData.getFacHaName());

		if (StringUtils.hasText(reqProcessData.getFacRelnType()))
			processData.setFacRelnType(reqProcessData.getFacRelnType());

		if (StringUtils.hasText(reqProcessData.getFacTypeCode()))
			processData.setFacTypeCode(reqProcessData.getFacTypeCode());

		if (StringUtils.hasText(reqProcessData.getPhysicalAddr1()))
			processData.setPhysicalAddr1(reqProcessData.getPhysicalAddr1());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalAddr2()))
			processData.setPhysicalAddr2(reqProcessData.getPhysicalAddr2());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalAddr3()))
			processData.setPhysicalAddr3(reqProcessData.getPhysicalAddr3());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalAddr4()))
			processData.setPhysicalAddr4(reqProcessData.getPhysicalAddr4());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalCity()))
			processData.setPhysicalCity(reqProcessData.getPhysicalCity());

		if (StringUtils.hasText(reqProcessData.getPhysicalProvince()))
			processData.setPhysicalProvince(reqProcessData.getPhysicalProvince());

		if (StringUtils.hasText(reqProcessData.getPhysicalPcode()))
			processData.setPhysicalPcode(reqProcessData.getPhysicalPcode());

		if (StringUtils.hasText(reqProcessData.getPhysicalCountry()))
			processData.setPhysicalCountry(reqProcessData.getPhysicalCountry());

		if (StringUtils.hasText(reqProcessData.getPhysicalAddrPrpsTypeCd()))
			processData.setPhysicalAddrPrpsTypeCd(reqProcessData.getPhysicalAddrPrpsTypeCd());

		if (StringUtils.hasText(reqProcessData.getPhysicalAddrValidationStatus()))
			processData.setPhysicalAddrValidationStatus(reqProcessData.getPhysicalAddrValidationStatus());		

		if (StringUtils.hasText(reqProcessData.getPhysicalAddrMailabilityScore()))
			processData.setPhysicalAddrMailabilityScore(reqProcessData.getPhysicalAddrMailabilityScore());	
		
		if (StringUtils.hasText(reqProcessData.getMailAddr1()))
			processData.setMailAddr1(reqProcessData.getMailAddr1());

		if (StringUtils.hasText(reqProcessData.getMailAddr2()))
			processData.setMailAddr2(reqProcessData.getMailAddr2());
		
		if (StringUtils.hasText(reqProcessData.getMailAddr3()))
			processData.setMailCity(reqProcessData.getMailCity());
		
		if (StringUtils.hasText(reqProcessData.getMailBc()))
			processData.setMailBc(reqProcessData.getMailBc());
		
		if (StringUtils.hasText(reqProcessData.getMailPcode()))
			processData.setMailPcode(reqProcessData.getMailPcode());
		
		if (StringUtils.hasText(reqProcessData.getMailCountry()))
			processData.setMailCountry(reqProcessData.getMailCountry());
		
		if (StringUtils.hasText(reqProcessData.getMailAddrPrpsTypeCd()))
			processData.setMailAddrPrpsTypeCd(reqProcessData.getMailAddrPrpsTypeCd());

		if (StringUtils.hasText(reqProcessData.getMailAddrValidationStatus()))
			processData.setMailAddrValidationStatus(reqProcessData.getMailAddrValidationStatus());		

		if (StringUtils.hasText(reqProcessData.getMailAddrMailabilityScore()))
			processData.setMailAddrMailabilityScore(reqProcessData.getMailAddrMailabilityScore());	
		
		if (StringUtils.hasText(reqProcessData.getPlrFacilityId()))
			processData.setPlrFacilityId(reqProcessData.getPlrFacilityId());

		if (StringUtils.hasText(reqProcessData.getFacIfcId()))
			processData.setFacIfcId(reqProcessData.getFacIfcId());	

		if (StringUtils.hasText(reqProcessData.getRowstatusCode()))
			processData.setRowstatusCode(reqProcessData.getRowstatusCode());

		processData.setUpdatedBy(AuthenticationUtils.getAuthenticatedUserId());
		processData.setUpdatedAt(new Date());

		try {
			processDataRepository.save(processData);

			Optional<Control> controlTable = controlRepository.findById(processData.getControlTableId());
			if (controlTable.isPresent()) {
				Control control = controlTable.get();
				
				dbUtilityService.setControlStatus(processData.getControlTableId(), RowStatusService.PRE_VALIDATION_IN_PROGRESS,authenticatedUserId );
				
				if (!processData.getRowstatusCode().equals("DO_NOT_LOAD")
				&& !processData.getRowstatusCode().equals("ON_HOLD")  
				&& !processData.getRowstatusCode().equals(RowStatusService.COMPLETED)) {
					logger.info("validate process data with id: {}", id);
				    // single validation
					dbUtilityService.validateProcessData(control, processData,authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", id);
				}

				dbUtilityService.setControlStatus(processData.getControlTableId(), RowStatusService.PRE_VALIDATION_COMPLETED,
												  authenticatedUserId);

			}

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Record updated sucessfully.", null, processData));
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while updating Process Data with id: " + id, null, "[]"));
		}
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/column-display-names")
	public ResponseEntity<ResponseMessage> getColumnDisplayNames() {
	    List<ColumnDisplayName> list = null;
		list = tableColumnInfoService.getColumnDisplayNames(TableColumnInfoService.PROCESS_DATA);
	    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", null, list));
	}

	// get specific row by id
	@PutMapping("/validate/{id}")
	public ResponseEntity<ResponseMessage> validateProcessDataById(@PathVariable Long id) {

		Optional<ProcessData> processDataTable = processDataRepository.findById(id);
		String authenticatedUserId=AuthenticationUtils.getAuthenticatedUserId();
		if (processDataTable.isPresent()) {
			ProcessData processData = processDataTable.get();

			Optional<Control> controlTable = controlRepository.findById(processData.getControlTableId());
			if (controlTable.isPresent()) {
				Control control = controlTable.get();
				
				dbUtilityService.setControlStatus(processData.getControlTableId(), RowStatusService.PRE_VALIDATION_IN_PROGRESS,authenticatedUserId );
				
				if ((!processData.getDoNotLoadFlag().equals("Y")) && (!processData.getRowstatusCode().equals("DO_NOT_LOAD")) && (!processData.getRowstatusCode().equals(RowStatusService.COMPLETED))) {
					logger.info("validate process data with id: {}", id);
				    // run asyn process
					dbUtilityService.validateProcessData(control, processData,authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", id);
				}

				dbUtilityService.setControlStatus(processData.getControlTableId(), RowStatusService.PRE_VALIDATION_COMPLETED,
												  authenticatedUserId);
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", null,
						controlRepository.findById(processData.getControlTableId())));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage("error", 404, "Control Id not found.", null, "[]"));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/validateallbycontroltableid/{id}")
	public ResponseEntity<ResponseMessage> validateAllProcessData(@PathVariable Long id) {

		
		List<ProcessData> processDataList = processDataRepository.getAllProcessDataByControlTableId(id);
		
		if (processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Nothing to validate.", null, "[]"));
			
		}
		String authenticatedUserId= AuthenticationUtils.getAuthenticatedUserId();
		dbUtilityService.setControlStatus(id, RowStatusService.PRE_VALIDATION_COMPLETED,
										  authenticatedUserId);
		// asynchronous operation
		dbUtilityService.validateProcessDataByControlTableId(id,authenticatedUserId);

		Optional<Control> control = controlRepository.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Validation process started!", null, control));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/plrload/{controlTableId}")
	public ResponseEntity<ResponseMessage> plrLoad(@PathVariable Long controlTableId) {

		Optional<Control> controlTable = controlRepository.findById(controlTableId);
		if (controlTable.isPresent()) {
			Control control = controlTable.get();
			if (!control.getStatusCode().equals(RowStatusService.APPROVED)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("error", 404, "Missing approval from Reg Admin to load to PLR.", null, "[]"));
			}
		}

		List<ProcessData> processDataList = processDataRepository.getAllProcessDataByControlTableId(controlTableId);
		
		if (processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("error", 404, "Nothing to load to PLR.", null, "[]"));			
		}
		
		String authenticatedUserId= AuthenticationUtils.getAuthenticatedUserId();
		dbUtilityService.setControlStatus(controlTableId, RowStatusService.PLR_LOAD_IN_PROGRESS,
										  authenticatedUserId);
		// asynchronous operation
		dbUtilityService.loadProcessDataToPlr(controlTableId,authenticatedUserId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "PLR load process started!", null, controlTable));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/reportsummary/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getReportSummaryByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required = false) String rowStatus) {
		
		List<ReportSummary> list = processDataService.getReportSummary(controlTableId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", null, list));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/{controlTableId}/column-distinct-values/{columnKey}")
	public ResponseEntity<ResponseMessage> getDistinctColumnValues(@PathVariable Long controlTableId, @PathVariable String columnKey) {
			
		if(ProcessDataService.PROCESS_DATA_COLUMNS.contains(columnKey)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", null, processDataService.getUniqueColumnValues(controlTableId, columnKey)));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error", 404, "Column not found.", null, new ArrayList<String>()));
		
	}
}
