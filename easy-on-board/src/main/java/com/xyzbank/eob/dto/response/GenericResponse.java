package com.xyzbank.eob.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T extends IResponse> {
    private List<T> data;
    private GlobalErrorResponse error;
    private int statusCode;
    private Map<String, String> validationErrors;
}
