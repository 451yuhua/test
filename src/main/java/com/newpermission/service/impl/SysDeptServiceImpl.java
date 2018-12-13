package com.newpermission.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newpermission.constant.CurrentUser;
import com.newpermission.dao.SysDeptMapper;
import com.newpermission.exception.ServiceExcetion;
import com.newpermission.pojo.SysDept;
import com.newpermission.pojo.criteria.SysDeptCriteria;
import com.newpermission.pojo.result.CommonCode;
import com.newpermission.service.SysDeptService;
import com.newpermission.utils.EntityFillUtil;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	
	@Autowired
	private SysDeptMapper sysDeptMapper;
	
	@Override
	public List<SysDept> findAll() {
		return sysDeptMapper.selectAll();
	}
	
	@Override
	public List<SysDept> getDeptAndChildrenById(Integer id) {
		if (null == id) {
			return null;
		}
		List<SysDept> depts = sysDeptMapper.selectAll();
		List<SysDept> deptChildren = EntityFillUtil.getChildren(depts, SysDept.class, id, "parentId", "id");
		return deptChildren;
	}
	
	@Override
	public void addDept(SysDeptCriteria deptCriteria, CurrentUser cUser) {
		if (null == deptCriteria) {
			throw new ServiceExcetion(CommonCode.SERVICE_ERROR, "要添加的数据为空!");
		}
		SysDept parentDept = null;
		if (deptCriteria.getParentId() != 0) {
			parentDept = sysDeptMapper.selectByPrimaryKey(deptCriteria.getParentId());
		}
		SysDept dept = new SysDept();
		BeanUtils.copyProperties(deptCriteria, dept);
		dept.setLevel(parentDept.getLevel()+".1");
		dept.setOperator(cUser.getUsername());
		sysDeptMapper.insertSelective(dept);
	}

}
