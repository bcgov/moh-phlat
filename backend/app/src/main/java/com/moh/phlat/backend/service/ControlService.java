package com.moh.phlat.backend.service;

public interface ControlService {
    public void setControlStatus(Long controlId, String statusCode, String authenticatedUserId);
}
