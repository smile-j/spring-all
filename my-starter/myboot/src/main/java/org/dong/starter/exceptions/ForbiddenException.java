package org.dong.starter.exceptions;

//自定义异常
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
