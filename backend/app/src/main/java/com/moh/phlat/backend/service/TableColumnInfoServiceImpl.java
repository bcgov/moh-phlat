package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;
import com.moh.phlat.backend.service.dto.ColumnInfo;

@Service
public class TableColumnInfoServiceImpl implements TableColumnInfoService {

    private static final Logger logger = LoggerFactory.getLogger(RowStatusServiceImpl.class);

    @Autowired
    private TableColumnInfoRepository tableColumnInfoRepository;

    private static ColumnInfo createColumnInfoData(String key, String title) {
        return ColumnInfo.builder()
						  .key(key)
                          .title(title)
                          .build();
    }
    
    public List<ColumnInfo> getColumnInfoList(String tableName) {
        List<ColumnInfo> items = new ArrayList<ColumnInfo>();
		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);

		for (TableColumnInfo row : tableColumnInfo) {
            ColumnInfo newRec = createColumnInfoData(row.getVariableName(), row.getTitle());
            items.add(newRec);
		}
        return items;
    }
}
