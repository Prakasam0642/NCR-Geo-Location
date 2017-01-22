package com.ncr.location.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationResponseVO {
	
	@JsonProperty("search_history")	
	private List<SearchResultVO> searchResult;

	public List<SearchResultVO> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SearchResultVO> searchResult) {
		this.searchResult = searchResult;
	}

	

	

}
