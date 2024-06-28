package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.repository.ControlRepository;

@Service
public class ControlServiceImpl implements ControlService {
	
	@Autowired
	private ControlRepository controlRepository;

	@Override
	public List<Control> findById(Long id) {
		
		return controlRepository.findAllById(id);
	}

	@Override
	public List<Control> findByFileName(String fileName) {
		return controlRepository.findByFileName(fileName);
	}

	@Override
	public List<Control> findAll(){
		return controlRepository.findAll();
	}

}
