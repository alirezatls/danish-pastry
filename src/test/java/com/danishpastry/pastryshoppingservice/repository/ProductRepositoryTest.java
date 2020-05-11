package com.danishpastry.pastryshoppingservice.repository;

import com.danishpastry.pastryshoppingservice.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Before
    public void before() {
        this.repository.deleteAll();
    }

    @After
    public void after() {
        this.repository.deleteAll();
    }

    @Test
    public void shouldSaveProduct() {
        Product product = new Product();
        product.setId(null);

        Product savedProduct = repository.save(product);
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    public void shouldGetAllProductByCategory() {
        Product product = new Product();
        product.setCategory("cake");
        Product product1 = new Product();
        product1.setCategory("cake");

        repository.saveAll(Arrays.asList(product, product1));

        List<Product> all = repository.findAllByCategory("cake", PageRequest.of(0, 10)).getContent();

        assertThat(all.size()).isEqualTo(2);

    }
}
