package com.moh.phlat.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;
import com.moh.phlat.backend.service.DbUtilityService;

@RestController
@RequestMapping("/tablecolumninfo")
@CrossOrigin(origins = "*")
public class TableColumnInfoController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private DbUtilityService dbUtilityService;
	
	@GetMapping("/getvariablenames/{tableName}")
	public String getVariableNamesByTableNameSortedById(@PathVariable String tableName) { 
		return dbUtilityService.getVariablesByTableNameSortedById(tableName);
	}
	
	@GetMapping("/getheadernames/{tableName}")
	public String getHeaderNamesByTableNameSortedById(@PathVariable String tableName) { 
		return dbUtilityService.getHeadersByTableNameSortedById(tableName);
	}
}
