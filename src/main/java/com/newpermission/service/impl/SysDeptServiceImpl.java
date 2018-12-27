package com.newpermission.service.impl;

import java.util.ArrayList;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
			log.info("添加部门操作没有获取到部门数据");
			throw new ServiceExcetion(CommonCode.SERVICE_ERROR, "要添加的数据为空!");
		}
		SysDept parentDept = null;
		if (deptCriteria.getParentId() == null) {//没有上级部门id，视为顶级部门
			deptCriteria.setParentId(0);
		}
		if (deptCriteria.getParentId() != 0) {
			parentDept = sysDeptMapper.selectByPrimaryKey(deptCriteria.getParentId());
		}
		if (!deptOprValidate(cUser.getDeptId(), deptCriteria.getParentId())) {
			log.info("您没有权限添加部门：{}",deptCriteria.getName());
			throw new ServiceExcetion(CommonCode.SERVICE_ERROR, "您没有权限添加部门："+deptCriteria.getName());
		}
		SysDept dept = new SysDept();
		BeanUtils.copyProperties(deptCriteria, dept);
		if (null != parentDept) {
			dept.setLevel(parentDept.getLevel() + ".1");
		}else {
			dept.setLevel("0");
		}
		dept.setOperator(cUser.getUsername());
		sysDeptMapper.insertSelective(dept);
	}
	
	private boolean deptOprValidate(Integer userDeptId, Integer deptId) {
		if (userDeptId == null) {
			return false;
		}
		if (userDeptId == 0) {
			return true;
		}
		List<SysDept> depts = getDeptAndChildrenById(userDeptId);
		if (depts == null || depts.isEmpty()) {
			return false;
		}
		List<Integer> deptIdList = new ArrayList<Integer>();
		for (SysDept d : depts) {
			if (d.getId() != null) {
				deptIdList.add(d.getId());
			}
		}
		if (deptIdList.isEmpty() || !deptIdList.contains(deptId)) {
			return false;
		}
		return true;
	}

}
