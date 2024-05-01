package com.xyzbank.eob.exception;

import org.springframework.http.HttpStatus;

public class UserRegistrationException extends BusinessException {
    final static String message = "User Registration not Possible";
    public UserRegistrationException(String reason) {
        super(message, HttpStatus.BAD_REQUEST.value(), reason);
    }
}
