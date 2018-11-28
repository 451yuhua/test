package com.newpermission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.newpermission.dao.SysAclMapper;
import com.newpermission.service.SysAclService;

public class SysAclServiceImpl implements SysAclService {

	@Autowired
	private SysAclMapper sysAclMapper;
	
}
