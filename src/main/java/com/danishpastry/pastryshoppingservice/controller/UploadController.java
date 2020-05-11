package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.service.UploadService;
import com.danishpastry.pastryshoppingservice.service.dto.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
public class UploadController {

    private UploadService service;

    @Autowired
    public UploadController(UploadService service) {
        this.service = service;
    }

    @Async
    @PostMapping("/api/user/upload")
    public CompletableFuture<UploadResult> upload(@RequestParam("file") MultipartFile multipartFile) {
        return CompletableFuture.completedFuture(service.upload(multipartFile));
    }
}
