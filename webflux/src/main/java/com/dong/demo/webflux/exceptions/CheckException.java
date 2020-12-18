package com.dong.demo.webflux.exceptions;

import lombok.Data;

@Data
public class CheckException  extends RuntimeException{

    private String filedName;
    private String filedValue;


    public CheckException() {
    }

    public CheckException(String filedName, String filedValue) {
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
