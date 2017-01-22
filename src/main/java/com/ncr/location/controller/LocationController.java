package com.ncr.location.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncr.location.exception.LocationException;
import com.ncr.location.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RequestMapping("/v1/location")
@Api(value = "/location", description = "Location Service for NCR")
@RestController
public class LocationController {

	@Autowired
	LocationService service;
	
	@ApiOperation(value = "Retrieve the Geo Location for an NCR User")
	@GetMapping("/angle/{lat}/{lng:.+}")
	public Object getLocationByAngle(HttpServletRequest request, @PathVariable String lat,  @PathVariable String lng){
		return ResponseEntity.ok().body(service.getLocationByAngle(request, lat, lng));
	}
	
	@ApiOperation(value = "Retrieve location history for an NCR User")
	@GetMapping("/history")
	public Object getsearchHistory(HttpServletRequest request){
		return ResponseEntity.ok().body(service.getSearchHistory(request));
	}
	
	
}
