package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.service.dto.ColumnInfo;

public interface TableColumnInfoService {
    String PROCESS_DATA = "PROCESS_DATA";
    String SOURCE_DATA = "SOURCE_DATA";

    List<ColumnInfo> getColumnInfoList(String tableName);
}
