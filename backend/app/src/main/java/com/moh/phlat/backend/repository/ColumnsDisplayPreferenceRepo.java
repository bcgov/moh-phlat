package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ColumnsDisplayPreferenceRepo extends JpaRepository<ColumnsDisplayPreference, Long> {
    ColumnsDisplayPreference findByUserIdAndPageName(String userId, String tableName);


    boolean existsByUserIdAndPageName(String userId, String pageName);
}
