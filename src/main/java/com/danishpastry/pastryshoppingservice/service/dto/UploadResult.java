package com.danishpastry.pastryshoppingservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResult {
    private String id;

    public static UploadResult of(String id) {
        return new UploadResult(id);
    }
}
