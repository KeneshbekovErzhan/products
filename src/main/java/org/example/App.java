package org.example;
import org.example.config.ProjectConfig;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ProjectConfig.class);
    public static void main(String[] args) {
        boolean check = true;
        while (check) {
            System.out.println("""
                    |       Menu      |
                    | (1) Products    |
                    | (2) Orders      |                   
                    | (3) Customers   |
                    | (4) Quit        |
 
                     """);
            System.out.print("Choose menu:");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    products();
                    break;
                case 2:
                    orders();
                    break;
                case 3:
                    customers();
                    break;
                case 4:
                    break;
            }
        }
    }

    private static void customers() {
        boolean check = true;
        while (check) {
            CustomerService customerService = context.getBean(CustomerService.class);
            System.out.println("""
                    |   Menu cutomers  |
                    | (1) Create       |
                    | (2) Get by id    |                   
                    | (3) Update       |
                    | (4) Delete       |
                    | (5) Get all      |
                    | (6) Quit         |

                     """);
            System.out.print("Choose menu:");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    customerService.saveCustomer();
                    break;
                case 2:
                    customerService.getById();
                    break;
                case 3:
                    customerService.updateCustomer();
                    break;
                case 4:
                    customerService.deleteCustomerById();
                    break;
                case 5:
                    System.out.println("V dorabotke )");
                    break;
                case 6:
                    check = false;
                    break;

            }
        }
    }

    private static void orders() {
        boolean check = true;
        while (check) {
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println("""
                    |   Menu orders    |
                    | (1) Create       |
                    | (2) Get by id    |                   
                    | (3) Update       |
                    | (4) Delete       |
                    | (5) Get all      |
                    | (6) Quit         |
 
                     """);
            System.out.print("Choose menu:");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    orderService.createOrder();
                    break;
                case 2:
                    orderService.getOrderById();
                    break;
                case 3:
                    orderService.updateOrder();
                    break;
                case 4:
                    orderService.deleteOrder();
                    break;
                case 5:
                    orderService.getAll();
                    break;
                case 6:
                    check = false;
                    break;

            }
        }
    }

    private static void products() {
        boolean check = true;
        while (check) {
            ProductService productService = context.getBean(ProductService.class);
            System.out.println("""
                    |   Menu products  |
                    | (1) Create       |
                    | (2) Get by id    |                   
                    | (3) Update       |
                    | (4) Delete       |
                    | (5) Get all      |
                    | (6) Quit         |

                     """);
            System.out.print("Choose menu:");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    productService.saveProduct();
                    break;
                case 2:
                    productService.getById();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProductById();
                    break;
                case 5:
                    productService.getAllProducts();
                    break;
                case 6:
                    check = false;
                    break;

            }
        }
    }
}