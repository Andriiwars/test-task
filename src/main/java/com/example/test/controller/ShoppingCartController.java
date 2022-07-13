package com.example.test.controller;

import com.example.test.dto.request.GoodsRequestDto;
import com.example.test.dto.response.ShoppingCartResponseDto;
import com.example.test.model.Goods;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import com.example.test.service.ShoppingCartService;
import com.example.test.service.UserService;
import com.example.test.service.mapper.RequestDtoMapper;
import com.example.test.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final UserService userService;
    private final ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> shoppingCartMapper;
    private final RequestDtoMapper<Goods, GoodsRequestDto> requestGoodsMapper;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(UserService userService,
                                  ResponseDtoMapper<ShoppingCartResponseDto,
                                          ShoppingCart> shoppingCartMapper,
                                  RequestDtoMapper<Goods, GoodsRequestDto> requestGoodsMapper,
                                  ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.requestGoodsMapper = requestGoodsMapper;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName()).orElseThrow(
                () -> new RuntimeException(authentication.getName()
                        + " This user was not found"));
        return shoppingCartMapper.toDto(shoppingCartService.findByUser(user));
    }

    @PutMapping("/goods")
    public void addProduct(Authentication authentication,
                           @RequestBody @Valid GoodsRequestDto goodsRequestDto) {
        Goods goods = requestGoodsMapper.toModel(goodsRequestDto);
        shoppingCartService.addGoods(goods, userService.findUserByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException(authentication.getName()
                        + " This user was not found")));
    }

}
