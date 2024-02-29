package com.moh.phlat.backend.security;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * As of now returning just constants as the roles are fixed, and it is made as
 * bean, so it can be used in @PreAuthorize annotations on controller methods
 */
@Component
public class RoleService {
    private final String ROLE_REG_ADMIN = "REG_ADMIN";
    private final String ROLE_REG_USER = "REG_USER";

    public String getRegAdminRole() {
        return ROLE_REG_ADMIN;
    }

    public String getRegUserRole() {
        return ROLE_REG_USER;
    }

    public List<String> getAllRoles() {
        return Arrays.asList(ROLE_REG_ADMIN, ROLE_REG_USER);

    }
}
