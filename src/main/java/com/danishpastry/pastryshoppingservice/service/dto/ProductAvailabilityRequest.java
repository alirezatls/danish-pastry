package com.danishpastry.pastryshoppingservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAvailabilityRequest {
    private String id;
    private boolean isAvailable;
}
