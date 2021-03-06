package com.newpermission.pojo.result;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 *
 * Created
 */
public class Result<T> {
    /**
     * 业务处理的状态代码
     */
    private int code;

    /**
     * 业务处理的状态提示信息
     */
    private String message;

    /**
     * 业务处理的返回状态
     */
    private T data;

    public Result<T> setCode(Code code) {
        this.code = code.getCode();
        this.message = code.getMessage();
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
