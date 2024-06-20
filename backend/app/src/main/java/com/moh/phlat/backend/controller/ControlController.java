package com.moh.phlat.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.response.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/controltable")
@CrossOrigin(origins = "*")
public class ControlController {
	private static final Logger logger = LoggerFactory.getLogger(ControlController.class);

	@Autowired
	private ControlRepository controlRepository;
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllControls(@RequestParam(required =false) List<String> ids, 
			@RequestParam(required =false) List<String> fileName, @RequestParam(required =false) List<String> userIds, 
			@RequestParam(required =false) List<String> fileExtractedDates, @RequestParam(required =false) List<String> batchLabelNames, 
			@RequestParam(required =false) List<String> loadTypeFacilitys, @RequestParam(required =false) List<String> loadTypeHds, 
			@RequestParam(required =false) List<String> loadTypeBusOrgs, @RequestParam(required =false) List<String> loadTypeOFRelationships, 
			@RequestParam(required =false) List<String> loadTypeOORelationships, @RequestParam(required =false) List<String> loadTypeIORelationships, 
			@RequestParam(required =false) List<String> loadTypeWlOrgXrefs, @RequestParam(required =false) List<String> loadTypeWlPracIdentXrefs, 
			@RequestParam(required =false) List<String> processStartDates, @RequestParam(required =false) List<String> processEndDates, 
			@RequestParam(required =false) List<String> statusCodes, @RequestParam(required =false) List<String> createdBy, 
			@RequestParam(required =false) List<String> createdAt, @RequestParam(required =false) List<String> updatedBy, 
			@RequestParam(required =false) List<String> updatedAt) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", controlRepository.findAll(ControlRepository.buildSpecificationIn(ids, fileName, 
						userIds, fileExtractedDates, batchLabelNames, loadTypeFacilitys, loadTypeHds, loadTypeBusOrgs, loadTypeOFRelationships, 
						loadTypeOORelationships, loadTypeIORelationships, loadTypeWlOrgXrefs, loadTypeWlPracIdentXrefs, processStartDates, 
						processEndDates, statusCodes, createdBy, createdAt, updatedBy, updatedAt))));
	}

	// view specific file control
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getControlById(@PathVariable Long id) {
		Optional<Control> controlTable = controlRepository.findById(id);
		if (controlTable.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Control table not found with id: " + id, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", controlRepository.findById(id)));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/filename/{fileName}")
	public ResponseEntity<ResponseMessage> getControlByFileName(@PathVariable String fileName) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", controlRepository.findByFileName(fileName)));

	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateControl(@PathVariable("id") long id,
			@RequestBody Control requestControl) {
		Optional<Control> controlTableData = controlRepository.findById(id);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Control table not found with id: " + id, "[]"));
		}

		try {

			Control _controlTable = controlTableData.get();

			_controlTable.setFileName(requestControl.getFileName());
			_controlTable.setUserId(AuthenticationUtils.getAuthenticatedUserId());
			_controlTable.setFileExtractedDate(requestControl.getFileExtractedDate());
			_controlTable.setBatchLabelName(requestControl.getBatchLabelName());

			_controlTable.setLoadTypeFacility(requestControl.getLoadTypeFacility());
			_controlTable.setLoadTypeHds(requestControl.getLoadTypeHds());
			_controlTable.setLoadTypeOrg(requestControl.getLoadTypeOrg());
			_controlTable.setLoadTypeOFRelationship(requestControl.getLoadTypeOFRelationship());
			_controlTable.setLoadTypeOORelationship(requestControl.getLoadTypeOORelationship());
			_controlTable.setLoadTypeIORelationship(requestControl.getLoadTypeIORelationship());
			_controlTable.setLoadTypeWOXref(requestControl.getLoadTypeWOXref());
			_controlTable.setLoadTypeWPIXref(requestControl.getLoadTypeWPIXref());
			_controlTable.setStatusCode(requestControl.getStatusCode());

			_controlTable.setUpdatedBy(AuthenticationUtils.getAuthenticatedUserId());
			_controlTable.setUpdatedAt(new Date());

			controlRepository.save(_controlTable);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", _controlTable));

		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while updating Control table with id: " + id, "[]"));
		}
	}

	@PreAuthorize("hasRole(@roleService.getRegAdminRole())")
	@PutMapping("/approve/{id}")
	public ResponseEntity<ResponseMessage> approveToLoadToPLR(@PathVariable("id") long id) {
		Optional<Control> controlTableData = controlRepository.findById(id);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Control table not found with id: " + id, "[]"));
		}

		try {

			Control _controlTable = controlTableData.get();

			_controlTable.setStatusCode("APPROVED");

			_controlTable.setUpdatedBy(AuthenticationUtils.getAuthenticatedUserId());
			_controlTable.setUpdatedAt(new Date());

			controlRepository.save(_controlTable);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Record approved successfully.", _controlTable));

		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while approving constrol table with id: " + id, "[]"));
		}
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/{controltableId}/distinct-values/{columnKey}")
	public ResponseEntity<ResponseMessage> getDistinctColumnValues(@PathVariable String controltableId, @PathVariable String columnKey) {

		List<String> controlData = new ArrayList<String>();

		switch(columnKey) {
			case "id":
				controlData = controlRepository.findAllDistinctId();
				break;
			case "file_name":
				controlData = controlRepository.findAllDistinctFileName();
				break;
			case "user_id":
				controlData = controlRepository.findAllDistinctUserId();
				break;
			case "file_extracted_date":
				controlData = controlRepository.findAllDistinctFileExtractedDate();
				break;
			case "batch_label_name":
				controlData = controlRepository.findAllDistinctBatchLabelName();
				break;
			case "load_type_facility":
				controlData = controlRepository.findAllDistinctLoadTypeFacility();
				break;
			case "load_type_hds":
				controlData = controlRepository.findAllDistinctLoadTypeHds();
				break;
			case "load_type_bus_org":
				controlData = controlRepository.findAllDistinctLoadTypeBusOrg();
				break;
			case "load_type_o_f_relationship":
				controlData = controlRepository.findAllDistinctLoadTypeOFRelationship();
				break;
			case "load_type_o_o_relationship":
				controlData = controlRepository.findAllDistinctLoadTypeOORelationship();
				break;
			case "load_type_i_o_relationship":
				controlData = controlRepository.findAllDistinctLoadTypeIORelationship();
				break;
			case "load_type_wl_org_xref":
				controlData = controlRepository.findAllDistinctLoadTypeWlOrgXref();
				break;
			case "load_type_wl_prac_ident_xref":
				controlData = controlRepository.findAllDistinctLoadTypeWlPracIdentXref();
				break;
			case "process_start_date":
				controlData = controlRepository.findAllDistinctProcessStartDate();
				break;
			case "process_end_date":
				controlData = controlRepository.findAllDistinctProcessEndDate();
				break;
			case "status_code":
				controlData = controlRepository.findAllDistinctStatusCode();
				break;
			case "created_by":
				controlData = controlRepository.findAllDistinctCreatedBy();
				break;
			case "created_at":
				controlData = controlRepository.findAllDistinctCreatedAt();
				break;
			case "updated_by":
				controlData = controlRepository.findAllDistinctUpdatedBy();
				break;
			case "updated_at":
				controlData = controlRepository.findAllDistinctUpdatedAt();
				break;
			default:
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error", 404, "Column not found.", 
						controlData));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", controlData));
	}
}
