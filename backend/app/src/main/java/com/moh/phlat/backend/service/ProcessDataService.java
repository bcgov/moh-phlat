package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.service.dto.ReportSummary;

public interface ProcessDataService {
    String PHLAT_ERROR_TYPE = "ERROR";
    String PHLAT_ERROR_CODE = "100";
    
    List<ProcessData> getProcessDataWithMessages(Long controlTableId, String rowStatus);
    List<ReportSummary> getReportSummary(Long controlTableId);
    public void setProcessDataStatus(Long processDataId, String rowstatusCode, String authenticatedUserId);
	public void validateProcessData(Control control, ProcessData processData, String authenticatedUserId);
   	public void validateProcessDataByControlTableId(Long controlTableId, String authenticatedUserId);
   	public void loadProcessDataToPlr(Long controlTableId, String authenticatedUserId);
}
