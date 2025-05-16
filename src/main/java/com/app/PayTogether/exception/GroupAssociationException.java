package com.app.PayTogether.exception;

public class GroupAssociationException extends RuntimeException {
    public GroupAssociationException(String message) {
        super(message);
    }

    public GroupAssociationException(String message, Throwable cause) {
        super(message, cause);
    }
}
