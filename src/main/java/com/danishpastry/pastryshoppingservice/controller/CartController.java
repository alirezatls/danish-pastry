package com.danishpastry.pastryshoppingservice.controller;

import com.danishpastry.pastryshoppingservice.domain.CartItem;
import com.danishpastry.pastryshoppingservice.service.CartItemService;
import com.danishpastry.pastryshoppingservice.service.dto.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private CartItemService service;

    @Autowired
    public CartController(CartItemService service) {
        this.service = service;
    }

    @PostMapping("/api/common/cart")
    public void add(@RequestBody CartRequest request) {
        service.addToCart(request);
    }

    @DeleteMapping("/api/common/cart/{id}")
    public void remove(@PathVariable("id") String id) {
        service.deleteItem(id);
    }

    @GetMapping("/api/common/cart")
    public List<CartItem> getAll() {
        return service.getAllItem();
    }
}
