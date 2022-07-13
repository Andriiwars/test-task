package com.example.test.service.mapper;

import com.example.test.dto.response.UserResponseDto;
import com.example.test.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements
        ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto toDto(User model) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(model.getId());
        userResponseDto.setEmail(model.getEmail());
        return userResponseDto;
    }
}
