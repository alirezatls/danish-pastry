package com.danishpastry.pastryshoppingservice.repository;


import com.danishpastry.pastryshoppingservice.domain.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, String> {
}
