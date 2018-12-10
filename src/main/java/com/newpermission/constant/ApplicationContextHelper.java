package com.newpermission.constant;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 由于spring中的bean不能直接被过滤器加载，因此需要自定义一个bean的获取帮助类来获取spring中的bean
 * @author Administrator
 *
 */
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
