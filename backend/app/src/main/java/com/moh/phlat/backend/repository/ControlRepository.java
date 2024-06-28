package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moh.phlat.backend.model.Control;


public interface ControlRepository extends JpaRepository<Control, Long> {
	public List<Control> findByFileName(String fileName);
}