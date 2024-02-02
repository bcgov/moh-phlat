package com.moh.phlat.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="USER_ID", length=30, nullable = false, unique = false)
    @NotNull(message="Userid is required.")
    @NotEmpty(message="Userid must not be null or empty.")
    private String userId;
    
    @Column(name="ROLE_CODE",  length=16, nullable = false)
    @NotNull(message="Role is required.")
    @NotEmpty(message="Role must not be null or empty.")
    private String roleCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

 
	
}
