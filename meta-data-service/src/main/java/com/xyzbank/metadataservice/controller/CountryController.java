package com.xyzbank.metadataservice.controller;

import com.xyzbank.metadataservice.dto.request.CountryRequest;
import com.xyzbank.metadataservice.dto.response.CountryResponse;
import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/country")
@Slf4j
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<GenericResponse<CountryResponse>> createCountry(@RequestBody @Valid CountryRequest countryRequest) {
        return ResponseEntity.ok().body(countryService.save(countryRequest));
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<CountryResponse>> getAllCountry() {
        return ResponseEntity.ok().body(countryService.findAll());
    }
}
