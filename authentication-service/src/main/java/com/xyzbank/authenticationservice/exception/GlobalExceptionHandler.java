package com.xyzbank.authenticationservice.exception;

import com.xyzbank.authenticationservice.dto.response.GenericResponse;
import com.xyzbank.authenticationservice.dto.response.GlobalErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RepositoryException.class)
    public ResponseEntity<GenericResponse<GlobalErrorResponse>> handleRepositoryException(RepositoryException repositoryException) {

        GenericResponse<GlobalErrorResponse> build = GenericResponse.<GlobalErrorResponse>builder()
                .error(GlobalErrorResponse.builder()
                        .message(repositoryException.getMessage())
                        .statusCode(repositoryException.getStatusCode())
                        .reason(repositoryException.getReason()).build())
                .statusCode(repositoryException.getStatusCode())
                .build();

        return ResponseEntity.ok(build);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<GenericResponse<GlobalErrorResponse>> handleBusinessException(BusinessException exception) {

        GenericResponse<GlobalErrorResponse> build = GenericResponse.<GlobalErrorResponse>builder()
                .error(GlobalErrorResponse.builder()
                        .message(exception.getMessage())
                        .statusCode(exception.getStatusCode())
                        .reason(exception.getReason()).build())
                .statusCode(exception.getStatusCode())
                .build();

        return ResponseEntity.ok(build);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(),
                HttpStatus.OK
        );
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
