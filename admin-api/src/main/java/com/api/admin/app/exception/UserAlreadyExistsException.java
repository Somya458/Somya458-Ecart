package com.api.admin.app.exception;

import lombok.Getter;

public class UserAlreadyExistsException extends RuntimeException {

    private final int errorCode;

    public UserAlreadyExistsException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}




