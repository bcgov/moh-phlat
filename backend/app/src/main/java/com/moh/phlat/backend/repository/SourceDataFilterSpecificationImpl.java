package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.model.SourceData;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SourceDataFilterSpecificationImpl implements SourceDataFilterSpecification {
	
	@Override
	public Specification<SourceData> buildSpecificationWhereEqual(String columnKey, String value) throws Exception{
		if (StringUtils.hasText(value)) {
			return Specification.where((root, query, builder) -> builder.equal(root.get(columnKey), value));
		}
		
		throw new Exception(columnKey +" is missing a required value.");
	}
	
	@Override
	public Specification<SourceData> buildSpecificationAnd(Specification<SourceData> spec, String columnKey, List<String> values){
		if (values != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(values));
		}
		return spec;
	}
}