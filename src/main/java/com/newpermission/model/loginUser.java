package com.newpermission.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class loginUser {

	private String username;
	
	private String telephone;
	
	private String password;
	
	private String validateCode;
	
	private String loginIP;
	
}
