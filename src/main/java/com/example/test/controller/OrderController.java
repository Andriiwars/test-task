package com.example.test.controller;

import com.example.test.dto.response.OrderResponseDto;
import com.example.test.model.Order;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import com.example.test.service.OrderService;
import com.example.test.service.ShoppingCartService;
import com.example.test.service.UserService;
import com.example.test.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ResponseDtoMapper<OrderResponseDto, Order> responseMapper;
    private final ShoppingCartService shoppingCartService;

    public OrderController(UserService userService,
                           OrderService orderService,
                           ResponseDtoMapper<OrderResponseDto, Order> responseMapper,
                           ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.orderService = orderService;
        this.responseMapper = responseMapper;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<OrderResponseDto> getHistory(Authentication authentication) {
        User user = userService.findUserByEmail(authentication.getName()).orElseThrow(
                () -> new RuntimeException(authentication.getName() + " This user was not found"));
        return orderService.findAllByUser(user).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication auth) {
        User user = userService.findUserByEmail(auth.getName()).orElseThrow(
                () -> new RuntimeException(auth.getName() + " This user was not found"));
        ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
        return responseMapper.toDto(orderService.completeOrder(shoppingCart));
    }
}
