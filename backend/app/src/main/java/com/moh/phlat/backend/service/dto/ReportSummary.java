package com.moh.phlat.backend.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ReportSummary {
	public String attribute;
	public Long count;
}