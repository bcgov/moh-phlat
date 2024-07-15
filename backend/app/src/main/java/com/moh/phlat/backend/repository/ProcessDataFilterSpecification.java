package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

public interface ProcessDataFilterSpecification {
	
	public Specification<ProcessData> getDataWithMessages(Long controlId);
	
	public Specification<ProcessData> buildSpecificationWhereEqual(String columnKey, String value);
	public Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, String value);
	public Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, List<String> values);
	
}