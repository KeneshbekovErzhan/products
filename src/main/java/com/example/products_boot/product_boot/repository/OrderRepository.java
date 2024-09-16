package com.example.products_boot.product_boot.repository;

import com.example.products_boot.product_boot.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
