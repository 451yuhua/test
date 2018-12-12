package com.newpermission.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newpermission.dao.SysAclMapper;
import com.newpermission.pojo.SysAcl;
import com.newpermission.service.SysAclService;

@Service
public class SysAclServiceImpl implements SysAclService {

	@Autowired
	private SysAclMapper sysAclMapper;

	@Override
	public String getUrl() {
		return sysAclMapper.selectUrl();
	}

	@Override
	public Set<String> getUrlsByRoleId(Integer roleId) {
		return sysAclMapper.getUrlsByRoleId(roleId);
	}
	
	@Override
	public Set<String> getUrlsByUserId(Integer userId){
		return sysAclMapper.getUrlsByUserId(userId);
	}

	@Override
	public Set<String> getDeptUrlsByUserId(Integer userId) {
		return sysAclMapper.getDeptUrlsByUserId(userId);
	}
	
	public void addAcl(SysAcl acl) {
		
	}
}
