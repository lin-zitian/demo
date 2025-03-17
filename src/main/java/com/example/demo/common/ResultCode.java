package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * Result Code Enum
 *
 * @author william
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IErrorCode{
    SUCCESS(HttpServletResponse.SC_OK, "Success"),

    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception"),

    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized"),

    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 Not Found"),

    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read"),

    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported"),

    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported"),

    REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "Request Rejected"),

    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),

    PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "Missing Required Parameter"),

    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch"),

    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Binding Error"),

    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error"),
    ERROR(500, "service is busy please try again later"),

    CHECK_FAILED(400, "parameter validation failed"),

    INTERNAL_SERVICE_EXCEPTION(600, "Internal Service Exception"),

    TOAST_EXCEPTION(450, "Try again later."),  //这个状态用于返回给h5或者app弹出toast

    ;

    final int status;

    final String message;

    @Override
    public Integer getCode() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
