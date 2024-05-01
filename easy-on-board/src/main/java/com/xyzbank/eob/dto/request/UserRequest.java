package com.xyzbank.eob.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import static com.xyzbank.eob.GlobalConstants.DOB_REGEX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@ToString
public class UserRequest {

    @NotEmpty(message = "Name should not be null or empty")
    @Size(min = 1, max = 50, message = "Invalid Name length")
    private String name;

    @NotEmpty(message = "Address should not be null or empty")
    @Size(min = 1, max = 200, message = "Invalid Address length")
    private String address;

    @NotEmpty(message = "Date Of Birth should not be null or empty")
    @Pattern(regexp = DOB_REGEX,message = "Enter Date Of Birth in given Format:(DD/MM/YYYY)")
    @Size(min = 10, max = 10, message = "Invalid Date Of Birth")
    private String dob;

    @NotNull(message = "User name should not be null or empty")
    @Size(min = 5, max = 20, message = "User name length should be length of 5 to 10")
    private String username;

    @NotNull(message = "Country should not be null or empty")
    private String country;

    @NotNull(message = "Document Type should not be null or empty")
    private String documentType;
}
