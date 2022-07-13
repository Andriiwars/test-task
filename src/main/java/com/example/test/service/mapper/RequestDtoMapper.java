package com.example.test.service.mapper;

public interface RequestDtoMapper<M, D> {
    M toModel(D dto);
}
