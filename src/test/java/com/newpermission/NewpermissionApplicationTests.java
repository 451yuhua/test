package com.newpermission;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.newpermission.dao.SysAclMapper;
import com.newpermission.pojo.SysUser;
import com.newpermission.service.CommonService;
import com.newpermission.service.SysAclService;
import com.newpermission.service.SysUserService;

import tk.mybatis.spring.annotation.MapperScan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewpermissionApplicationTests {

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private CommonService commonService;
	
//	@Autowired
//	private SysAclMapper sysAclMapper;
	
	@Autowired
	private SysAclService aclService;
	
	@Test
	public void contextLoads() {
//		System.out.println(commonService.getCurrentUser("6670B63DD85ACEC9C5468D46C01F44A5A607401A").getUsername());
		SysUser user = userService.findByUsername("Admin");
		if(null != user) {
			System.out.println("Admin的邮箱为："+user.getMail());
		}
	}
	
	@Test
	public void urlsTest() {
		/*Set<String> urls = sysAclMapper.selectUrlsByIds(4);
		for (String url : urls) {
			System.out.println(url);
		}*/
		String url = aclService.getUrl();
		System.out.println(url);
	}

}
