package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;

public interface ColumnsDisplayPreferenceService {
    ColumnsDisplayPreference saveOrUpdatePreferences(ColumnsDisplayPreference columnsDisplayPreference);

    ColumnsDisplayPreference getPreferences(String userId, String viewName);


}
