package com.example.products_boot.product_boot.Controller;

import com.example.products_boot.product_boot.request.OrderSave;
import com.example.products_boot.product_boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderControllerV2 {
    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody OrderSave orderSave){
        orderService.createOrder(orderSave);
        return ResponseEntity.ok("Order saved!");

    }

}
