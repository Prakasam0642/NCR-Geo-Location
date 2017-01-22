package com.ncr.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ncr.location.filter.LocationFilter;

@SpringBootApplication
@Configuration
public class MsGoogleLocationApplication {
	
	public static void main(String args[]){
		SpringApplication.run(MsGoogleLocationApplication.class, args);
	}
	
	@Bean
	public LocationFilter authorizationFilter() {
		return new LocationFilter();
	}

}
