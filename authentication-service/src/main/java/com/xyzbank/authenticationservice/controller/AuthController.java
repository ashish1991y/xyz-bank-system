package com.xyzbank.authenticationservice.controller;

import com.xyzbank.authenticationservice.dto.request.AuthRequest;
import com.xyzbank.authenticationservice.dto.response.AuthResponse;
import com.xyzbank.authenticationservice.dto.response.GenericResponse;
import com.xyzbank.authenticationservice.dto.response.LoginResponse;
import com.xyzbank.authenticationservice.service.AuthService;
import com.xyzbank.authenticationservice.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<GenericResponse<AuthResponse>> getToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok().body(service.generateToken(authRequest));
    }
}
