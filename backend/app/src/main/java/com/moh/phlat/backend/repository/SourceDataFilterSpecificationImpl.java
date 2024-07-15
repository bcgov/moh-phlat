package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.model.SourceData;

import jakarta.persistence.criteria.JoinType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SourceDataFilterSpecificationImpl implements SourceDataFilterSpecification {
	
	@Override
	public Specification<SourceData> buildSpecificationWhereEqual(String columnKey, String value){
		return Specification.where((root, query, builder) -> builder.equal(root.get(columnKey), value));
	}
	
	@Override
	public Specification<SourceData> buildSpecificationAnd(Specification<SourceData> spec, String columnKey, String value){
		if (value != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(value));
		}
		return spec;
	}

	@Override
	public Specification<SourceData> buildSpecificationAnd(Specification<SourceData> spec, String columnKey, List<String> values){
		if (values != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(values));
		}
		return spec;
	}
}