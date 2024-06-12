package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.service.dto.ReportSummary;

import java.util.List;

public interface ProcessDataService {

    List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus);
    List<ReportSummary> getReportSummary(Long controlTableId);
}
