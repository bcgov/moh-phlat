package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.repository.StatusRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statuses")
@CrossOrigin(origins = "*")
public class StatusController {
    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusRepository statusRepository;


    @PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<ResponseMessage> getAllStatus(
            @RequestParam(required = false) Boolean isDeleted) {

        if (isDeleted == null) {
            // return all statuses
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseMessage("success", 200, "", statusRepository.findAll()));
        } else if (isDeleted) {
            // return only deleted statuses
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseMessage("success", 200, "", statusRepository.findAllByIsDeletedTrue()));
        } else {
            // return only active non-deleted statuses
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ResponseMessage("success", 200, "", statusRepository.findAllByIsDeletedFalse()));

        }
    }

}
