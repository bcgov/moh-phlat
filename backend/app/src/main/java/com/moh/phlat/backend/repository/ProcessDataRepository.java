package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.ProcessData;

@Repository
public interface ProcessDataRepository extends JpaRepository<ProcessData, Long> {
    List<ProcessData> getAllProcessDataByControlTableId(Long controlTableId);

    List<ProcessData> findByControlTableIdAndRowstatusCode(Long controlTableId, String reqRowStatusCode);

    @Query("SELECT pd from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId")
    @EntityGraph(value = "ProcessData.withMessageDetails", type = EntityGraph.EntityGraphType.FETCH)
    List<ProcessData> getProcessDataWithMessages(Long controlTableId);


    @Query("SELECT pd from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId AND pd.rowstatusCode = :rowStatus")
    @EntityGraph(value = "ProcessData.withMessageDetails", type = EntityGraph.EntityGraphType.FETCH)
    List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus);

    @Query("SELECT md.messageType, md.messageCode, md.messageDesc,count(md) from ProcessData pd LEFT JOIN pd.messages md WHERE pd.controlTableId = :controlTableId group by md.messageType, md.messageCode, md.messageDesc")
    List<Object[]> getProcessDataWithMessageCodeCount(Long controlTableId);

	long countAllByControlTableIdAndRowstatusCode(Long controlTableId, String string);

	long countByControlTableId(Long controlTableId);

    @Query("SELECT md.id, md.messageCode from ProcessData pd LEFT JOIN pd.messages md WHERE pd.id = :processDataId")
    List<Object[]> getAllMessagesForProcessDataId(Long processDataId);

}