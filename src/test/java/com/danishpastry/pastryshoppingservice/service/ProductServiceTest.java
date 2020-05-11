package com.danishpastry.pastryshoppingservice.service;

import com.danishpastry.pastryshoppingservice.domain.Product;
import com.danishpastry.pastryshoppingservice.domain.ProductRequest;
import com.danishpastry.pastryshoppingservice.domain.Thumbnail;
import com.danishpastry.pastryshoppingservice.repository.ProductRepository;
import com.danishpastry.pastryshoppingservice.repository.ThumbnailRepository;
import com.danishpastry.pastryshoppingservice.service.dto.ProductResponse;
import com.danishpastry.pastryshoppingservice.service.impl.DefaultProductService;
import com.danishpastry.pastryshoppingservice.service.impl.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductMapper mapper;
    @Mock
    private ThumbnailRepository thumbnailRepository;
    @InjectMocks
    private ProductService service = new DefaultProductService(repository, mapper, thumbnailRepository);

    @Test
    public void shouldGetProductById() {
        Product p = new Product();
        p.setId("1234");
        ProductResponse response = new ProductResponse();
        response.setId("1234");
        when(repository.findById(anyString())).thenReturn(Optional.of(p));
        when(mapper.toProductResponse(p)).thenReturn(response);

        ProductResponse product = service.getProduct("1234");

        assertThat(product.getId()).isNotNull();
        assertThat(product.getId()).isEqualTo("1234");
    }

    @Test
    public void shouldGetAllProduct() {
        List<Product> products = Arrays.asList(new Product("1"), new Product("2"));
        List<ProductResponse> responses = Arrays.asList(new ProductResponse("1"), new ProductResponse("2"));
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repository.findAll(pageRequest)).thenReturn(new PageImpl<>(products));
        when(mapper.toListOFProductResponse(products)).thenReturn(responses);

        List<ProductResponse> allProduct = service.getAllProduct(pageRequest);

        assertThat(allProduct.size()).isEqualTo(2);
    }

    @Test
    public void shouldGetAllProductByCategoryType() {
        Product p = new Product();
        p.setCategory("cake");
        ProductResponse response = new ProductResponse();
        response.setCategory("cake");
        List<Product> products = Collections.singletonList(p);
        List<ProductResponse> responses = Collections.singletonList(response);

        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repository.findAllByCategory("cake", pageRequest)).thenReturn(new PageImpl<>(products));
        when(mapper.toListOFProductResponse(products)).thenReturn(responses);

        List<ProductResponse> all = service.getAllProductByCategory("cake", pageRequest);

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getCategory()).isEqualTo("cake");

    }

    @Test
    public void shouldDeleteProduct() {
        Product product = new Product("1234");
        when(repository.findById("1234")).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);

        service.deleteProductById("1234");
    }

    @Test
    public void shouldAddProduct() {
        ProductRequest request = new ProductRequest();
        request.setDescription("a new phone");
        request.setThumbnailId("12");
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setName("image.jpg");
        when(thumbnailRepository.findById("12")).thenReturn(Optional.of(thumbnail));
        when(repository.save(any(Product.class))).thenReturn(new Product("1234"));
        when(mapper.mapToProduct(request)).thenReturn(new Product("1234"));
        when(mapper.toProductResponse(any(Product.class))).thenReturn(new ProductResponse("1234"));
        ProductResponse productResponse = service.create(request);

        assertThat(productResponse.getId()).isEqualTo("1234");
    }

}
