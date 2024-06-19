package com.moh.phlat.backend.service;

import com.moh.phlat.backend.service.dto.UiColumnName;
import java.util.List;

public interface TableColumnInfoService {
    List<UiColumnName> getUiColumnNames(String tableName);
}
