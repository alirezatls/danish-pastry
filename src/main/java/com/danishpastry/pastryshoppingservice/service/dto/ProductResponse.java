package com.danishpastry.pastryshoppingservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private ThumbnailResponse thumbnailResponse;
    private boolean isAvailable;

    public ProductResponse(String id) {
        this.id = id;
    }
}
