package com.moh.phlat.backend.repository;

import com.moh.phlat.backend.model.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RowStatusRepository extends JpaRepository<RowStatus, Long> {

    List<RowStatus> findAllByIsDeletedTrue();

    List<RowStatus> findAllByIsDeletedFalse();

}
