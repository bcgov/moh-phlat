package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.SourceData;
import com.moh.phlat.backend.repository.ControlRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
	public List<Control> findAll(Pageable pageable){
		Specification<Control> spec = Specification.where(null);
		List<Control> list = controlRepository.findAll(spec, pageable);
		return list;
	}

}
