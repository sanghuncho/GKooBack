package com.gkoo.exception;

public class CustomerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
