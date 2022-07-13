package com.example.test.service.mapper;

import com.example.test.dto.response.GoodsResponseDto;
import com.example.test.dto.response.OrderResponseDto;
import com.example.test.model.Goods;
import com.example.test.model.Order;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ResponseDtoMapper<OrderResponseDto, Order> {
    private final ResponseDtoMapper<GoodsResponseDto, Goods> goodsMapper;

    public OrderMapper(ResponseDtoMapper<GoodsResponseDto, Goods> goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public OrderResponseDto toDto(Order model) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderTime(model.getOrderTime());
        dto.setId(model.getId());
        dto.setGoods(model.getGoods().stream()
                .map(goodsMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }
}
