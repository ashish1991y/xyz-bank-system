package com.xyzbank.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GatewayException extends RuntimeException{
    private String message;
    private Integer statusCode;
    private String reason;
}
