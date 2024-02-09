package com.moh.phlat.backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

public interface DbUtilityService {
	public String getVariablesByTableNameSortedById(String tableName);
	public String getHeadersByTableNameSortedById(String tableName);
	public void setProcessDataStatus(Long processDataId, String rowstatusCode);
	public void setControlStatus(Long controlId, String statusCode);
	public void validateProcessDataByControlTableId(Long controlTableId);
	public void validateProcessData(Control control, ProcessData processData);
	public void loadProcessDataToPlr(Long controlTableId);
	
}
