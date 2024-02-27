package com.moh.phlat.backend.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CONTROL")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Control {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID")
    private Long id;
    
    @Column(name="FILE_NAME")
    @NotNull(message="filename must not be null")
    private String fileName;;
    
    @Column(name="USER_ID")
    @NotNull(message="userid must not be null")
    private String userId;
    
    @Column(name="FILE_EXTRACTED_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @JsonFormat(pattern="yyyy-MM-dd")
    private Date fileExtractedDate;

    @Column(name="PROCESS_START_DATE")
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date processStartDate;
    
    @Column(name="PROCESS_END_DATE")
    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date processEndDate;

    
    @Column(name="BATCH_LABEL_NAME")
    private String batchLabelName;

    
    @Column(name="LOAD_TYPE_FACILITY")
    private Boolean loadTypeFacility;


    @Column(name="LOAD_TYPE_HDS")
    private Boolean loadTypeHds;

   
    @Column(name="LOAD_TYPE_BUS_ORG")
    private Boolean loadTypeOrg;

    
    @Column(name="LOAD_TYPE_O_F_RELATIONSHIP")
    private Boolean loadTypeOFRelationship;
    
    
    @Column(name="LOAD_TYPE_O_O_RELATIONSHIP")
    private Boolean loadTypeOORelationship;
    
    @Column(name="LOAD_TYPE_I_O_RELATIONSHIP")
    private Boolean loadTypeIORelationship;
    
    @Column(name="LOAD_TYPE_WL_ORG_XREF")
    private Boolean loadTypeWOXref;
    
    @Column(name="LOAD_TYPE_WL_PRAC_IDENT_XREF")
    private Boolean loadTypeWPIXref;
    
    @Column(name="STATUS_CODE")
    private String statusCode;
    
	@Column(name="CREATED_AT")
    private Date createdAt;

	@Column(name="CREATED_BY")
    private String createdBy;
    
    @Column(name="UPDATED_AT")
    private Date updatedAt;

	@Column(name="UPDATED_BY")
    private String updatedBy;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getFileExtractedDate() {
		return fileExtractedDate;
	}

	public void setFileExtractedDate(Date fileExtractedDate) {
		this.fileExtractedDate = fileExtractedDate;
	}
	
	public Date getProcessStartDate() {
		return processStartDate;
	}

	public void setProcessStartDate(Date processStartDate) {
		this.processStartDate = processStartDate;
	}
	
	public Date getProcessEndDate() {
		return processEndDate;
	}

	public void setProcessEndDate(Date processEndDate) {
		this.processEndDate = processEndDate;
	}
	
	
	public String getBatchLabelName() {
		return batchLabelName;
	}

	public void setBatchLabelName(String batchLabelName) {
		this.batchLabelName = batchLabelName;
	}

	public Boolean getLoadTypeFacility() {
		return loadTypeFacility;
	}

	public void setLoadTypeFacility(Boolean loadTypeFacility) {
		this.loadTypeFacility = loadTypeFacility;
	}

	public Boolean getLoadTypeHds() {
		return loadTypeHds;
	}

	public void setLoadTypeHds(Boolean loadTypeHds) {
		this.loadTypeHds = loadTypeHds;
	}

	public Boolean getLoadTypeOrg() {
		return loadTypeOrg;
	}

	public void setLoadTypeOrg(Boolean loadTypeOrg) {
		this.loadTypeOrg = loadTypeOrg;
	}

	public Boolean getLoadTypeOFRelationship() {
		return loadTypeOFRelationship;
	}

	public void setLoadTypeOFRelationship(Boolean loadTypeOFRelationship) {
		this.loadTypeOFRelationship = loadTypeOFRelationship;
	}

	public Boolean getLoadTypeOORelationship() {
		return loadTypeOORelationship;
	}

	public void setLoadTypeOORelationship(Boolean loadTypeOORelationship) {
		this.loadTypeOORelationship = loadTypeOORelationship;
	}

	public Boolean getLoadTypeIORelationship() {
		return loadTypeIORelationship;
	}

	public void setLoadTypeIORelationship(Boolean loadTypeIORelationship) {
		this.loadTypeIORelationship = loadTypeIORelationship;
	}

	public Boolean getLoadTypeWOXref() {
		return loadTypeWOXref;
	}

	public void setLoadTypeWOXref(Boolean loadTypeWOXref) {
		this.loadTypeWOXref = loadTypeWOXref;
	}

	public Boolean getLoadTypeWPIXref() {
		return loadTypeWPIXref;
	}

	public void setLoadTypeWPIXref(Boolean loadTypeWPIXref) {
		this.loadTypeWPIXref = loadTypeWPIXref;
	}
	

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
