package com.isolaja.mobileapp.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = -5754799793907191744L;

    public UserServiceException(String message) {
        super(message);
    }
}
