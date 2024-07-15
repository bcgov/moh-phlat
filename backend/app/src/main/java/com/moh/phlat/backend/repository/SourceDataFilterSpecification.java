package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

public interface SourceDataFilterSpecification {
	
	public Specification<SourceData> buildSpecificationWhereEqual(String columnKey, String value);
	public Specification<SourceData> buildSpecificationAnd(Specification<SourceData> spec, String columnKey, String value);
	public Specification<SourceData> buildSpecificationAnd(Specification<SourceData> spec, String columnKey, List<String> values);
	
}