package com.danishpastry.pastryshoppingservice.util;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
public class MultipartFileUtil {
    private static final String PREFIX = "temp_";
    private MultipartFile multipartFile;

    public File toFile() {
        try {
            return tryConvertToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File tryConvertToFile() throws IOException {
        File file = getFile();
        this.multipartFile.transferTo(file);
        return file;
    }

    private File getFile() throws IOException {
        return Files.createTempFile(PREFIX, getFormat()).toFile();
    }

    private String getFormat() {
        String name = this.multipartFile.getName();
        int index = name.lastIndexOf(".");
        return name.substring(index);
    }

}
