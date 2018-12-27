package com.newpermission.dept;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.newpermission.constant.CurrentUser;
import com.newpermission.pojo.criteria.SysDeptCriteria;
import com.newpermission.service.SysDeptService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptTest {

	@Autowired
	private SysDeptService sysDeptService;
	
	@Test
	public void deptAddTest() {
		SysDeptCriteria deptCriteria = new SysDeptCriteria();
		CurrentUser cUser = new CurrentUser();
		cUser.setUsername("Admin");
		cUser.setDeptId(1);
		deptCriteria.setName("添加测试");
		deptCriteria.setOperateIp("127.0.0.1");
		deptCriteria.setRemark("remark");
		deptCriteria.setParentId(1);
		sysDeptService.addDept(deptCriteria, cUser);
	}
}
