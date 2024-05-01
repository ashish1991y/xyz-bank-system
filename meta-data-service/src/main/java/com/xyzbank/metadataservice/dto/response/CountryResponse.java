package com.xyzbank.metadataservice.dto.response;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CountryResponse implements IResponse {
    private String name;
    private String countryCode;
    private String status;
}
