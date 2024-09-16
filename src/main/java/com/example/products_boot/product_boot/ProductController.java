package com.example.products_boot.product_boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello :) ";
    }
}
