package com.xyzbank.metadataservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryException extends RuntimeException {
    private String message;
    private Integer statusCode;
    private String reason;
}
