package com.moh.phlat.backend.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moh.phlat.backend.model.MessageDetail;
import com.moh.phlat.backend.repository.MessageDetailRepository;
import com.moh.phlat.backend.response.ResponseMessage;

@RestController
@RequestMapping("/messagedetail")
@CrossOrigin(origins = "*")
public class MessageDetailController {
	private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	private MessageDetailRepository messageDetailRepository;

	// create new status
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PostMapping("/add")
	public ResponseEntity<ResponseMessage> addNessageDetail(@RequestBody MessageDetail reqMessageDetail) {
		MessageDetail messageDetail = new MessageDetail();
		messageDetail.setProcessDataId(reqMessageDetail.getProcessDataId());
		messageDetail.setRowstatusCode(reqMessageDetail.getRowstatusCode());
		messageDetail.setErrorCode(reqMessageDetail.getErrorCode());
		messageDetail.setErrorType(reqMessageDetail.getErrorType());
		messageDetail.setErrorMsg(reqMessageDetail.getErrorMsg());

		try {
			messageDetailRepository.save(messageDetail);
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 500, "Cannot add new message detail.", "[]"));

			}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", messageDetail));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllMessageDetails() {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success", 200, "", messageDetailRepository.findAll()));
	}


	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getMessageDetailById(@PathVariable Long id) {
		Optional<MessageDetail> messageDetail = messageDetailRepository.findById(id);
		if (messageDetail.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Message detail not found id: " + id, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", messageDetailRepository.findById(id)));
	}
}
