package com.xyzbank.eob.dto.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserRegisterResponse implements IResponse {
    private String userName;
    private String password;
}
