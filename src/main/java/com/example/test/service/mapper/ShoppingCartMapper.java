package com.example.test.service.mapper;

import com.example.test.dto.response.GoodsResponseDto;
import com.example.test.dto.response.ShoppingCartResponseDto;
import com.example.test.dto.response.UserResponseDto;
import com.example.test.model.Goods;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper implements ResponseDtoMapper<ShoppingCartResponseDto,
        ShoppingCart> {
    private final ResponseDtoMapper<UserResponseDto, User> userMapper;
    private final ResponseDtoMapper<GoodsResponseDto, Goods> goodsMapper;

    public ShoppingCartMapper(ResponseDtoMapper<UserResponseDto, User> userMapper,
                              ResponseDtoMapper<GoodsResponseDto, Goods> goodsMapper) {
        this.userMapper = userMapper;
        this.goodsMapper = goodsMapper;
    }

    @Override
    public ShoppingCartResponseDto toDto(ShoppingCart model) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setUserResponseDto(userMapper.toDto(model.getUser()));
        dto.setGoods(model.getGoods().stream()
                .map(goodsMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
