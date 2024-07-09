package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.service.dto.ColumnDisplayName;

public interface TableColumnInfoService {
    String PROCESS_DATA = "PROCESS_DATA";
    String SOURCE_DATA = "SOURCE_DATA";

	public String getHeadersByTableNameSortedById(String tableName);
    List<ColumnDisplayName> getColumnDisplayNames(String tableName);
}
