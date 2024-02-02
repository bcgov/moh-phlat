package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

	List findAllByIsDeletedTrue();

	List findAllByIsDeletedFalse();
	
}
