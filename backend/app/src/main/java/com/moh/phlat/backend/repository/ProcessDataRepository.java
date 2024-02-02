package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.moh.phlat.backend.model.ProcessData;

@Repository
public interface ProcessDataRepository extends CrudRepository<ProcessData, Long> {
	public List<ProcessData> getAllProcessDataByControlTableId(Long controlTableId);
}
