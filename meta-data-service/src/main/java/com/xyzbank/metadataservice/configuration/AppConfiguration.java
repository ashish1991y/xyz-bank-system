package com.xyzbank.metadataservice.configuration;

import com.xyzbank.metadataservice.dto.response.StatusMasterRepository;
import com.xyzbank.metadataservice.model.StatusMaster;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
    private final StatusMasterRepository statusMasterRepository;
    private Map<String, StatusMaster> statusMasterMap;

    @PostConstruct
    public void init() {
        statusMasterMap = statusMasterRepository.findAll()
                .stream()
                .collect(Collectors.toMap(StatusMaster::getName, obj -> obj));
    }
    public Map<String, StatusMaster> getStatusMasterMap() {
        return statusMasterMap;
    }
}
