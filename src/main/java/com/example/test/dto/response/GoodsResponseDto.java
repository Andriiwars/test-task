package com.example.test.dto.response;

import lombok.Data;

@Data
public class GoodsResponseDto {
    private Long id;
    private ProductResponseDto product;
    private Long count;
}
