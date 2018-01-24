package com.project.locate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * An array of addresses.
 */
@ApiModel(description = "An array of addresses.")
@Data
public class Addresses {
	@JsonProperty("address")
	private String address = null;
}
