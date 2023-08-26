package com.upx.ticketsapi.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String action) {
        super("User does not have permission to " + action);
    }
}
