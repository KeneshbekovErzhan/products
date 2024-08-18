package org.example.models;

import org.example.utils.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
    private int customerId;
    private String customerName;
    private String contactInfo;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Customer(int customerId, String customerName, String contactInfo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
    }
    public Customer() {
    }
}
