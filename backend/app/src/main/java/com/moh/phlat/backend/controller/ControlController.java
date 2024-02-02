package com.moh.phlat.backend.controller;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.moh.phlat.backend.repository.ControlRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/controltable")
@CrossOrigin(origins = "*")
public class ControlController {
	private static final Logger logger = LoggerFactory.getLogger(ControlController.class);

	@Autowired
	private ControlRepository controlRepository;

	@PostMapping(value = "/add")
	public ResponseEntity<Control> createControl(@RequestBody Control requestControl) throws HandleInternalException {
		Control _controlTable = new Control();
		_controlTable.setFileName(requestControl.getFileName());
		_controlTable.setUserId(requestControl.getUserId());
		_controlTable.setFileExtractedDate(requestControl.getFileExtractedDate());
		_controlTable.setProcessStartDate(requestControl.getProcessStartDate());
		_controlTable.setProcessEndDate(requestControl.getProcessEndDate());
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

		_controlTable.setCreatedBy(requestControl.getCreatedBy());
		_controlTable.setCreatedAt(new Date());

		try {
			controlRepository.save(_controlTable);
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate")) {
				throw new HandleInternalException(
						"Control table already has file name: " + requestControl.getFileName());
			} else {
				logger.error(e.getMessage());
				throw new HandleInternalException("Cannot add control file name: " + requestControl.getFileName()
						+ ". Please report this to your system administrator");
			}
		}
		return new ResponseEntity<>(_controlTable, HttpStatus.CREATED);
	}

	@GetMapping("/view/all")
	public @ResponseBody Iterable<Control> getAllControls() {
		return controlRepository.findAll();
	}

	// view specific file control
	@GetMapping("/view/{id}")
	public Control getControlById(@PathVariable Long id) throws HandleNotFoundException {
		return controlRepository.findById(id)
				.orElseThrow(() -> new HandleNotFoundException("Control not found with id: " + id));
	}
	
	@GetMapping("/view/filename/{fileName}")
	public Iterable<Control> getControlByFileName(@PathVariable String fileName) {
		return controlRepository.findByFileName(fileName);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Control> updateControl(@PathVariable("id") long id, @RequestBody Control requestControl) throws HandleNotFoundException {
		Optional<Control> controlTableData = controlRepository.findById(id);
		
		if (controlTableData.isPresent()) {
			Control _controlTable = controlTableData.get();

			_controlTable.setFileName(requestControl.getFileName());
			_controlTable.setUserId(requestControl.getUserId());
			_controlTable.setFileExtractedDate(requestControl.getFileExtractedDate());
			_controlTable.setProcessStartDate(requestControl.getProcessStartDate());
			_controlTable.setProcessEndDate(requestControl.getProcessEndDate());
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

			return new ResponseEntity<>(controlRepository.save(_controlTable), HttpStatus.OK);
		} else {
			throw new HandleNotFoundException("Control not found with id: " + id);
		}
	}

	/*
	 * MIGHT NEED THIS CODE LATER
	 * 
	 * @DeleteMapping("/delete/{id}") public ResponseEntity<HttpStatus>
	 * deleteControl(@PathVariable("id") long id) { try {
	 * controlRepository.deleteById(id); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

	/*
	 * @DeleteMapping("/delete/all") public ResponseEntity<HttpStatus>
	 * deleteAllControls() { try { controlRepository.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */

}
