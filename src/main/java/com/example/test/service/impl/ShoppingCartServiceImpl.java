package com.example.test.service.impl;

import com.example.test.dao.ShoppingCartDao;
import com.example.test.model.Goods;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import com.example.test.service.GoodsService;
import com.example.test.service.ShoppingCartService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final GoodsService goodsService;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao,
                                   GoodsService goodsService) {
        this.shoppingCartDao = shoppingCartDao;
        this.goodsService = goodsService;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartDao.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartDao.findAll();
    }

    @Override
    public ShoppingCart findByUser(User user) {
        return shoppingCartDao.findByUser(user);
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setTimeCreated(System.currentTimeMillis());
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public void addGoods(Goods goods, User user) {
        if (goods.getCount() > goodsService
                .findByProductId(goods.getProduct().getId()).getCount()) {
            throw new RuntimeException("Invalid count");
        }
        ShoppingCart shoppingCart = shoppingCartDao.findByUser(user);
        goods.setInShoppingCart(true);
        goodsService.save(goods);
        shoppingCart.getGoods().add(goods);
        shoppingCartDao.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> findAllByTimeIsLessThan(Long timeCreated) {
        return shoppingCartDao.findAllByTimeCreatedIsLessThan(timeCreated);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setGoods(null);
        shoppingCartDao.save(shoppingCart);
    }
}
