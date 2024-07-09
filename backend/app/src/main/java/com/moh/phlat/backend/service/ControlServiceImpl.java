package com.moh.phlat.backend.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.repository.ControlRepository;

@Service
public class ControlServiceImpl implements ControlService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	private ControlRepository controlRepository;


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

}
