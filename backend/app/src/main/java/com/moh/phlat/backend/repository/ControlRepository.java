package com.moh.phlat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.moh.phlat.backend.model.Control;

@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {

	public List<Control> findAllById(Long id);
	
	public List<Control> findByFileName(String fileName);
	
	public @NonNull List<Control> findAll();
}
