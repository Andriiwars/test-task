package com.example.test.dao;

import com.example.test.model.Order;
import com.example.test.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    @Query("select distinct o from Order o "
            + "left join fetch o.goods "
            + "left join fetch o.user where o.user = :user")
    List<Order> findAllByUser(User user);
}
