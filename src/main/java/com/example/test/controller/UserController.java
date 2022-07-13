package com.example.test.controller;

import com.example.test.dto.response.UserResponseDto;
import com.example.test.model.User;
import com.example.test.service.UserService;
import com.example.test.service.mapper.ResponseDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ResponseDtoMapper<UserResponseDto, User> mapper;

    public UserController(UserService userService,
                          ResponseDtoMapper<UserResponseDto, User> mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userService.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
