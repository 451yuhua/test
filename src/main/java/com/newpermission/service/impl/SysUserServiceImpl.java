package com.newpermission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newpermission.dao.SysUserMapper;
import com.newpermission.pojo.SysUser;
import com.newpermission.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper userMapper;

	@Override
	public SysUser findByUsername(String username) {
		SysUser user = new SysUser();
		user.setUsername(username);
		return userMapper.selectOne(user);
	}
	
	
}
