package com.product.spring.controller;

import com.product.spring.model.Product;
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

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts()
    {
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id)
    {
        Product product=productService.getProduct(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<?> save(@RequestBody Product product)
    {
        productService.saveProduct(product);
        return ResponseEntity.ok().body("New Product has been save with id "+product.getId());
    }

    @PutMapping("product/{id}")
    public ResponseEntity<?> editProduct(@PathVariable("id") Long id,Product product)
    {
        productService.updateProduct(id,product);
        return ResponseEntity.ok().body("Update Successfull");
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Delete product that id = "+id);
    }
}
