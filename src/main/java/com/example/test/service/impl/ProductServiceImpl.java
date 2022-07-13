package com.example.test.service.impl;

import com.example.test.dao.ProductDao;
import com.example.test.model.Product;
import com.example.test.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id).orElseThrow();
    }
}
