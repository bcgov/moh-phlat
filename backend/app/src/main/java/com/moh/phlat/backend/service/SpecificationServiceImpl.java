package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class SpecificationServiceImpl<T> implements SpecificationService {
	
	public <T> Specification<T> hasDataWithMessages(Long controlId){
		return (root, query, builder) -> {
			root.fetch("messages",JoinType.LEFT);
			return builder.equal(root.get("controlTableId"), controlId);
		};
	}
	
	@Override
	public <T> Specification<T> buildSpecificationWhereEqual(String columnKey, String value){
		return Specification.where((root, query, builder) -> builder.equal(root.get(columnKey), value));
	}
	
	@Override
	public <T> Specification<T> buildSpecificationAnd(Specification<T> spec, String columnKey, String value){
		if (value != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(value));
		}
		return spec;
	}

	@Override
	public <T> Specification<T> buildSpecificationAnd(Specification<T> spec, String columnKey, List<String> values){
		if (values != null) {
			spec = spec.and((root,query, builder) -> root.get(columnKey).in(values));
		}
		return spec;
	}
}