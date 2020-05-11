package com.danishpastry.pastryshoppingservice.repository;

import com.danishpastry.pastryshoppingservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findAllByCategory(String category, Pageable pageable);
}
