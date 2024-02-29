package com.moh.phlat.backend.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class RoleService {

    //return blank if key is not found
    @Value("${phlat.roles.reg-admin:}")
    private String adminRole;

    //return blank if key is not found
    @Value("${phlat.roles.reg-user:}")
    private String regUser;


    public List<String> getAllRoles() {
        List<String> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(regUser);
        return roles;

    }
}
