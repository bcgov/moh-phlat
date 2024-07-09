package com.moh.phlat.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.repository.TableColumnInfoRepository;
import com.moh.phlat.backend.service.dto.ColumnDisplayName;

@Service
public class TableColumnInfoServiceImpl implements TableColumnInfoService {

    private static final Logger logger = LoggerFactory.getLogger(RowStatusServiceImpl.class);

    @Autowired
    private TableColumnInfoRepository tableColumnInfoRepository;

    private static ColumnDisplayName createColumnInfoData(String key, String title) {
        return ColumnDisplayName.builder()
						  .key(key)
                          .title(title)
                          .build();
    }
    
    public List<ColumnDisplayName> getColumnDisplayNames(String tableName) {
        List<ColumnDisplayName> items = new ArrayList<ColumnDisplayName>();
		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);

		for (TableColumnInfo row : tableColumnInfo) {
            ColumnDisplayName newRec = createColumnInfoData(row.getVariableName(), row.getTitle());
            items.add(newRec);
		}
        return items;
    }

    @Override
	public String getHeadersByTableNameSortedById(String tableName) {
		String headerName = "";
		String retResult = "";
		Integer i = 0;


		List<TableColumnInfo> tableColumnInfo = tableColumnInfoRepository.findByTableNameOrderByIdAsc(tableName);

		List<String> listHeaderName = new ArrayList<>();

		for (TableColumnInfo row : tableColumnInfo) {
			headerName = row.getHeaderName();
			if (!headerName.isEmpty()) {
				i = i + 1;
				if (i == 1) {
					retResult = retResult + headerName;
				} else {
					retResult = retResult + "," + headerName;
				}
				listHeaderName.add(row.getHeaderName());
			}
		}

		return retResult;

	}



}
