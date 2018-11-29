package com.newpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newpermission.constant.CurrentUser;
import com.newpermission.service.CommonService;
import com.newpermission.service.SysUserService;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private SysUserService sysUserService;
	
	public ResponseEntity<CurrentUser> login(){
		CurrentUser cUser = new CurrentUser();
		return new ResponseEntity<CurrentUser>(cUser ,HttpStatus.OK);
	}
}