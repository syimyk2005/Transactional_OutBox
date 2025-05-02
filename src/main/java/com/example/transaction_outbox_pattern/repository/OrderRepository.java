package com.example.transaction_outbox_pattern.repository;

import com.example.transaction_outbox_pattern.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(Integer id);
}
