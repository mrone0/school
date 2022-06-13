package com.school.commons;

import lombok.Data;

/**
 * 统一响应结果集
 *
 */
@Data
public class Result<T> {

    //操作代码
    Integer code;

    //提示信息
    String message;

    //结果数据
    T data;
    //结果数据二
    T data2;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public Result(ResultCode resultCode,T data, T data2){
        this.code=resultCode.code();
        this.message=resultCode.message();
        this.data=data;
        this.data2=data2;
    }

    public Result(String message) {
        this.message = message;
    }

    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    public static <T> Result SUCCESS(T data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static <T> Result SUCCESS2(T data2) {
        return new Result(ResultCode.SUCCESS, data2);
    }


    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String message) {
        return new Result(message);
    }

}
