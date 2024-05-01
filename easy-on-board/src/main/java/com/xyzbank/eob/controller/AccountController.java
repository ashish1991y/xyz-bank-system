package com.xyzbank.eob.controller;

import com.xyzbank.eob.dto.response.AccountOverview;
import com.xyzbank.eob.dto.response.GenericResponse;
import com.xyzbank.eob.dto.response.Login;
import com.xyzbank.eob.service.AccountService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    @Value("${fallback.default.string}")
    private String fallbackValue;
    private final AccountService accountService;

    @GetMapping(value = "/overview")
    @RateLimiter(name = "userOverviewFallBack", fallbackMethod = "registerUserFallBack")
    public ResponseEntity<GenericResponse<AccountOverview>> userOverview(@RequestHeader("userName") String userName) {
        return ResponseEntity.ok().body(accountService.getUserAccountOverview(userName));
    }

    public ResponseEntity<GenericResponse<AccountOverview>> registerUserFallBack(RequestNotPermitted e) {
        AccountOverview accountOverview = new AccountOverview(fallbackValue, fallbackValue, new BigDecimal(0), fallbackValue);
        return ResponseEntity.ok().body(GenericResponse.<AccountOverview>builder()
                .data(Arrays.asList(accountOverview))
                .statusCode(HttpStatus.SC_TOO_MANY_REQUESTS)
                .build());
    }

    @GetMapping(value = "/login")
    public ResponseEntity<GenericResponse<Login>> login(@RequestHeader("userName") String userName) {
        Login login = new Login("Hello," + userName + " Successfully logged in");
        return ResponseEntity.ok().body(GenericResponse.<Login>builder()
                .data(Arrays.asList(login))
                .statusCode(HttpStatus.SC_OK)
                .build());
    }
}
