package com.danishpastry.pastryshoppingservice.service;

import com.danishpastry.pastryshoppingservice.service.dto.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    UploadResult upload(MultipartFile multipartFile);
}
