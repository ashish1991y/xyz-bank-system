package com.xyzbank.authenticationservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidAccessException extends BusinessException{
    final static String message = "User don't have access";
    public InvalidAccessException(String reason) {
        super(message, HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), reason);
    }
}
