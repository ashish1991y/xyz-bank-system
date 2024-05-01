package com.xyzbank.eob.service.impl;

import com.xyzbank.eob.configuration.UserServiceProperties;
import com.xyzbank.eob.exception.BusinessException;
import com.xyzbank.eob.model.User;
import com.xyzbank.eob.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final UserServiceProperties userServiceProperties;

    @Override
    public void upload(MultipartFile file, User savedUser) {
        if (file.isEmpty()) {
            throw new BusinessException("File upload error",
                    HttpStatus.BAD_REQUEST.value(), "Cannot upload an empty file.");
        }
        String extension = "";
        String originalFilename = file.getOriginalFilename();
        int fileNameLastIndex = originalFilename.lastIndexOf('.');
        if (fileNameLastIndex > 0) {
            extension = originalFilename.substring(fileNameLastIndex + 1);
        }
        if (!userServiceProperties.getAllowedExtensions().contains(extension)) {
            throw new BusinessException("Invalid file error", HttpStatus.BAD_REQUEST.value(),
                    "Selected file type is not supported," + " allowed values are "
                            + userServiceProperties.getAllowedExtensions().stream()
                            .collect(Collectors.joining(",")));
        }
        try {
            String targetPath = userServiceProperties.getUploadDir() + savedUser.getId() + "//documents//";
            File directory = new File(targetPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            Path root = Paths.get(targetPath);
            Files.copy(file.getInputStream(), root.resolve(originalFilename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new BusinessException("", HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}