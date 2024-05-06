package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.RowStatus;
import com.moh.phlat.backend.repository.RowStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowStatusServiceImpl implements RowStatusService {

    private static final Logger logger = LoggerFactory.getLogger(RowStatusServiceImpl.class);

    @Autowired
    private RowStatusRepository rowStatusRepository;

    @Override
    public List<RowStatus> getRowStatuses(Boolean isDeleted) {
        logger.info("isDeleted flag is {}", isDeleted);
        if (isDeleted == null) {
            // return all statuses
            logger.info("returning all row statuses...");
            return rowStatusRepository.findAll();
        } else if (isDeleted) {
            logger.info("returning deleted row statuses...");
            // return only deleted statuses
            return rowStatusRepository.findAllByIsDeletedTrue();
        } else {
            // return only active non-deleted statuses
            logger.info("returning only active/non deleted statuses...");
            return rowStatusRepository.findAllByIsDeletedFalse();

        }


    }
}
