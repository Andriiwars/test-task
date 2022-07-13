package com.example.test.service.mapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
