package com.product.spring.controller;

import com.product.spring.model.Product;
import com.product.spring.service.ProducerService;
import com.product.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    public ProductService productService;

    @Autowired
    public ProducerService producerService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts()
    {
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id)
    {
        Product product=productService.getProduct(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public void save(@RequestBody Product product)
    {
        productService.saveProduct(product);
        producerService.sendMsg(product);
    }

    @PutMapping("/product/{id}")
    public void editProduct(@PathVariable("id") long id,@RequestBody Product product)
    {
        System.out.println(product.toString());
        productService.updateProduct(id,product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") long id)
    {
        productService.deleteProduct(id);
    }

    @PostMapping("/produce")
    public void sendMsg(@RequestBody Product product)
    {
        producerService.sendMsg(product);
    }

}
