package com.newpermission.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.newpermission.constant.ApplicationContextHelper;
import com.newpermission.constant.CurrentUser;
import com.newpermission.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@javax.servlet.annotation.WebFilter(urlPatterns="/test/api",filterName="WebFilter")
public class WebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String servletPath = httpRequest.getServletPath();
		log.info(servletPath);
		System.out.println(servletPath);
//		String token = httpRequest.getHeader("access-token");
		String token = "6670B63DD85ACEC9C5468D46C01F44A5A607401A";
		/*if(token == null) {
			return;
		}*/
		System.out.println(getCurrentUrls(token));
		chain.doFilter(httpRequest, httpResponse);

	}
	
	public String getCurrentUrls(String token) {
		CommonService commonService = ApplicationContextHelper.getBean(CommonService.class);
		CurrentUser cUser = commonService.getCurrentUser(token);
		return cUser.getUsername();
	}

}
