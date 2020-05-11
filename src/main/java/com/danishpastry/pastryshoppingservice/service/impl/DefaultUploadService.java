package com.danishpastry.pastryshoppingservice.service.impl;

import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import com.danishpastry.pastryshoppingservice.repository.ThumbnailRepository;
import com.danishpastry.pastryshoppingservice.service.StorageService;
import com.danishpastry.pastryshoppingservice.service.UploadService;
import com.danishpastry.pastryshoppingservice.service.dto.UploadResult;
import com.danishpastry.pastryshoppingservice.util.MultipartFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class DefaultUploadService implements UploadService {

    private StorageService storageService;
    private ThumbnailRepository repository;

    @Autowired
    public DefaultUploadService(StorageService storageService, ThumbnailRepository repository) {
        this.storageService = storageService;
        this.repository = repository;
    }

    @Override
    public UploadResult upload(MultipartFile multipartFile) {
        File file = new MultipartFileUtil(multipartFile).toFile();
        String url = storageService.upload(file);
        Thumbnail thumbnail = getThumbnail(file, url);
        Thumbnail result = repository.save(thumbnail);
        return UploadResult.of(result.getId());
    }

    private Thumbnail getThumbnail(File file, String url) {
        return Thumbnail.builder()
                .name(file.getName())
                .creationDate(LocalDateTime.now())
                .isDeleted(false)
                .originalUrl(url)
                .thumbnails(new HashMap<>())
                .build();
    }
}
