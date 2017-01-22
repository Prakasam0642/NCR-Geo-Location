package com.ncr.location.util;

import org.springframework.util.StringUtils;

public class ValidationUtil {

private static float MIN_LATITUDE = Float.valueOf("-90.0000");
private static float MAX_LATITUDE = Float.valueOf("90.0000");
private static float MIN_LONGITUDE = Float.valueOf("-180.0000");
private static float MAX_LONGITUDE = Float.valueOf("180.0000");	
	
	public static boolean isValidLatitude(String latitude) throws NullPointerException, NumberFormatException{
		if(!StringUtils.isEmpty(latitude)) {
			float lat = Float.parseFloat(latitude);
		    if(lat >= MIN_LATITUDE && lat <= MAX_LATITUDE) {
		      return true;
		    } 
		} 
		return false;
	}
	
	public static boolean isValidLongitude(String longitude) throws NullPointerException, NumberFormatException{
		if(!StringUtils.isEmpty(longitude)) {
			float longt = Float.parseFloat(longitude);
		    if(longt >= MIN_LONGITUDE && longt <= MAX_LONGITUDE) {
		      return true;
		    }
		}
		return false;
	}	
}
