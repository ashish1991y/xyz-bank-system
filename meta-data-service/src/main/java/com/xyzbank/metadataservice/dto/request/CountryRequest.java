package com.xyzbank.metadataservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {
    @NotEmpty(message = "Country name should not be null or empty")
    @Size(min = 1, max = 50, message = "Invalid Country name length")
    private String name;

    @NotEmpty(message = "Country code should not be null or empty")
    @Size(min = 1, max = 5, message = "Invalid Country name length")
    private String countryCode;
}
