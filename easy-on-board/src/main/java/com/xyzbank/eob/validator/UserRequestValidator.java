package com.xyzbank.eob.validator;

import com.xyzbank.eob.GlobalConstants;
import com.xyzbank.eob.dto.request.UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest request = (UserRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "value.notnull", "Name should not be null or empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "value.notnull", "Address should not be null or empty");


        if (request.getDob() != null && request.getDob().isEmpty()) {
            errors.rejectValue("dob", "value.notnull", "Date Of Birth should not be null or empty");
        } else if (request.getDob() != null && !request.getDob().matches(GlobalConstants.DOB_REGEX)) {
            errors.rejectValue("dob", "pattern.invalid", "Enter Date Of Birth in given Format:(DD/MM/YYYY)");
        } else if (request.getDob() != null && (request.getDob().length() != 10)) {
            errors.rejectValue("dob", "size.invalid", "Invalid Date Of Birth");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "value.notnull", "UserName should not be null or empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "value.notnull", "Country should not be null or empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentType", "value.notnull", "DocumentType should not be null or empty");

    }
}