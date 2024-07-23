package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.moh.phlat.backend.model.ProcessData;
import jakarta.persistence.criteria.JoinType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessDataFilterSpecificationImpl implements ProcessDataFilterSpecification {
	
	public Specification<ProcessData> getDataWithMessages(Long controlId){
		return (root, query, builder) -> {
			root.join("messages",JoinType.LEFT);
			return builder.equal(root.get("controlTableId"), controlId);
		};
	}
	
	@Override
	public Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, String value){
		if (StringUtils.hasText(value)) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(value));
		}
		return spec;
	}

	@Override
	public Specification<ProcessData> buildSpecificationAnd(Specification<ProcessData> spec, String columnKey, List<String> values){
		if (values != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(values));
		}
		return spec;
	}
}