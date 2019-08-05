package com.practice.base;

import lombok.Data;

/**
 * 结果封装
 *
 * @author kexin.ding
 */
@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;

    private Result() {
        throw new IllegalStateException("Result error!");
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return result(ResponseCode.SUCCESS, data);
    }

    public static <T> Result<T> fail(T data) {
        return result(ResponseCode.FAIL, data);
    }

    public static <T> Result<T> error(T data) {
        return result(ResponseCode.INTERNAL_SERVER_ERROR, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResponseCode.SUCCESS.code(), message, data);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<>(ResponseCode.FAIL.code(), message, data);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<>(ResponseCode.INTERNAL_SERVER_ERROR.code(), message, data);
    }

    public static <T> Result<T> result(ResponseCode resultCode, T data) {
        return new Result<>(resultCode.code(), resultCode.message(), data);
    }

}
