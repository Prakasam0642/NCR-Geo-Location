package com.ncr.location;


import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class MsGoogleLocationApplicationTests extends AbstractTestNGSpringContextTests{
	
	private RestTemplate restTemplate;
	private String locEndPoint = "http://localhost:8080/v1/location/angle/";
	private String locSearchHistoryEndPoint = "http://localhost:8080/v1/location/history";
	
	@BeforeSuite
	public void testBeforeSuite() {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void getLocation_LocationFound() {
		boolean result = false;
		String lat = "33.969601";
		String longt = "-84.100033";
		ResponseEntity<Object> obj = this.restTemplate.exchange(locEndPoint + lat+"/"+longt,HttpMethod.GET, null, Object.class);	
		if (obj != null && obj.getStatusCode() != null && obj.getStatusCode().equals(HttpStatus.OK)) {
			result = true;
		}
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void getSearchHistory_Found() {
		boolean result = false;
		ResponseEntity<Object> obj = this.restTemplate.exchange(locSearchHistoryEndPoint,HttpMethod.GET, null, Object.class);	
		if (obj != null && obj.getStatusCode() != null && obj.getStatusCode().equals(HttpStatus.OK)) {
			result = true;
		}
		Assert.assertEquals(true, result);
	}

}
