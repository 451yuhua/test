package com.newpermission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newpermission.pojo.SysDept;
import com.newpermission.service.SysDeptService;
import com.newpermission.utils.EntityFillUtil;

@RequestMapping("/dept")
@RestController
@CrossOrigin
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
	@GetMapping("/getDepts")
	public List<Map<String, Object>> getDepts() {
		List<SysDept> depts = sysDeptService.findAll();
		return EntityFillUtil.vueTreeFormatCommonUtil(depts, "name", "parentId", 0, "id");
	}
}
