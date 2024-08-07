package com.moh.phlat.backend.response;

public class ResponseMessage {
	private String status;
	private Integer statusCode;
	private String message;
	private Long totalItems;
	private Object data;
	
	public ResponseMessage() {
	}

	public ResponseMessage(String status, int statusCode, String message, Long totalItems, Object data) {
		super();
		this.status= status;
		this.statusCode = statusCode;
		this.message = message;
		this.totalItems = totalItems;
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

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Object getData() {
		return data;
	}

	public void setDataa(Object data) {
		this.data = data;
	}

}
