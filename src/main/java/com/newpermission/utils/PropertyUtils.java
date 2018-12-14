package com.newpermission.utils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class PropertyUtils {
	
	private static String genAclCodeKey = "ACLCODE:{}";
	
	@Autowired
	private RedisUtil redisUtil;

	public static String aclCodeGen() {
		String aclCode = null;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String aclCodePre = format.format(date);
		//TO DO
		return aclCode;
	}
	
	private String getAclCode(String date) {
		String aclCodeKey = MessageFormat.format(genAclCodeKey, date);
		
		return null;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String aclCodePre = format.format(date);
		System.out.println(aclCodePre);
	}
}
