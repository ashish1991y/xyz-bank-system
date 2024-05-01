package com.xyzbank.eob.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "easy-on-board")
@Getter
@Setter
public class UserServiceProperties {
    private String uploadDir;
    private List<String> allowedExtensions;
}
