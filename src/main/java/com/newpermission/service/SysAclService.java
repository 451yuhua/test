package com.newpermission.service;

import java.util.List;
import java.util.Set;

public interface SysAclService {

	String getUrl();
	Set<String> getUrlsByRoleId(Integer roleId);
	Set<String> getUrlsByUserId(Integer userId);
	List<String> getDeptUrlsByUserId(Integer userId);
}
