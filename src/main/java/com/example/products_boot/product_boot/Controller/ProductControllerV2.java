package com.example.products_boot.product_boot.Controller;

import com.example.products_boot.product_boot.models.Product;
import com.example.products_boot.product_boot.request.ProductSave;
import com.example.products_boot.product_boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductControllerV2 {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductSave product){
        productService.saveProduct(product);
        return ResponseEntity.ok("Product saved!");
    }

    @GetMapping("/{product_name}")
    public ResponseEntity<?> getProductByProductName(@PathVariable String product_name){
        Product product = productService.getProductByName(product_name);
        return ResponseEntity.ok(product);
    }

}
