package com.example.test.controller;

import com.example.test.dto.request.ProductRequestDto;
import com.example.test.dto.response.ProductResponseDto;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import com.example.test.service.mapper.RequestDtoMapper;
import com.example.test.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RequestDtoMapper<Product, ProductRequestDto> requestMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> responseMapper;

    public ProductController(ProductService productService,
                             RequestDtoMapper<Product, ProductRequestDto> requestMapper,
                             ResponseDtoMapper<ProductResponseDto, Product> responseMapper) {
        this.productService = productService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public ProductResponseDto add(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productService.save(requestMapper.toModel(requestDto));
        return responseMapper.toDto(product);
    }
}
