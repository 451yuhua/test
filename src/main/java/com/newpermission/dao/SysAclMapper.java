package com.newpermission.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newpermission.pojo.SysAcl;
import com.newpermission.utils.MyMapper;

/**
* Created by Mybatis Generator on 2018/11/26
*/
public interface SysAclMapper extends MyMapper<SysAcl> {
	
	Set<String> getUrlsByRoleId(@Param("roleId") Integer roleId);
	String selectUrlById(Integer id);
	String selectUrl();
	Set<String> getUrlsByUserId(@Param("userId") Integer userId);
	Set<String> getDeptUrlsByUserId(@Param("userId") Integer userId);
}