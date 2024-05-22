package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import com.moh.phlat.backend.service.DbUtilityServiceImpl.ReportSummary;
import java.util.List;

public interface DbUtilityService {
	public String getVariablesByTableNameSortedById(String tableName);
	public String getHeadersByTableNameSortedById(String tableName);
	public void setProcessDataStatus(Long processDataId, String rowstatusCode, String authenticatedUserId);
	public void setControlStatus(Long controlId, String statusCode, String authenticatedUserId);
	public void validateProcessDataByControlTableId(Long controlTableId, String authenticatedUserId);
	public void validateProcessData(Control control, ProcessData processData, String authenticatedUserId);
	public void loadProcessDataToPlr(Long controlTableId, String authenticatedUserId);
	public List<ReportSummary> getReportSummary(Long controlTableId);
}
