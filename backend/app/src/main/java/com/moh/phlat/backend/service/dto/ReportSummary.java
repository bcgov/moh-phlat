package com.moh.phlat.backend.service.dto;

public class ReportSummary {

	public String rowStatus;
	public Long rowStatusCount;
		
	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public Long getRowStatusCount() {
		return rowStatusCount;
	}
	public void setRowStatusCount(Long rowStatusCount) {
		this.rowStatusCount = rowStatusCount;
	}
}