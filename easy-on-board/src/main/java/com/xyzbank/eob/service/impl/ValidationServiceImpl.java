package com.xyzbank.eob.service.impl;

import com.xyzbank.eob.dto.ValidationDTO;
import com.xyzbank.eob.dto.request.UserRequest;
import com.xyzbank.eob.service.ValidationService;
import com.xyzbank.eob.validator.UserRequestValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {
    private final UserRequestValidator validator;

    public ValidationServiceImpl(UserRequestValidator validator) {
        this.validator = validator;
    }

    @Override
    public ValidationDTO<UserRequest> validate(UserRequest request) {
        Errors errors = new BeanPropertyBindingResult(request, "request");
        validator.validate(request, errors);
        Map<String, String> errorData = errors.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, error -> error.getDefaultMessage()));
        return new ValidationDTO<>(request, errorData);
    }
}