package com.product.spring.service;

import com.product.spring.dao.ProductDAO;
import com.product.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class ProductServiceImp implements ProductService {
    @Autowired
    public ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProduct(long id) {
       return productDAO.getProduct(id);
    }

    @Override
    public Long saveProduct(Product product) {
        return productDAO.saveProduct(product);
    }

    @Transactional
    @Override
    public void updateProduct(Long id, Product product) {
        productDAO.updateProduct(id,product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}
