package com.moh.phlat.backend.repository;


import java.util.List;

public interface ProcessDataCustomRepository {

    /**
     * Find all unique column Values based on control ID and column key
     *
     * @param controlTableId - controlTableId
     * @param columnKey      - columnKey
     * @return - returns all unique column Values based on control ID and column key
     */
    List<String> getUniqueColumnValues(Long controlTableId, String columnKey);
}