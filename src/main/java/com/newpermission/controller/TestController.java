package com.newpermission.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
