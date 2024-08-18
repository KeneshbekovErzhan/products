package org.example;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;

import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean check = true;
        while (check) {
            System.out.println("""
                    |       Menu      |
                    | (1) Products    |
                    | (2) Orders      |                   
                    | (3) Customers ---  |
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
            }
        }
    }

    private static void customers() {
        boolean check = true;
        while (check) {
            CustomerService customerService = new CustomerService();
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
        OrderService orderService = new OrderService();
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
                    orderService.saveOrder();
                    break;
                case 2:
                    orderService.getById();
                    break;
                case 3:
                    orderService.updateOrder();
                    break;
                case 4:
                    orderService.deleteOrderById();
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

    private static void products() {
        boolean check = true;
        while (check) {
            ProductService productService = new ProductService();
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
                    productService.get_ById();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProduct();
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
}