package com.newpermission.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.newpermission.constant.ApplicationContextHelper;
import com.newpermission.constant.CurrentUser;
import com.newpermission.pojo.result.CommonCode;
import com.newpermission.pojo.result.ResultGenerator;
import com.newpermission.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@javax.servlet.annotation.WebFilter(urlPatterns= {"/test/api","/sys/dept/*"},filterName="WebFilter")
public class WebFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String servletPath = httpRequest.getServletPath();
		String token = httpRequest.getHeader("access-token");
//		String askToken = httpRequest.getHeader("asktoken");
		log.info(servletPath);
		if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			System.out.println(RequestMethod.OPTIONS.name());
			chain.doFilter(httpRequest, httpResponse);
		}
		System.out.println(servletPath);
		httpResponse.setContentType("text/html;charset=UTF-8");
		String result = null;
//		httpResponse.setCharacterEncoding("UTF-8");
		if(StringUtils.isEmpty(token)) {
//			httpResponse.getWriter().print(new String("您无权访问路径".getBytes(), StandardCharsets.UTF_8)+servletPath);
			result = JSON.toJSONString(ResultGenerator.genFailResult(CommonCode.SERVICE_UNAVAILABLE, "您无权访问该路径", null));
			httpResponse.getWriter().print(result);
			return;
		}
//		String token = httpRequest.getHeader("access-token");
//		String token = "6670B63DD85ACEC9C5468D46C01F44A5A607401A";
		/*if(token == null) {
			return;
		}*/
//		System.out.println(getCurrentUrls(token));
		Set<String> urls = getCurrentUrls(token);
		if (null != urls && urls.size() > 0 && !urls.contains(servletPath)) {
			log.info("没有权限访问url：{}",servletPath);
			result = JSON.toJSONString(ResultGenerator.genFailResult(CommonCode.SERVICE_UNAVAILABLE, "您无权访问该路径", null));
			httpResponse.getWriter().print(result);
//			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		chain.doFilter(httpRequest, httpResponse);

	}
	
	public Set<String> getCurrentUrls(String token) {
		CommonService commonService = ApplicationContextHelper.getBean(CommonService.class);
		CurrentUser cUser = commonService.getCurrentUser(token);
		if (null != cUser) {
			return cUser.getUrlSet();
		}
		return null;
	}

}
