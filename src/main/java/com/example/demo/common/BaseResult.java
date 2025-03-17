package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResult<T> {

    private Integer status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResult() {
    }

    private BaseResult(IErrorCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(), data);
    }

    private BaseResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResult<T> ok() {
        return BaseResult.ok(null);
    }

    public static <T> BaseResult<T> ok(T data) {
        return new BaseResult<>(ResultCode.SUCCESS, data);
    }

    public static <T> BaseResult<T> fail(String message) {
        return fail(ResultCode.FAILURE.getCode(), message);
    }

    public static <T> BaseResult<T> fail(IErrorCode resultCode) {
        return fail(resultCode.getCode(), resultCode.getMessage());
    }

    public static <T> BaseResult<T> fail(Integer code, String message) {
        return new BaseResult<>(code, message, null);
    }

    public static <T> BaseResult<T> error() {
        return new BaseResult<>(ResultCode.ERROR, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
