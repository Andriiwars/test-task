package com.example.test.dao;

import com.example.test.model.ShoppingCart;
import com.example.test.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Long> {
    @Query("select distinct sc from ShoppingCart sc "
            + "left join fetch sc.goods left join fetch sc.user where sc.user = :user")
    ShoppingCart findByUser(User user);

    List<ShoppingCart> findAllByTimeCreatedIsLessThan(Long timeCreated);
}
