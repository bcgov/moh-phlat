package com.moh.phlat.backend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.moh.phlat.backend.service.ProcessDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import jakarta.validation.Valid;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.ProcessDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.dto.ReportSummary;
import com.moh.phlat.backend.service.RowStatusService;

@RestController
@RequestMapping("/processdata")
@CrossOrigin(origins = "*")
public class ProcessDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessDataController.class);

	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private ProcessDataRepository processDataRepository;

	@Autowired
	private DbUtilityService dbUtilityService;

	@Autowired
	private ProcessDataService processDataService;

	// get process data by control id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/controltable/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllProcessDataByControlTableId(
		@PathVariable Long controlTableId, @RequestParam(required = false) String rowStatus, @RequestParam(required =false) List<String> id,
		@RequestParam(required =false) List<String> actions, @RequestParam(required =false) List<String> rowStatusCode,
		@RequestParam(required =false) List<String> doNotLoad, @RequestParam(required =false) List<String> stakeholder,
		@RequestParam(required =false) List<String> hdsLpcId, @RequestParam(required =false) List<String> hdsCpnId,
		@RequestParam(required =false) List<String> hdsProviderId1, @RequestParam(required =false) List<String> hdsProviderId2,
		@RequestParam(required =false) List<String> hdsProviderId3, @RequestParam(required =false) List<String> hdsProviderIdType1,
		@RequestParam(required =false) List<String> hdsProviderIdType2, @RequestParam(required =false) List<String> hdsProviderIdType3,
		@RequestParam(required =false) List<String> hdsHibcFacId, @RequestParam(required =false) List<String> hdsType,
		@RequestParam(required =false) List<String> hdsName, @RequestParam(required =false) List<String> hdsNameAlias,
		@RequestParam(required =false) List<String> hdsPrefNameFlag, @RequestParam(required =false) List<String> hdsEmail,
		@RequestParam(required =false) List<String> hdsWebsite, @RequestParam(required =false) List<String> hdsBusTelAreaCode,
		@RequestParam(required =false) List<String> hdsBusTelNum, @RequestParam(required =false) List<String> hdsTelExt,
		@RequestParam(required =false) List<String> hdsCellAreaCode, @RequestParam(required =false) List<String> hdsCellNum,
		@RequestParam(required =false) List<String> hdsFaxAreaCode, @RequestParam(required =false) List<String> hdsFaxNum,
		@RequestParam(required =false) List<String> hdsServiceDelType, @RequestParam(required =false) List<String> pcnCLinicType,
		@RequestParam(required =false) List<String> pcnPciFlag, @RequestParam(required =false) List<String> hdsHoursOfOp,
		@RequestParam(required =false) List<String> hdsContactName, @RequestParam(required =false) List<String> hdsIsForProfitFlag,
		@RequestParam(required =false) List<String> sourceStatus, @RequestParam(required =false) List<String> hdsParentIpcId,
		@RequestParam(required =false) List<String> busIpcId, @RequestParam(required =false) List<String> busCpnId,
		@RequestParam(required =false) List<String> busName, @RequestParam(required =false) List<String> busLegalName,
		@RequestParam(required =false) List<String> busPayeeNum, @RequestParam(required =false) List<String> busOwnerName,
		@RequestParam(required =false) List<String> busOwnerType, @RequestParam(required =false) List<String> busOwnerTypeOther,
		@RequestParam(required =false) List<String> facBuildingName, @RequestParam(required =false) List<String> facHdsDetailAddInfo,
		@RequestParam(required =false) List<String> physAddr1, @RequestParam(required =false) List<String> physAddr2,
		@RequestParam(required =false) List<String> physAddr3, @RequestParam(required =false) List<String> physAddr4,
		@RequestParam(required =false) List<String> physCity, @RequestParam(required =false) List<String> physProv,
		@RequestParam(required =false) List<String> physPCode, @RequestParam(required =false) List<String> physCountry,
		@RequestParam(required =false) List<String> physAddrIsPrivate, @RequestParam(required =false) List<String> mailAddr1,
		@RequestParam(required =false) List<String> mailAddr2, @RequestParam(required =false) List<String> mailAddr3,
		@RequestParam(required =false) List<String> mailAddr4, @RequestParam(required =false) List<String> mailCity,
		@RequestParam(required =false) List<String> mailBc, @RequestParam(required =false) List<String> mailPcode,
		@RequestParam(required =false) List<String> mailCountry, @RequestParam(required =false) List<String> mailAddrIsPriv,
		@RequestParam(required =false) List<String> messages) {

		//TODO this should be replaced by call to ControlService which is not yet introduced
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Process Data not found for control_id: " + controlTableId, "[]"));
		}

		if (rowStatus == null && id == null && actions == null && rowStatusCode == null && messages== null && doNotLoad == null && stakeholder == null && 
				hdsLpcId == null && hdsCpnId == null && hdsProviderId1 == null && hdsProviderId2 == null && hdsProviderId3 == null && 
				hdsProviderIdType1 == null && hdsProviderIdType2 == null && hdsProviderIdType3 == null && hdsHibcFacId == null && hdsType == null && 
				hdsName == null && hdsNameAlias == null && hdsPrefNameFlag == null && hdsEmail == null && hdsWebsite == null && 
				hdsBusTelAreaCode == null && hdsBusTelNum == null && hdsTelExt == null && hdsCellAreaCode == null && hdsCellNum == null && 
				hdsFaxAreaCode == null && hdsFaxNum == null && hdsServiceDelType == null && pcnCLinicType == null && pcnPciFlag == null && 
				hdsHoursOfOp == null && hdsContactName == null && hdsIsForProfitFlag == null && sourceStatus == null && hdsParentIpcId == null && 
				busIpcId == null && busCpnId == null && busName == null && busLegalName == null && busPayeeNum == null && busOwnerName == null && 
				busOwnerType == null && busOwnerTypeOther == null && facBuildingName == null && facHdsDetailAddInfo == null && physAddr1 == null && 
				physAddr2 == null && physAddr3 == null && physAddr4 == null && physCity == null && physProv == null && physPCode == null && 
				physCountry == null && physAddrIsPrivate == null && mailAddr1 == null && mailAddr2 == null && mailAddr3 == null && mailAddr4 == null && 
				mailCity == null && mailBc == null && mailPcode == null && mailCountry == null && mailAddrIsPriv == null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
				processDataRepository.getAllProcessDataByControlTableId(controlTableId)));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", 
					processDataRepository.findAll(ProcessDataRepository.buildSpecificationIn(controlTableId, rowStatus, id, actions,  rowStatusCode, 
							messages, doNotLoad, stakeholder, hdsLpcId, hdsCpnId, hdsProviderId1, hdsProviderId2, hdsProviderId3, hdsProviderIdType1, 
							hdsProviderIdType2, hdsProviderIdType3, hdsHibcFacId, hdsType, hdsName, hdsNameAlias, hdsPrefNameFlag, hdsEmail,
							hdsWebsite, hdsBusTelAreaCode, hdsBusTelNum, hdsTelExt, hdsCellAreaCode, hdsCellNum, hdsFaxAreaCode, hdsFaxNum,
							hdsServiceDelType, pcnCLinicType, pcnPciFlag, hdsHoursOfOp, hdsContactName, hdsIsForProfitFlag,
							sourceStatus, hdsParentIpcId, busIpcId, busCpnId, busName, busLegalName, busPayeeNum, busOwnerName,
							busOwnerType, busOwnerTypeOther, facBuildingName, facHdsDetailAddInfo, physAddr1, physAddr2,
							physAddr3, physAddr4, physCity, physProv, physPCode, physCountry, physAddrIsPrivate, mailAddr1,
							mailAddr2, mailAddr3, mailAddr4, mailCity, mailBc, mailPcode, mailCountry, mailAddrIsPriv))));
		}
	}

	// get specific row by id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getProcessDataById(@PathVariable Long id) {
		Optional<ProcessData> processData = processDataRepository.findById(id);
		if (processData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Process Data not found for id: " + id, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", processDataRepository.findById(id)));

	}

		
	// update an existing record
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateProcessDataById(@RequestBody @Valid ProcessData reqProcessData,
			@PathVariable Long id) {

		Optional<ProcessData> processDataTable = processDataRepository.findById(id);

		if (processDataTable.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("error", 404, "Process data not found with id: " + id, "[]"));
		}

		ProcessData processData = processDataTable.get();

		if (StringUtils.hasText(reqProcessData.getDoNotLoadFlag()))
			processData.setDoNotLoadFlag(reqProcessData.getDoNotLoadFlag());
		
		if (StringUtils.hasText(reqProcessData.getStakeholder()))
			processData.setStakeholder(reqProcessData.getStakeholder());
		
		if (StringUtils.hasText(reqProcessData.getStakeholderId()))
			processData.setStakeholderId(reqProcessData.getStakeholderId());

		if (StringUtils.hasText(reqProcessData.getHdsIpcId()))
			processData.setHdsIpcId(reqProcessData.getHdsIpcId());

		if (StringUtils.hasText(reqProcessData.getHdsCpnId()))
			processData.setHdsCpnId(reqProcessData.getHdsCpnId());

		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifier1()))
			processData.setHdsProviderIdentifier1(reqProcessData.getHdsProviderIdentifier1());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifier2()))
			processData.setHdsProviderIdentifier2(reqProcessData.getHdsProviderIdentifier2());

		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifier3()))
			processData.setHdsProviderIdentifier3(reqProcessData.getHdsProviderIdentifier3());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifierType1()))
			processData.setHdsProviderIdentifierType1(reqProcessData.getHdsProviderIdentifierType1());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifierType2()))
			processData.setHdsProviderIdentifierType2(reqProcessData.getHdsProviderIdentifierType2());
		
		if (StringUtils.hasText(reqProcessData.getHdsProviderIdentifierType3()))
			processData.setHdsProviderIdentifierType3(reqProcessData.getHdsProviderIdentifierType3());

		if (StringUtils.hasText(reqProcessData.getHdsMspFacilityNumber()))
			processData.setHdsMspFacilityNumber(reqProcessData.getHdsMspFacilityNumber());

		if (StringUtils.hasText(reqProcessData.getHdsPauthId()))
			processData.setHdsPauthId(reqProcessData.getHdsPauthId());

		if (StringUtils.hasText(reqProcessData.getHdsCategoryCode()))
			processData.setHdsCategoryCode(reqProcessData.getHdsCategoryCode());

		if (StringUtils.hasText(reqProcessData.getHdsRoleTypeCode()))
			processData.setHdsRoleTypeCode(reqProcessData.getHdsRoleTypeCode());

		if (StringUtils.hasText(reqProcessData.getHdsType()))
			processData.setHdsType(reqProcessData.getHdsType());

		if (StringUtils.hasText(reqProcessData.getHdsSubType()))
			processData.setHdsSubType(reqProcessData.getHdsSubType());			
		
		if (StringUtils.hasText(reqProcessData.getHdsUserChid()))
			processData.setHdsUserChid(reqProcessData.getHdsUserChid());		
		
		if (StringUtils.hasText(reqProcessData.getHdsCreatedDts()))
			processData.setHdsCreatedDts(reqProcessData.getHdsCreatedDts());	

		if (StringUtils.hasText(reqProcessData.getHdsInvalidatedDts()))
			processData.setHdsInvalidatedDts(reqProcessData.getHdsInvalidatedDts());	

		if (StringUtils.hasText(reqProcessData.getHdsName()))
			processData.setHdsName(reqProcessData.getHdsName());
	
		if (StringUtils.hasText(reqProcessData.getHdsPreferredNameFlag()))
			processData.setHdsPreferredNameFlag(reqProcessData.getHdsPreferredNameFlag());
		
		if (StringUtils.hasText(reqProcessData.getHdsEmail()))
			processData.setHdsEmail(reqProcessData.getHdsEmail());
		
		if (reqProcessData.getHdsWebsite() != null)
			processData.setHdsWebsite(reqProcessData.getHdsWebsite());

		if (StringUtils.hasText(reqProcessData.getHdsBusTelAreaCode()))
			processData.setHdsBusTelAreaCode(reqProcessData.getHdsBusTelAreaCode());
		
		if (StringUtils.hasText(reqProcessData.getHdsBusTelNumber()))
			processData.setHdsBusTelNumber(reqProcessData.getHdsBusTelNumber());
		
		if (StringUtils.hasText(reqProcessData.getHdsTelExtension()))
			processData.setHdsTelExtension(reqProcessData.getHdsTelExtension());
		
		if (StringUtils.hasText(reqProcessData.getHdsCellAreaCode()))
			processData.setHdsCellAreaCode(reqProcessData.getHdsCellAreaCode());
		
		if (StringUtils.hasText(reqProcessData.getHdsCellNumber()))
			processData.setHdsCellNumber(reqProcessData.getHdsCellNumber());
		
		if (StringUtils.hasText(reqProcessData.getHdsFaxAreaCode()))
			processData.setHdsFaxAreaCode(reqProcessData.getHdsFaxAreaCode());
		
		if (StringUtils.hasText(reqProcessData.getHdsFaxNumber()))
			processData.setHdsFaxNumber(reqProcessData.getHdsFaxNumber());
		
		if (StringUtils.hasText(reqProcessData.getPcnServiceDeliveryType()))
			processData.setPcnServiceDeliveryType(reqProcessData.getPcnServiceDeliveryType());	

		if (StringUtils.hasText(reqProcessData.getPcnClinicType()))
			processData.setPcnClinicType(reqProcessData.getPcnClinicType());
		
		if (StringUtils.hasText(reqProcessData.getPcnPciFlag()))
			processData.setPcnPciFlag(reqProcessData.getPcnPciFlag());
		
		if (StringUtils.hasText(reqProcessData.getSourceStatus()))
			processData.setSourceStatus(reqProcessData.getSourceStatus());
		
		if (StringUtils.hasText(reqProcessData.getPcnClinicStatus()))
			processData.setPcnClinicStatus(reqProcessData.getPcnClinicStatus());

		if (StringUtils.hasText(reqProcessData.getHdsEffectiveStartDate()))
			processData.setHdsEffectiveStartDate(reqProcessData.getHdsEffectiveStartDate());

		if (StringUtils.hasText(reqProcessData.getHdsEffectiveEndDate()))
			processData.setHdsEffectiveEndDate(reqProcessData.getHdsEffectiveEndDate());	

		if (StringUtils.hasText(reqProcessData.getFacAddressUnit()))
			processData.setFacAddressUnit(reqProcessData.getFacAddressUnit());	

		if (StringUtils.hasText(reqProcessData.getFacBuildingName()))
			processData.setFacBuildingName(reqProcessData.getFacBuildingName());	

		if (StringUtils.hasText(reqProcessData.getFacCivicAddrId()))
			processData.setFacCivicAddrId(reqProcessData.getFacCivicAddrId());	

		if (StringUtils.hasText(reqProcessData.getFacCivicAddr()))
			processData.setFacCivicAddr(reqProcessData.getFacCivicAddr());	

		if (StringUtils.hasText(reqProcessData.getFacLatitude()))
			processData.setFacLatitude(reqProcessData.getFacLatitude());	

		if (StringUtils.hasText(reqProcessData.getFacLongitude()))
			processData.setFacLongitude(reqProcessData.getFacLongitude());	

		if (StringUtils.hasText(reqProcessData.getFacStreetDirection()))
			processData.setFacStreetDirection(reqProcessData.getFacStreetDirection());	

		if (StringUtils.hasText(reqProcessData.getStreetDirectionPrefix()))
			processData.setStreetDirectionPrefix(reqProcessData.getStreetDirectionPrefix());	

		if (StringUtils.hasText(reqProcessData.getStreetTypePrefix()))
			processData.setStreetTypePrefix(reqProcessData.getStreetTypePrefix());	

		if (StringUtils.hasText(reqProcessData.getFacCivicNumber()))
			processData.setFacCivicNumber(reqProcessData.getFacCivicNumber());

		if (StringUtils.hasText(reqProcessData.getFacStreetName()))
			processData.setFacStreetName(reqProcessData.getFacStreetName());

		if (StringUtils.hasText(reqProcessData.getFacStreetType()))
			processData.setFacStreetType(reqProcessData.getFacStreetType());

		if (StringUtils.hasText(reqProcessData.getFacLocalityName()))
			processData.setFacLocalityName(reqProcessData.getFacLocalityName());

		if (StringUtils.hasText(reqProcessData.getFacProvinceCode()))
			processData.setFacProvinceCode(reqProcessData.getFacProvinceCode());

		if (StringUtils.hasText(reqProcessData.getFacSiteId()))
			processData.setFacSiteId(reqProcessData.getFacSiteId());

		if (StringUtils.hasText(reqProcessData.getFacScore()))
			processData.setFacScore(reqProcessData.getFacScore());

		if (StringUtils.hasText(reqProcessData.getFacMatchPrecision()))
			processData.setFacMatchPrecision(reqProcessData.getFacMatchPrecision());

		if (StringUtils.hasText(reqProcessData.getFacPrecisionPoints()))
			processData.setFacPrecisionPoints(reqProcessData.getFacPrecisionPoints());

		if (StringUtils.hasText(reqProcessData.getFacHsdaName()))
			processData.setFacHsdaName(reqProcessData.getFacHsdaName());

		if (StringUtils.hasText(reqProcessData.getFacDatabcResults()))
			processData.setFacDatabcResults(reqProcessData.getFacDatabcResults());

		if (StringUtils.hasText(reqProcessData.getFacPcnCode()))
			processData.setFacPcnCode(reqProcessData.getFacPcnCode());

		if (StringUtils.hasText(reqProcessData.getFacPcnName()))
			processData.setFacPcnName(reqProcessData.getFacPcnName());

		if (StringUtils.hasText(reqProcessData.getFacChsaStatus()))
			processData.setFacChsaStatus(reqProcessData.getFacChsaStatus());

		if (StringUtils.hasText(reqProcessData.getFacPcnStatus()))
			processData.setFacPcnStatus(reqProcessData.getFacPcnStatus());

		if (StringUtils.hasText(reqProcessData.getFacChsaCode()))
			processData.setFacChsaCode(reqProcessData.getFacChsaCode());

		if (StringUtils.hasText(reqProcessData.getFacChsaName()))
			processData.setFacChsaName(reqProcessData.getFacChsaName());

		if (StringUtils.hasText(reqProcessData.getFacLhaName()))
			processData.setFacLhaName(reqProcessData.getFacLhaName());

		if (StringUtils.hasText(reqProcessData.getFacHaName()))
			processData.setFacHaName(reqProcessData.getFacHaName());

		if (StringUtils.hasText(reqProcessData.getFacRelnType()))
			processData.setFacRelnType(reqProcessData.getFacRelnType());

		if (StringUtils.hasText(reqProcessData.getFacTypeCode()))
			processData.setFacTypeCode(reqProcessData.getFacTypeCode());

		if (StringUtils.hasText(reqProcessData.getPhysicalAddr1()))
			processData.setPhysicalAddr1(reqProcessData.getPhysicalAddr1());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalAddr2()))
			processData.setPhysicalAddr2(reqProcessData.getPhysicalAddr2());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalAddr3()))
			processData.setPhysicalAddr3(reqProcessData.getPhysicalAddr3());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalAddr4()))
			processData.setPhysicalAddr4(reqProcessData.getPhysicalAddr4());
		
		if (StringUtils.hasText(reqProcessData.getPhysicalCity()))
			processData.setPhysicalCity(reqProcessData.getPhysicalCity());

		if (StringUtils.hasText(reqProcessData.getPhysicalProvince()))
			processData.setPhysicalProvince(reqProcessData.getPhysicalProvince());

		if (StringUtils.hasText(reqProcessData.getPhysicalPcode()))
			processData.setPhysicalPcode(reqProcessData.getPhysicalPcode());

		if (StringUtils.hasText(reqProcessData.getPhysicalCountry()))
			processData.setPhysicalCountry(reqProcessData.getPhysicalCountry());

		if (StringUtils.hasText(reqProcessData.getPhysicalAddrPrpsTypeCd()))
			processData.setPhysicalAddrPrpsTypeCd(reqProcessData.getPhysicalAddrPrpsTypeCd());

		if (StringUtils.hasText(reqProcessData.getPhysicalAddrValidationStatus()))
			processData.setPhysicalAddrValidationStatus(reqProcessData.getPhysicalAddrValidationStatus());		

		if (StringUtils.hasText(reqProcessData.getMailAddr1()))
			processData.setMailAddr1(reqProcessData.getMailAddr1());

		if (StringUtils.hasText(reqProcessData.getMailAddr2()))
			processData.setMailAddr2(reqProcessData.getMailAddr2());
		
		if (StringUtils.hasText(reqProcessData.getMailAddr3()))
			processData.setMailCity(reqProcessData.getMailCity());
		
		if (StringUtils.hasText(reqProcessData.getMailBc()))
			processData.setMailBc(reqProcessData.getMailBc());
		
		if (StringUtils.hasText(reqProcessData.getMailPcode()))
			processData.setMailPcode(reqProcessData.getMailPcode());
		
		if (StringUtils.hasText(reqProcessData.getMailCountry()))
			processData.setMailCountry(reqProcessData.getMailCountry());
		
		if (StringUtils.hasText(reqProcessData.getMailAddrPrpsTypeCd()))
			processData.setMailAddrPrpsTypeCd(reqProcessData.getMailAddrPrpsTypeCd());

		if (StringUtils.hasText(reqProcessData.getMailAddrValidationStatus()))
			processData.setMailAddrValidationStatus(reqProcessData.getMailAddrValidationStatus());		

		if (StringUtils.hasText(reqProcessData.getPlrFacilityId()))
			processData.setPlrFacilityId(reqProcessData.getPlrFacilityId());

		if (StringUtils.hasText(reqProcessData.getRowstatusCode()))
			processData.setRowstatusCode(reqProcessData.getRowstatusCode());

		processData.setUpdatedBy(AuthenticationUtils.getAuthenticatedUserId());
		processData.setUpdatedAt(new Date());

		try {
			processDataRepository.save(processData);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Record updated sucessfully.", processData));
		} catch (Exception e) {
			logger.error("Error occured: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 500,
					"Internal error encountered while updating Process Data with id: " + id, "[]"));
		}
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getformfields/header")
	public String getAllHeader() {
		return dbUtilityService.getVariablesByTableNameSortedById("PROCESS_DATA");
	}

	// get specific row by id
	@PutMapping("/validate/{id}")
	public ResponseEntity<ResponseMessage> validateProcessDataById(@PathVariable Long id) {

		Optional<ProcessData> processDataTable = processDataRepository.findById(id);
		String authenticatedUserId=AuthenticationUtils.getAuthenticatedUserId();
		if (processDataTable.isPresent()) {
			ProcessData processData = processDataTable.get();

			Optional<Control> controlTable = controlRepository.findById(processData.getControlTableId());
			if (controlTable.isPresent()) {
				Control control = controlTable.get();
				
				dbUtilityService.setControlStatus(processData.getControlTableId(), RowStatusService.PRE_VALIDATION_IN_PROGRESS,authenticatedUserId );
				
				if ((!processData.getDoNotLoadFlag().equals("Y")) && (!processData.getRowstatusCode().equals("DO_NOT_LOAD")) && (!processData.getRowstatusCode().equals(RowStatusService.COMPLETED))) {
					logger.info("validate process data with id: {}", id);
				    // run asyn process
					dbUtilityService.validateProcessData(control, processData,authenticatedUserId);
				} else {
					logger.info("skip validating process data with id: {}", id);
				}

				dbUtilityService.setControlStatus(processData.getControlTableId(), RowStatusService.PRE_VALIDATION_COMPLETED,
												  authenticatedUserId);
				
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
						controlRepository.findById(processData.getControlTableId())));
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage("error", 404, "Control Id not found.", "[]"));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/validateallbycontroltableid/{id}")
	public ResponseEntity<ResponseMessage> validateAllProcessData(@PathVariable Long id) {

		
		List<ProcessData> processDataList = processDataRepository.getAllProcessDataByControlTableId(id);
		
		if (processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Nothing to validate.", "[]"));
			
		}
		String authenticatedUserId= AuthenticationUtils.getAuthenticatedUserId();
		dbUtilityService.setControlStatus(id, RowStatusService.PRE_VALIDATION_COMPLETED,
										  authenticatedUserId);
		// asynchronous operation
		dbUtilityService.validateProcessDataByControlTableId(id,authenticatedUserId);

		Optional<Control> control = controlRepository.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Validation process started!", control));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PutMapping("/plrload/{controlTableId}")
	public ResponseEntity<ResponseMessage> plrLoad(@PathVariable Long controlTableId) {

		Optional<Control> controlTable = controlRepository.findById(controlTableId);
		if (controlTable.isPresent()) {
			Control control = controlTable.get();
			if (!control.getStatusCode().equals(RowStatusService.APPROVED)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("error", 404, "Missing approval from Reg Admin to load to PLR.", "[]"));
			}
		}

		List<ProcessData> processDataList = processDataRepository.getAllProcessDataByControlTableId(controlTableId);
		
		if (processDataList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("error", 404, "Nothing to load to PLR.", "[]"));			
		}
		
		String authenticatedUserId= AuthenticationUtils.getAuthenticatedUserId();
		dbUtilityService.setControlStatus(controlTableId, RowStatusService.PLR_LOAD_IN_PROGRESS,
										  authenticatedUserId);
		// asynchronous operation
		dbUtilityService.loadProcessDataToPlr(controlTableId,authenticatedUserId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "PLR load process started!", controlTable));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/reportsummary/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getReportSummaryByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required = false) String rowStatus) {
		
		List<ReportSummary> list = processDataService.getReportSummary(controlTableId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", list));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/{controltableId}/distinct-values/{columnKey}")
	public ResponseEntity<ResponseMessage> getDistinctColumnValues(@PathVariable String controltableId, @PathVariable String columnKey) {
	
		List<String> processData = new ArrayList<String>();
	
		switch(columnKey) {
			case "id":
				processData = processDataRepository.findAllDistinctId();
				break;
			case "control_id":
				processData = processDataRepository.findAllDistinctControlId();
				break;
			case "do_not_load":
				processData = processDataRepository.findAllDistinctDoNotLoad();
				break;
			case "stakeholder":
				processData = processDataRepository.findAllDistinctStakeholder();
				break;
			case "hds_ipc_id":
				processData = processDataRepository.findAllDistinctHdsIpcId();
				break;
			case "hds_cpn_id":
				processData = processDataRepository.findAllDistinctHdsCpnId();
				break;
			case "hds_provider_identifier1":
				processData = processDataRepository.findAllDistinctHdsProviderIdentifier1();
				break;
			case "hds_provider_identifier2":
				processData = processDataRepository.findAllDistinctHdsProviderIdentifier2();
				break;
			case "hds_provider_identifier3":
				processData = processDataRepository.findAllDistinctHdsProviderIdentifier3();
				break;
			case "hds_provider_identifier_type1":
				processData = processDataRepository.findAllDistinctHdsProviderIdentifierType1();
				break;
			case "hds_provider_identifier_type2":
				processData = processDataRepository.findAllDistinctHdsProviderIdentifierType2();
				break;
			case "hds_provider_identifier_type3":
				processData = processDataRepository.findAllDistinctHdsProviderIdentifierType3();
				break;
			case "hds_hibc_facility_id":
				processData = processDataRepository.findAllDistinctHdsHibcFacilityId();
				break;
			case "hds_type":
				processData = processDataRepository.findAllDistinctHdsType();
				break;
			case "hds_name":
				processData = processDataRepository.findAllDistinctHdsName();
				break;
			case "hds_name_alias":
				processData = processDataRepository.findAllDistinctHdsNameAlias();
				break;
			case "hds_preferred_name_flag":
				processData = processDataRepository.findAllDistinctHdsPreferredNameFlag();
				break;
			case "hds_email":
				processData = processDataRepository.findAllDistinctHdsEmail();
				break;
			case "hds_website":
				processData = processDataRepository.findAllDistinctHdsWebsite();
				break;
			case "HDS_BUS_TEL_AREA_CODE":
				processData = processDataRepository.findAllDistinctHdsBusTelAreaCode();
				break;
			case "HDS_BUS_TEL_NUMBER":
				processData = processDataRepository.findAllDistinctHdsBusTelNumber();
				break;
			case "HDS_TEL_EXTENSION":
				processData = processDataRepository.findAllDistinctHdsTelExtension();
				break;
			case "HDS_CELL_AREA_CODE":
				processData = processDataRepository.findAllDistinctHdsCellAreaCode();
				break;
			case "HDS_CELL_NUMBER":
				processData = processDataRepository.findAllDistinctHdsCellNumber();
				break;
			case "HDS_FAX_AREA_CODE":
				processData = processDataRepository.findAllDistinctHdsFaxAreaCode();
				break;
			case "HDS_FAX_NUMBER":
				processData = processDataRepository.findAllDistinctHdsFaxNumber();
				break;
			case "HDS_SERVICE_DELIVERY_TYPE":
				processData = processDataRepository.findAllDistinctHdsServiceDeliveryType();
				break;
			case "PCN_CLINIC_TYPE":
				processData = processDataRepository.findAllDistinctPcnClinicType();
				break;
			case "PCN_PCI_FLAG":
				processData = processDataRepository.findAllDistinctPcnPciFlag();
				break;
			case "HDS_HOURS_OF_OPERATION":
				processData = processDataRepository.findAllDistinctHdsHoursOfOperation();
				break;
			case "HDS_CONTACT_NAME":
				processData = processDataRepository.findAllDistinctHdsContactName();
				break;
			case "HDS_IS_FOR_PROFIT_FLAG":
				processData = processDataRepository.findAllDistinctHdsIsForProfitFlag();
				break;
			case "SOURCE_STATUS":
				processData = processDataRepository.findAllDistinctSourceStatus();
				break;
			case "HDS_PARENT_IPC_ID":
				processData = processDataRepository.findAllDistinctHdsParentIpcId();
				break;
			case "BUS_IPC_ID":
				processData = processDataRepository.findAllDistinctBusIpcId();
				break;
			case "BUS_CPN_ID":
				processData = processDataRepository.findAllDistinctBusCpnId();
				break;
			case "BUS_NAME":
				processData = processDataRepository.findAllDistinctBusName();
				break;
			case "BUS_LEGAL_NAME":
				processData = processDataRepository.findAllDistinctBusLegalName();
				break;
			case "BUS_PAYEE_NUMBER":
				processData = processDataRepository.findAllDistinctBusPayeeNumber();
				break;
			case "BUS_OWNER_NAME":
				processData = processDataRepository.findAllDistinctBusOwnerName();
				break;
			case "BUS_OWNER_TYPE":
				processData = processDataRepository.findAllDistinctBusOwnerType();
				break;
			case "BUS_OWNER_TYPE_OTHER":
				processData = processDataRepository.findAllDistinctBusOwnerTypeOther();
				break;
			case "FAC_BUILDING_NAME":
				processData = processDataRepository.findAllDistinctFacBuildingName();
				break;
			case "FACILITY_HDS_DETAILS_ADDITIONAL_INFO":
				processData = processDataRepository.findAllDistinctFacilityHdsDetailsAdditionalInfo();
				break;
			case "PHYSICAL_ADDR1":
				processData = processDataRepository.findAllDistinctPhysicalAddr1();
				break;
			case "PHYSICAL_ADDR2":
				processData = processDataRepository.findAllDistinctPhysicalAddr2();
				break;
			case "PHYSICAL_ADDR3":
				processData = processDataRepository.findAllDistinctPhysicalAddr3();
				break;
			case "PHYSICAL_ADDR4":
				processData = processDataRepository.findAllDistinctPhysicalAddr4();
				break;
			case "PHYSICAL_CITY":
				processData = processDataRepository.findAllDistinctPhysicalCity();
				break;
			case "PHYSICAL_PROVINCE":
				processData = processDataRepository.findAllDistinctPhysicalProvince();
				break;
			case "PHYSICAL_PCODE":
				processData = processDataRepository.findAllDistinctPhysicalPcode();
				break;
			case "PHYSICAL_COUNTRY":
				processData = processDataRepository.findAllDistinctPhysicalCountry();
				break;
			case "PHYS_ADDR_IS_PRIVATE":
				processData = processDataRepository.findAllDistinctPhysAddrIsPrivate();
				break;
			case "MAIL_ADDR1":
				processData = processDataRepository.findAllDistinctMailAddr1();
				break;
			case "MAIL_ADDR2":
				processData = processDataRepository.findAllDistinctMailAddr2();
				break;
			case "MAIL_ADDR3":
				processData = processDataRepository.findAllDistinctMailAddr3();
				break;
			case "MAIL_ADDR4":
				processData = processDataRepository.findAllDistinctMailAddr4();
				break;
			case "MAIL_CITY":
				processData = processDataRepository.findAllDistinctMailCity();
				break;
			case "MAIL_BC":
				processData = processDataRepository.findAllDistinctMailBc();
				break;
			case "MAIL_PCODE":
				processData = processDataRepository.findAllDistinctMailPcode();
				break;
			case "MAIL_COUNTRY":
				processData = processDataRepository.findAllDistinctMailCountry();
				break;
			case "MAIL_ADDR_IS_PRIVATE":
				processData = processDataRepository.findAllDistinctMailAddrIsPrivate();
				break;
			default:
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error", 404, "Column not found.", 
						processData));
		}
	
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", processData));
	}
}
