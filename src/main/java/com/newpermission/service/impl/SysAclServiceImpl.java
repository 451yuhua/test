package com.newpermission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newpermission.dao.SysAclMapper;
import com.newpermission.service.SysAclService;

@Service
public class SysAclServiceImpl implements SysAclService {

	@Autowired
	private SysAclMapper sysAclMapper;

	@Override
	public String getUrl() {
		return sysAclMapper.selectUrl();
	}
	
}
