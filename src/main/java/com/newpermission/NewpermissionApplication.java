package com.newpermission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.newpermission.filter.WebFilter;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.newpermission.dao")
@ServletComponentScan
public class NewpermissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewpermissionApplication.class, args);
	}
	
}
