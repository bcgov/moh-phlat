package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
