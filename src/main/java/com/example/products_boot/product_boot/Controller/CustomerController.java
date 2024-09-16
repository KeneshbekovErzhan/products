package com.example.products_boot.product_boot.Controller;
import com.example.products_boot.product_boot.models.Customer;
import com.example.products_boot.product_boot.request.CustomerSave;
import com.example.products_boot.product_boot.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerSave customer){
        customerService.saveCustomer(customer);
        return ResponseEntity.ok("Customer saved! ");
    }


    @GetMapping("/getByName/{customerName}")
    public ResponseEntity<?> getByCustomerName(@PathVariable String customerName){
        Customer customer = customerService.getByCustomerName(customerName);
        return ResponseEntity.ok(customer);
    }

    @Modifying
    @Transactional
    @DeleteMapping ("/{customerName}")
    public ResponseEntity<String> deleteCustomerByName( @PathVariable String customerName){
        customerService.deleteCustomerByCustomerName(customerName);
        return ResponseEntity.ok("Customer deleted! ");
    }


   /* @GetMapping ("/deleteCustomer/{customerName}")
    public String deleteCustomerByName(@PathVariable String customerName) {
        customerService.deleteCustomerByCustomerName(customerName);
        return "Customer deleted! "; */
    }

