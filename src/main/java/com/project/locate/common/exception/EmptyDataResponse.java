package com.project.locate.common.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * EmptyDataResponse
 */
@Data
public class EmptyDataResponse {
	@JsonProperty("data")
	private String data = null;

	@JsonProperty("notifications")
	private List<Notification> notifications = null;
}
