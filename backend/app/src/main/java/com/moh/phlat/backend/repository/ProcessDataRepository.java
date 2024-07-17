package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.ProcessData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessDataRepository extends JpaRepository<ProcessData, Long> {
    List<ProcessData> getAllProcessDataByControlTableId(Long controlTableId);

    List<ProcessData> findByControlTableIdAndRowstatusCode(Long controlTableId, String reqRowStatusCode);

    @Query("SELECT md.messageType, md.messageCode, md.messageDesc,count(md) from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId group by md.messageType, md.messageCode, md.messageDesc")
    List<Object[]> getProcessDataWithMessageCodeCount(Long controlTableId);

	long countAllByControlTableIdAndRowstatusCode(Long controlTableId, String string);

	long countByControlTableId(Long controlTableId);   
	
	List<ProcessData> findAll(Specification<ProcessData> spec);

}