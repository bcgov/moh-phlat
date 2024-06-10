package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.RowStatusService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbUtilityServiceImpl implements DbUtilityService {
	 public class ReportSummary {
		public String attribute;
		public Long count;
		
		public String getAttribute() {
			return attribute;
		}
		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private MessageService messageService;
	
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

		for (TableColumnInfo row : tableColumnInfo) {
			variableName = row.getVariableName();
			if (!variableName.isEmpty()) {
				listVariableName.add(row.getVariableName());
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

			for (ProcessData s : processDataList) {
				// skip if the rowstatus is COMPLETED or marked as DO_NOT_LOAD
				if (!s.getDoNotLoadFlag().equals("Y") && (!s.getRowstatusCode().equals(RowStatusService.DO_NOT_LOAD))
						&& (!s.getRowstatusCode().equals( RowStatusService.COMPLETED))) {
					logger.info("validate process data with id: {}", s.getId());

					// run asyn process
					validateProcessData(control, s, authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", s.getId());
				}
			}
			setControlStatus(control.getId(), RowStatusService.PRE_VALIDATION_COMPLETED,
							 authenticatedUserId);
			logger.info(RowStatusService.PRE_VALIDATION_COMPLETED);
		}
	}

	
	@Async
	@Override
	public void loadProcessDataToPlr(Long controlTableId, String authenticatedUserId) {
		logger.info("START PLR LOAD IN ASYNC MODE");

		Optional<Control> controlTable = controlRepository.findById(controlTableId);

		if (controlTable.isPresent()) {
			Control control = controlTable.get();

			Iterable<ProcessData> processDataList = processDataRepository
					.getAllProcessDataByControlTableId(controlTableId);

			for (ProcessData s : processDataList) {
				// skip record marked as DO_NOT_LOAD and only VALID records
				if (!s.getDoNotLoadFlag().equals("Y") && s.getRowstatusCode().equals(RowStatusService.VALID)) {
					logger.info("loading process data with id: {} to PLR.", s.getId());

					// TO-DO call to PLR via ESB here
					// loadPlrViaEsb(control, s);
				}
			}
			setControlStatus(control.getId(), RowStatusService.PLR_LOAD_COMPLETED, authenticatedUserId);
			logger.info("PLR_LOAD COMPLETED");
		}
	}

	@Override
	public List<ReportSummary> getReportSummary(Long controlTableId) {
		String attribute;
		Long count;

		List<ReportSummary> items = new ArrayList<ReportSummary>();

		attribute ="TOTAL INPUT RECORDS";
		count = processDataRepository.countByControlTableId(controlTableId);
		ReportSummary rs1 = new ReportSummary();
		rs1.setAttribute((String) attribute);
		rs1.setCount((Long)count);
		items.add(rs1);
		
		attribute ="TOTAL INITIAL ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.INITIAL);
		ReportSummary rs2 = new ReportSummary();
		rs2.setAttribute((String) attribute);
		rs2.setCount((Long)count);
		items.add(rs2);		
		
		attribute ="TOTAL DO_NOT_LOAD ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.DO_NOT_LOAD);
		ReportSummary rs3 = new ReportSummary();
		rs3.setAttribute((String) attribute);
		rs3.setCount((Long)count);
		items.add(rs3);		
		
		attribute ="TOTAL INVALID ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.INVALID);
		ReportSummary rs4 = new ReportSummary();
		rs4.setAttribute((String) attribute);
		rs4.setCount((Long)count);
		items.add(rs4);		

		attribute ="TOTAL VALID ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.VALID);
		ReportSummary rs5 = new ReportSummary();
		rs5.setAttribute((String) attribute);
		rs5.setCount((Long)count);
		items.add(rs5);		
		
		attribute ="TOTAL WARNING ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.WARNING);
		ReportSummary rs6 = new ReportSummary();
		rs6.setAttribute((String) attribute);
		rs6.setCount((Long)count);
		items.add(rs6);				
		
		attribute ="TOTAL COMPLETED ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.COMPLETED);
		ReportSummary rs7 = new ReportSummary();
		rs7.setAttribute((String) attribute);
		rs7.setCount((Long)count);
		items.add(rs7);	
		
		attribute ="TOTAL POTENTIAL_DUPLICATE ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.POTENTIAL_DUPLICATE);
		ReportSummary rs8 = new ReportSummary();
		rs8.setAttribute((String) attribute);
		rs8.setCount((Long)count);
		items.add(rs8);	
		
		attribute ="TOTAL LOAD_ERROR ROWSTATUS";
		count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.LOAD_ERROR);
		ReportSummary rs9 = new ReportSummary();
		rs9.setAttribute((String) attribute);
		rs9.setCount((Long)count);
		items.add(rs9);			
		
		// adding message code and desc to the list

		List<Object[]> listMsg = processDataRepository.getProcessDataWithMessageCodeCount(controlTableId);

		for (Object[] msg : listMsg){
			String code = (String) msg[1];
			if (StringUtils.hasText(code)) {
				ReportSummary rsMessage = new ReportSummary();
				attribute = (String) msg[0] + " " + (String) msg[1] + " " + (String) msg[2];
				count = (Long) msg[3];
				rsMessage.setAttribute((String) attribute);
	 			rsMessage.setCount((Long)count);
				items.add(rsMessage);	
			}	
		}   
		return items;
	}
}
