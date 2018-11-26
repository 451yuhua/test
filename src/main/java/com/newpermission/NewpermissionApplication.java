package com.newpermission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.newpermission.dao")
public class NewpermissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewpermissionApplication.class, args);
	}
}
