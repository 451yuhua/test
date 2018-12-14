package com.newpermission.service;

import com.newpermission.constant.CurrentUser;

public interface CommonService {

	boolean putCurrentUserToRedis(CurrentUser currentUser);
	
	CurrentUser getCurrentUser(String token);
	
	String genAclCode();
}
