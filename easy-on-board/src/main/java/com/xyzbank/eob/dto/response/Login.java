package com.xyzbank.eob.dto.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login implements IResponse {

    private static final long serialVersionUID = 2925363405195082909L;

    private String message;
}
