package com.newpermission.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.newpermission.filter.WebFilter;

@Configuration
public class FilterConfiguration {

	/*@Bean
	public FilterRegistrationBean<WebFilter> testFilterRegistration() {
		FilterRegistrationBean<WebFilter> registration = new FilterRegistrationBean<WebFilter>(new WebFilter());
		registration.addUrlPatterns("/test/api"); //
		//registration.addInitParameter("paramName", "paramValue"); //
		registration.setName("WebFilter");
		return registration;
	}*/
}
