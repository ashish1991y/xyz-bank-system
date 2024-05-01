package com.xyzbank.eob.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyzbank.eob.dto.request.UserRequest;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.dto.response.UserRegisterResponse;
import com.xyzbank.eob.service.UserService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/onboarding")
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class UserController {
    private final UserService userService;
    @Value("${fallback.default.string}")
    private String fallbackValue;

    @PostMapping(value = "/register")
    @RateLimiter(name = "registerUserFallBack", fallbackMethod = "registerUserFallBack")
    public ResponseEntity<GenericResponse<UserRegisterResponse>> registerUser(@RequestParam("document") MultipartFile file,
                                                                              @RequestParam("userData") String userData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserRequest userRequest = mapper.readValue(userData, UserRequest.class);
        return ResponseEntity.ok().body(userService.register(userRequest, file));
    }

    public ResponseEntity<GenericResponse<UserRegisterResponse>> registerUserFallBack(RequestNotPermitted e) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse(fallbackValue, fallbackValue);
        return ResponseEntity.ok().body(GenericResponse.<UserRegisterResponse>builder()
                .data(Arrays.asList(userRegisterResponse))
                .statusCode(HttpStatus.SC_TOO_MANY_REQUESTS)
                .build());
    }
}
