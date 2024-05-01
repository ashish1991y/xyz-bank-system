package com.xyzbank.eob.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class UnsupportedFileTypeException extends BusinessException {
    final static String message = "Unsupported file type";
    public UnsupportedFileTypeException(String reason) {
        super(message, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), reason);
    }
}
