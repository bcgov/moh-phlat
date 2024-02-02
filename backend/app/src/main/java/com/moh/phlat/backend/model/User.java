package com.moh.phlat.backend.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="user_id", length=30, nullable = false, unique = true)
    @NotNull(message="Userid is required.")
    @NotEmpty(message="Userid must not be null or empty.")
    private String userId;
    
    @Column(name="first_name",  length=40, nullable = false)
    @NotNull(message="First name is required.")
    @NotEmpty(message="First name must not be null or empty.")   
    private String firstName;

    @Column(name="last_name",  length=40, nullable = false)
    @NotNull(message="Last name is required.")
    @NotEmpty(message="Last name must not be null or empty.")   
    private String lastName;
    
    @Column(name="active_flag")
    private Boolean activeFlag;

	@Column(name="created_at")
    private Date createdAt;

	@Column(name="created_by")
    private String createdBy;
    
    @Column(name="updated_at")
    private Date updatedAt;

	@Column(name="updated_by")
    private String updatedBy;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
}
