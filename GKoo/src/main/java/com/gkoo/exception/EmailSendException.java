package com.gkoo.exception;

public class EmailSendException extends RuntimeException {
    private static final long serialVersionUID = 1263056848329657698L;

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
