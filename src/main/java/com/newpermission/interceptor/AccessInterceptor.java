package com.newpermission.interceptor;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessInterceptor extends HandlerInterceptorAdapter {
	
	private static String ACCESS_TOKEN = "AccessToken:{}";
	
	@Autowired
	private RedisTemplate<String, String> redis;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//设置响应请求的格式
        response.setContentType("text/html;charset=UTF-8");
        
		String token = request.getHeader("access-token");
		log.info(token);
		if(StringUtils.isEmpty(token)) {
			response.getWriter().print("You don't have the permission to access!");
			return false;
		}
		String key = MessageFormat.format(ACCESS_TOKEN, token);
		ValueOperations<String, String> valueOperations = redis.opsForValue();
		String temp = valueOperations.get(key);
		return super.preHandle(request, response, handler);
	}
}
