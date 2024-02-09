package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.response.ResponseMessage;

@Repository
public interface ProcessDataRepository extends CrudRepository<ProcessData, Long> {
	public List<ProcessData> getAllProcessDataByControlTableId(Long controlTableId);
}