package com.newpermission.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newpermission.pojo.result.Result;
import com.newpermission.pojo.result.ResultGenerator;
import com.newpermission.utils.IpUtils;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/api")
	public String testAPI(String param) {
		return param;
	}
	
	@RequestMapping("/noFilter")
	public String noFilter() {
		return "noTilter!";
	}
	
	@GetMapping("/getIp")
	public Result<?> ipTest(HttpServletRequest request) {
		String ipAddress = IpUtils.getIpAddress(request);
		return ResultGenerator.genSuccessResult(ipAddress);
	}
	
}
