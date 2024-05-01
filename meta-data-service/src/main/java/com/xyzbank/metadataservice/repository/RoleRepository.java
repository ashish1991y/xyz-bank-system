package com.xyzbank.metadataservice.repository;

import com.xyzbank.metadataservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
}
