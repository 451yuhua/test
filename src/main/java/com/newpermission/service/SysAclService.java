package com.newpermission.service;

import java.util.Set;

import com.newpermission.constant.CurrentUser;
import com.newpermission.pojo.criteria.SysAclCriteria;

public interface SysAclService {

	String getUrl();
	Set<String> getUrlsByRoleId(Integer roleId);
	Set<String> getUrlsByUserId(Integer userId);
	Set<String> getDeptUrlsByUserId(Integer userId);
	void addAcl(SysAclCriteria aclCriteria, CurrentUser cUser);
}
