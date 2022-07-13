package com.example.test.service.mapper;

import com.example.test.dto.request.GoodsRequestDto;
import com.example.test.dto.response.GoodsResponseDto;
import com.example.test.dto.response.ProductResponseDto;
import com.example.test.model.Goods;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class GoodsMapper implements ResponseDtoMapper<GoodsResponseDto, Goods>,
            RequestDtoMapper<Goods, GoodsRequestDto> {
    private final ResponseDtoMapper<ProductResponseDto, Product> productMapper;
    private final ProductService productService;

    public GoodsMapper(ResponseDtoMapper<ProductResponseDto, Product> productMapper,
                       ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    public GoodsResponseDto toDto(Goods model) {
        GoodsResponseDto dto = new GoodsResponseDto();
        dto.setId(model.getId());
        dto.setProduct(productMapper.toDto(model.getProduct()));
        dto.setCount(model.getCount());
        return dto;
    }

    @Override
    public Goods toModel(GoodsRequestDto dto) {
        Goods goods = new Goods();
        goods.setCount(dto.getCount());
        goods.setProduct(productService.findById(dto.getProductId()));
        return goods;
    }
}
