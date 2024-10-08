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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.esb.boundary.PlrDataLoad;
import com.moh.phlat.backend.esb.json.PlrLoadResults;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;

@Service
public class DbUtilityServiceImpl implements DbUtilityService {

	private static final Logger logger = LoggerFactory.getLogger(DbUtilityServiceImpl.class);

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private TableColumnInfoRepository tableColumnInfoRepository;

	@Autowired
	private ProcessDataRepository processDataRepository;
	
	@Autowired
	private PlrDataLoad plrDataLoad;

	@Override
	public String getVariablesByTableNameSortedById(String tableName) {
		String variableName = "";

		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);

		List<String> listVariableName = new ArrayList<>();

		for (TableColumnInfo row : tableColumnInfo) {
			variableName = row.getVariableName();
			if (!variableName.isEmpty()) {
				listVariableName.add(row.getVariableName());
			}
		}

		String result = listVariableName.stream()
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

		for (TableColumnInfo row : tableColumnInfo) {
			headerName = row.getHeaderName();
			//logger.info("headerName: " + headerName);
			if (!headerName.isEmpty()) {
				i = i + 1;
				if (i == 1) {
					retResult = retResult + headerName;
				} else {
					retResult = retResult + "," + headerName;
				}
				listHeaderName.add(row.getHeaderName());
			}
		}

		// String result = listHeaderName.stream().map(item -> "\"" + item + "\"").collect(Collectors.joining(","));

		return retResult;

	}

	@Override
	public void setProcessDataStatus(Long processDataId, String rowstatusCode, String authenticatedUserId) {

		Optional<ProcessData> processDataTable = processDataRepository.findById(processDataId);
		try {
			if (processDataTable.isPresent()) {
				ProcessData processData = processDataTable.get();

				processData.setRowstatusCode(rowstatusCode);
				processData.setUpdatedBy(authenticatedUserId);
				processDataRepository.save(processData);
			}
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
		}
	}

	public void setControlStatus(Long controlId, String statusCode, String authenticatedUserId) {
		Optional<Control> controlTable = controlRepository.findById(controlId);
		try {
			if (controlTable.isPresent()) {
				Control control1 = controlTable.get();

				control1.setStatusCode(statusCode);
				control1.setUpdatedBy(authenticatedUserId);
				controlRepository.save(control1);
			}

		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
		}
	}
	


	public void validateProcessData(Control control, ProcessData processData, String authenticatedUserId) {
		Boolean isValid = true;
		
		if (control.getLoadTypeFacility() || control.getLoadTypeHds()) {
			// required checks
			if (!StringUtils.hasText(processData.getHdsName())) {
				isValid = false;
				logger.info("Required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType("ERROR")
									 .messageCode("101")
									 .messageDesc("HDS Name cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);

			}
			// error detection
			
			
			if (isValid) { 
				setProcessDataStatus(processData.getId(), RowStatusService.VALID,authenticatedUserId);
			} else {
				setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
			}
			
		}
	}

	@Async
	@Override
	public void validateProcessDataByControlTableId(Long controlTableId, String authenticatedUserId) {
		logger.info("START VALIDATE ASYNC");

		Optional<Control> controlTable = controlRepository.findById(controlTableId);

		if (controlTable.isPresent()) {
			Control control = controlTable.get();

			Iterable<ProcessData> processDataList = processDataRepository
					.getAllProcessDataByControlTableId(controlTableId);

			for (ProcessData rec : processDataList) {
				// skip if the rowstatus is COMPLETED or marked as DO_NOT_LOAD
				if (!"Y".equals(rec.getDoNotLoadFlag()) && !RowStatusService.DO_NOT_LOAD.equals(rec.getRowstatusCode())
						&& !RowStatusService.COMPLETED.equals(rec.getRowstatusCode())) {
					logger.info("validate process data with id: {}", rec.getId());

					// run asyn process
					validateProcessData(control, rec, authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", rec.getId());
				}
			}
			setControlStatus(control.getId(), RowStatusService.PRE_VALIDATION_COMPLETED,
							 authenticatedUserId);
			logger.info(RowStatusService.PRE_VALIDATION_COMPLETED);
		}
	}

	
	@Async
	@Transactional
	@Override
	public void loadProcessDataToPlr(Long controlTableId, String authenticatedUserId) {
		logger.info("START PLR LOAD IN ASYNC MODE");

		Optional<Control> controlTable = controlRepository.findById(controlTableId);

		if (controlTable.isPresent()) {
			Control control = controlTable.get();

			Iterable<ProcessData> processDataList = processDataRepository
					.getAllProcessDataByControlTableId(controlTableId);

			// Begin load process
			for (ProcessData processData : processDataList) {
				// Skip record marked as DO_NOT_LOAD and send to PLR VALID or POTENTIAL_DUPLICATE records only
				if (!"Y".equals(processData.getDoNotLoadFlag()) && RowStatusService.VALID.equals(processData.getRowstatusCode())) {
					logger.info("loading process data with id: {} to PLR.", processData.getId());

					// Load the ProcessData record
					PlrLoadResults maintainResults = plrDataLoad.loadPlrViaEsb(control, processData);
					
					// If all load types succeeded, set the RowStatusCode to COMPLETED
					if (maintainResults.isAllLoaded(control)) {
						processData.setRowstatusCode(RowStatusService.COMPLETED);
					}
					
					// Save all changes to this ProcessData record
					processDataRepository.save(processData);
				}
				// Check if there has been repeated issues with loading records
				if (plrDataLoad.getPlrEsbBoundary().hasPersistentIssue()) {
					// Too many errors are happening back to back; stop the load process
					setControlStatus(control.getId(), RowStatusService.LOAD_ERROR, authenticatedUserId);
					logger.error("PLR Load has a persistent issue. Ending this run. Resolve and try again later.");
					return;
				}
			}
			// Load completed successfully
			setControlStatus(control.getId(), RowStatusService.PLR_LOAD_COMPLETED, authenticatedUserId);
			logger.info("PLR Load Completed");
		}
	}	
}
