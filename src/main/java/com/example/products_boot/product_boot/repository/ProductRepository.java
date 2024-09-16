package com.example.products_boot.product_boot.repository;

import com.example.products_boot.product_boot.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByProductName(String product_name);
}
