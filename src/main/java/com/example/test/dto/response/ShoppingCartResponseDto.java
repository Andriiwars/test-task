package com.example.test.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private UserResponseDto userResponseDto;
    private List<GoodsResponseDto> goods;
}
