package com.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6963468689173732622L;

	private String errorCode;
	private String errorMsg;

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	private String stacktrace;

	public ErrorResponse() {
	}

	public ErrorResponse(String errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMsg = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String message) {
		this.errorMsg = message;
	}

}
