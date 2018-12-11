package com.newpermission.exception;

import com.newpermission.pojo.result.Code;

import lombok.Getter;

@Getter
public class ServiceExcetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4396516030736991238L;
	
	private Code code;
	
	public ServiceExcetion(Code code , String message) {
		super(message);
		this.code = code;
		this.code.setMessage(message);
	}
	
	public ServiceExcetion(Code code, Throwable cause) {
		super(cause);
		this.code = code;
	}
	
	public ServiceExcetion(Code code) {
		super(code.getMessage());
		this.code = code;
	}
}
