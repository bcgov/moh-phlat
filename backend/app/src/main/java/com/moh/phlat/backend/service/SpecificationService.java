package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

public interface SpecificationService {
	
	public Specification<ProcessData> getDataWithMessagesForProcessData(Long controlId);
	
	public Specification<ProcessData> buildSpecificationWhereEqualForProcessData(String columnKey, String value);
	public Specification<ProcessData> buildSpecificationAndForProcessData(Specification<ProcessData> spec, String columnKey, String value);
	public Specification<ProcessData> buildSpecificationAndForProcessData(Specification<ProcessData> spec, String columnKey, List<String> values);
	
	public Specification<SourceData> buildSpecificationWhereEqualForSourceData(String columnKey, String value);
	public Specification<SourceData> buildSpecificationAndForSourceData(Specification<SourceData> spec, String columnKey, String value);
	public Specification<SourceData> buildSpecificationAndForSourceData(Specification<SourceData> spec, String columnKey, List<String> values);
	
}