package com.danishpastry.pastryshoppingservice.service;

import com.danishpastry.pastryshoppingservice.domain.ProductRequest;
import com.danishpastry.pastryshoppingservice.service.dto.ProductAvailabilityRequest;
import com.danishpastry.pastryshoppingservice.service.dto.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse getProduct(String id);

    List<ProductResponse> getAllProduct(Pageable pageable);

    List<ProductResponse> getAllProductByCategory(String category, Pageable pageable);

    void deleteProductById(String id);

    void updateAvailability(ProductAvailabilityRequest request);

    ProductResponse create(ProductRequest request);
}
