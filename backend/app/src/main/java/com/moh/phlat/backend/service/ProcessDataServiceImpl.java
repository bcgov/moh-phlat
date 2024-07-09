package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.service.dto.ReportSummary;

@Service
public class ProcessDataServiceImpl implements ProcessDataService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	private static final String PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME = "processData.mandatoryColumnDisplayName";
	@Autowired
	private MessageService messageService;

	// @Autowired
	// private ProcessDataService processDataService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    ProcessDataRepository processDataRepository;

	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private ControlService controlService;

    @Override
    public List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus) {

        if (StringUtils.hasText(rowStatus)) {
            return processDataRepository.getProcessDataWithMessages(controlTableId, rowStatus);
        } else {
            return processDataRepository.getProcessDataWithMessages(controlTableId);
        }

    }

    private static ReportSummary createReportSummaryData(String reportAttributeName, Long reportAttributeValue) {
        return ReportSummary.builder()
						  .attribute(reportAttributeName)
                          .count(reportAttributeValue)
                          .build();
    }

    public List<ReportSummary> getReportSummary(Long controlTableId) {
		String reportAttributeName;
		Long reportAttributeValue;

		List<ReportSummary> items = new ArrayList<ReportSummary>();

		reportAttributeName ="TOTAL INPUT RECORDS";
		reportAttributeValue = processDataRepository.countByControlTableId(controlTableId);
     	ReportSummary rs1 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs1);
		
		reportAttributeName = "TOTAL INITIAL ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.INITIAL);
     	ReportSummary rs2 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs2);

		reportAttributeName = "TOTAL DO_NOT_LOAD ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.DO_NOT_LOAD);
     	ReportSummary rs3 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs3);		
		
		reportAttributeName ="TOTAL INVALID ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.INVALID);
     	ReportSummary rs4 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs4);	

		reportAttributeName ="TOTAL VALID ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.VALID);
		ReportSummary rs5 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs5);	
		
		reportAttributeName ="TOTAL WARNING ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId,RowStatusService.WARNING);
     	ReportSummary rs6 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs6);	

		reportAttributeName ="TOTAL COMPLETED ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.COMPLETED);
     	ReportSummary rs7 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs7);

		reportAttributeName = "TOTAL POTENTIAL_DUPLICATE ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.POTENTIAL_DUPLICATE);
     	ReportSummary rs8 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs8);
		
		reportAttributeName = "TOTAL LOAD_ERROR ROWSTATUS";
		reportAttributeValue = processDataRepository.countAllByControlTableIdAndRowstatusCode(controlTableId, RowStatusService.LOAD_ERROR);
     	ReportSummary rs9 = createReportSummaryData(reportAttributeName, reportAttributeValue);
		items.add(rs9);			
		
		// adding message code and desc to the list

		List<Object[]> listMsg = processDataRepository.getProcessDataWithMessageCodeCount(controlTableId);

		for (Object[] msg : listMsg){
			String code = (String) msg[1];
			if (StringUtils.hasText(code)) {
				reportAttributeName = (String) msg[0] + " " + (String) msg[1] + " " + (String) msg[2];
				reportAttributeValue = (Long) msg[3];
				ReportSummary rsMessage = createReportSummaryData(reportAttributeName, reportAttributeValue);
				items.add(rsMessage);	
			}	
		}   
		return items;
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

	public void validateProcessData(Control control, ProcessData processData, String authenticatedUserId) {
		Boolean isValid = true;

		List<String> columnnDisplayNames = new ArrayList();
		columnnDisplayNames.add("HDS Name");
		
		// mandatoryired checks
		if (!StringUtils.hasText(processData.getHdsName())) {
			isValid = false;
			logger.info("Required check failed on process data id: {}", processData.getId());
			Message msg = Message.builder()
								 .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
								 .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
								 .messageDesc( messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
										 columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
								 .sourceSystemName(MessageSourceSystem.PHLAT)
								 .processData(processData)
								 .build();
				messageService.createMessage(msg);
		}


		if (!StringUtils.hasText(processData.getStakeholder())) {
			columnnDisplayNames.clear();
			columnnDisplayNames.add("Stakeholder");
			isValid = false;
			logger.info("Required check failed on process data id: {}", processData.getId());
			Message msg = Message.builder()
								 .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
								 .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
								 .messageDesc(messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
										 columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
								 .sourceSystemName(MessageSourceSystem.PHLAT)
								 .processData(processData)
								 .build();
			messageService.createMessage(msg);
		}

		if (!StringUtils.hasText(processData.getPhysicalAddr1())) {
			columnnDisplayNames.clear();
			columnnDisplayNames.add("Physical Addr 1");
			isValid = false;
			logger.info("Required check failed on process data id: {}", processData.getId());
			Message msg = Message.builder()
								 .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
								 .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
								 .messageDesc(messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
										 columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
								 .sourceSystemName(MessageSourceSystem.PHLAT)
								 .processData(processData)
								 .build();
			messageService.createMessage(msg);
		}


		if (!StringUtils.hasText(processData.getPhysicalCity())) {
			columnnDisplayNames.clear();
			columnnDisplayNames.add("Physical Addr City");
			isValid = false;
			logger.info("Required check failed on process data id: {}", processData.getId());
			Message msg = Message.builder()
								 .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
								 .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
								 .messageDesc(messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
										 columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
								 .sourceSystemName(MessageSourceSystem.PHLAT)
								 .processData(processData)
								 .build();
			messageService.createMessage(msg);
		}



		if (control.getLoadTypeHds()) {
			// mandoary checks for HDS run type
			if (!StringUtils.hasText(processData.getHdsType())) {
				columnnDisplayNames.clear();
				columnnDisplayNames.add("HDS Type");
				isValid = false;
				logger.info("Required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
						             .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
						             .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
						             .messageDesc(messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
								            columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
									 .sourceSystemName(MessageSourceSystem.PHLAT)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			// mandatory check for PCN Srvc Delivery Type
			if (!StringUtils.hasText(processData.getPcnServiceDeliveryType()) && processData.getStakeholder().equals("PAS")) {
				columnnDisplayNames.clear();
				columnnDisplayNames.add("PCN Srvc Delivery Type");
				isValid = false;
				logger.info("Required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
						             .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
						             .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
						             .messageDesc(messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
								            columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
									 .sourceSystemName(MessageSourceSystem.PHLAT)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			// mandatory check for PCN Clinic  Type
			if (!StringUtils.hasText(processData.getPcnClinicType()) && processData.getStakeholder().equals("PAS")) {
				columnnDisplayNames.clear();
				columnnDisplayNames.add("PCN Clinic Type");
				isValid = false;
				logger.info("Required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
						             .messageType(ProcessDataService.PHLAT_ERROR_TYPE)
						             .messageCode(ProcessDataService.PHLAT_ERROR_CODE)
						             .messageDesc(messageSource.getMessage(PROCESS_DATA_MANDATORY_COLUMN_DISPLAY_NAME,
								            columnnDisplayNames.toArray() , LocaleContextHolder.getLocale()))
									 .sourceSystemName(MessageSourceSystem.PHLAT)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}


		}

			
		if (isValid) { 
			setProcessDataStatus(processData.getId(), RowStatusService.VALID,authenticatedUserId);
		} else {
			setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
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
			controlService.setControlStatus(control.getId(), RowStatusService.PRE_VALIDATION_COMPLETED,
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

			for (ProcessData rec : processDataList) {
				// skip record marked as DO_NOT_LOAD and send to PLR VALID records only
				if (!"Y".equals(rec.getDoNotLoadFlag()) && RowStatusService.VALID.equals(rec.getRowstatusCode())) {
					logger.info("loading process data with id: {} to PLR.", rec.getId());

					// TO-DO call to PLR via ESB here
					// loadPlrViaEsb(control, s);
				}
			}
			controlService.setControlStatus(control.getId(), RowStatusService.PLR_LOAD_COMPLETED, authenticatedUserId);
			logger.info("PLR_LOAD COMPLETED");
		}
	}	
}
