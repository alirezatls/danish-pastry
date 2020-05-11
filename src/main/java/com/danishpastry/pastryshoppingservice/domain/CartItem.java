package com.danishpastry.pastryshoppingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash
public class CartItem {
    @Id
    private String id;
    private String name;
    private double cost;
}
