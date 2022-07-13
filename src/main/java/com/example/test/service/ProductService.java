package com.example.test.service;

import com.example.test.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);
}
