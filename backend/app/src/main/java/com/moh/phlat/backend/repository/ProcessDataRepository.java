package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.ProcessData;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ProcessDataRepository extends JpaRepository<ProcessData, Long> {
    List<ProcessData> getAllProcessDataByControlTableId(Long controlTableId);

    List<ProcessData> findByControlTableIdAndRowstatusCode(Long controlTableId, String reqRowStatusCode);

    @Query("SELECT pd from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId")
    @EntityGraph(value = "ProcessData.withMessageDetails", type = EntityGraph.EntityGraphType.FETCH)
    List<ProcessData> getProcessDataWithMessages(Long controlTableId, Pageable pageable);


    @Query("SELECT pd from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId AND pd.rowstatusCode = :rowStatus")
    @EntityGraph(value = "ProcessData.withMessageDetails", type = EntityGraph.EntityGraphType.FETCH)
    List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus, Pageable pageable);

   @Query("SELECT pd.rowstatusCode, count(pd) from ProcessData pd  where pd.controlTableId = :controlTableId group by pd.rowstatusCode")
    List<Object[]> getRowstatusCountByControlTableId(Long controlTableId);
      
    @Query("SELECT md.messageCode, md.messageDesc,count(md) from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId group by md.messageCode, md.messageDesc")
    List<Object[]> getProcessDataWithMessageCodeCount(Long controlTableId);


	long countAllByControlTableIdAndRowstatusCode(Long controlTableId, String string);

	long countByControlTableId(Long controlTableId);   
}