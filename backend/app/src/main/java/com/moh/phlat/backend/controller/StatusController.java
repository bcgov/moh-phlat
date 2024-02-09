package com.moh.phlat.backend.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moh.phlat.backend.model.Status;
import com.moh.phlat.backend.repository.StatusRepository;
import com.moh.phlat.backend.response.ResponseMessage;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "*")
public class StatusController {
	private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	private StatusRepository statusRepository;

	// create new status
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addNewStatus(@RequestBody Status requestStatus) {
		Status status = new Status();
		status.setCode(requestStatus.getCode());
		status.setDescription(requestStatus.getDescription());
		status.setType(requestStatus.getType());
		status.setIsDeleted(false);

		try {
			statusRepository.save(status);
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (e.getMessage().contains("duplicate")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseMessage("error", 400, "Duplicate Status Code is not allowed.", "[]"));

			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 500, "Cannot add new status code.", "[]"));

			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", status));
	}

	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllStatus(
			@RequestParam(required = false) Boolean isDeleted) {

		if (isDeleted == null) {
			// return everything included isdeleted or not
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success", 200, "", statusRepository.findAll()));
		} else if (isDeleted) {
			// return on records with soft delete
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success", 200, "", statusRepository.findAllByIsDeletedTrue()));
		} else {
			// return only records that are not marked as deleted.
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success", 200, "", statusRepository.findAllByIsDeletedFalse()));

		}
	}

	// view status by id
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getStatusById(@PathVariable Long id) {
		Optional<Status> status = statusRepository.findById(id);
		if (status.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Status code not found id: " + id, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", statusRepository.findById(id)));
	}

	// update an existing status

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateStatusById(@RequestBody Status requestStatus,
			@PathVariable Long id) {
		Optional<Status> status = statusRepository.findById(id);
		if (status.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Status code not found with id: " + id, "[]"));
		}
		
		try {
			Status status1 = status.get();
			status1.setDescription(requestStatus.getDescription());
			status1.setType(requestStatus.getType());
			statusRepository.save(status1);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", status1));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage("error", 500, "Internal error encountered while updating Status Code with id: " + id, "[]"));
		}
	}

	// soft delete a status
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseMessage> deleteStatusById(@PathVariable("id") Long id) {
		Optional<Status> status = statusRepository.findById(id);

		if (status.isPresent()) {
			Status status1 = status.get();
			status1.setIsDeleted(true);

			statusRepository.save(status1);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", status1));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage("error", 404, "Status code not found with id: " + id, "[]"));
	}

}
