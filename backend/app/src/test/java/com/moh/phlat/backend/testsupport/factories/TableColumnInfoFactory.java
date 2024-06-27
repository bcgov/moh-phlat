package com.moh.phlat.backend.testsupport.factories;

import java.util.ArrayList;
import java.util.List;

import com.moh.phlat.backend.model.TableColumnInfo;
import com.moh.phlat.backend.service.dto.ColumnDisplayName;

public class TableColumnInfoFactory extends BaseFactory {
    public static List<TableColumnInfo> createTableColumnInfo() {
        List<TableColumnInfo> tableColumnInfo = new ArrayList<>();

        tableColumnInfo.add(createTableColumnInfoAttributes(1L,"SOURCE_DATA", "DO_NOT_LOAD_FLAG", "DO_NOT_LOAD_FALG", "doNotLoadFlag", "Do Not Load Flag"));
        return tableColumnInfo;

    }

    private static TableColumnInfo createTableColumnInfoAttributes(Long id, String tableName, String columnName, String headerName, String variableName, String title) {

        return TableColumnInfo.builder()
                          .id(id)
                          .tableName(tableName)
                          .columnName(columnName)
                          .headerName(headerName)
                          .variableName(variableName)
                          .title(title)
                          .build();
    }

    private static ColumnDisplayName createColumnDisplayNameData(String key, String title) {
        return ColumnDisplayName.builder()
						  .key(key)
                          .title(title)
                          .build();
    }

    public static List<ColumnDisplayName> getColumnDisplayNameList() {
        List<ColumnDisplayName> items = new ArrayList<ColumnDisplayName>();
		List<TableColumnInfo> tableColumnInfo = createTableColumnInfo();

		for (TableColumnInfo row : tableColumnInfo) {
            ColumnDisplayName newRec = createColumnDisplayNameData(row.getVariableName(), row.getTitle());
            items.add(newRec);
		}
        return items;
    }

}
