package com.xyzbank.metadataservice.service.impl;

import com.xyzbank.metadataservice.configuration.AppConfiguration;
import com.xyzbank.metadataservice.constants.Status;
import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.dto.request.RoleRequest;
import com.xyzbank.metadataservice.dto.response.RoleResponse;
import com.xyzbank.metadataservice.exception.DataAlreadyExist;
import com.xyzbank.metadataservice.model.Role;
import com.xyzbank.metadataservice.repository.RoleRepository;
import com.xyzbank.metadataservice.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    private final AppConfiguration appConfiguration;
    @Override
    public GenericResponse<RoleResponse> save(RoleRequest roleRequest) {
        if (roleRepository.existsByName(roleRequest.getName())) {
            throw new DataAlreadyExist(HttpStatus.SC_BAD_REQUEST, "Role already Exist");
        }
        Role role = new Role(roleRequest.getName());
        role.setStatus(appConfiguration.getStatusMasterMap().get(Status.ACTIVE.toString()));
        roleRepository.save(role);
        RoleResponse roleResponse = RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .status(role.getStatus().getName())
                .build();

        return GenericResponse.
                <RoleResponse>builder()
                .data(Arrays.asList(roleResponse))
                .statusCode(HttpStatus.SC_CREATED)
                .build();
    }
}
