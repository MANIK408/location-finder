package com.project.locate.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Indicate no object is found, typically mapped to 404 HTTP status code
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Long resourceId;

	public ResourceNotFoundException(Long resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
}
