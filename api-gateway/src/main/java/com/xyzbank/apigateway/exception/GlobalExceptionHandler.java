package com.xyzbank.apigateway.exception;

import com.xyzbank.apigateway.dto.GenericResponse;
import com.xyzbank.apigateway.dto.GlobalErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = GatewayException.class)
    public ResponseEntity<GenericResponse<GlobalErrorResponse>> handleRepositoryException(GatewayException repositoryException) {

        GenericResponse<GlobalErrorResponse> build = GenericResponse.<GlobalErrorResponse>builder()
                .error(GlobalErrorResponse.builder()
                        .message(repositoryException.getMessage())
                        .statusCode(repositoryException.getStatusCode())
                        .reason(repositoryException.getReason()).build())
                .statusCode(repositoryException.getStatusCode())
                .build();

        return ResponseEntity.ok(build);
    }

}
