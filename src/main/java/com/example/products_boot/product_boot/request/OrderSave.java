package com.example.products_boot.product_boot.request;

import com.example.products_boot.product_boot.models.Customer;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class OrderSave {
    private Integer orderId;
    private String orderStatus;
    private Date date;
    private Customer customer;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
