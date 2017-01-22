package com.ncr.location.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class SearchResultVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("lattitude")
	public String lat;
	@JsonProperty("longtitude")
	public String longt;
	public String address;
	public String timestamp;
	@JsonProperty("Result")
	public String noResult;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongt() {
		return longt;
	}
	public void setLongt(String longt) {
		this.longt = longt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNoResult() {
		return noResult;
	}
	public void setNoResult(String noResult) {
		this.noResult = noResult;
	}
	
}
