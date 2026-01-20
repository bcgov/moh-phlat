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

		if (reqProcessData.getStakeholder() != null)
			processData.setStakeholder(reqProcessData.getStakeholder().trim());
		
		if (reqProcessData.getStakeholderId() != null)
			processData.setStakeholderId(reqProcessData.getStakeholderId().trim());

		if (reqProcessData.getHdsIpcId() != null)
			processData.setHdsIpcId(reqProcessData.getHdsIpcId().trim());

		if (reqProcessData.getHdsCpnId() != null)
			processData.setHdsCpnId(reqProcessData.getHdsCpnId().trim());

		if (reqProcessData.getHdsProviderIdentifier1() != null)
			processData.setHdsProviderIdentifier1(reqProcessData.getHdsProviderIdentifier1().trim());
		
		if (reqProcessData.getHdsProviderIdentifier2() != null)
			processData.setHdsProviderIdentifier2(reqProcessData.getHdsProviderIdentifier2().trim());

		if (reqProcessData.getHdsProviderIdentifier3() != null)
			processData.setHdsProviderIdentifier3(reqProcessData.getHdsProviderIdentifier3().trim());
		
		if (reqProcessData.getHdsProviderIdentifierType1() != null)
			processData.setHdsProviderIdentifierType1(reqProcessData.getHdsProviderIdentifierType1().trim());
		
		if (reqProcessData.getHdsProviderIdentifierType2() != null)
			processData.setHdsProviderIdentifierType2(reqProcessData.getHdsProviderIdentifierType2().trim());
		
		if (reqProcessData.getHdsProviderIdentifierType3() != null)
			processData.setHdsProviderIdentifierType3(reqProcessData.getHdsProviderIdentifierType3().trim());

		if (reqProcessData.getHdsMspFacilityNumber() != null)
			processData.setHdsMspFacilityNumber(reqProcessData.getHdsMspFacilityNumber().trim());

		if (reqProcessData.getHdsPauthId() != null)
			processData.setHdsPauthId(reqProcessData.getHdsPauthId().trim());

		if (reqProcessData.getHdsCategoryCode() != null)
			processData.setHdsCategoryCode(reqProcessData.getHdsCategoryCode().trim());

		if (reqProcessData.getHdsRoleTypeCode() != null)
			processData.setHdsRoleTypeCode(reqProcessData.getHdsRoleTypeCode().trim());

		if (reqProcessData.getHdsType() != null)
			processData.setHdsType(reqProcessData.getHdsType().trim());

		if (reqProcessData.getHdsSubType() != null)
			processData.setHdsSubType(reqProcessData.getHdsSubType().trim());			
		
		if (reqProcessData.getHdsUserChid() != null)
			processData.setHdsUserChid(reqProcessData.getHdsUserChid().trim());		
		
		if (reqProcessData.getHdsCreatedDts() != null)
			processData.setHdsCreatedDts(reqProcessData.getHdsCreatedDts().trim());	

		if (reqProcessData.getHdsInvalidatedDts() != null)
			processData.setHdsInvalidatedDts(reqProcessData.getHdsInvalidatedDts().trim());	

		if (reqProcessData.getHdsName() != null)
			processData.setHdsName(reqProcessData.getHdsName().trim());
	
		if (reqProcessData.getHdsPreferredNameFlag() != null)
			processData.setHdsPreferredNameFlag(reqProcessData.getHdsPreferredNameFlag().trim());
		
		if (reqProcessData.getHdsEmail() != null)
			processData.setHdsEmail(reqProcessData.getHdsEmail().trim());
		
		if (reqProcessData.getHdsWebsite() != null)
			processData.setHdsWebsite(reqProcessData.getHdsWebsite().trim());

		if (reqProcessData.getHdsBusTelAreaCode() != null)
			processData.setHdsBusTelAreaCode(reqProcessData.getHdsBusTelAreaCode().trim());
		
		if (reqProcessData.getHdsBusTelNumber() != null)
			processData.setHdsBusTelNumber(reqProcessData.getHdsBusTelNumber().trim());
		
		if (reqProcessData.getHdsTelExtension() != null)
			processData.setHdsTelExtension(reqProcessData.getHdsTelExtension().trim());
		
		if (reqProcessData.getHdsCellAreaCode() != null)
			processData.setHdsCellAreaCode(reqProcessData.getHdsCellAreaCode().trim());
		
		if (reqProcessData.getHdsCellNumber() != null)
			processData.setHdsCellNumber(reqProcessData.getHdsCellNumber().trim());
		
		if (reqProcessData.getHdsFaxAreaCode() != null)
			processData.setHdsFaxAreaCode(reqProcessData.getHdsFaxAreaCode().trim());
		
		if (reqProcessData.getHdsFaxNumber() != null)
			processData.setHdsFaxNumber(reqProcessData.getHdsFaxNumber().trim());
		
		if (reqProcessData.getPcnServiceDeliveryType() != null)
			processData.setPcnServiceDeliveryType(reqProcessData.getPcnServiceDeliveryType().trim());	

		if (reqProcessData.getPcnClinicType() != null)
			processData.setPcnClinicType(reqProcessData.getPcnClinicType().trim());
		
		if (reqProcessData.getPcnPciFlag() != null)
			processData.setPcnPciFlag(reqProcessData.getPcnPciFlag().trim());
		
		if (reqProcessData.getSourceStatus() != null)
			processData.setSourceStatus(reqProcessData.getSourceStatus().trim());
		
		if (reqProcessData.getHdsEffectiveStartDate() != null)
			processData.setHdsEffectiveStartDate(reqProcessData.getHdsEffectiveStartDate().trim());

		if (reqProcessData.getHdsEffectiveEndDate() != null)
			processData.setHdsEffectiveEndDate(reqProcessData.getHdsEffectiveEndDate().trim());	

		if (reqProcessData.getFacAddressUnit() != null)
			processData.setFacAddressUnit(reqProcessData.getFacAddressUnit().trim());	

		if (reqProcessData.getFacBuildingName() != null)
			processData.setFacBuildingName(reqProcessData.getFacBuildingName().trim());	

		if (reqProcessData.getFacCivicAddrId() != null)
			processData.setFacCivicAddrId(reqProcessData.getFacCivicAddrId().trim());	

		if (reqProcessData.getFacCivicAddr() != null)
			processData.setFacCivicAddr(reqProcessData.getFacCivicAddr().trim());	

		if (reqProcessData.getFacLatitude() != null)
			processData.setFacLatitude(reqProcessData.getFacLatitude().trim());	

		if (reqProcessData.getFacLongitude() != null)
			processData.setFacLongitude(reqProcessData.getFacLongitude().trim());	

		if (reqProcessData.getFacStreetDirection() != null)
			processData.setFacStreetDirection(reqProcessData.getFacStreetDirection().trim());	

		if (reqProcessData.getStreetDirectionPrefix() != null)
			processData.setStreetDirectionPrefix(reqProcessData.getStreetDirectionPrefix().trim());	

		if (reqProcessData.getStreetTypePrefix() != null)
			processData.setStreetTypePrefix(reqProcessData.getStreetTypePrefix().trim());	

		if (reqProcessData.getFacCivicNumber() != null)
			processData.setFacCivicNumber(reqProcessData.getFacCivicNumber().trim());

		if (reqProcessData.getFacStreetName() != null)
			processData.setFacStreetName(reqProcessData.getFacStreetName().trim());

		if (reqProcessData.getFacStreetType() != null)
			processData.setFacStreetType(reqProcessData.getFacStreetType().trim());

		if (reqProcessData.getFacLocalityName() != null)
			processData.setFacLocalityName(reqProcessData.getFacLocalityName().trim());

		if (reqProcessData.getFacProvinceCode() != null)
			processData.setFacProvinceCode(reqProcessData.getFacProvinceCode().trim());

		if (reqProcessData.getFacSiteId() != null)
			processData.setFacSiteId(reqProcessData.getFacSiteId().trim());

		if (reqProcessData.getFacScore() != null)
			processData.setFacScore(reqProcessData.getFacScore().trim());

		if (reqProcessData.getFacMatchPrecision() != null)
			processData.setFacMatchPrecision(reqProcessData.getFacMatchPrecision().trim());

		if (reqProcessData.getFacPrecisionPoints() != null)
			processData.setFacPrecisionPoints(reqProcessData.getFacPrecisionPoints().trim());

		if (reqProcessData.getFacHsdaName() != null)
			processData.setFacHsdaName(reqProcessData.getFacHsdaName().trim());

		if (reqProcessData.getFacDatabcResults() != null)
			processData.setFacDatabcResults(reqProcessData.getFacDatabcResults().trim());

		if (reqProcessData.getFacPcnCode() != null)
			processData.setFacPcnCode(reqProcessData.getFacPcnCode().trim());

		if (reqProcessData.getFacPcnName() != null)
			processData.setFacPcnName(reqProcessData.getFacPcnName().trim());

		if (reqProcessData.getFacChsaStatus() != null)
			processData.setFacChsaStatus(reqProcessData.getFacChsaStatus().trim());

		if (reqProcessData.getFacPcnStatus() != null)
			processData.setFacPcnStatus(reqProcessData.getFacPcnStatus().trim());

		if (reqProcessData.getFacChsaCode() != null)
			processData.setFacChsaCode(reqProcessData.getFacChsaCode().trim());

		if (reqProcessData.getFacChsaName() != null)
			processData.setFacChsaName(reqProcessData.getFacChsaName().trim());

		if (reqProcessData.getFacLhaName() != null)
			processData.setFacLhaName(reqProcessData.getFacLhaName().trim());

		if (reqProcessData.getFacHaName() != null)
			processData.setFacHaName(reqProcessData.getFacHaName().trim());

		if (reqProcessData.getFacRelnType() != null)
			processData.setFacRelnType(reqProcessData.getFacRelnType().trim());

		if (reqProcessData.getFacTypeCode() != null)
			processData.setFacTypeCode(reqProcessData.getFacTypeCode().trim());

		if (reqProcessData.getPhysicalAddr1() != null)
			processData.setPhysicalAddr1(reqProcessData.getPhysicalAddr1().trim());
		
		if (reqProcessData.getPhysicalAddr2() != null)
			processData.setPhysicalAddr2(reqProcessData.getPhysicalAddr2().trim());
		
		if (reqProcessData.getPhysicalAddr3() != null)
			processData.setPhysicalAddr3(reqProcessData.getPhysicalAddr3().trim());
		
		if (reqProcessData.getPhysicalAddr4() != null)
			processData.setPhysicalAddr4(reqProcessData.getPhysicalAddr4().trim());
		
		if (reqProcessData.getPhysicalCity() != null)
			processData.setPhysicalCity(reqProcessData.getPhysicalCity().trim());

		if (reqProcessData.getPhysicalProvince() != null)
			processData.setPhysicalProvince(reqProcessData.getPhysicalProvince().trim());

		if (reqProcessData.getPhysicalPcode() != null)
			processData.setPhysicalPcode(reqProcessData.getPhysicalPcode().trim());

		if (reqProcessData.getPhysicalCountry() != null)
			processData.setPhysicalCountry(reqProcessData.getPhysicalCountry().trim());

		if (reqProcessData.getPhysicalAddrPrpsTypeCd() != null)
			processData.setPhysicalAddrPrpsTypeCd(reqProcessData.getPhysicalAddrPrpsTypeCd().trim());

		if (reqProcessData.getPhysicalAddrValidationStatus() != null)
			processData.setPhysicalAddrValidationStatus(reqProcessData.getPhysicalAddrValidationStatus().trim());		

		if (reqProcessData.getPhysicalAddrMailabilityScore() != null)
			processData.setPhysicalAddrMailabilityScore(reqProcessData.getPhysicalAddrMailabilityScore().trim());	
		
		if (reqProcessData.getMailAddr1() != null)
			processData.setMailAddr1(reqProcessData.getMailAddr1().trim());

		if (reqProcessData.getMailAddr2() != null)
			processData.setMailAddr2(reqProcessData.getMailAddr2().trim());
		
		if (reqProcessData.getMailAddr3() != null)
			processData.setMailAddr3(reqProcessData.getMailAddr3().trim());
		
		if (reqProcessData.getMailAddr4() != null)
			processData.setMailAddr4(reqProcessData.getMailAddr4().trim());

		if (reqProcessData.getMailCity() != null)
			processData.setMailCity(reqProcessData.getMailCity().trim());

		if (reqProcessData.getMailProvince() != null)
			processData.setMailProvince(reqProcessData.getMailProvince().trim());
		
		if (reqProcessData.getMailPcode() != null)
			processData.setMailPcode(reqProcessData.getMailPcode().trim());
		
		if (reqProcessData.getMailCountry() != null)
			processData.setMailCountry(reqProcessData.getMailCountry().trim());
		
		if (reqProcessData.getMailAddrPrpsTypeCd() != null)
			processData.setMailAddrPrpsTypeCd(reqProcessData.getMailAddrPrpsTypeCd().trim());

		if (reqProcessData.getMailAddrValidationStatus() != null)
			processData.setMailAddrValidationStatus(reqProcessData.getMailAddrValidationStatus().trim());		

		if (reqProcessData.getMailAddrMailabilityScore() != null)
			processData.setMailAddrMailabilityScore(reqProcessData.getMailAddrMailabilityScore().trim());	
		
		if (reqProcessData.getPlrFacilityId() != null)
			processData.setPlrFacilityId(reqProcessData.getPlrFacilityId().trim());

		if (reqProcessData.getFacIfcId() != null)
			processData.setFacIfcId(reqProcessData.getFacIfcId().trim());	

		if (reqProcessData.getRowstatusCode() != null)
			processData.setRowstatusCode(reqProcessData.getRowstatusCode().trim());
	
		if (reqProcessData.getPrimaryCareGroupAction() != null)
			processData.setPrimaryCareGroupAction(reqProcessData.getPrimaryCareGroupAction().trim());				
		
		if (reqProcessData.getPrimaryCareGroupEffectiveStartDate() != null)
			processData.setPrimaryCareGroupEffectiveStartDate(reqProcessData.getPrimaryCareGroupEffectiveStartDate());				
		
		if (reqProcessData.getPrimaryCareGroupEffectiveEndDate() != null)
			processData.setPrimaryCareGroupEffectiveEndDate(reqProcessData.getPrimaryCareGroupEffectiveEndDate());				

		if (reqProcessData.getHdsSubTypeGroupAction() != null)
			processData.setHdsSubTypeGroupAction(reqProcessData.getHdsSubTypeGroupAction().trim());				
		
		if (reqProcessData.getHdsSubTypeGroupEffectiveStartDate() != null)
			processData.setHdsSubTypeGroupEffectiveStartDate(reqProcessData.getHdsSubTypeGroupEffectiveStartDate());				
		
		if (reqProcessData.getHdsSubTypeGroupEffectiveEndDate() != null)
			processData.setHdsSubTypeGroupEffectiveEndDate(reqProcessData.getHdsSubTypeGroupEffectiveEndDate());				

		if (reqProcessData.getHdsNameGroupAction() != null)
			processData.setHdsNameGroupAction(reqProcessData.getHdsNameGroupAction().trim());				
		
		if (reqProcessData.getHdsNameGroupEffectiveStartDate() != null)
			processData.setHdsNameGroupEffectiveStartDate(reqProcessData.getHdsNameGroupEffectiveStartDate());				
		
		if (reqProcessData.getHdsNameGroupEffectiveEndDate() != null)
			processData.setHdsNameGroupEffectiveEndDate(reqProcessData.getHdsNameGroupEffectiveEndDate());				
		
		if (reqProcessData.getHdsEmailGroupAction() != null)
			processData.setHdsEmailGroupAction(reqProcessData.getHdsEmailGroupAction().trim());		
		
		if (reqProcessData.getHdsEmailGroupEffectiveStartDate() != null)
			processData.setHdsEmailGroupEffectiveStartDate(reqProcessData.getHdsEmailGroupEffectiveStartDate());				
		
		if (reqProcessData.getHdsEmailGroupEffectiveEndDate() != null)
			processData.setHdsEmailGroupEffectiveEndDate(reqProcessData.getHdsEmailGroupEffectiveEndDate());				

		if (reqProcessData.getHdsWebsiteGroupAction() != null)
			processData.setHdsWebsiteGroupAction(reqProcessData.getHdsWebsiteGroupAction().trim());		
		
		if (reqProcessData.getHdsWebsiteGroupEffectiveStartDate() != null)
			processData.setHdsWebsiteGroupEffectiveStartDate(reqProcessData.getHdsWebsiteGroupEffectiveStartDate());				
		
		if (reqProcessData.getHdsWebsiteGroupEffectiveEndDate() != null)
			processData.setHdsWebsiteGroupEffectiveEndDate(reqProcessData.getHdsWebsiteGroupEffectiveEndDate());				

		if (reqProcessData.getBusinessPhoneGroupAction() != null)
			processData.setBusinessPhoneGroupAction(reqProcessData.getBusinessPhoneGroupAction().trim());		
		
		if (reqProcessData.getBusinessPhoneGroupEffectiveStartDate() != null)
			processData.setBusinessPhoneGroupEffectiveStartDate(reqProcessData.getBusinessPhoneGroupEffectiveStartDate());				
		
		if (reqProcessData.getBusinessPhoneGroupEffectiveEndDate() != null)
			processData.setBusinessPhoneGroupEffectiveEndDate(reqProcessData.getBusinessPhoneGroupEffectiveEndDate());				

		if (reqProcessData.getHdsCellGroupAction() != null)
			processData.setHdsCellGroupAction(reqProcessData.getHdsCellGroupAction().trim());		
		
		if (reqProcessData.getHdsCellGroupEffectiveStartDate() != null)
			processData.setHdsCellGroupEffectiveStartDate(reqProcessData.getHdsCellGroupEffectiveStartDate());				
		
		if (reqProcessData.getHdsCellGroupEffectiveEndDate() != null)
			processData.setHdsCellGroupEffectiveEndDate(reqProcessData.getHdsCellGroupEffectiveEndDate());				

		if (reqProcessData.getHdsFaxGroupAction() != null)
			processData.setHdsFaxGroupAction(reqProcessData.getHdsFaxGroupAction().trim());		
		
		if (reqProcessData.getHdsFaxGroupEffectiveStartDate() != null)
			processData.setHdsFaxGroupEffectiveStartDate(reqProcessData.getHdsFaxGroupEffectiveStartDate());				
		
		if (reqProcessData.getHdsFaxGroupEffectiveEndDate() != null)
			processData.setHdsFaxGroupEffectiveEndDate(reqProcessData.getHdsFaxGroupEffectiveEndDate());				

		if (reqProcessData.getStatusGroupAction() != null)
			processData.setStatusGroupAction(reqProcessData.getStatusGroupAction().trim());
		
		if (reqProcessData.getStatusGroupEffectiveStartDate() != null)
			processData.setStatusGroupEffectiveStartDate(reqProcessData.getStatusGroupEffectiveStartDate());				
		
		if (reqProcessData.getStatusGroupEffectiveEndDate() != null)
			processData.setStatusGroupEffectiveEndDate(reqProcessData.getStatusGroupEffectiveEndDate());				

		if (reqProcessData.getPhysicalAddressGroupAction() != null)
			processData.setPhysicalAddressGroupAction(reqProcessData.getPhysicalAddressGroupAction().trim());			
		
		if (reqProcessData.getPhysicalAddressGroupEffectiveStartDate() != null)
			processData.setPhysicalAddressGroupEffectiveStartDate(reqProcessData.getPhysicalAddressGroupEffectiveStartDate());				
		
		if (reqProcessData.getPhysicalAddressGroupEffectiveEndDate() != null)
			processData.setPhysicalAddressGroupEffectiveEndDate(reqProcessData.getPhysicalAddressGroupEffectiveEndDate());				

		if (reqProcessData.getMailingAddressGroupAction() != null)
			processData.setMailingAddressGroupAction(reqProcessData.getMailingAddressGroupAction().trim());			
		
		if (reqProcessData.getMailingAddressGroupEffectiveStartDate() != null)
			processData.setMailingAddressGroupEffectiveStartDate(reqProcessData.getMailingAddressGroupEffectiveStartDate());				
		
		if (reqProcessData.getMailingAddressGroupEffectiveEndDate() != null)
			processData.setMailingAddressGroupEffectiveEndDate(reqProcessData.getMailingAddressGroupEffectiveEndDate());				

		if (reqProcessData.getRecordAction() != null)
			processData.setRecordAction(reqProcessData.getRecordAction());
		
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
		dbUtilityService.setControlStatus(id, RowStatusService.PRE_VALIDATION_IN_PROGRESS,
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
