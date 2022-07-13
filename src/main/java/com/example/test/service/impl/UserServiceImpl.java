package com.example.test.service.impl;

import com.example.test.dao.UserDao;
import com.example.test.model.User;
import com.example.test.service.ShoppingCartService;
import com.example.test.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           PasswordEncoder encoder,
                           ShoppingCartService shoppingCartService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        shoppingCartService.createShoppingCart(user);
        return userDao.save(user);
    }
}
