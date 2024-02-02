package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
