package com.tizo.msporders.repository;

import com.tizo.msporders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Order, Long> {

    List<Order> findByClientEmail(String email);
    Order findById(UUID id);

}
