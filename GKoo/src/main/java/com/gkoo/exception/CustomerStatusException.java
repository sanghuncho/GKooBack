package com.gkoo.exception;

public class CustomerStatusException extends RuntimeException {
    public CustomerStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
