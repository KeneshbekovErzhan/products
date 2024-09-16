package com.example.products_boot.product_boot.service;

import com.example.products_boot.product_boot.models.Customer;
import com.example.products_boot.product_boot.repository.CustomerDao;
import com.example.products_boot.product_boot.repository.CustomerRepository;
import com.example.products_boot.product_boot.request.CustomerSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerDao customerDao;
    Scanner scanner = new Scanner(System.in);


    public void saveCustomer(CustomerSave customerSave){
        Customer customer = new Customer();
        customer.setCustomerName(customerSave.getCustomerName());
        customer.setContactInfo(customerSave.getContactInfo());
        customerRepository.save(customer);
    }

    public void getById(int i){
        System.out.print("Enter customer id: ");
        int id = 1;
        customerRepository.getReferenceById(id);
    }
    public void updateCustomer(int customer_id){
        System.out.print("Enter customer id: ");
        int id = scanner.nextInt();
        Customer customer = customerRepository.getReferenceById(id);
        printCustomer(customer);
        scanner.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
            if(!customerName.isEmpty()){
            customer.setCustomerName(customerName);
        }
            System.out.print("Enter contact info: ");
            String contactInfo = scanner.nextLine();
        if(!contactInfo.isEmpty()){
            customer.setContactInfo(contactInfo);
        }
        customerRepository.save(customer);
        System.out.println("Customer updated");
    }

    public void deleteCustomerByCustomerName(String customerName){
        customerRepository.deleteCustomerByCustomerName(customerName);
    }

    private void printCustomer(Customer customer){
        System.out.println("--------------");
        System.out.println("| Customer id: " + customer.getCustomerId());
        System.out.println("| Customer name: " + customer.getCustomerName());
        System.out.println("| Contact info: " + customer.getContactInfo());
        System.out.println("--------------");
    }

    public Customer getByCustomerName(String customerName) {
        return customerRepository.findCustomerByCustomerName(customerName);
    }
}
