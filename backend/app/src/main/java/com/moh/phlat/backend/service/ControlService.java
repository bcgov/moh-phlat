package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.model.Control;

public interface ControlService {
	List<Control> findById(Long id);
	
	List<Control> findByFileName(String fileName);
	
	List<Control> findAll(List<String> ids, List<String> fileName, List<String> userIds, 
			List<String> fileExtractedDates, List<String> batchLabelNames, List<String> loadTypeFacilitys, List<String> loadTypeHds, 
			List<String> loadTypeBusOrgs, List<String> loadTypeOFRelationships, List<String> loadTypeOORelationships, 
			List<String> loadTypeIORelationships, List<String> loadTypeWlOrgXrefs, List<String> loadTypeWlPracIdentXrefs, List<String> processStartDates, 
			List<String> processEndDates, List<String> statusCodes, List<String> createdBy, List<String> createdAt, List<String> updatedBy, 
			List<String> updatedAt);
}
