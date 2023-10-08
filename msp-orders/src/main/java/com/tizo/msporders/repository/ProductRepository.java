package com.tizo.msporders.repository;

import com.tizo.msporders.entity.Order;
import com.tizo.msporders.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
