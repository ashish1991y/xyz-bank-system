package com.xyzbank.metadataservice.controller;

import com.xyzbank.metadataservice.dto.request.RoleRequest;
import com.xyzbank.metadataservice.dto.response.GenericResponse;
import com.xyzbank.metadataservice.dto.response.RoleResponse;
import com.xyzbank.metadataservice.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<GenericResponse<RoleResponse>> createRole(@RequestBody @Valid RoleRequest roleRequest) {
        return ResponseEntity.ok().body(roleService.save(roleRequest));
    }
}
