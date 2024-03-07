package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.MessageDetail;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.MessageDetailRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;

@Service
public class DbUtilityServiceImpl implements DbUtilityService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private MessageDetailRepository messageDetailRepository;
	
	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private TableColumnInfoRepository tableColumnInfoRepository;

	@Autowired
	private ProcessDataRepository processDataRepository;

	@Override
	public String getVariablesByTableNameSortedById(String tableName) {
		String variableName = "";

//		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findAllByOrderByIdAsc();
		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);
		// findAllByTableNameSortedById(tableName.toUpperCase());

		List<String> listVariableName = new ArrayList<>();

		for (TableColumnInfo _row : tableColumnInfo) {
			variableName = _row.getVariableName();
			if (!variableName.isEmpty()) {
				listVariableName.add(_row.getVariableName());
			}
		}

		String result = listVariableName.stream()
//				 .sorted()
				.map(item -> "\"" + item + "\"").collect(Collectors.joining(", "));

		return "[" + result + "]";

	}

	@Override
	public String getHeadersByTableNameSortedById(String tableName) {
		// logger.info("tableName: " + tableName);
		String headerName = "";
		String retResult = "";
		Integer i = 0;


		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);

		List<String> listHeaderName = new ArrayList<>();

		for (TableColumnInfo _row : tableColumnInfo) {
			headerName = _row.getHeaderName();
			//logger.info("headerName: " + headerName);
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

		// String result = listHeaderName.stream().map(item -> "\"" + item + "\"").collect(Collectors.joining(","));

		return retResult;

	}

	@Override
	public void setProcessDataStatus(Long processDataId, String rowstatusCode, String authenticatedUserId) {

		Optional<ProcessData> _processData = processDataRepository.findById(processDataId);
		try {
			if (_processData.isPresent()) {
				ProcessData processData1 = _processData.get();

				processData1.setRowstatusCode(rowstatusCode);
				processData1.setUpdatedBy(authenticatedUserId);
				processDataRepository.save(processData1);
			}
		} catch (Exception e) {
			logger.error("Unexpected exception: " + e.getMessage());
		}
	}

	public void setControlStatus(Long controlId, String statusCode, String authenticatedUserId) {
		Optional<Control> _control = controlRepository.findById(controlId);
		try {
			if (_control.isPresent()) {
				Control control1 = _control.get();

				control1.setStatusCode(statusCode);
				control1.setUpdatedBy(authenticatedUserId);
				controlRepository.save(control1);
			}

		} catch (Exception e) {
			logger.error("Unexpected exception: " + e.getMessage());
		}
	}
	
	public void addMessageDetail(Long processDataId, String rowstatusCode, Integer errorCode, String errorType, String errorMsg) {

		MessageDetail messageDetail = new MessageDetail();
		
		messageDetail.setProcessDataId(processDataId);
		messageDetail.setRowstatusCode(rowstatusCode);
		messageDetail.setErrorCode(errorCode);
		messageDetail.setErrorType(errorType);
		messageDetail.setErrorMsg(errorMsg);
		
		try {
			messageDetailRepository.save(messageDetail);
		} catch (Exception e) {
			logger.error("Unexpected exception: " + e.getMessage());
		}
	}

	public void validateProcessData(Control control, ProcessData processData, String authenticatedUserId) {
		Boolean isValid = true;
		
		if (control.getLoadTypeFacility() || control.getLoadTypeHds()) {
			logger.info("Handle run type: Facility or HDS");
			// TO DO:
			// auto fix

			// required checks
			if (processData.getHdsName().isEmpty()) {
				isValid = false;
				logger.info("Required check failed on process data id: " + processData.getId());
				addMessageDetail(processData.getId(), "INVALID", 101, "Mandatory", "HDS Name cannot be empty.");
			}
			// error detection
			
			
			if (isValid) { 
				setProcessDataStatus(processData.getId(), "VALID",authenticatedUserId);
			} else {
				setProcessDataStatus(processData.getId(), "INVALID", authenticatedUserId);
			}
			
		}
	}

	@Async
	@Override
	public void validateProcessDataByControlTableId(Long controlTableId, String authenticatedUserId) {
		logger.info("START VALIDATE ASYNC");

		Optional<Control> _control = controlRepository.findById(controlTableId);

		if (_control.isPresent()) {
			Control control = _control.get();

			Iterable<ProcessData> processDataList = processDataRepository
					.getAllProcessDataByControlTableId(controlTableId);

			for (ProcessData s : processDataList) {
				// skip if the rowstatus is COMPLETE or marked as DO_NOT_LOAD
				if (!s.getDoNotLoad().equals("Y") && (!s.getRowstatusCode().equals("DO_NOT_LOAD"))
						&& (!s.getRowstatusCode().equals("COMPLETE"))) {
					logger.info("validate process data with id: " + s.getId());

					// run asyn process
					validateProcessData(control, s, authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: " + s.getId());
				}
			}
			setControlStatus(control.getId(), "PRE-VALIDATION_COMPLETED",
							 authenticatedUserId);
			logger.info("PRE-VALIDATION COMPLETED");
		}
	}

	
	@Async
	@Override
	public void loadProcessDataToPlr(Long controlTableId, String authenticatedUserId) {
		logger.info("START PLR LOAD IN ASYNC MODE");

		Optional<Control> _control = controlRepository.findById(controlTableId);

		if (_control.isPresent()) {
			Control control = _control.get();

			Iterable<ProcessData> processDataList = processDataRepository
					.getAllProcessDataByControlTableId(controlTableId);

			for (ProcessData s : processDataList) {
				// skip record marked as DO_NOT_LOAD and only VALID records
				if (!s.getDoNotLoad().equals("Y") && s.getRowstatusCode().equals("VALID")) {
					logger.info("loading process data with id: " + s.getId() + " to PLR.");

					// TO-DO call to PLR via ESB here
					// loadPlrViaEsb(control, s);
				}
			}
			setControlStatus(control.getId(), "PLR_LOAD_COMPLETED", authenticatedUserId);
			logger.info("PLR_LOAD COMPLETED");
		}
	}

}
