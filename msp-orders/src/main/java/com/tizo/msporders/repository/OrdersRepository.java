package com.tizo.msporders.repository;

import com.tizo.msporders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
