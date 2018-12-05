package com.newpermission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newpermission.pojo.SysDept;
import com.newpermission.pojo.result.Code;
import com.newpermission.pojo.result.CommonCode;
import com.newpermission.pojo.result.Result;
import com.newpermission.pojo.result.ResultGenerator;
import com.newpermission.service.SysDeptService;
import com.newpermission.utils.EntityFillUtil;

@RequestMapping("/dept")
@RestController
@CrossOrigin
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
	@GetMapping("/getDeptsTree")
	public Result<List<Map<String, Object>>> getDeptsTree() {
		List<SysDept> depts = sysDeptService.findAll();
		return ResultGenerator.genSuccessResult(EntityFillUtil.vueTreeFormatCommonUtil(depts, "name", "parentId", 0, "id"));
	}
	
	@GetMapping("/getDeptChildren/{id}")
	public List<Map<String, Object>> getDeptChildren(@PathVariable Integer id) {
		List<SysDept> depts = sysDeptService.getDeptAndChildrenById(id);
		List<SysDept> deptChildre = EntityFillUtil.getChildren(depts, SysDept.class, id, "parentId", "id");
		return EntityFillUtil.treeFormatByNode(deptChildre, SysDept.class, "name", "parentId", id, "id");
	}
	
	@GetMapping("/getDeptResult/{id}")
	public Result<List<Map<String, Object>>> getDeptResult(@PathVariable Integer id) {
		List<SysDept> depts = sysDeptService.getDeptAndChildrenById(id);
		List<SysDept> deptChildren = EntityFillUtil.getChildren(depts, SysDept.class, id, "parentId", "id");
		return ResultGenerator.genSuccessResult(EntityFillUtil.treeFormatByNode(deptChildren, SysDept.class, "name", "parentId", id, "id"));
	}
}
