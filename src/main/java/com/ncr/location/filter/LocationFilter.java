package com.ncr.location.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.location.constants.LocationConstants;
import com.ncr.location.exception.LocationException;
import com.ncr.location.util.ValidationUtil;
import com.ncr.location.vo.ErrorResponseVO;

public class LocationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		try {
			
			if(!isValidURL(httpRequest)) {
				sendErrorResponse(HttpStatus.UNAUTHORIZED.toString(), HttpStatus.UNAUTHORIZED.name(), LocationConstants.UNAUTHORIZED, response);
				return;
			}
			if(httpRequest.getRequestURI().contains("/location/angle")){
				String[] requestUrls = httpRequest.getRequestURI().split("/");
				
				if(!ValidationUtil.isValidLatitude(requestUrls[4])) {
					sendErrorResponse(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.name(), LocationConstants.BAD_REQUEST, response);
					return;			
				}
				if(!ValidationUtil.isValidLongitude(requestUrls[5])) {
					sendErrorResponse(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.name(), LocationConstants.BAD_REQUEST, response);
					return;				
				}
			}
		}catch(NullPointerException nullEx) {
			sendErrorResponse(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.name(), LocationConstants.BAD_REQUEST, response);
			return;	
		}catch(NumberFormatException numbrEx) {
			sendErrorResponse(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.name(), LocationConstants.BAD_REQUEST, response);
			return;
		}catch(ArrayIndexOutOfBoundsException ex) {
			sendErrorResponse(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.name(), LocationConstants.BAD_REQUEST, response);
			return;
		}catch(LocationException ex) {
			sendErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.name(), LocationConstants.INTERNAL_ERROR, response);
			return;
		}
		chain.doFilter(request, response);
		
	}
	
	private void sendErrorResponse(String errorCode, String errorMessage, String desc, ServletResponse response) throws IOException{
		ErrorResponseVO errorResponse = new ErrorResponseVO();
		ObjectMapper jsonMapper = new ObjectMapper();
		errorResponse.setErrorCode(errorCode);
		errorResponse.setMessage(errorMessage);
		errorResponse.setDescription(desc);
		response.setContentType(LocationConstants.CONTENT_TYPE);
	    PrintWriter out = response.getWriter();
	    out.print(jsonMapper.writeValueAsString(errorResponse));
	}
	
	private boolean isValidURL(HttpServletRequest httpRequest) throws NullPointerException{
		if (httpRequest.getRequestURI().contains(LocationConstants.VALID_URL_1) || httpRequest.getRequestURI().contains(LocationConstants.VALID_URL_2)) {
			return true;
		}
		return false;
		
	}
	

}
