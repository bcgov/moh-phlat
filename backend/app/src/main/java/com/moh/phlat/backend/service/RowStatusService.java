package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.RowStatus;

import java.util.List;

public interface RowStatusService {
    String UPLOAD_COMPLETED = "UPLOAD_COMPLETED";
    String UPLOAD_ERROR = "UPLOAD_ERROR";
    String DO_NOT_LOAD = "DO_NOT_LOAD";
    String INITIAL = "INITIAL";
    String VALID = "VALID";
    String INVALID = "INVALID";
    String APPROVED = "APPROVED";
    String COMPLETED ="COMPLETED";
    String PRE_VALIDATION_IN_PROGRESS ="PRE-VALIDATION_IN_PROGRESS";
    String PRE_VALIDATION_COMPLETED ="PRE-VALIDATION_COMPLETED";
    String PLR_LOAD_COMPLETED = "PLR_LOAD_COMPLETED";
    String PLR_LOAD_IN_PROGRESS = "PLR_LOAD_IN_PROGRESS";
    String WARNING = "WARNING";
    String POTENTIAL_DUPLICATE = "POTENTIAL_DUPLICATE";
    String LOAD_ERROR = "LOAD_ERROR";
    String ON_HOLD = "ON_HOLD";
    List<RowStatus> getRowStatuses(Boolean isDeleted);
}
