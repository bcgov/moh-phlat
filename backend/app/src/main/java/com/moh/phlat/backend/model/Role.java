package com.moh.phlat.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="code", length=16, nullable = false, unique = true)
    @NotNull(message="Code is required.")
    @NotEmpty(message="Code must not be null or empty.")
    private String code;
    
    @Column(name="description",  length=40, nullable = false)
    @NotNull(message="Description is required.")
    @NotEmpty(message="Description must not be null or empty.")
    private String description;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String descr) {
		this.description = descr;
	}
	
	
}
