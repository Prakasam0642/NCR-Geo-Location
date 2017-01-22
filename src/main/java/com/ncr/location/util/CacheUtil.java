package com.ncr.location.util;



import java.util.Date;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ncr.location.constants.LocationConstants;
import com.ncr.location.exception.LocationException;
import com.ncr.location.vo.SearchResultVO;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

@Component
public class CacheUtil {
	
	@Autowired
	EvictUtil searchHistory;
	
	public void writeToCache(LinkedHashMap<Date, SearchResultVO> searchHistory) throws LocationException{
		NamedCache namedCache = CacheFactory.getCache(LocationConstants.CACHE_NAME);
		namedCache.put(LocationConstants.SEARCH_KEY, searchHistory);
	}
	
	@SuppressWarnings("unchecked")
	public LinkedHashMap<Date, SearchResultVO> getSearchHistory() throws LocationException{
		NamedCache namedCache = CacheFactory.getCache(LocationConstants.CACHE_NAME);
		if(namedCache.get(LocationConstants.SEARCH_KEY)!=null) {
			return (LinkedHashMap<Date, SearchResultVO>)namedCache.get(LocationConstants.SEARCH_KEY);
		} else {
			return searchHistory;
		}
	}
	
	 
	
	

}
