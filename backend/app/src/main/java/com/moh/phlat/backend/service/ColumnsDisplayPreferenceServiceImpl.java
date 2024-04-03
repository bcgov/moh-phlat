package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.ColumnsDisplayPreference;
import com.moh.phlat.backend.repository.ColumnsDisplayPreferenceRepo;
import jakarta.persistence.EntityNotFoundException;
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
    public ColumnsDisplayPreference getPreferences(String userId, String pageName) {
        return columnsDisplayPreferenceRepo.findByUserIdAndPageName(userId, pageName);
    }

    @Override
    public boolean isExist(String userId, String pageName) {
        return columnsDisplayPreferenceRepo.existsByUserIdAndPageName(userId, pageName);
    }

    @Override
    public boolean isExist(Long id) {
        return columnsDisplayPreferenceRepo.existsById(id);
    }

    @Override
    public ColumnsDisplayPreference getPreferences(long id) {
        try {
            ColumnsDisplayPreference preference=columnsDisplayPreferenceRepo.getReferenceById(id);
            //fetching the data to fail fast and throw exception if entity was not found.
            preference.getPageName();
            return preference;

        } catch (EntityNotFoundException e) {
            logger.error("Entity with id {} not found", id);
        }
        return null;
    }
}
