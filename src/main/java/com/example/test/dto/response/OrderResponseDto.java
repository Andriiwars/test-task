package com.example.test.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private LocalDateTime orderTime;
    private List<GoodsResponseDto> goods;
}
