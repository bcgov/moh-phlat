package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.SourceData;

@Repository
public interface SourceDataRepository extends CrudRepository<SourceData, Long> {
	public List<SourceData> getAllSourceDataByControlTableId(Long controlTableId);
	
	//List<SourceData> findById(Long id);

	//List<SourceData> findAll();
	//findAll(Specification<SourceData> spec, Pageable pageable);
	List<SourceData> findAll(Pageable pageable);
}
