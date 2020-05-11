package com.danishpastry.pastryshoppingservice.service;

import com.danishpastry.pastryshoppingservice.domain.CartItem;
import com.danishpastry.pastryshoppingservice.service.dto.CartRequest;

import java.util.List;

public interface CartItemService {

    void addToCart(CartRequest request);

    void deleteItem(String id);

    List<CartItem> getAllItem();
}
