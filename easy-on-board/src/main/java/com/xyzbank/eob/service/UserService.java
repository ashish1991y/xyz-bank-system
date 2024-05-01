package com.xyzbank.eob.service;

import com.xyzbank.eob.dto.response.UserRegisterResponse;
import com.xyzbank.eob.dto.request.UserRequest;
import com.xyzbank.eob.dto.response.GenericResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    GenericResponse<UserRegisterResponse> register(UserRequest customerRequest, MultipartFile document);
}
