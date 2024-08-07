package com.moh.phlat.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.SourceDataFilterParams;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.SourceDataRepository;
import com.moh.phlat.backend.response.ResponseMessage;
import com.moh.phlat.backend.service.FileService;
import com.moh.phlat.backend.service.SourceDataService;
import com.moh.phlat.backend.service.TableColumnInfoService;
import com.moh.phlat.backend.service.dto.ColumnDisplayName;

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
	private SourceDataService sourceDataService;

    @Autowired
    private TableColumnInfoService tableColumnInfoService;	

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("view/all")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllSourceDatas() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", null, sourceDataRepository.findAll()));
	}

	// get specific row by id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseMessage> getSourceDataById(@PathVariable Long id) {

		Optional<SourceData> soureData = sourceDataRepository.findById(id);
		if (soureData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage("success", 404, "Source Data not found for id: " + id, null, "[]"));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessage("success", 200, "", null, sourceDataRepository.findById(id)));

	}

	// get source data by control id
	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@PostMapping("/controltableid/{controlTableId}")
	public @ResponseBody ResponseEntity<ResponseMessage> getAllSourceDataByControlTableId(
			@PathVariable Long controlTableId, @RequestParam(required = true) int page, @RequestParam(required = true) int itemsPerPage, 
			@RequestBody SourceDataFilterParams filterSource) {
		Optional<Control> controlTableData = controlRepository.findById(controlTableId);

		if (controlTableData.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("success", 404,
					"Source Data not found for control_id: " + controlTableId, null, "[]"));
		} else if (page < 1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("success", 400,
					"Page needs to be larger than 0.", null, "[]"));
		}
		
		List<Order> sortOrders = new ArrayList<>();
		for(Map.Entry<String,String> entry:filterSource.getSort().entrySet()) {
			if("asc".equals(entry.getValue()) || "desc".equals(entry.getValue())) {
				sortOrders.add(new Order(entry.getValue().equals("asc")?Sort.Direction.ASC:Sort.Direction.DESC, entry.getKey()));
			}
		}

		Page<SourceData> entirePage = sourceDataService.getSourceData(controlTableId, page, itemsPerPage, filterSource, sortOrders);

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", entirePage.getTotalElements(), entirePage.getContent()));

	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/column-display-names")
	public ResponseEntity<ResponseMessage> getColumnDisplayNames() {
	    List<ColumnDisplayName> list = null;
		list = tableColumnInfoService.getColumnDisplayNames(TableColumnInfoService.SOURCE_DATA);
	    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", null, list));
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
						.body(new ResponseMessage("error", 400, "File Name is required.", null, 0));
			}

			if (control.getBatchLabelName().trim().isEmpty()) {
				logger.info("{} Batch Label Name is required.", _newFileName);

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 400, "Batch Label Name is required.", null, 0));
			}

			if (control.getUserId().trim().isEmpty()) {
				logger.info("Userid is required.");

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("error", 400, "Userid is required.", null, 0));
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
						null, 0));
			}

			controlRepository.save(control);

			// Async process
			fileService.processAndSaveData(file, control.getId(),authenticateUserId);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("success", 200, "File uploading started.", null, control.getId()));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseMessage("error", 400, "Please upload a non-empty CSV file with the standard format!", null, 0));
	}

	@PreAuthorize("hasAnyRole(@roleService.getAllRoles())")
	@GetMapping("/{controlTableId}/column-distinct-values/{columnKey}")
	public ResponseEntity<ResponseMessage> getDistinctColumnValues(@PathVariable Long controlTableId, @PathVariable String columnKey) {

		if(SourceDataService.SOURCE_DATA_COLUMNS.contains(columnKey)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success", 200, "", null, sourceDataService.getUniqueColumnValues(controlTableId, columnKey)));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error", 404, "Column not found.", null, new ArrayList<String>()));
		
	}
}
