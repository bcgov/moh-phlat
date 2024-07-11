package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

import jakarta.persistence.criteria.JoinType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpecificationServiceImpl implements SpecificationService {
	
	public Specification<ProcessData> getDataWithMessagesForProcessData(Long controlId){
		return (root, query, builder) -> {
			root.fetch("messages",JoinType.LEFT);
			return builder.equal(root.get("controlTableId"), controlId);
		};
	}
	
	@Override
	public Specification<ProcessData> buildSpecificationWhereEqualForProcessData(String columnKey, String value){
		return Specification.where((root, query, builder) -> builder.equal(root.get(columnKey), value));
	}
	
	@Override
	public Specification<ProcessData> buildSpecificationAndForProcessData(Specification<ProcessData> spec, String columnKey, String value){
		if (value != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(value));
		}
		return spec;
	}

	@Override
	public Specification<ProcessData> buildSpecificationAndForProcessData(Specification<ProcessData> spec, String columnKey, List<String> values){
		if (values != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(values));
		}
		return spec;
	}
	
	@Override
	public Specification<SourceData> buildSpecificationWhereEqualForSourceData(String columnKey, String value){
		return Specification.where((root, query, builder) -> builder.equal(root.get(columnKey), value));
	}
	
	@Override
	public Specification<SourceData> buildSpecificationAndForSourceData(Specification<SourceData> spec, String columnKey, String value){
		if (value != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(value));
		}
		return spec;
	}

	@Override
	public Specification<SourceData> buildSpecificationAndForSourceData(Specification<SourceData> spec, String columnKey, List<String> values){
		if (values != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(values));
		}
		return spec;
	}
}