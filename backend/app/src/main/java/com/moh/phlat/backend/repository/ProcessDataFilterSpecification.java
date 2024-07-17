package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.moh.phlat.backend.model.ProcessData;

public interface ProcessDataFilterSpecification {
	
	Specification<ProcessData> getDataWithMessages(Long controlId);
	
	Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, String value);
	Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, List<String> values);
	
}