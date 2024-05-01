package com.xyzbank.authenticationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthResponse implements IResponse{

    private static final long serialVersionUID = 8317676219297719109L;
    private String jwtToken;
}
