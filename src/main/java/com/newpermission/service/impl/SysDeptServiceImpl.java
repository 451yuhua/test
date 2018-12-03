package com.newpermission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newpermission.dao.SysDeptMapper;
import com.newpermission.pojo.SysDept;
import com.newpermission.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	
	@Autowired
	private SysDeptMapper sysDeptMapper;
	
	@Override
	public List<SysDept> findAll() {
		return sysDeptMapper.selectAll();
	}

}
