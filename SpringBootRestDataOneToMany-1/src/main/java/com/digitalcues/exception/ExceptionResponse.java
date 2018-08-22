package com.digitalcues.exception;

public class ExceptionResponse {
	
	 private String errorCode;
	 private String errorMessage;
	 
	 public ExceptionResponse(){
		 
	 }
	 
	public ExceptionResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
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



	@Override
	public String toString() {
		return "ExceptionResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
	
	
	 
	 

}
