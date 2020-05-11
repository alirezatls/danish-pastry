package com.danishpastry.pastryshoppingservice.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartItemPojoTest {
    @Test
    public void testCartItem() {
        CartItem item = new CartItem();
        item.setCost(12d);

        assertThat(item.getCost()).isEqualTo(12d);
    }
}
