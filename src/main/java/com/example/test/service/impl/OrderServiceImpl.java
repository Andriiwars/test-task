package com.example.test.service.impl;

import com.example.test.dao.OrderDao;
import com.example.test.model.Goods;
import com.example.test.model.Order;
import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import com.example.test.service.GoodsService;
import com.example.test.service.OrderService;
import com.example.test.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;
    private final GoodsService goodsService;

    public OrderServiceImpl(OrderDao orderDao,
                            ShoppingCartService shoppingCartService,
                            GoodsService goodsService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
        this.goodsService = goodsService;
    }

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setGoods(List.copyOf(shoppingCart.getGoods()));
        order.setUser(shoppingCart.getUser());
        order.setOrderTime(LocalDateTime.now());
        order.getGoods().forEach(e -> {
            Goods goods = goodsService.findByProductId(e.getProduct().getId());
            goods.setCount(goods.getCount() - e.getCount());
            goodsService.save(goods);
        });
        orderDao.save(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderDao.findAllByUser(user);
    }
}
