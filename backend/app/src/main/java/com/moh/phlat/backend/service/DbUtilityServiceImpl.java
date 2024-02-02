package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;

@Service
public class DbUtilityServiceImpl implements DbUtilityService{
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private TableColumnInfoRepository tableColumnInfoRepository;

	@Override
	public String getVariablesByTableNameSortedById(String tableName) {
		String variableName = "";
		
//		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findAllByOrderByIdAsc();
		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);
				//findAllByTableNameSortedById(tableName.toUpperCase());
	
		List<String> listVariableName = new ArrayList<>();
		
		for (TableColumnInfo _row : tableColumnInfo) {
			variableName = _row.getVariableName();
			if (!variableName.isEmpty()) {
				listVariableName.add(_row.getVariableName());
			}
		}	
		
		 String result = listVariableName.stream()
//				 .sorted()
				 .map(item -> "\"" + item + "\"")
	             .collect(Collectors.joining(", "));
		 
		 
		return "[" + result + "]";

	}

	@Override
	public String getHeadersByTableNameSortedById(String tableName) {
		logger.info("tableName: " + tableName);
		String headerName = "";
		String retResult = "";
		Integer i = 0;
		//List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findAllByOrderByIdAsc();

		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);
		
		List<String> listHeaderName = new ArrayList<>();
		
		for (TableColumnInfo _row : tableColumnInfo) {
			headerName = _row.getHeaderName();
			logger.info("headerName: " + headerName);
			if (!headerName.isEmpty()) {
				i = i + 1;
				if (i == 1) {
					retResult = retResult + headerName;
				} else {
					retResult = retResult + "," + headerName;
				}
				listHeaderName.add(_row.getHeaderName());
			}
		}	
		
		 String result = listHeaderName.stream()
				 .map(item -> "\"" + item + "\"")
	             .collect(Collectors.joining(","));
		 
		 logger.info((retResult));
		 logger.info((result));
		return retResult;

	}	
	
}
