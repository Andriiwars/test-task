package com.example.test.dao;

import com.example.test.model.Goods;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Long> {
    @Query("select g from Goods g where g.product.id = :id and g.isInShoppingCart = false")
    Optional<Goods> findByProductId(Long id);
}
