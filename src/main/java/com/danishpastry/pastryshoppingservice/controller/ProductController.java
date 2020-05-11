package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.domain.ProductRequest;
import com.danishpastry.pastryshoppingservice.service.ProductService;
import com.danishpastry.pastryshoppingservice.service.dto.ProductAvailabilityRequest;
import com.danishpastry.pastryshoppingservice.service.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/api/user/product/{id}")
    public ProductResponse get(@PathVariable("id") String id) {
        return service.getProduct(id);
    }

    @GetMapping("/api/user/product/{index}/{size}")
    public List<ProductResponse> getAll(@PathVariable("index") int index, @PathVariable("size") int size) {
        return service.getAllProduct(PageRequest.of(index, size));
    }

    @GetMapping("/api/user/product/{category}/{index}/{size}")
    public List<ProductResponse> getAllByCategory(@PathVariable("category") String category,
                                                  @PathVariable("index") int index,
                                                  @PathVariable("size") int size) {
        return service.getAllProductByCategory(category, PageRequest.of(index, size));
    }

    @DeleteMapping("/api/user/product/{id}")
    public void delete(@PathVariable("id") String id) {
        service.deleteProductById(id);
    }

    @PostMapping("/api/user/product")
    public ProductResponse add(@RequestBody ProductRequest request) {
        return service.create(request);
    }

    @PutMapping("/api/user/product")
    public void updateByAvailability(@RequestBody ProductAvailabilityRequest request) {
        service.updateAvailability(request);
    }

}
