package com.example.products_boot.product_boot.repository;

import com.example.products_boot.product_boot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByCustomerName(String customer_name);

    void deleteCustomerByCustomerName(String customer_name);
}
