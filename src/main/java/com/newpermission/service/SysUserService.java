package com.newpermission.service;

import com.newpermission.pojo.SysUser;

public interface SysUserService {

	SysUser findByUsername(String username);
}
