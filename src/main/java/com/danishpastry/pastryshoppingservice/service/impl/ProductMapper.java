package com.danishpastry.pastryshoppingservice.service.impl;

import com.danishpastry.pastryshoppingservice.domain.Product;
import com.danishpastry.pastryshoppingservice.domain.ProductRequest;
import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import com.danishpastry.pastryshoppingservice.service.dto.ProductResponse;
import com.danishpastry.pastryshoppingservice.service.dto.ThumbnailResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product p) {
        ProductResponse res = new ProductResponse();
        res.setId(p.getId());
        res.setName(p.getName());
        res.setDescription(p.getDescription());
        res.setPrice(p.getPrice());
        res.setCategory(p.getCategory());
        res.setThumbnailResponse(mapToThumbnailResponse(p.getThumbnail()));
        res.setAvailable(p.isAvailable());
        return res;
    }

    public List<ProductResponse> toListOFProductResponse(List<Product> products) {
        return products.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

    public Product mapToProduct(ProductRequest request) {
        Product p = new Product();
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setPrice(request.getPrice());
        p.setCategory(request.getCategory());
        p.setCreationDate(LocalDateTime.now());
        p.setDeleted(false);
        p.setAvailable(true);
        return p;
    }

    private ThumbnailResponse mapToThumbnailResponse(Thumbnail thumbnail) {
        ThumbnailResponse res = new ThumbnailResponse();
        res.setId(thumbnail.getId());
        res.setName(thumbnail.getName());
        res.setOriginalUrl(thumbnail.getOriginalUrl());
        res.setThumbnails(thumbnail.getThumbnails());
        return res;
    }
}
