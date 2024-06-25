package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.service.dto.UiColumnName;

public interface TableColumnInfoService {
    String PROCESS_DATA = "PROCESS_DATA";
    String SOURCE_DATA = "SOURCE_DATA";

    List<UiColumnName> getUiColumnNames(String tableName);
}
