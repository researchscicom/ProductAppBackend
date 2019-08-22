package com.product.spring.dao;

import com.product.spring.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO{

    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public List<Product> getAllProducts() {
        List<Product> ls= sessionFactory.getCurrentSession().createQuery("from Product").list();
        return ls;
    }

    @Override
    public Product getProduct(long id) {
        return sessionFactory.getCurrentSession().get(Product.class,id);
    }

    @Override
    public Long saveProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product.getId();
    }

    @Override
    public void updateProduct(Long id, Product product) {

    }

    @Override
    public void deleteProduct(Long id) {
        Product product=sessionFactory.getCurrentSession().byId(Product.class).load(id);
        sessionFactory.getCurrentSession().delete(product);
    }
}
