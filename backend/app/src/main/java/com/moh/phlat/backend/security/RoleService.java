package com.moh.phlat.backend.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Getter
@Service
public class RoleService {

    //return blank if key is not found
    @Value("${phlat.roles.reg-admin:}")
    private String regAdminRole;

    //return blank if key is not found
    @Value("${phlat.roles.reg-user:}")
    private String regUserRole;


    public List<String> getAllRoles() {
        return Arrays.asList(regUserRole, regAdminRole);

    }
}
