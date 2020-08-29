package com.gkoo.exception;

public class AuctionServiceException extends RuntimeException {
    private static final long serialVersionUID = 4154446088588687740L;

    public AuctionServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
