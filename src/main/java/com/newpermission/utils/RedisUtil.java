package com.newpermission.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public String getValue(String key) {
		ValueOperations<String, String> operation = getOpt();
		return operation.get(key);
	}
	
	public void setString(String key, String value) {
		getOpt().set(key, value);
	}
	
	private ValueOperations<String, String> getOpt(){
		return redisTemplate.opsForValue();
	}
	
	public void setTime(String key, Long time) {
		redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}
}
