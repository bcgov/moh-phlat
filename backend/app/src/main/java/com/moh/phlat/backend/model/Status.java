package com.moh.phlat.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="code", length=30, nullable = false, unique = true)
    @NotNull(message="Code is required.")
    @NotEmpty(message="Code must not be null or empty.")
    private String code;
    
    @Column(name="description",  length=130, nullable = false)
    @NotNull(message="Description is required.")
    @NotEmpty(message="Description must not be null or empty.")
    private String description;

    @Column(name="is_deleted")
    private Boolean isDeleted;

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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}	
	
	
}
