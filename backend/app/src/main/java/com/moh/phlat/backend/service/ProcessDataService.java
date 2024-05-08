package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;

import java.util.List;

public interface ProcessDataService {

    List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus);
}
