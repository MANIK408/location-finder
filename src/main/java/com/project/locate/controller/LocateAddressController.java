package com.project.locate.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.locate.common.exception.EmptyDataResponse;
import com.project.locate.dto.AddressesResponse;
import com.project.locate.services.AddressServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Api(value = "addresses", description = "the addresses API")
@Slf4j
public class LocateAddressController {
	AddressServices addressServices;

	@Autowired
	public LocateAddressController(AddressServices addressServices) {
		this.addressServices = addressServices;
	}

	@ApiOperation(value = "Retrieve list of addresses", notes = "Bases on the provided search criteria. This service will provide the list of likely addresses. ", response = AddressesResponse.class, tags = {
			"Addresses", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return an address or List of addresses based on search criteria", response = AddressesResponse.class),
			@ApiResponse(code = 400, message = "Indicates that the server could not understand the request.", response = EmptyDataResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized.  This will be returned when no authentication information is provided.", response = EmptyDataResponse.class),
			@ApiResponse(code = 403, message = "The principal associated with the request does not have sufficent rights to preform this operation on the requested resource.", response = EmptyDataResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = EmptyDataResponse.class) })

	@RequestMapping(value = "/addresses", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<AddressesResponse> getAddress(
			@ApiParam(value = "The auth token - JWT", required = true) @RequestHeader(value = "authorization", required = true) String authorization,
			@NotNull @ApiParam(value = "Search criteria for the location", required = true) @RequestParam(value = "search_key", required = true) String searchKey,
			@ApiParam(value = "Search api for the location") @RequestParam(value = "search_api", required = false) String searchApi) {
		log.info("Going to service layer: " + searchKey);
		ResponseEntity<AddressesResponse> entity = ResponseEntity.ok(addressServices.searchAddressViaGoogle(searchKey));
		/*AddressesResponse httpEntity = entity.getBody(); //TODO: Testing Exception scenarios
		httpEntity = null;
		if (httpEntity == null) {
			throw new BusinessValidationException("7212");
		}*/
		log.info(" Result from google services" + entity);
		return entity;
	}
}
