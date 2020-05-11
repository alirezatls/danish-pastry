package com.danishpastry.pastryshoppingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private Thumbnail thumbnail;
    private LocalDateTime creationDate;
    private boolean isDeleted;
    private boolean isAvailable;

    public Product(String id) {
        this.id = id;
    }

}
