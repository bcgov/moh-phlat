package com.moh.phlat.backend.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.repository.ControlRepository;

@Service
public class ControlServiceImpl implements ControlService {
	
	@Autowired
	private ControlRepository controlRepository;
	
	@Autowired
	private SpecificationService specificationService;

	@Override
	public List<Control> findById(Long id) {
		
		return findAll(Arrays.asList(id.toString()), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Override
	public List<Control> findByFileName(String fileName) {
		return findAll(null, Arrays.asList(fileName), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Override
	public List<Control> findAll(List<String> ids, List<String> fileName, List<String> userIds,
			List<String> fileExtractedDates, List<String> batchLabelNames, List<String> loadTypeFacilitys,
			List<String> loadTypeHds, List<String> loadTypeBusOrgs, List<String> loadTypeOFRelationships,
			List<String> loadTypeOORelationships, List<String> loadTypeIORelationships, List<String> loadTypeWlOrgXrefs,
			List<String> loadTypeWlPracIdentXrefs, List<String> processStartDates, List<String> processEndDates,
			List<String> statusCodes, List<String> createdBy, List<String> createdAt, List<String> updatedBy,
			List<String> updatedAt) {
		
		return controlRepository.findAll(specificationService.buildSpecificationInControl(ids, fileName, 
				userIds, fileExtractedDates, batchLabelNames, loadTypeFacilitys, loadTypeHds, loadTypeBusOrgs, loadTypeOFRelationships, 
				loadTypeOORelationships, loadTypeIORelationships, loadTypeWlOrgXrefs, loadTypeWlPracIdentXrefs, processStartDates, 
				processEndDates, statusCodes, createdBy, createdAt, updatedBy, updatedAt));
	}

}
