package com.example.products_boot.product_boot.service;

import com.example.products_boot.product_boot.repository.*;
import com.example.products_boot.product_boot.models.*;
import com.example.products_boot.product_boot.request.OrderSave;
import com.example.products_boot.product_boot.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    Scanner sc = new Scanner(System.in);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderSave orderSave;
    @Autowired
    CustomerDao customerDao;

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

    public void createOrder(OrderSave orderSave){
        Order order = new Order();
        order.setOrderStatus(orderSave.getOrderStatus());

        Customer customer = customerDao.getById(1); //Poka tak, nado ispravit
        order.setCustomer(customer);
        setDate(order);
        orderRepository.save(order);



       // List<Product> productList = new ArrayList<>();
       // productList = addArrayList(productList);
       // order.setProductList(productList);
       // orderRepository.save(order);
    }

    public void getOrderById(){
        System.out.println("Enter order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Order order = orderRepository.getReferenceById(id);
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
        orderRepository.save(order);
    }

    public void deleteOrder(){
        System.out.println("Enter order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        orderRepository.deleteById(id);
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
                int id = Integer.parseInt(productId);
                Product product = productRepository.getReferenceById(id);
                productList.add(product);
                System.out.println("Product with id: " + id + " added to list");
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
