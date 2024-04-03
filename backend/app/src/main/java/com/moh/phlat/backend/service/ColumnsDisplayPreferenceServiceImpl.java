package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;
import com.moh.phlat.backend.repository.ColumnsDisplayPreferenceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColumnsDisplayPreferenceServiceImpl implements ColumnsDisplayPreferenceService {

    private static final Logger logger = LoggerFactory.getLogger(ColumnsDisplayPreferenceServiceImpl.class);
    @Autowired
    private ColumnsDisplayPreferenceRepo columnsDisplayPreferenceRepo;

    @Override
    public ColumnsDisplayPreference saveOrUpdatePreferences(ColumnsDisplayPreference columnsDisplayPreference) {
        return columnsDisplayPreferenceRepo.save(columnsDisplayPreference);
    }

    @Override
    public ColumnsDisplayPreference getPreferences(String userId, String viewName) {
        return columnsDisplayPreferenceRepo.findByUserIdAndViewName(userId, viewName);
    }


}
