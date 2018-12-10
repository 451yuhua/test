package com.newpermission.service.impl;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newpermission.constant.CurrentUser;
import com.newpermission.dao.SysUserMapper;
import com.newpermission.model.loginUser;
import com.newpermission.pojo.SysUser;
import com.newpermission.service.CommonService;
import com.newpermission.service.SysAclService;
import com.newpermission.service.SysUserService;
import com.newpermission.utils.GenerateUtil;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysAclService aclService;
	
	@Autowired
	private CommonService commonService;

	@Override
	public SysUser findByUsername(String username) {
		SysUser user = new SysUser();
		user.setUsername(username);
		user = userMapper.selectOne(user);
//		CurrentUser cUser = new CurrentUser();
//		BeanUtils.copyProperties(user, cUser);
//		if (user.getId() != null) {
//			Set<String> urls = aclService.getUrlsByUserId(user.getId());
//			cUser.setUrlSet(urls);
//		}
//		cUser.setId(user.getId());
//		cUser.setUsername(username);
//		cUser.setTelephone(user.getTelephone());
//		String[] params = {user.getTelephone(),System.currentTimeMillis()+""};
//		String token = GenerateUtil.generateToken(params, "SHA-1");
//		cUser.setToken(token);
//		commonService.putCurrentUserToRedis(cUser);
		return user;
		
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean validateUsername() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CurrentUser login(loginUser loginUser) {
		if (loginUser == null) {//判断登陆参数是否为空
			return null;
		}
		SysUser user = findByUsername(loginUser.getUsername());
		if (null == user) {//查看该用户是否存在
			return null;
		}
		if (!user.getPassword().equals(loginUser.getPassword())) {//验证密码是否正确
			return null;
		}
		CurrentUser cUser = new CurrentUser();
		BeanUtils.copyProperties(user, cUser);
		if (user.getId() != null) {
			Set<String> urls = aclService.getUrlsByUserId(user.getId());
			cUser.setUrlSet(urls);
		}
		String[] params = {user.getTelephone(),System.currentTimeMillis()+""};
		String token = GenerateUtil.generateToken(params, "SHA-1");
		cUser.setToken(token);
		commonService.putCurrentUserToRedis(cUser);
		return cUser;
	}
	
	
}
