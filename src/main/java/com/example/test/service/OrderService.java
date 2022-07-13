package com.example.test.service;

import com.example.test.model.Order;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> findAllByUser(User user);
}
