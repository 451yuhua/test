package com.newpermission.service;

import java.util.List;

import com.newpermission.constant.CurrentUser;
import com.newpermission.pojo.SysDept;
import com.newpermission.pojo.criteria.SysDeptCriteria;

public interface SysDeptService {

	List<SysDept> findAll();

	List<SysDept> getDeptAndChildrenById(Integer id);

	void addDept(SysDeptCriteria deptCriteria, CurrentUser cUser);

}
