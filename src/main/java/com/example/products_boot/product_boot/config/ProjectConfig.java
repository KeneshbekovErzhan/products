package com.example.products_boot.product_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "com.example.*")
public class ProjectConfig {
    @Bean
    Connection connection(){
        try {
           return DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=Admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
