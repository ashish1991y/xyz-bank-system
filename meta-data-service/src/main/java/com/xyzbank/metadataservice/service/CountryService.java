package com.xyzbank.metadataservice.service;

import com.xyzbank.metadataservice.dto.request.CountryRequest;
import com.xyzbank.metadataservice.dto.response.CountryResponse;
import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.model.Country;
import com.xyzbank.metadataservice.model.Role;

import java.util.List;

public interface CountryService {
    GenericResponse<CountryResponse> save(CountryRequest countryRequest);
    GenericResponse<CountryResponse> findAll();
}
