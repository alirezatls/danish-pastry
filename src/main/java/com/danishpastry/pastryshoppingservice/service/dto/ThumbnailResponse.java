package com.danishpastry.pastryshoppingservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailResponse {
    private String id;
    private String name;
    private String originalUrl;
    private Map<String, String> thumbnails;
}
