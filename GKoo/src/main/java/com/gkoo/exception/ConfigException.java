package com.gkoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConfigException extends RuntimeException {

    private static final long serialVersionUID = 4154446088588687740L;

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
