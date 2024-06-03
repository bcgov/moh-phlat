package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProcessDataService {

    List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus, Pageable pageable);
}
