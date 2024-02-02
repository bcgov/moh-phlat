package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
