package com.example.test.service.impl;

import com.example.test.dao.GoodsDao;
import com.example.test.model.Goods;
import com.example.test.service.GoodsService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public Goods save(Goods goods) {
        return goodsDao.save(goods);
    }

    @Override
    public Goods findByProductId(Long id) {
        return goodsDao.findByProductId(id).orElseThrow();
    }

    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }
}
