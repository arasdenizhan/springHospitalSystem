package com.denzhn.repo.commons.exceptions;

public abstract class SystemException extends RuntimeException {
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
