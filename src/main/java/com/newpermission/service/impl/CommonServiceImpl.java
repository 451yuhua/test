package com.newpermission.service.impl;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.newpermission.constant.CurrentUser;
import com.newpermission.exception.ServiceExcetion;
import com.newpermission.pojo.result.CommonCode;
import com.newpermission.service.CommonService;
import com.newpermission.utils.GenerateUtil;
import com.newpermission.utils.RedisUtil;

@Service
public class CommonServiceImpl implements CommonService {

	private static String ACCESS_TOKEN = "AccessToken:{0}";
	
	private static String genAclCodeKey = "ACLCODE:{0}";
	
	@Autowired
	private RedisTemplate<String, CurrentUser> currentUserRedis;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public boolean putCurrentUserToRedis(CurrentUser currentUser) {
		if(null == currentUser || currentUser.getToken() == null) {
			return false;
		}
		String token = currentUser.getToken();
		/*if (StringUtils.isEmpty(token)) {
			String[] params = { currentUser.getTelephone(), System.currentTimeMillis() + "" };
			token = GenerateUtil.generateToken(params, "SHA-1");
		}*/
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

	@Override
	public String genAclCode() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String aclCodePre = format.format(date);
		String aclKey = MessageFormat.format(genAclCodeKey, aclCodePre);
		String aclCodeSuffix = redisUtil.getValue(aclKey);
		if(StringUtils.isEmpty(aclCodeSuffix)) {
			aclCodeSuffix = "00001";
		}else {
			int suffix = Integer.parseInt(aclCodeSuffix);
			aclCodeSuffix = ""+ suffix++;
		}
		redisUtil.setString(aclKey, aclCodeSuffix);
		redisUtil.setTime(aclKey, 60L);
		return aclCodePre + aclCodeSuffix;
	}
	
	@Override
	public void removeCurrentUser(String token) {
		if (null == token) {
			throw new ServiceExcetion(CommonCode.SERVER_INERNAL_ERROR, "没有登录验证信息");
		}
		String key = MessageFormat.format(ACCESS_TOKEN, token);
		currentUserRedis.delete(key);
	}

}
