package com.example.products_boot.product_boot.service;

import com.example.products_boot.product_boot.models.Product;
import com.example.products_boot.product_boot.repository.ProductRepository;
import com.example.products_boot.product_boot.request.ProductSave;
import com.example.products_boot.product_boot.utils.Printable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    Scanner scanner = new Scanner(System.in);

    Printable<Product> printable = p -> {
        System.out.println("--------------");
        System.out.println("product id: " + p.getProductId());
        System.out.println("product name: " + p.getProductName());
        System.out.println("product description: " + p.getDescription());
        System.out.println("product price: " + p.getPrice());
        System.out.println("---------------");
    };

    public void saveProduct(ProductSave productSave){
        Product product = new Product();
        product.setProductName(productSave.getProductName());
        product.setDescription(productSave.getDescription());
        product.setPrice(productSave.getPrice());
        productRepository.save(product);
        System.out.println("Customer saved !");
    }

    public void noProductResponse (int id) {
        System.out.printf("There is no product with this ID %s", id);
        System.out.println();
    }

    public void getById(){
        int id = 1;
        Product product = productRepository.getReferenceById(id);
        if (productRepository.getReferenceById(id) != null){
            printProduct(product);
        } else {
            noProductResponse(id);
        }
    }

    public void updateProduct(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();

        Product product = productRepository.getReferenceById(id);
        if (productRepository.getReferenceById(id) != null) {
            System.out.println("Enter product name: ");
            System.out.println();
            String productName = scanner.nextLine();
            if (!productName.isEmpty()){
                product.setProductName(productName);
            }
            System.out.println("Enter product description: ");
            String productDescription = scanner.nextLine();
            if (!productDescription.isEmpty()){
                product.setDescription(productDescription);
            }
            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            if (productPrice != 0){
                product.setPrice(productPrice);
            }
            productRepository.save(product);
            productRepository.save(product);
            System.out.println("Product updated!");
        } else {
            noProductResponse(id);
        }
    }

    public void deleteProductById(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();
        productRepository.deleteById(id);
        System.out.println("Product with id: " + id + "deleted");
    }

    private void printProduct(Product product){
        System.out.println("--------------");
        System.out.println("product id: " + product.getProductId());
        System.out.println("product name: " + product.getProductName());
        System.out.println("product description: " + product.getDescription());
        System.out.println("product price: " + product.getPrice());
        System.out.println("---------------");

    }

    public void getAllProducts(){
        List productList = new ArrayList<Product>();
        productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            for (Product product: productRepository.findAll()) {
                printable.print(product);
            }
        } else {
            System.out.println("No products.");
        }
    }

    public Product getProductByName(String product_name){
        return productRepository.findProductByProductName(product_name);
    }
}
