package com.newpermission;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.newpermission.dao.SysAclMapper;
import com.newpermission.dao.SysDeptAclMapper;
import com.newpermission.dao.SysDeptMapper;
import com.newpermission.dao.SysDeptUserMapper;
import com.newpermission.pojo.SysAcl;
import com.newpermission.pojo.SysDept;
import com.newpermission.pojo.SysDeptAcl;
import com.newpermission.pojo.SysDeptUser;
import com.newpermission.pojo.SysUser;
import com.newpermission.service.CommonService;
import com.newpermission.service.SysAclService;
import com.newpermission.service.SysUserService;
import com.newpermission.utils.EntityFillUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewpermissionApplicationTests {

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SysAclMapper sysAclMapper;
	
	@Autowired
	private SysAclService aclService;
	
	@Autowired
	private SysDeptMapper sysDeptMapper;
	
	@Autowired
	private SysDeptAclMapper sysDeptAclMapper;
	
	@Autowired 
	private SysDeptUserMapper sysDeptUserMapper;
	
	@Test
	public void contextLoads() {
//		System.out.println(commonService.getCurrentUser("6670B63DD85ACEC9C5468D46C01F44A5A607401A").getUsername());
//		SysUser user = userService.findByUsername("Admin");
//		if(null != user) {
//			System.out.println("Admin的邮箱为："+user.getMail());
//		}
		assertEquals("admin", userService.findByUsername("Admin").getMail());//断言测试
	}
	
	@Test
	public void urlsTest() {
		Set<String> urls = sysAclMapper.getDeptUrlsByUserId(1);
		for (String url : urls) {
			System.out.println(url);
		}
//		String url = aclService.getUrl();
//		System.out.println(url);
	}
	
	@Test
	public void deptAclTest() {
		SysDeptAcl sysDeptAcl = new SysDeptAcl();
		SysDept sysDept = new SysDept();
		SysDeptUser sysDeptUser = new SysDeptUser();
		sysDeptUser.setDeptId(3);
		sysDeptUser.setOperator("Admin");
		sysDeptUser.setUserId(2);
		int num = sysDeptUserMapper.insertSelective(sysDeptUser);
		System.out.println(num);
//		sysDept.setLevel("1");
//		sysDept.setName("海尔中国");
//		sysDept.setOperator("Admin");
//		sysDept.setParentId(0);
//		sysDeptMapper.insertSelective(sysDept);
//		sysDeptAcl.setAclId(1);
//		sysDeptAcl.setDeptId(1);
//		sysDeptAcl.setOperator("Admin");
//		sysDeptAcl.setOpertateIp("127.0.0.1");
//		sysDeptAclMapper.insertSelective(sysDeptAcl);
		
	}
	
	@Test
	public void deptTreeTest() {
		List<SysDept> depts = sysDeptMapper.selectAll();
//		List<SysDept> parentDepts = EntityFillUtil.getParentsAndChildren(depts, SysDept.class, "parentId", "id", 2);
//		List<Map<String, Object>> deptParentTree = EntityFillUtil.vueTreeFormatCommonUtil(parentDepts, "name", "parentId", 0, "id");
//		List<Map<String, Object>> deptTree = EntityFillUtil.vueTreeFormatCommonUtil(depts, "name", "parentId", 2, "id");
//		List<Map<String, Object>> resultList = EntityFillUtil.vueTreeFormatFromNodeUtil(parentDepts, "name", "parentId", 2, "id", deptTree);
//		String treeResult = JSON.toJSONString(deptTree);
//		String treeParentResult = JSON.toJSONString(deptParentTree);
		Long start = System.currentTimeMillis();
		List<Map<String, Object>> resultList = EntityFillUtil.treeFormatByNode(depts, SysDept.class, "name", "parentId", 2, "id");
		Long end = System.currentTimeMillis();
		String result = JSON.toJSONString(resultList);
//		System.out.println(treeParentResult);
//		System.out.println(treeResult);
		System.out.println(result);
		System.out.println(end - start);
	}

}
