package com.newpermission.service.impl;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.newpermission.constant.CurrentUser;
import com.newpermission.service.CommonService;
import com.newpermission.utils.GenerateUtil;

@Service
public class CommonServiceImpl implements CommonService {

	private static String ACCESS_TOKEN = "AccessToken:{0}";
	
	@Autowired
	private RedisTemplate<String, CurrentUser> currentUserRedis;
	
	@Override
	public boolean putCurrentUserToRedis(CurrentUser currentUser) {
		if(null == currentUser) {
			return false;
		}
		String[] params = {currentUser.getTelephone(), System.currentTimeMillis()+""};
		String token = GenerateUtil.generateToken(params, "SHA-1");
		String key = MessageFormat.format(ACCESS_TOKEN, token);
		ValueOperations<String, CurrentUser> currentOpration = currentUserRedis.opsForValue();
		currentOpration.set(key, currentUser,60L*60*2,TimeUnit.SECONDS);
		return true;
	}

	@Override
	public CurrentUser getCurrentUser(String token) {
		String key = MessageFormat.format(ACCESS_TOKEN, token);
		ValueOperations<String, CurrentUser> currentOpration = currentUserRedis.opsForValue();
		CurrentUser cUser = currentOpration.get(key);
		return cUser;
	}

}
