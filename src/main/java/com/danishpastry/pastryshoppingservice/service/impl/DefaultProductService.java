package com.danishpastry.pastryshoppingservice.service.impl;

import com.danishpastry.pastryshoppingservice.domain.Product;
import com.danishpastry.pastryshoppingservice.domain.ProductRequest;
import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import com.danishpastry.pastryshoppingservice.repository.ProductRepository;
import com.danishpastry.pastryshoppingservice.repository.ThumbnailRepository;
import com.danishpastry.pastryshoppingservice.service.ProductService;
import com.danishpastry.pastryshoppingservice.service.dto.ProductAvailabilityRequest;
import com.danishpastry.pastryshoppingservice.service.dto.ProductResponse;
import com.danishpastry.pastryshoppingservice.service.exception.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    private ProductRepository repository;
    private ProductMapper mapper;
    private ThumbnailRepository thumbnailRepository;

    @Autowired
    public DefaultProductService(ProductRepository repository, ProductMapper mapper, ThumbnailRepository thumbnailRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.thumbnailRepository = thumbnailRepository;
    }

    @Override
    public ProductResponse getProduct(String id) {
        Product product = fetchProduct(id);
        return mapper.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProduct(Pageable pageable) {
        List<Product> products = repository.findAll(pageable).getContent();
        return mapper.toListOFProductResponse(products);
    }

    @Override
    public List<ProductResponse> getAllProductByCategory(String category, Pageable pageable) {
        List<Product> products = repository.findAllByCategory(category, pageable).getContent();
        return mapper.toListOFProductResponse(products);
    }

    @Override
    public void deleteProductById(String id) {
        Product product = fetchProduct(id);
        product.setDeleted(true);
        repository.save(product);
    }

    @Override
    public void updateAvailability(ProductAvailabilityRequest request) {
        Product product = fetchProduct(request.getId());
        product.setAvailable(request.isAvailable());
        repository.save(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Thumbnail thumbnail = thumbnailRepository.findById(request.getThumbnailId()).orElseThrow(RuntimeException::new);
        Product product = mapper.mapToProduct(request);
        product.setThumbnail(thumbnail);
        repository.save(product);
        return mapper.toProductResponse(product);
    }

    private Product fetchProduct(String id) {
        return repository.findById(id)
                .orElseThrow(NoSuchProductException::new);
    }
}
