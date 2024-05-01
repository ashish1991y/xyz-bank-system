package com.xyzbank.eob.service;

import com.xyzbank.eob.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void upload(MultipartFile file, User savedUser);
}
