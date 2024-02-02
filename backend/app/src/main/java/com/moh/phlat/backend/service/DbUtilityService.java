package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.model.TableColumnInfo;

public interface DbUtilityService {
//	public String getVariablesByTableName(String tableName);
//	public String getHeadersByTableName(String tableName);
	public String getVariablesByTableNameSortedById(String string);
	public String getHeadersByTableNameSortedById(String string);
}
