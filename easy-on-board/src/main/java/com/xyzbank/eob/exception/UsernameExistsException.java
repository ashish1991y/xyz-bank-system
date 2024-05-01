package com.xyzbank.eob.exception;

import org.springframework.http.HttpStatus;

public class UsernameExistsException extends BusinessException{
    final static String message = "Username already Exists";
    public UsernameExistsException(String reason) {
        super(message, HttpStatus.BAD_REQUEST.value(), reason);
    }
}
