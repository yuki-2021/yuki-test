package com.yuki.exception.deal.exception;

/**
 * 自定义异常
 *
 */
public class YukiException extends RuntimeException{

    private int code;

    public YukiException() {
        super();
    }

    public YukiException(int code,String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
