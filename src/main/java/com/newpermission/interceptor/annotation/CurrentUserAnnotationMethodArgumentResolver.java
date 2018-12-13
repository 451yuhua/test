package com.newpermission.interceptor.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.newpermission.constant.ApplicationContextHelper;
import com.newpermission.constant.CurrentUser;
import com.newpermission.service.CommonService;

public class CurrentUserAnnotationMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private CommonService commonsService;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUserAnnotation.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String token = webRequest.getHeader("access-token");
		if (null == token) {
			return null;
		}
		if (null == commonsService) {
			commonsService = ApplicationContextHelper.getBean(CommonService.class);
		}
		CurrentUser cUser = commonsService.getCurrentUser(token);
		return cUser;
	}

}
