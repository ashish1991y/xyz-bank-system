package com.xyzbank.metadataservice.service.impl;
import com.xyzbank.metadataservice.configuration.AppConfiguration;
import com.xyzbank.metadataservice.constants.Status;
import com.xyzbank.metadataservice.dto.request.CountryRequest;
import com.xyzbank.metadataservice.dto.response.CountryResponse;
import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.exception.DataAlreadyExist;
import com.xyzbank.metadataservice.model.Country;
import com.xyzbank.metadataservice.repository.CountryRepository;
import com.xyzbank.metadataservice.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final AppConfiguration appConfiguration;

    @Override
    public GenericResponse<CountryResponse> save(CountryRequest countryRequest) {
        validateCountryUniqueness(countryRequest.getName());
        Country savedCountry = saveCountry(countryRequest);
        return buildCountryResponse(savedCountry);
    }

    private void validateCountryUniqueness(String countryName) {
        if (countryRepository.existsByName(countryName)) {
            throw new DataAlreadyExist(HttpStatus.SC_BAD_REQUEST, "Country already Exist");
        }
    }

    private Country saveCountry(CountryRequest countryRequest) {
        Country country = new Country(
                countryRequest.getName(),
                countryRequest.getCountryCode());
        country.setStatus(appConfiguration.getStatusMasterMap().get(Status.ACTIVE.toString()));
        return countryRepository.save(country);
    }

    private GenericResponse<CountryResponse> buildCountryResponse(Country savedCountry) {
        CountryResponse response = new CountryResponse(
                savedCountry.getName(),
                savedCountry.getCountryCode(),
                savedCountry.getStatus().getName());

        return GenericResponse.<CountryResponse>builder()
                .data(Arrays.asList(response))
                .statusCode(HttpStatus.SC_CREATED)
                .build();
    }
    @Override
    public GenericResponse<CountryResponse> findAll() {
        List<Country> countries = countryRepository.findAll();
        List<CountryResponse> response = countries.stream().map(
                country -> new CountryResponse(
                        country.getName(),
                        country.getCountryCode(),
                        country.getStatus().getName()
                )).collect(Collectors.toList());

        return GenericResponse.<CountryResponse>builder()
                .data(response)
                .statusCode(HttpStatus.SC_CREATED)
                .build();
    }
}
