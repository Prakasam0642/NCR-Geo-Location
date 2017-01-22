package com.ncr.location.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ncr.location.constants.LocationConstants;
import com.ncr.location.exception.LocationException;
import com.ncr.location.service.LocationService;
import com.ncr.location.util.CacheUtil;
import com.ncr.location.util.LocationUtil;
import com.ncr.location.vo.ErrorResponseVO;
import com.ncr.location.vo.GeoCodeResponse;
import com.ncr.location.vo.LocationResponseVO;
import com.ncr.location.vo.SearchResultVO;




@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationUtil locationUtil;

	@Autowired
	CacheUtil cacheUtil;
	
	@Override
	public Object getLocationByAngle(HttpServletRequest request, String lat, String longt){
		SearchResultVO responseVO = null;
		try{
			DateFormat df = new SimpleDateFormat(LocationConstants.ISO_DATE_FORMAT);
			LinkedHashMap<Date, SearchResultVO> searchHistory = null;
			GeoCodeResponse geoCodeResponse = 	locationUtil.getLocationResponse(lat, longt);
			if(geoCodeResponse != null && geoCodeResponse.getResults()!=null && !geoCodeResponse.getResults().isEmpty()){
				responseVO = new SearchResultVO();
				responseVO.setLat(lat);
				responseVO.setLongt(longt);
				responseVO.setTimestamp(df.format(new Date()));
				responseVO.setAddress(geoCodeResponse.getResults().get(0).getFormatted_address());
				searchHistory = cacheUtil.getSearchHistory();
				searchHistory.put(new Date(), responseVO);
				cacheUtil.writeToCache(searchHistory);
			} else {
				responseVO = new SearchResultVO();
				responseVO.setNoResult(LocationConstants.NO_RESULTS_FOUND);		
			}
		}catch(Exception ex) {
			ErrorResponseVO errorResponse = new ErrorResponseVO();
			errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			errorResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
			errorResponse.setDescription(LocationConstants.INTERNAL_ERROR);
			return errorResponse;
		}
		return responseVO;
	}

	@Override
	public Object getSearchHistory(HttpServletRequest request){
		LocationResponseVO locationResponseVO = null;
			try {
				LinkedHashMap<Date,SearchResultVO> searchHistory = null;
				List<SearchResultVO> searchResultList = new ArrayList<SearchResultVO>();
				locationResponseVO = new LocationResponseVO();
				searchHistory = cacheUtil.getSearchHistory();
				for(SearchResultVO responseVO : searchHistory.values()) {
					searchResultList.add(responseVO);
				}
				locationResponseVO.setSearchResult(searchResultList);
			} catch(LocationException ex) {
				ErrorResponseVO errorResponse = new ErrorResponseVO();
				errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
				errorResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
				errorResponse.setDescription(LocationConstants.INTERNAL_ERROR);
				return errorResponse;
			}
		return locationResponseVO;
	}

}
