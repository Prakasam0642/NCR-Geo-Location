package com.ncr.location.util;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ncr.location.constants.LocationConstants;
import com.ncr.location.vo.SearchResultVO;

@Component
public class EvictUtil extends LinkedHashMap<Date, SearchResultVO>{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected boolean removeEldestEntry(Map.Entry<Date, SearchResultVO> eldest) {
		return size() > LocationConstants.MAX_ENTRIES;
	}
	
}
