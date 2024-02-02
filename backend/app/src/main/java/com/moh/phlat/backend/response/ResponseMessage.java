package com.moh.phlat.backend.response;

public class ResponseMessage {
	private String status;
	private Integer statusCode;
	private String message;
	private Object data;
	
	public ResponseMessage() {
	}

	public ResponseMessage(String status, int statusCode, String message, Object data) {
		super();
		this.status= status;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setDataa(Object data) {
		this.data = data;
	}

}
