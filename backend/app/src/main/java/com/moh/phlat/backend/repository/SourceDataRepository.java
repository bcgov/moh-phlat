package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.SourceData;

@Repository
public interface SourceDataRepository extends CrudRepository<SourceData, Long> {
	
	List<SourceData> getAllSourceDataByControlTableId(Long controlTableId);
	Page<SourceData> findAll(Specification<SourceData> spec, Pageable pageable);
}
