package com.moh.phlat.backend.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class UiColumnName {
	public String key;
	public String title;
}