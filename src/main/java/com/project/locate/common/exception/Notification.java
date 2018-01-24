package com.project.locate.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Notification
 */
@Data
public class Notification {
	@JsonProperty("code")
	private String code = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("uuid")
	private String uuid = null;

	@JsonProperty("timestamp")
	private String timestamp = null;

}
