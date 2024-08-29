package org.example.service;

import org.example.dao.CustomerDao;
import org.example.dao.OrderDao;
import org.example.dao.ProductDao;
import org.example.models.Customer;
import org.example.models.Order;
import org.example.models.Product;

import org.example.utils.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    Scanner sc = new Scanner(System.in);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

    Printable<Order> printable = order -> {
        System.out.println("----------------------------");
        System.out.println("ORDER ID: " + order.getOrderId());
        System.out.println("ORDER STATUS: " + order.getOrderStatus());
        System.out.println("CUSTOMER ID: " + order.getCustomer().getCustomerId());
        List<Product> productList = order.getProductList();

        Printable<Product> printable1 = product -> {
            System.out.println("ID: " + product.getProductId());
            System.out.println("NAME: " + product.getProductName());
            System.out.println("DESCRIPTION: " + product.getDescription());
            System.out.println("PRICE: " + product.getPrice());
        };
        for(Product product: productList){
            printable1.print(product);
        }
        System.out.println("----------------------------");
    };

    public void createOrder(){
        System.out.println("Enter order status: ");
        String orderStatus = sc.nextLine();
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        System.out.println("Enter customer's id: ");
        int customerId = sc.nextInt();
        sc.nextLine();
        Customer customer = customerDao.getById(customerId);
        order.setCustomer(customer);


        setDate(order);
        List<Product> productList = new ArrayList<>();
        productList = addArrayList(productList);
        order.setProductList(productList);

        orderDao.create(order);
    }

    public void getOrderById(){
        System.out.println("Enter order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Order order = orderDao.getById(id);
        printable.print(order);
    }

    public void updateOrder(){
        System.out.println("Enter order id: ");
        Order order = orderDao.getById(sc.nextInt());
        sc.nextLine();
        List<Product> productList = order.getProductList();

        System.out.println("Enter order status: ");
        String orderStatus = sc.nextLine();
        order.setOrderStatus(orderStatus);

        setDate(order);
        productList = addArrayList(productList);
        order.setProductList(productList);
        orderDao.updateOrder(order);

    }

    public void deleteOrder(){
        System.out.println("Enter order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        orderDao.deleteOrder(id);
        System.out.println("Order with id: " + id + " is deleted");
    }


    private List<Product> addArrayList(List<Product> productList){
        boolean alive = true;

        sc.nextLine();
        while(alive){
            System.out.println("Enter product's id: .../ And if you're done type 'exit' to exit");
            String productId = sc.nextLine();
            if (productId.equals("exit")){
                alive = false;
            } else  {
                int idProduct = Integer.parseInt(productId);
                Product product = productDao.getById(idProduct);
                productList.add(product);
                System.out.println("Product with id: " + idProduct + " added to list");
            }
        }
        return productList;
    }

    private Order setDate(Order order){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        order.setDate(date);
        return order;
    }

    public void getAll(){
        for(Order order : orderDao.getAll()){
            printable.print(order);
        }
    }
}
