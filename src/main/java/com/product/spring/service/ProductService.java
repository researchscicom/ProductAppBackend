package com.product.spring.service;

import com.product.spring.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(long id);
    Long saveProduct(Product product);
    void updateProduct(Long id,Product product);
    void deleteProduct(Long id);
}
