package com.xyzbank.metadataservice.service;

import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.dto.request.RoleRequest;
import com.xyzbank.metadataservice.dto.response.RoleResponse;
import com.xyzbank.metadataservice.model.Role;

import java.util.List;

public interface RoleService {
    GenericResponse<RoleResponse> save(RoleRequest roleRequest);
}
