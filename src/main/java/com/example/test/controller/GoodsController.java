package com.example.test.controller;

import com.example.test.dto.request.GoodsRequestDto;
import com.example.test.dto.response.GoodsResponseDto;
import com.example.test.model.Goods;
import com.example.test.service.GoodsService;
import com.example.test.service.mapper.RequestDtoMapper;
import com.example.test.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final RequestDtoMapper<Goods, GoodsRequestDto> requestMapper;
    private final ResponseDtoMapper<GoodsResponseDto, Goods> responseMapper;

    public GoodsController(GoodsService goodsService,
                           RequestDtoMapper<Goods, GoodsRequestDto> requestMapper,
                           ResponseDtoMapper<GoodsResponseDto, Goods> responseMapper) {
        this.goodsService = goodsService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @GetMapping
    public List<GoodsResponseDto> getAll() {
        return goodsService.findAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public GoodsResponseDto add(@RequestBody @Valid GoodsRequestDto requestDto) {
        Goods goods = goodsService.save(requestMapper.toModel(requestDto));
        return responseMapper.toDto(goods);
    }
}
