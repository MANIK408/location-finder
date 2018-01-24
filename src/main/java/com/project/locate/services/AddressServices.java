package com.project.locate.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.locate.dto.Addresses;
import com.project.locate.dto.AddressesResponse;
import com.project.locate.stubs.locationws.AddressComplete_Interactive_AutoComplete_v1_00_Results;
import com.project.locate.stubs.locationws.PostcodeAnywhere;
import com.project.locate.stubs.locationws.PostcodeAnywhereLocator;
import com.project.locate.stubs.locationws.PostcodeAnywhere_Soap;

@Component
public class AddressServices {
	@Autowired
	RestTemplate restTemplate;
	@Value("${google.location.uri}")
	private String googleLocUri;
	@Value("${google.location.key}")
	private String googlekey;
	@Value("${google.location.lang.attribute}")
	private String langAttribute;
	@Value("${google.location.lang.pref}")
	private String langPref;
	@Value("${google.location.querystring}")
	private String querystring;
	@Value("${canadapost.location.key}")
	private String caPostkey;

	public AddressesResponse searchAddressViaGoogle(String query) {
		List<Addresses> addressBeans = null;
		AddressesResponse addressListBean = null;
		Map<?, ?> googleService = (LinkedHashMap<?, ?>) restTemplate.getForObject(
				googleLocUri + googlekey + "&" + langAttribute + langPref + "&" + querystring + query, Object.class);

		if (null != googleService) {
			addressListBean = new AddressesResponse();
			addressBeans = new ArrayList<Addresses>();
			Iterator<?> entries = googleService.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<?, ?> entry = (Map.Entry<?, ?>) entries.next();
				entry.getKey();
				ArrayList<?> list = (ArrayList<?>) entry.getValue();
				Iterator<?> iterator = list.iterator();
				while (iterator.hasNext()) {
					Map<?, ?> map = (Map<?, ?>) iterator.next();
					Iterator<?> mapItr = map.entrySet().iterator();
					Map.Entry<?, ?> entryItr = (Map.Entry<?, ?>) mapItr.next();
					Addresses addressBean = new Addresses();
					addressBean.setAddress((String) entryItr.getValue());
					addressBeans.add(addressBean);
					addressListBean.setData(addressBeans);

				}
				break;
			}
		}
		return addressListBean;
	}

	/*
	 * public AddressListDTO searchAddressViaGoogleApi(String query) {
	 * AddressListDTO addressListBean = restTemplate.getForObject( googleLocUri +
	 * googlekey + "&" + langAttribute + langPref + "&" + querystring + query,
	 * com.project.locate.dto.AddressListDTO.class); return addressListBean; }
	 */
	public AddressesResponse searchAddressViaCaPost(String query) {
		AddressComplete_Interactive_AutoComplete_v1_00_Results[] results = null;
		List<Addresses> addressBeans = null;
		AddressesResponse addressListBean = null;
		PostcodeAnywhere anywhere = new PostcodeAnywhereLocator();
		Set<String> set = null;
		try {
			PostcodeAnywhere_Soap geoIPServiceSoap = anywhere.getPostcodeAnywhere_Soap();
			results = geoIPServiceSoap.addressComplete_Interactive_AutoComplete_v1_00(caPostkey, query, "", 0, "", "");
			if (results.length > 0) {
				addressBeans = new ArrayList<Addresses>();
				addressListBean = new AddressesResponse();
				set = new HashSet<String>();
			}
			for (int i = 0; i < results.length; i++) {
				set.add((StringUtils.isBlank(results[i].getText()) ? "" : results[i].getText()).concat(
						" " + (StringUtils.isBlank(results[i].getDescription()) ? "" : results[i].getDescription())));
			}
			if (null != set) {
				Iterator<String> iterator = (Iterator<String>) set.iterator();
				while (iterator.hasNext()) {
					Addresses addressLocated = new Addresses();
					addressLocated.setAddress((String) iterator.next());
					addressBeans.add(addressLocated);
				}
				addressListBean.setData(addressBeans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressListBean;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
