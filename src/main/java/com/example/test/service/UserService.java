package com.example.test.service;

import com.example.test.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);

    List<User> findAll();

    User save(User user);
}
