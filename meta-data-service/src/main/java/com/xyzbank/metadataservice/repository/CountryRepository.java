package com.xyzbank.metadataservice.repository;

import com.xyzbank.metadataservice.model.Country;
import com.xyzbank.metadataservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsByName(String name);
}
