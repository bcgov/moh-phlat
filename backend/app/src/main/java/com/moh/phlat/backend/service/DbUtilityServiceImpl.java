package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;
import com.moh.phlat.backend.service.DbUtilityService;

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
			logger.error("Error occured: {}", e.getMessage(), e);
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
				if (!s.getDoNotLoadFlag().equals("Y") && (!s.getRowstatusCode().equals("DO_NOT_LOAD"))
						&& (!s.getRowstatusCode().equals("COMPLETE"))) {
					logger.info("validate process data with id: {}", s.getId());

					// run asyn process
					validateProcessData(control, s, authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", s.getId());
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
				if (!s.getDoNotLoadFlag().equals("Y") && s.getRowstatusCode().equals("VALID")) {
					logger.info("loading process data with id: {} to PLR.", s.getId());

					// TO-DO call to PLR via ESB here
					// loadPlrViaEsb(control, s);
				}
			}
			setControlStatus(control.getId(), "PLR_LOAD_COMPLETED", authenticatedUserId);
			logger.info("PLR_LOAD COMPLETED");
		}
	}

	@Override
	public List<ReportSummary> getReportSummary(Long controlTableId) {
		String _attribute;
		Long _count;

		List<ReportSummary> items = new ArrayList<ReportSummary>();

		_attribute ="TOTAL INPUT RECORDS";
		_count = processDataRepository.countByControlTableId(controlTableId);
		ReportSummary rs1 = new ReportSummary();
		rs1.setAttribute((String) _attribute);
		rs1.setCount((Long)_count);
		items.add(rs1);
		
		_attribute ="TOTAL INITIAL ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"INITIAL");
		ReportSummary rs2 = new ReportSummary();
		rs2.setAttribute((String) _attribute);
		rs2.setCount((Long)_count);
		items.add(rs2);		
		
		_attribute ="TOTAL DO_NOT_LOAD ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"DO_NOT_LOAD");
		ReportSummary rs3 = new ReportSummary();
		rs3.setAttribute((String) _attribute);
		rs3.setCount((Long)_count);
		items.add(rs3);		
		
		_attribute ="TOTAL INVALID ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"INVALID");
		ReportSummary rs4 = new ReportSummary();
		rs4.setAttribute((String) _attribute);
		rs4.setCount((Long)_count);
		items.add(rs4);		

		_attribute ="TOTAL VALID ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"VALID");
		ReportSummary rs5 = new ReportSummary();
		rs5.setAttribute((String) _attribute);
		rs5.setCount((Long)_count);
		items.add(rs5);		
		
		_attribute ="TOTAL WARNING ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"WARNING");
		ReportSummary rs6 = new ReportSummary();
		rs6.setAttribute((String) _attribute);
		rs6.setCount((Long)_count);
		items.add(rs6);				
		
		_attribute ="TOTAL COMPLETED ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"COMPLETED");
		ReportSummary rs7 = new ReportSummary();
		rs7.setAttribute((String) _attribute);
		rs7.setCount((Long)_count);
		items.add(rs7);	
		
		_attribute ="TOTAL POTENTIAL_DUPLICATE ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"POTENTIAL_DUPLICATE");
		ReportSummary rs8 = new ReportSummary();
		rs8.setAttribute((String) _attribute);
		rs8.setCount((Long)_count);
		items.add(rs8);	
		
		_attribute ="TOTAL LOAD_ERROR ROWSTATUS";
		_count = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,"LOAD_ERROR");
		ReportSummary rs9 = new ReportSummary();
		rs9.setAttribute((String) _attribute);
		rs9.setCount((Long)_count);
		items.add(rs9);			
		
		// adding message code and desc to the list

		List<Object[]> _listMsg = processDataRepository.getProcessDataWithMessageCodeCount(controlTableId);
		for (Object[] _msg : _listMsg){
			String _code = (String) _msg[1];
			if (_code!=null) {
				ReportSummary rsMessage = new ReportSummary();
				_attribute = (String) _msg[0] + " " + (String) _msg[1] + " " + (String) _msg[2];
				_count = (Long)_msg[3];
				rsMessage.setAttribute((String) _attribute);
	 			rsMessage.setCount((Long)_count);
				items.add(rsMessage);	
			}	
		}   
		return items;
	}
}
