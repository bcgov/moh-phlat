package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.moh.phlat.backend.model.TableColumnInfo;

public interface TableColumnInfoRepository extends CrudRepository<TableColumnInfo, Long>{
	// public List<TableColumnInfo> findAllByTableNameSortedById(String tableName);

	public List<TableColumnInfo> findAllByOrderByIdAsc();
	public List<TableColumnInfo> findByTableNameOrderByIdAsc(String tableName);

}
