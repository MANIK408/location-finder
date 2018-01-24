package com.project.locate.common.exception;

public class UnauthenticatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthenticatedException(String message) {
		super(message);
	}

	public UnauthenticatedException(Throwable cause) {
		super(cause);
	}

}
