package com.newpermission;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan("com.newpermission.dao")
@ServletComponentScan
public class NewpermissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewpermissionApplication.class, args);
	}
	
}
