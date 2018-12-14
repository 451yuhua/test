package com.newpermission.service.impl;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newpermission.constant.CurrentUser;
import com.newpermission.dao.SysAclMapper;
import com.newpermission.exception.ServiceExcetion;
import com.newpermission.pojo.SysAcl;
import com.newpermission.pojo.criteria.SysAclCriteria;
import com.newpermission.pojo.result.CommonCode;
import com.newpermission.service.SysAclService;

@Service
@Transactional
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
	
	@Override
	public void addAcl(SysAclCriteria aclCriteria, CurrentUser cUser) {
		if (null == aclCriteria || null == cUser) {
			throw new ServiceExcetion(CommonCode.SERVICE_ERROR, "添加权限失败");
		}
		SysAcl acl = new SysAcl();
		BeanUtils.copyProperties(aclCriteria, acl);
		acl.setOperator(cUser.getUsername());
		acl.setCode("");
	}
}
