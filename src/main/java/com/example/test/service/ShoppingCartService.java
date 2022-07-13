package com.example.test.service;

import com.example.test.model.Goods;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart save(ShoppingCart shoppingCart);

    List<ShoppingCart> findAll();

    ShoppingCart findByUser(User user);

    void createShoppingCart(User user);

    void addGoods(Goods goods, User user);

    List<ShoppingCart> findAllByTimeIsLessThan(Long timeCreated);

    void clear(ShoppingCart shoppingCart);
}
