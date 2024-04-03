package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ColumnsDisplayPreferenceRepo extends JpaRepository<ColumnsDisplayPreference, Long> {
    ColumnsDisplayPreference findByUserIdAndViewName(String userId, String tableName);

}
