package com.moh.phlat.backend.controller;

import java.util.Date;
import java.util.Optional;
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

	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllControls() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", controlRepository.findAll()));
	}

	// view specific file control
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

	@GetMapping("/view/filename/{fileName}")
	public ResponseEntity<ResponseMessage> getControlByFileName(@PathVariable String fileName) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", controlRepository.findByFileName(fileName)));

	}

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
			_controlTable.setUserId(requestControl.getUserId());
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

			_controlTable.setUpdatedBy(requestControl.getUpdatedBy());
			_controlTable.setUpdatedAt(new Date());

			controlRepository.save(_controlTable);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", _controlTable));

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while updating Control table with id: " + id, "[]"));
		}
	}
	
	@PutMapping("/approve/{id}")
	public ResponseEntity<ResponseMessage> approveToLoadToPLR(@PathVariable("id") long id,
			@RequestBody Control requestControl) {
		Optional<Control> controlTableData = controlRepository.findById(id);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Control table not found with id: " + id, "[]"));
		}

		try {

			Control _controlTable = controlTableData.get();

			_controlTable.setStatusCode("APPROVED");

			_controlTable.setUpdatedBy(requestControl.getUpdatedBy());
			_controlTable.setUpdatedAt(new Date());

			controlRepository.save(_controlTable);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", _controlTable));

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while approving constrol table with id: " + id, "[]"));
		}
	}

	
}
