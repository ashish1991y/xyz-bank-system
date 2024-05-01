package com.xyzbank.metadataservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataAlreadyExist extends RepositoryException {
    final static String message = "Duplicate Data";
    public DataAlreadyExist(Integer statusCode, String reason) {
        super(message, statusCode, reason);
    }
}
