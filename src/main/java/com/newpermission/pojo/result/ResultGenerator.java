package com.newpermission.pojo.result;

/**
 * 响应结果生成工具
 *
 * Created
 */
public class ResultGenerator {

    public static <T> Result<T> genSuccessResult() {
        return genSuccessResult(CommonCode.SUCCESS);
    }

    public static <T> Result<T> genSuccessResult(Code code) {
        return new Result<T>().setCode(code);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        Result<T> result =  genSuccessResult();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> genSuccessResult(Code code, T data) {
        Result<T> result =  genSuccessResult(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> genSuccessResult(Code code, String message, T data) {
        Result<T> result =  genSuccessResult(code);
        result.setMessage(message).setData(data);
        return result;
    }

    public static <T> Result<T> genFailResult(Code code, T data) {
        Result<T> result = genFailResult(code);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> genFailResult(Code code, String message, T data) {
        Result<T> result = genFailResult(code);
        result.setData(data).setMessage(message);
        return result;
    }

    public static <T> Result<T> genFailResult(Code code) {
        return new Result<T>().setCode(code);
    }
}
