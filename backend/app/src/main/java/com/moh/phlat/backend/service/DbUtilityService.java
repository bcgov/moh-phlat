package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import java.util.List;

public interface DbUtilityService {
    String PHLAT_ERROR_TYPE = "ERROR";
	String PHLAT_WARNING_TYPE = "WARNING";
    String PHLAT_ERROR_CODE = "100";
	String PHLAT_WARNING_CODE = "101";
	String PHLAT_END_REASON_CODE_CHG = "CHG";
	String PHLAT_END_REASON_CODE_CORR = "CORR";
	String PHLAT_END_REASON_CODE_CEASE = "CEASE";
	
	public String getVariablesByTableNameSortedById(String tableName);
	public String getHeadersByTableNameSortedById(String tableName);
	public void setProcessDataStatus(Long processDataId, String rowstatusCode, String authenticatedUserId);
	public void setControlStatus(Long controlId, String statusCode, String authenticatedUserId);
	public void validateProcessDataByControlTableId(Long controlTableId, String authenticatedUserId);
	public void validateProcessData(Control control, ProcessData processData, String authenticatedUserId);
	public void loadProcessDataToPlr(Long controlTableId, String authenticatedUserId);
}
