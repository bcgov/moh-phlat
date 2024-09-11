package com.moh.phlat.backend.esb.boundary;

import lombok.Getter;

public class PlrError {
	@Getter
	private String errorCode;
	@Getter
	private String errorType;
	@Getter
	private String errorMessage;
	
	public PlrError(String errorCode, String errorType, String errorMessage) {
		this.errorCode = errorCode;
		this.errorType = errorType;
		this.errorMessage = errorMessage;
	}
}