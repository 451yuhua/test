package com.newpermission.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newpermission.constant.CurrentUser;
import com.newpermission.interceptor.annotation.CurrentUserAnnotation;
import com.newpermission.pojo.criteria.SysAclCriteria;
import com.newpermission.pojo.result.Result;
import com.newpermission.pojo.result.ResultGenerator;
import com.newpermission.service.SysAclService;
import com.newpermission.utils.IpUtils;

@RestController("/sys/acl")
public class SysAclController {

	@Autowired
	private SysAclService sysAclService;
	
	@PostMapping("/addAcl")
	public Result<?> addAcl(SysAclCriteria aclCriteria, @CurrentUserAnnotation CurrentUser cUser, HttpServletRequest request) {
		String ip = null;
		if (aclCriteria != null && aclCriteria.getIp() == null) {
			ip = IpUtils.getIpAddress(request);
			if (StringUtils.isEmpty(ip)) {
				ip = "127.0.0.1";
			}
			aclCriteria.setIp(ip);
		}
		return ResultGenerator.genSuccessResult();
	}
}
