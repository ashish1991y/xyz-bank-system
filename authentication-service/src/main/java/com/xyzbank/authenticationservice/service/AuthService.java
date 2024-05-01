package com.xyzbank.authenticationservice.service;

import com.xyzbank.authenticationservice.dto.request.AuthRequest;
import com.xyzbank.authenticationservice.dto.response.AuthResponse;
import com.xyzbank.authenticationservice.dto.response.GenericResponse;
import com.xyzbank.authenticationservice.dto.response.LoginResponse;

public interface AuthService{
    public GenericResponse<AuthResponse> generateToken(AuthRequest authRequest);
}
