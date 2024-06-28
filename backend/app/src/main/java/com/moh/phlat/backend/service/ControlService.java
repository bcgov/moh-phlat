package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.model.Control;

public interface ControlService {			
	List<Control> findById(Long id);
	
	List<Control> findByFileName(String fileName);
	
	List<Control> findAll();
}
