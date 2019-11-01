package com.yuki.exception.deal.exception;

/**
 * 错误返回内容
 *
 */
public class ErrorResponseEntity {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
