package com.danishpastry.pastryshoppingservice.service.impl;

import com.danishpastry.pastryshoppingservice.service.StorageService;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

@Service
public class GoogleDriveStorageService implements StorageService {

    private String folderID;

    private Drive drive;

    @Autowired
    public GoogleDriveStorageService(Drive drive) {
        this.drive = drive;
    }

    @Override

    public String upload(java.io.File file) {
        try {
            return tryUploadFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String tryUploadFile(java.io.File input) throws Exception {
        String mimeType = Files.probeContentType(Paths.get(input.getAbsolutePath()));
        File fileMetadata = new File();
        fileMetadata.setMimeType(mimeType);
        fileMetadata.setName(input.getName());
        fileMetadata.setParents(Collections.singletonList(folderID));
        com.google.api.client.http.FileContent fileContent = new FileContent(mimeType, input);
        File file = drive.files().create(fileMetadata, fileContent)
                .setFields("id,webContentLink,webViewLink").execute();
        return file.getWebContentLink();
    }

    @Value("${google.folder-id}")
    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }
}
