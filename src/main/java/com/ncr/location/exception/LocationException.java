package com.ncr.location.exception;

public class LocationException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	private String errorDesc;
	
	public LocationException() {
		super();
	}

	public LocationException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public LocationException(String errorCode, String errorMessage, String errorDesc) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
