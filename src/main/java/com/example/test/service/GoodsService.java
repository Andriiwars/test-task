package com.example.test.service;

import com.example.test.model.Goods;
import java.util.List;

public interface GoodsService {
    Goods save(Goods goods);

    Goods findByProductId(Long id);

    List<Goods> findAll();
}
