package com.newpermission.service;

import com.newpermission.constant.CurrentUser;
import com.newpermission.model.loginUser;
import com.newpermission.pojo.SysUser;

public interface SysUserService {

	SysUser findByUsername(String username);
	String getUsername();
	boolean validateUsername();
	CurrentUser login(loginUser loginUser);
}
