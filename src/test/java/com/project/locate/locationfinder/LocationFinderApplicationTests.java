package com.project.locate.locationfinder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.locate.controller.LocateAddressController;
import com.project.locate.dto.AddressesResponse;
import com.project.locate.services.AddressServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationFinderApplicationTests {
	private String SEARCH_CRITERIA = "1780 Eglinton";
	private int STATUS_OK = 200;

	@InjectMocks
	private LocateAddressController controller;

	@Mock
	private AddressServices service;

	@Test
	public void getAddress() {
		when(service.searchAddressViaGoogle(SEARCH_CRITERIA)).thenReturn(new AddressesResponse());
		ResponseEntity<AddressesResponse> responseEntity = controller.getAddress("", SEARCH_CRITERIA,
				StringUtils.EMPTY);
		assertEquals(responseEntity.getStatusCodeValue(), STATUS_OK);
	}

}
