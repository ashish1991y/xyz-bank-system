package com.xyzbank.authenticationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse implements IResponse{

    private static final long serialVersionUID = 8317676219297719109L;
    private String status;
}
