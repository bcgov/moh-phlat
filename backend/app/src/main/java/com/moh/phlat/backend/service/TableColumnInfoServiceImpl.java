package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;
import com.moh.phlat.backend.service.dto.UiColumnName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class TableColumnInfoServiceImpl implements TableColumnInfoService {

    private static final Logger logger = LoggerFactory.getLogger(RowStatusServiceImpl.class);

    @Autowired
    private TableColumnInfoRepository tableColumnInfoRepository;

    //@Override
    
    private static UiColumnName createUiColumnNameData(String key, String title) {
        return UiColumnName.builder()
						  .key(key)
                          .title(title)
                          .build();
    }
    
    public List<UiColumnName> getUiColumnNames(String tableName) {
        List<UiColumnName> items = new ArrayList<UiColumnName>();
		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);

		for (TableColumnInfo row : tableColumnInfo) {
            UiColumnName newRec = createUiColumnNameData(row.getVariableName(), row.getTitle());
            items.add(newRec);
		}
        return items;
    }
}
