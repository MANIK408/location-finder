package com.project.locate.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.locate.common.exception.Notification;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * An array of transactions and cursor information
 */
@ApiModel(description = "An array of transactions and cursor information")
@Data
public class AddressesResponse {
	@JsonProperty("data")
	private List<Addresses> data = null;

	@JsonProperty("notifications")
	private List<Notification> notifications = null;
}
