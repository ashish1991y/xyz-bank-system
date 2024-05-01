package com.xyzbank.metadataservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyzbank.metadataservice.dto.request.CountryRequest;
import com.xyzbank.metadataservice.dto.response.CountryResponse;
import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.service.CountryService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CountryController countryController;

    @Test
    void testCreateCountry() throws IOException {
        CountryResponse countryResponse = new CountryResponse("Germany", "DE","active");
        GenericResponse<CountryResponse>  response= GenericResponse.<CountryResponse>builder()
                .data(Arrays.asList(countryResponse))
                .statusCode(org.apache.http.HttpStatus.SC_OK)
                .build();
        Mockito.when(countryService.save(any(CountryRequest.class)))
                .thenReturn(response);

        CountryRequest request = new CountryRequest("Germany","DE");
        // Act
        ResponseEntity<GenericResponse<CountryResponse>> responseEntity = countryController.createCountry(request);

        // Assert
        assertEquals(org.apache.http.HttpStatus.SC_OK, responseEntity.getStatusCode().value());
        assertEquals("DE", responseEntity.getBody().getData().get(0).getCountryCode());
        assertEquals("Germany", responseEntity.getBody().getData().get(0).getName());
    }
}