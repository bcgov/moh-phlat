package com.moh.phlat.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moh.phlat.backend.service.dto.UiColumnName;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.ProcessDataService;
import com.moh.phlat.backend.service.TableColumnInfoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import com.moh.phlat.backend.response.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tablecolumninfo")
@CrossOrigin(origins = "*")
public class TableColumnInfoController {
	@Autowired
	private DbUtilityService dbUtilityService;

	@Autowired
	private TableColumnInfoService tableColumnInfoService;

	
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getvariablenames/{tableName}")
	public String getVariableNamesByTableNameSortedById(@PathVariable String tableName) { 
		return dbUtilityService.getVariablesByTableNameSortedById(tableName);
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getheadernames/{tableName}")
	public String getHeaderNamesByTableNameSortedById(@PathVariable String tableName) { 
		return dbUtilityService.getHeadersByTableNameSortedById(tableName);
	}
}
