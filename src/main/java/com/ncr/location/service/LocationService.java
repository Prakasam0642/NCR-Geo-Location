package com.ncr.location.service;

import javax.servlet.http.HttpServletRequest;

import com.ncr.location.exception.LocationException;

public interface LocationService {

	public Object getLocationByAngle(HttpServletRequest request, String lat, String longt);
	public Object getSearchHistory(HttpServletRequest request);
}
