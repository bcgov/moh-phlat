package com.moh.phlat.backend.service;


import com.moh.phlat.backend.model.ColumnsDisplayPreference;


public interface ColumnsDisplayPreferenceService {
    ColumnsDisplayPreference saveOrUpdatePreferences(ColumnsDisplayPreference columnsDisplayPreference);

    ColumnsDisplayPreference getPreferences(String userId, String pageName);

    boolean isExist(String userId, String pageName);

    boolean isExist(Long id);

    ColumnsDisplayPreference getPreferences(long id);
}
