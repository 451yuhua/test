package com.newpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newpermission.pojo.SysDept;
import com.newpermission.utils.MyMapper;

/**
* Created by Mybatis Generator on 2018/11/26
*/
public interface SysDeptMapper extends MyMapper<SysDept> {
	
	void batchUpdateDeptGroup(@Param("depts") List<SysDept> depts);
}