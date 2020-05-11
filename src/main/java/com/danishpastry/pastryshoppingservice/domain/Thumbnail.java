package com.danishpastry.pastryshoppingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Thumbnail {
    @Id
    private String id;
    private String name;
    private LocalDateTime creationDate;
    private String originalUrl;
    private Map<String, String> thumbnails;
    private boolean isDeleted;
}
