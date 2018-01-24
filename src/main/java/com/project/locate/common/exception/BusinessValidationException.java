package com.project.locate.common.exception;

public class BusinessValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessValidationException(String message) {
		super(message);
	}

	public BusinessValidationException(Throwable cause) {
		super(cause);
	}
}
