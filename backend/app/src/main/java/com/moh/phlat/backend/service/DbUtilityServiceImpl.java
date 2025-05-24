package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.addressdoctor.service.AddressDoctorValidation;
import com.moh.phlat.backend.databc.service.DataBCValidation;
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
	
	@Autowired
	private AddressDoctorValidation addressDoctorValidation;
	
	@Autowired
	private DataBCValidation dataBCValidation;

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
		
		clearMessagesFromProcessData(processData);
		
		Boolean isValid = true;

		if (control.getLoadTypeFacility() || control.getLoadTypeHds()) {
			// required checks
			if (!StringUtils.hasText(processData.getStakeholder())) {
				isValid = false;
				logger.info("Stakeholder required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Stakeholder cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			if (!StringUtils.hasText(processData.getHdsName())) {
				isValid = false;
				logger.info("HDS Name required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("HDS Name cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			if (!StringUtils.hasText(processData.getHdsType())) {
				isValid = false;
				logger.info("HDS Type required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("HDS Type cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			if (!StringUtils.hasText(processData.getPhysicalAddr1())) {
				isValid = false;
				logger.info("Physical Addr 1 required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Physical Addr 1 cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			if (!StringUtils.hasText(processData.getPhysicalCity())) {
				isValid = false;
				logger.info("Physical Addr City required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Physical Addr City cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			String physicalCountry = "";
			physicalCountry = processData.getPhysicalCountry().toUpperCase();
			if (!StringUtils.hasText(physicalCountry)) {
				isValid = false;
				logger.info("Physical Addr Country required check failed on process data id: {}", processData.getId());
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Physical Address Country cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			} else if (!physicalCountry.equals("CANADA") && !physicalCountry.equals("CA")) {
				isValid = false;
				logger.info("Out of country address on process data id: {}", processData.getId()); 
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Physical Address is out of country")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}	

			String mailCountry = "";
			mailCountry = processData.getMailCountry().toUpperCase();

			if (StringUtils.hasText(processData.getMailAddr1())) {
				if (StringUtils.hasText(mailCountry) &&  !mailCountry.equals("CANADA") && !mailCountry.equals("CA")) {
					isValid = false;
					logger.info("Out of country mailing address on process data id: {}", processData.getId()); 
					Message msg = Message.builder()
										.messageType(DbUtilityService.PHLAT_ERROR_TYPE)
										.messageCode(DbUtilityService.PHLAT_ERROR_CODE)
										.messageDesc("Mailing Address is out of country")
										.sourceSystemName(MessageSourceSystem.PLR)
										.processData(processData)
										.build();
					messageService.createMessage(msg);
				}
			}

			AtomicBoolean hasCorrectEndReasons = new AtomicBoolean(true);
			processData.mapOfAllHdsGroupActions().forEach((group,action) -> {
				
				if (StringUtils.hasText(action)) {
					
					String actionUpper = action.toUpperCase();
					if (!actionUpper.equals(DbUtilityService.PHLAT_END_REASON_CODE_CHG)
							&& !actionUpper.equals(DbUtilityService.PHLAT_END_REASON_CODE_CORR)
							&& !actionUpper.equals(DbUtilityService.PHLAT_END_REASON_CODE_CEASE)) {
						
						hasCorrectEndReasons.set(false);
						logger.info("Invalid end reason code on {} for process data id: {}", group, processData.getId()); 
						Message msg = Message.builder()
											.messageType(DbUtilityService.PHLAT_ERROR_TYPE)
											.messageCode(DbUtilityService.PHLAT_ERROR_CODE)
											.messageDesc("End reason code must be CHG, CORR or CEASE")
											.sourceSystemName(MessageSourceSystem.PLR)
											.processData(processData)
											.build();
						messageService.createMessage(msg);
					}
				}
			});
			isValid = hasCorrectEndReasons.get();

			// error detection

			if (isValid) { 
				setProcessDataStatus(processData.getId(), RowStatusService.VALID,authenticatedUserId);
			} else {
				setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
			}

			if (!physicalCountry.equals("CANADA") && !physicalCountry.equals("CA")) {
				logger.info("Skip record with an out of country physical address on process data id: {}", processData.getId());
				return;
			}

			if (StringUtils.hasText(mailCountry) && !mailCountry.equals("CANADA") && !mailCountry.equals("CA")) {
				logger.info("Skip record with an out of country mailing address on process data id: {}", processData.getId());
				return;
			}

			logger.info("Call Address Doctor on process data id: {} with HDS provider id of {}", processData.getId(), processData.getHdsProviderIdentifier1());

			try {
				addressDoctorValidation.validateAddresses(control, processData);
			} catch (Exception e) {
				logger.error("Error occured: {}", e.getMessage(), e);
			}	

			logger.info("Call dataBC on process data id: {} with HDS provider id of {}", processData.getId(), processData.getHdsProviderIdentifier1());
			
			try {
				dataBCValidation.getDataBCResults(processData);
			} catch (Exception e) {
				logger.error("Error occured: {}", e.getMessage(), e);
			}	

			try {
				dataBCValidation.getCHSAResults(processData);
			} catch (Exception e) {
				logger.error("Error occured: {}", e.getMessage(), e);
			}				

			if (StringUtils.hasText(processData.getPhysicalAddrMailabilityScore())) {
				if (Integer.parseInt(processData.getPhysicalAddrMailabilityScore()) < 3) {
					setProcessDataStatus(processData.getId(), RowStatusService.VALID, authenticatedUserId);
					Message msg = Message.builder()
										.messageType(DbUtilityService.PHLAT_WARNING_TYPE)
										.messageCode(DbUtilityService.PHLAT_WARNING_CODE)
										.messageDesc("Address Doctor mailability score is less than 3")
										.sourceSystemName(MessageSourceSystem.PLR)
										.processData(processData)
										.build();
					messageService.createMessage(msg);
				}
			}

			Integer dataBcScore = null;

			if (StringUtils.hasText(processData.getFacScore())) {
				dataBcScore = Integer.parseInt(processData.getFacScore());
			}

			Integer dataBcPrecisionPoints = null;
			if (StringUtils.hasText(processData.getFacPrecisionPoints())) {
				dataBcPrecisionPoints = Integer.parseInt(processData.getFacPrecisionPoints());
			}

			if (dataBcScore != null && dataBcPrecisionPoints != null) {
				if ((dataBcScore < 96) || (dataBcPrecisionPoints < 98)) {
					setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
					Message msg = Message.builder()
										.messageType(DbUtilityService.PHLAT_ERROR_TYPE)
										.messageCode(DbUtilityService.PHLAT_ERROR_CODE)
										.messageDesc("Data SCORE is less than 96 or PRECISION_POINTS is less than 98")
										.sourceSystemName(MessageSourceSystem.PLR)
										.processData(processData)
										.build();
					messageService.createMessage(msg);
				}
			} else if (dataBcScore != null) {
				if (dataBcScore < 96) {
					setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
					Message msg = Message.builder()
										.messageType(DbUtilityService.PHLAT_ERROR_TYPE)
										.messageCode(DbUtilityService.PHLAT_ERROR_CODE)
										.messageDesc("Data SCORE is less than 96")
										.sourceSystemName(MessageSourceSystem.PLR)
										.processData(processData)
										.build();
					messageService.createMessage(msg);
				}
			} else if (dataBcPrecisionPoints != null) {
				if ((dataBcPrecisionPoints < 98)) {
					setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
					Message msg = Message.builder()
										.messageType(DbUtilityService.PHLAT_ERROR_TYPE)
										.messageCode(DbUtilityService.PHLAT_ERROR_CODE)
										.messageDesc("PRECISION_POINTS is less than 98")
										.sourceSystemName(MessageSourceSystem.PLR)
										.processData(processData)
										.build();
					messageService.createMessage(msg);
				}
			}

			String civicAddress = "";
			civicAddress = processData.getFacCivicAddr();
			if (!StringUtils.hasText(civicAddress) && StringUtils.hasText(processData.getPhysicalAddrValidationStatus())) {
				logger.info("dataBC lookup failed on process data id: {}", processData.getId());
				setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("dataBC failed on Civic Address")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			} else if (!StringUtils.hasText(civicAddress)) {
				logger.info("Civic Address required check on process data id: {}", processData.getId());
				setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
				Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Civic address cannot be empty")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
				messageService.createMessage(msg);
			}

			
			if (StringUtils.hasText(civicAddress)) {
				if (!isValidCivicAddress(civicAddress)) {
					logger.info("Validating Civic Address on process data id: {}", processData.getId());
					setProcessDataStatus(processData.getId(), RowStatusService.INVALID, authenticatedUserId);
					Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_ERROR_TYPE)
									 .messageCode(DbUtilityService.PHLAT_ERROR_CODE)
									 .messageDesc("Invalid civic address format")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
					messageService.createMessage(msg);
				} else if (!isAllUpperCase(civicAddress)) {
					logger.info("Checking upper case Civic Addres on process data id: {}", processData.getId());
					setProcessDataStatus(processData.getId(), RowStatusService.VALID, authenticatedUserId);
					Message msg = Message.builder()
									 .messageType(DbUtilityService.PHLAT_WARNING_TYPE)
									 .messageCode(DbUtilityService.PHLAT_WARNING_CODE)
									 .messageDesc("Civic address is not in uppercase")
									 .sourceSystemName(MessageSourceSystem.PLR)
									 .processData(processData)
									 .build();
					messageService.createMessage(msg);
				}

			}				
		}	
	}

	@Async
	@Transactional
	@Override
	public void validateProcessDataByControlTableId(Long controlTableId, String authenticatedUserId) {
		logger.info("START VALIDATE ASYNC");

		Optional<Control> controlTable = controlRepository.findById(controlTableId);

		if (controlTable.isPresent()) {
			Control control = controlTable.get();

			Iterable<ProcessData> processDataList = processDataRepository
					.getAllProcessDataByControlTableId(controlTableId);
			Integer maxCount =  processDataRepository.getAllProcessDataByControlTableId(controlTableId).size();
			Integer curCount = 0;
			Double percentCompleted = 0.0;
			try {
				for (ProcessData rec : processDataList) {
					curCount++;
					if (maxCount == 0 ) {
						percentCompleted = 100.0;
					} else {
						percentCompleted = ((double) curCount / (double) maxCount) * 100;
					}	
					logger.info("validate process data with id: {} - {} of {} - {}% completed", rec.getId(), curCount, maxCount, (int) Math.rint(percentCompleted));

					// skip if the rowstatus is COMPLETED or marked as DO_NOT_LOAD
					if (!RowStatusService.ON_HOLD.equals(rec.getRowstatusCode())
						&& !RowStatusService.DO_NOT_LOAD.equals(rec.getRowstatusCode())
						&& !RowStatusService.COMPLETED.equals(rec.getRowstatusCode())) {

						// run asyn process
						validateProcessData(control, rec, authenticatedUserId);

						// Check if there has been repeated issues with the AddressDoctor calls
						if (addressDoctorValidation.getAddressDoctorService().hasPersistentIssue()) {
							// Too many errors are happening back to back; stop the validation process
							setControlStatus(control.getId(), RowStatusService.UPLOAD_ERROR, authenticatedUserId);
							logger.error("AddressDoctor connection has a persistent issue. Ending this run. Resolve and try again later.");
							return;
						}
						
					} else {
						logger.info("skip validating process data with id: {}", rec.getId());
					}
				}
				setControlStatus(control.getId(), RowStatusService.PRE_VALIDATION_COMPLETED,
								authenticatedUserId);
				logger.info(RowStatusService.PRE_VALIDATION_COMPLETED);
			} catch (Exception e) {
				setControlStatus(control.getId(), RowStatusService.UPLOAD_ERROR,
								authenticatedUserId);
				logger.error("Error occured: {}", e.getMessage(), e);
			}				
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
				// send to PLR records with VALID status only 
				if (RowStatusService.VALID.equals(processData.getRowstatusCode())) {
					logger.info("loading process data with id: {} to PLR.", processData.getId());

					clearMessagesFromProcessData(processData);
					
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
	
	public void clearMessagesFromProcessData(ProcessData processData) {
		if (processData.getMessages() != null && !processData.getMessages().isEmpty()) {
			messageService.deleteMessages(processData.getMessages());
			processData.getMessages().clear();
			processDataRepository.save(processData);
		}
	}

	public static boolean isValidCivicAddress(String address) {
        if (address == null || address.isEmpty()) {
            return false;
        }

        // Split the address into parts
        String[] parts = address.split(", ");
        if (parts.length != 3) {
            return false;
        }

        String streetPart = parts[0];

        // Validate the street part (civic number and street name)
        String[] streetParts = streetPart.split(" ");
        if (streetParts.length < 2) {
            return false;
        }

        // Check if the first part is a number (civic number)
        try {
            Integer.parseInt(streetParts[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
	
	public static boolean isAllUpperCase(String address) {
        // Check if the address is not null and not empty
        if (address == null || address.isEmpty()) {
            return false;
        }
        
        // Iterate through each character in the string
        for (char c : address.toCharArray()) {
            // If the character is a letter and not uppercase, return false
            if (Character.isLetter(c) && !Character.isUpperCase(c)) {
                return false;
            }
        }
        
        // If all letters are uppercase, return true
        return true;
    }

}
