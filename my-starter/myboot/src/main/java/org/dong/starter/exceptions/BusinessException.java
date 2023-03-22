package org.dong.starter.exceptions;

public class BusinessException  extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
