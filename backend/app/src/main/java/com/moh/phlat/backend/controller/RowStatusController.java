package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.model.RowStatus;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.RowStatusService;
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

import java.util.List;

@RestController
@RequestMapping("/row-statuses")
@CrossOrigin(origins = "*")
public class RowStatusController {
    private static final Logger logger = LoggerFactory.getLogger(RowStatusController.class);

    @Autowired
    private RowStatusService rowStatusService;


    @PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<ResponseMessage> getAllRowStatuses(
            @RequestParam(required = false) Boolean isDeleted) {

        List<RowStatus> rowStatuses = rowStatusService.getRowStatuses(isDeleted);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new ResponseMessage("success", 200, "", rowStatuses));

    }
}