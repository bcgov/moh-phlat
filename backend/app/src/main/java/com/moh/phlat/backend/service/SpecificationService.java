package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

import jakarta.persistence.TypedQuery;

public interface SpecificationService {
	
	public <T> Specification<T> hasDataWithMessages(Long controlId);
	
	public <T> Specification<T> buildSpecificationWhereEqual(String columnKey, String value);
	public <T> Specification<T> buildSpecificationAnd(Specification<T> spec, String columnKey, String value);
	public <T> Specification<T> buildSpecificationAnd(Specification<T> spec, String columnKey, List<String> values);
	
}