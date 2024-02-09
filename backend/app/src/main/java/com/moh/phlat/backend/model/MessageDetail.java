package com.moh.phlat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "message_detail")
public class MessageDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "process_data_id")
	private Long processDataId;

	@Column(name = "rowstatus_code")
	private String rowstatusCode;

	@Column(name = "error_code")
	private Integer errorCode;

	@Column(name = "error_type")
	private String errorType;

	@Column(name = "error_msg")
	private String errorMsg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProcessDataId() {
		return processDataId;
	}

	public void setProcessDataId(Long processDataId) {
		this.processDataId = processDataId;
	}

	public String getRowstatusCode() {
		return rowstatusCode;
	}

	public void setRowstatusCode(String rowstatusCode) {
		this.rowstatusCode = rowstatusCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
