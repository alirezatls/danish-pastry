package com.danishpastry.pastryshoppingservice.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPojoTest {

    @Test
    public void testProductPojo() {
        Product product = new Product();
        product.setId(null);
        product.setName("danish pastry");

        assertThat(product.getName()).isEqualTo("danish pastry");
    }
}
