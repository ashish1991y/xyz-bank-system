package com.xyzbank.eob.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalErrorResponse implements IResponse {
    private String message;
    private Integer statusCode;
    private String reason;
}
