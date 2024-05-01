package com.xyzbank.eob.service;

import com.xyzbank.eob.dto.ValidationDTO;
import com.xyzbank.eob.dto.request.UserRequest;

public interface ValidationService {

    ValidationDTO<UserRequest> validate(UserRequest requestData);
}