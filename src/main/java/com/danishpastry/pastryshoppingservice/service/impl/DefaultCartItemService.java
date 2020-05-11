package com.danishpastry.pastryshoppingservice.service.impl;

import com.danishpastry.pastryshoppingservice.domain.CartItem;
import com.danishpastry.pastryshoppingservice.repository.CartItemRepository;
import com.danishpastry.pastryshoppingservice.service.CartItemService;
import com.danishpastry.pastryshoppingservice.service.dto.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DefaultCartItemService implements CartItemService {

    private CartItemRepository repository;

    @Autowired
    public DefaultCartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addToCart(CartRequest request) {
        CartItem item = new CartItem();
        item.setName(request.getName());
        item.setCost(request.getCost());
        repository.save(item);
    }

    @Override
    public void deleteItem(String id) {
        CartItem cartItem = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(cartItem);
    }

    @Override
    public List<CartItem> getAllItem() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
