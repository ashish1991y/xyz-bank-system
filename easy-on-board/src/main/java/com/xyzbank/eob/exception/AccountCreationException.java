package com.xyzbank.eob.exception;

import org.springframework.http.HttpStatus;

public class AccountCreationException extends BusinessException {
    final static String message = "Account creation not Possible";

    public AccountCreationException(String reason) {
        super(message, HttpStatus.BAD_REQUEST.value(), reason);
    }
}