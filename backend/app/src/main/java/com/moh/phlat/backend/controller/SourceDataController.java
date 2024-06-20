package com.moh.phlat.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.SourceDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.DbUtilityService;
import com.moh.phlat.backend.service.FileService;

@RestController
@RequestMapping("/sourcedata")
@CrossOrigin(origins = "*")
public class SourceDataController {
	private static final Logger logger = LoggerFactory.getLogger(SourceDataController.class);

	@Autowired
	private ControlRepository controlRepository;

	@Autowired
	private SourceDataRepository sourceDataRepository;

	@Autowired
	private FileService fileService;

	@Autowired
	private DbUtilityService dbUtilityService;

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllSourceDatas() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", sourceDataRepository.findAll()));
		// return sourceDataRepository.findAll();

	}

	// get specific row by id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getSourceDataById(@PathVariable Long id) {

		Optional<SourceData> soureData = sourceDataRepository.findById(id);
		if (soureData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Source Data not found for id: " + id, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", sourceDataRepository.findById(id)));

	}

	// get source data by control id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/controltableid/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllSourceDataByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required =false) List<String> id,
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
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Process Data not found for control_id: " + controlTableId, "[]"));
		}		
		
		
		if (id == null && doNotLoad == null && stakeholder == null && 
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
					sourceDataRepository.getAllSourceDataByControlTableId(controlTableId)));
			// return sourceDataRepository.getAllSourceDataByControlTableId(controlTableId);
		} else {
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "",
					sourceDataRepository.findAll(SourceDataRepository.buildSpecificationIn(controlTableId, id,
							doNotLoad, stakeholder, hdsLpcId, hdsCpnId, hdsProviderId1, hdsProviderId2, hdsProviderId3, hdsProviderIdType1, 
							hdsProviderIdType2, hdsProviderIdType3, hdsHibcFacId, hdsType, hdsName, hdsNameAlias, hdsPrefNameFlag, hdsEmail,
							hdsWebsite, hdsBusTelAreaCode, hdsBusTelNum, hdsTelExt, hdsCellAreaCode, hdsCellNum, hdsFaxAreaCode, hdsFaxNum,
							hdsServiceDelType, pcnCLinicType, pcnPciFlag, hdsHoursOfOp, hdsContactName, hdsIsForProfitFlag,
							sourceStatus, hdsParentIpcId, busIpcId, busCpnId, busName, busLegalName, busPayeeNum, busOwnerName,
							busOwnerType, busOwnerTypeOther, facBuildingName, facHdsDetailAddInfo, physAddr1, physAddr2,
							physAddr3, physAddr4, physCity, physProv, physPCode, physCountry, physAddrIsPrivate, mailAddr1,
							mailAddr2, mailAddr3, mailAddr4, mailCity, mailBc, mailPcode, mailCountry, mailAddrIsPriv))));
		}

	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getformfields/header")
	public String getAllHeader() {
		return dbUtilityService.getVariablesByTableNameSortedById("SOURCE_DATA");
	}

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			Control newControlTable) {
		String _oldFileName = null;
		String _newFileName = null;
		Boolean isFound = false;

		if (fileService.hasCsvFormat(file, "SOURCE_DATA")) {
			String authenticateUserId=AuthenticationUtils.getAuthenticatedUserId();
			logger.info("File is valid");
			Control control = new Control();
			control.setFileName(newControlTable.getFileName());
			control.setUserId(authenticateUserId);
			control.setFileExtractedDate(newControlTable.getFileExtractedDate());
			control.setBatchLabelName(newControlTable.getBatchLabelName());
			control.setLoadTypeFacility(newControlTable.getLoadTypeFacility());
			control.setLoadTypeHds(newControlTable.getLoadTypeHds());
			control.setLoadTypeOrg(newControlTable.getLoadTypeOrg());
			control.setLoadTypeOFRelationship(newControlTable.getLoadTypeOFRelationship());
			control.setLoadTypeOORelationship(newControlTable.getLoadTypeOORelationship());
			control.setLoadTypeIORelationship(newControlTable.getLoadTypeIORelationship());
			control.setLoadTypeWOXref(newControlTable.getLoadTypeWOXref());
			control.setLoadTypeWPIXref(newControlTable.getLoadTypeWPIXref());
			control.setProcessStartDate(new Date());
			control.setStatusCode("UPLOAD_IN_PROGRESS");
			control.setCreatedBy(authenticateUserId);
			control.setCreatedAt(new Date());

			// check mandatory fields: FileName, UserId, BatchLabelName
			if (control.getFileName().trim().isEmpty()) {
				logger.info("File Name is required.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 400, "File Name is required.", 0));
			}

			if (control.getBatchLabelName().trim().isEmpty()) {
				logger.info("{} Batch Label Name is required.", _newFileName);

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 400, "Batch Label Name is required.", 0));
			}

			if (control.getUserId().trim().isEmpty()) {
				logger.info("Userid is required.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 400, "Userid is required.", 0));
			}

			// File Name must be unique

			Iterable<Control> _controlTable = controlRepository.findByFileName(newControlTable.getFileName());
			_newFileName = newControlTable.getFileName();
			for (Control s : _controlTable) {
				_oldFileName = s.getFileName();
				if (_oldFileName.contentEquals(_newFileName)) {
					isFound = true;
				}
			}

			if (isFound) {
				logger.error("{} file has already been uploaded before.", _newFileName);

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("error", 400,
						_newFileName + " file has already been uploaded before. Please upload a different data file.",
						0));
			}

			controlRepository.save(control);

			// Async process
			fileService.processAndSaveData(file, control.getId(),authenticateUserId);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success", 200, "File uploading started.", control.getId()));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseMessage("error", 400, "Please upload a non-empty CSV file with the standard format!", 0));
	}
	
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/getColumnList/{controlTableId}")
	public ResponseEntity<ResponseMessage> getColumnLists(@PathVariable Long controlTableId, @RequestParam(required = false) String columnKey) {

		List<String> _sourceData = new ArrayList();

		switch(columnKey) {
			case "id":
				_sourceData = sourceDataRepository.findAllDistinctId(controlTableId);
				break;
			case "control_id":
				_sourceData = sourceDataRepository.findAllDistinctControlId(controlTableId);
				break;
			case "doNotLoadFlag":
				_sourceData = sourceDataRepository.findAllDistinctDoNotLoad(controlTableId);
				break;
			case "stakeholder":
				_sourceData = sourceDataRepository.findAllDistinctStakeholder(controlTableId);
				break;
			case "hdsIpcId":
				_sourceData = sourceDataRepository.findAllDistinctHdsIpcId(controlTableId);
				break;
			case "hdsCpnId":
				_sourceData = sourceDataRepository.findAllDistinctHdsCpnId(controlTableId);
				break;
			case "hdsProviderIdentifier1":
				_sourceData = sourceDataRepository.findAllDistinctHdsProviderIdentifier1(controlTableId);
				break;
			case "hdsProviderIdentifier2":
				_sourceData = sourceDataRepository.findAllDistinctHdsProviderIdentifier2(controlTableId);
				break;
			case "hdsProviderIdentifier3":
				_sourceData = sourceDataRepository.findAllDistinctHdsProviderIdentifier3(controlTableId);
				break;
			case "hdsProviderIdentifierType1":
				_sourceData = sourceDataRepository.findAllDistinctHdsProviderIdentifierType1(controlTableId);
				break;
			case "hdsProviderIdentifierType2":
				_sourceData = sourceDataRepository.findAllDistinctHdsProviderIdentifierType2(controlTableId);
				break;
			case "hdsProviderIdentifierType3":
				_sourceData = sourceDataRepository.findAllDistinctHdsProviderIdentifierType3(controlTableId);
				break;
			case "hdsHibcFacilityId":
				_sourceData = sourceDataRepository.findAllDistinctHdsHibcFacilityId(controlTableId);
				break;
			case "hdsType":
				_sourceData = sourceDataRepository.findAllDistinctHdsType(controlTableId);
				break;
			case "hdsName":
				_sourceData = sourceDataRepository.findAllDistinctHdsName(controlTableId);
				break;
			case "hdsNameAlias":
				_sourceData = sourceDataRepository.findAllDistinctHdsNameAlias(controlTableId);
				break;
			case "hdsPreferredNameFlag":
				_sourceData = sourceDataRepository.findAllDistinctHdsPreferredNameFlag(controlTableId);
				break;
			case "hdsEmail":
				_sourceData = sourceDataRepository.findAllDistinctHdsEmail(controlTableId);
				break;
			case "hdsWebsite":
				_sourceData = sourceDataRepository.findAllDistinctHdsWebsite(controlTableId);
				break;
			case "hdsBusTelAreaCode":
				_sourceData = sourceDataRepository.findAllDistinctHdsBusTelAreaCode(controlTableId);
				break;
			case "hdsBusTelNumber":
				_sourceData = sourceDataRepository.findAllDistinctHdsBusTelNumber(controlTableId);
				break;
			case "hdsTelExtension":
				_sourceData = sourceDataRepository.findAllDistinctHdsTelExtension(controlTableId);
				break;
			case "hdsCellAreaCode":
				_sourceData = sourceDataRepository.findAllDistinctHdsCellAreaCode(controlTableId);
				break;
			case "hdsCellNumber":
				_sourceData = sourceDataRepository.findAllDistinctHdsCellNumber(controlTableId);
				break;
			case "hdsFaxAreaCode":
				_sourceData = sourceDataRepository.findAllDistinctHdsFaxAreaCode(controlTableId);
				break;
			case "hdsFaxNumber":
				_sourceData = sourceDataRepository.findAllDistinctHdsFaxNumber(controlTableId);
				break;
			case "hdsServiceDeliveryType":
				_sourceData = sourceDataRepository.findAllDistinctHdsServiceDeliveryType(controlTableId);
				break;
			case "pcnClinicType":
				_sourceData = sourceDataRepository.findAllDistinctPcnClinicType(controlTableId);
				break;
			case "pcnPciFlag":
				_sourceData = sourceDataRepository.findAllDistinctPcnPciFlag(controlTableId);
				break;
			case "hdsHoursOfOperation":
				_sourceData = sourceDataRepository.findAllDistinctHdsHoursOfOperation(controlTableId);
				break;
			case "hdsContactName":
				_sourceData = sourceDataRepository.findAllDistinctHdsContactName(controlTableId);
				break;
			case "hdsIsForProfitFlag":
				_sourceData = sourceDataRepository.findAllDistinctHdsIsForProfitFlag(controlTableId);
				break;
			case "sourceStatus":
				_sourceData = sourceDataRepository.findAllDistinctSourceStatus(controlTableId);
				break;
			case "hdaParentIpcId":
				_sourceData = sourceDataRepository.findAllDistinctHdsParentIpcId(controlTableId);
				break;
			case "busIpcId":
				_sourceData = sourceDataRepository.findAllDistinctBusIpcId(controlTableId);
				break;
			case "busCpnId":
				_sourceData = sourceDataRepository.findAllDistinctBusCpnId(controlTableId);
				break;
			case "busName":
				_sourceData = sourceDataRepository.findAllDistinctBusName(controlTableId);
				break;
			case "busLegalName":
				_sourceData = sourceDataRepository.findAllDistinctBusLegalName(controlTableId);
				break;
			case "busPayeeNumber":
				_sourceData = sourceDataRepository.findAllDistinctBusPayeeNumber(controlTableId);
				break;
			case "busOwnerName":
				_sourceData = sourceDataRepository.findAllDistinctBusOwnerName(controlTableId);
				break;
			case "busOwnerType":
				_sourceData = sourceDataRepository.findAllDistinctBusOwnerType(controlTableId);
				break;
			case "busOwnerTypeOther":
				_sourceData = sourceDataRepository.findAllDistinctBusOwnerTypeOther(controlTableId);
				break;
			case "facBuildingName":
				_sourceData = sourceDataRepository.findAllDistinctFacBuildingName(controlTableId);
				break;
			case "facilityHdsDetailsAdditionalInfo":
				_sourceData = sourceDataRepository.findAllDistinctFacilityHdsDetailsAdditionalInfo(controlTableId);
				break;
			case "physicalAddr1":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalAddr1(controlTableId);
				break;
			case "physicalAddr2":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalAddr2(controlTableId);
				break;
			case "physicalAddr3":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalAddr3(controlTableId);
				break;
			case "physicalAddr":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalAddr4(controlTableId);
				break;
			case "physicalCity":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalCity(controlTableId);
				break;
			case "physicalProvince":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalProvince(controlTableId);
				break;
			case "physicalPcode":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalPcode(controlTableId);
				break;
			case "physicalCountry":
				_sourceData = sourceDataRepository.findAllDistinctPhysicalCountry(controlTableId);
				break;
			case "physAddrIsPrivate":
				_sourceData = sourceDataRepository.findAllDistinctPhysAddrIsPrivate(controlTableId);
				break;
			case "mailAddr1":
				_sourceData = sourceDataRepository.findAllDistinctMailAddr1(controlTableId);
				break;
			case "mailAddr2":
				_sourceData = sourceDataRepository.findAllDistinctMailAddr2(controlTableId);
				break;
			case "mailAddr3":
				_sourceData = sourceDataRepository.findAllDistinctMailAddr3(controlTableId);
				break;
			case "mailAddr4":
				_sourceData = sourceDataRepository.findAllDistinctMailAddr4(controlTableId);
				break;
			case "nailCity":
				_sourceData = sourceDataRepository.findAllDistinctMailCity(controlTableId);
				break;
			case "mailBc":
				_sourceData = sourceDataRepository.findAllDistinctMailBc(controlTableId);
				break;
			case "mailPcode":
				_sourceData = sourceDataRepository.findAllDistinctMailPcode(controlTableId);
				break;
			case "mailCountry":
				_sourceData = sourceDataRepository.findAllDistinctMailCountry(controlTableId);
				break;
			case "mailAddrIsPrivate":
				_sourceData = sourceDataRepository.findAllDistinctMailAddrIsPrivate(controlTableId);
				break;
			default:
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error", 404, "Column not found.", 
						_sourceData));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "Retrieved the items in the column.", 
				_sourceData));
	}

}
