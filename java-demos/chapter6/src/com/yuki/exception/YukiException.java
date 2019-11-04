package com.yuki.exception;

/**
 * 自定义异常
 *
 * @author yuki
 */
public class YukiException extends Exception {

    public YukiException() {
    }

    public YukiException(String message) {
        super(message);
    }

    public YukiException(String message, Throwable cause) {
        super(message, cause);
    }

    public YukiException(Throwable cause) {
        super(cause);
    }
}
