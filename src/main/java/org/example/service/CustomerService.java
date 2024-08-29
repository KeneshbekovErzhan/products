package org.example.service;

import org.example.dao.CustomerDao;
import org.example.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerService {

    @Autowired
     private CustomerDao customerDao;
    Scanner scanner = new Scanner(System.in);

    public void saveCustomer(){
        Customer customer = new Customer();
        System.out.print("Enter customer name: ");
        customer.setCustomerName(scanner.nextLine());

        System.out.print("Enter customer info: ");
        customer.setContactInfo(scanner.nextLine());
        customerDao.create(customer);
        System.out.println("Customer saved");
    }

    public void getById(){
        System.out.print("Enter customer id: ");
        int id = scanner.nextInt();
        Customer customer = customerDao.getById(id);
        printCustomer(customer);
    }
    public void updateCustomer(){
        System.out.print("Enter customer id: ");
        int id = scanner.nextInt();
        Customer customer = customerDao.getById(id);
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
        customerDao.updateCustomer(customer);
        System.out.println("Customer updated");
    }

    public void deleteCustomerById(){
        System.out.print("Enter customer id: ");
        int id = scanner.nextInt();
        customerDao.deleteCustomer(id);
        System.out.println("Customer with id: " + id + " deleted");
    }

    private void printCustomer(Customer customer){
        System.out.println("--------------");
        System.out.println("| Customer id: " + customer.getCustomerId());
        System.out.println("| Customer name: " + customer.getCustomerName());
        System.out.println("| Contact info: " + customer.getContactInfo());
        System.out.println("--------------");
    }

}
