package com.naira.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naira.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}