package org.example.service;

import org.example.dao.ProductDao;
import org.example.models.Product;

import java.util.Scanner;

public class ProductService {
    Scanner scanner = new Scanner(System.in);
    ProductDao productDao = new ProductDao();

    public void saveProduct(){
        Product product = new Product();
        System.out.print("Enter product name: ");
        product.setProductName(scanner.nextLine());
        System.out.print("Enter description: ");
        product.setDescription(scanner.nextLine());
        System.out.print("Enter price: ");
        product.setPrice(scanner.nextDouble());
        productDao.createProduct(product);
        System.out.println("Product saved");
    }

    public void updateProduct(){
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        Product product = productDao.getById(id);
        printProduct(product);
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        if(!productName.isEmpty()){
            product.setProductName(productName);
        }
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        if(!description.isEmpty()){
            product.setDescription(description);
        }
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        if(price>0){
            product.setPrice(price);
        }
        System.out.println("Product updated");
    }

    public void get_ById(){
        Product product = new Product();
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        productDao.getById(id);
        printProduct(product);
    }

    public void deleteProduct(){
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        productDao.deleteProduct(id);
        System.out.println("Product with id: " + id + "deleted");
    }
    private void printProduct(Product product){
        System.out.println("-----------------");
        System.out.println("Product name: " + product.getProductName());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Price: " + product.getPrice());
        System.out.println("-----------------");
    }

}
