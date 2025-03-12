package com.mypackage.repository;

import com.mypackage.entity.Order;
import com.mypackage.entity.Order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);

    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);
}