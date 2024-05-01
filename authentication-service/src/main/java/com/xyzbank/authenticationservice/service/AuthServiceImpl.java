package com.xyzbank.authenticationservice.service;

import com.xyzbank.authenticationservice.dto.request.AuthRequest;
import com.xyzbank.authenticationservice.dto.response.AuthResponse;
import com.xyzbank.authenticationservice.dto.response.GenericResponse;
import com.xyzbank.authenticationservice.dto.response.LoginResponse;
import com.xyzbank.authenticationservice.exception.InvalidAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public GenericResponse<AuthResponse> generateToken(AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (!authenticate.isAuthenticated()) {
            throw new InvalidAccessException("invalid access");
        }
        String token = jwtService.generateToken(authRequest.getUsername());
        AuthResponse authResponse = new AuthResponse(token);
        return GenericResponse.<AuthResponse>builder()
                .data(Arrays.asList(authResponse))
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
