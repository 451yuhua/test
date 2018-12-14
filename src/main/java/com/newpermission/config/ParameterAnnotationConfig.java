package com.newpermission.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.newpermission.interceptor.annotation.CurrentUserAnnotationMethodArgumentResolver;

@Configuration
public class ParameterAnnotationConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
		resolvers.add(new CurrentUserAnnotationMethodArgumentResolver());
	}
}
