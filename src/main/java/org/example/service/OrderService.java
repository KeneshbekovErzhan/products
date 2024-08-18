package org.example.service;

import org.example.dao.CustomerDao;
import org.example.dao.OrderDao;
import org.example.models.Customer;
import org.example.models.Order;
import java.util.Date;
import java.util.Scanner;

public class OrderService {
    Scanner scanner = new Scanner(System.in);
    OrderDao orderDao = new OrderDao();

    public void saveOrder(){
        Order order = new Order();
        System.out.print("Enter order status: ");
        order.setOrderStatus(scanner.nextLine());
        Date currentDate = new Date();
        order.setDate(currentDate);
        System.out.print("Enter cusromer id: ");
        int customerId = scanner.nextInt();
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getById(customerId);
        order.setCustomer(customer);
        orderDao.create(order);
        System.out.println("Order saved");
    }

    public void getById(){
        System.out.print("Enter id order: ");
        int id = scanner.nextInt();
        Order order = orderDao.getById(id);
        printOrder(order);
    }

    public void updateOrder(){
        System.out.print("Enter order id: ");
        int idOrder = scanner.nextInt();
        Order order = orderDao.getById(idOrder);
        printOrder(order);
        System.out.print("Enter customer id: ");
        int idCustomer = scanner.nextInt();
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.getById(idCustomer);
        if(idCustomer>0){ // :)
            order.setCustomer(customer);
        }
        System.out.print("Enter order status: ");
        String orderStatus = scanner.nextLine();
        if(!orderStatus.isEmpty()){
            order.setOrderStatus(orderStatus);
        }
        System.out.print("Enter 'yes' if want to update the date: ");
        String date = scanner.nextLine();
        if (date.toLowerCase().equals("yes")){
            Date date1 = new Date();
            order.setDate(date1);
        }
    }

    public void deleteOrderById(){
        System.out.print("Enter order id: ");
        int id = scanner.nextInt();
        orderDao.deleteOrder(id);
        System.out.println("Order with: " + id + " deleted");
        orderDao.deleteOrdersProducts(id);

    }

    private void printOrder(Order order){
        System.out.println("-----------------");
        System.out.println("| Order id: " + order.getOrderId());
        System.out.println("| Customer id: " + order.getCustomer().getCustomerId());
        System.out.println("| Order status: " + order.getOrderStatus());
        System.out.println("| Date: " + order.getDate());
        System.out.println("-----------------");

    }
}
