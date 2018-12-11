package com.newpermission.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.newpermission.pojo.result.Result;
import com.newpermission.pojo.result.ResultGenerator;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalException {

	@ExceptionHandler(ServiceExcetion.class)
	public Result<?> handleServiceException(HttpServletRequest request, ServiceExcetion sExcetion) {
		log.info(sExcetion.getMessage());
		return ResultGenerator.genFailResult(sExcetion.getCode());
	}
}
