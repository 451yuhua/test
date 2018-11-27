package com.newpermission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.newpermission.pojo.SysUser;
import com.newpermission.service.CommonService;
import com.newpermission.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewpermissionApplicationTests {

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private CommonService commonService;
	
	@Test
	public void contextLoads() {
		System.out.println(commonService.getCurrentUser("6670B63DD85ACEC9C5468D46C01F44A5A607401A").getUsername());
//		SysUser user = userService.findByUsername("Admin");
		/*if(null != user) {
			System.out.println("Admin的邮箱为："+user.getMail());
		}*/
	}

}
