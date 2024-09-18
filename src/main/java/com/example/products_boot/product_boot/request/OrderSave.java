package com.example.products_boot.product_boot.request;

import com.example.products_boot.product_boot.models.Customer;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class OrderSave {
    private String orderStatus;


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
