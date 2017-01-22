package com.ncr.location.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.ncr.location.constants.LocationConstants;
import com.ncr.location.vo.GeoCodeResponse;


@Component
public class LocationUtil {
	
	@Autowired
	private Environment env;

	public GeoCodeResponse  getLocationResponse(String lat, String longt) throws Exception{
		String response = null;
		StringBuffer urlBuffer = new StringBuffer(env.getProperty(LocationConstants.LOCATION_URL));
		urlBuffer.append(lat);
		urlBuffer.append(LocationConstants.COMMA_SYMBOL);
		urlBuffer.append(longt);
		response = getLocationRestTemplate().getForObject(urlBuffer.toString(), String.class);
		return new Gson().fromJson(response, GeoCodeResponse.class);
	}
	
	public RestTemplate getLocationRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}	

}
