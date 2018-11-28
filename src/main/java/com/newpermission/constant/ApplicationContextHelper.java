package com.newpermission.constant;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {

	private static ApplicationContext appContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;

	}
	
	public static <T> T getBean(Class<T> clazz) {
		if(appContext == null) {
			return null;
		}
		return appContext.getBean(clazz);
	}

}
