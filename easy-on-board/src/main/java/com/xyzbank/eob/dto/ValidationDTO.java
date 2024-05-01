package com.xyzbank.eob.dto;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationDTO<T> {
    T requestDTO;

    private Map<String, String> validationErrors;

}