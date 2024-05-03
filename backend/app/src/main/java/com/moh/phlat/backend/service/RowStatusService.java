package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.RowStatus;

import java.util.List;

public interface RowStatusService {

    List<RowStatus> getRowStatuses(Boolean isDeleted);
}
