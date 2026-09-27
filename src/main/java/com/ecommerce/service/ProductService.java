package com.ecommerce.service;


import java.util.List;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

public class ProductService {

    private SessionFactory sessionFactory;

    public ProductService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE PRODUCT
    public Product createProduct(String name,
                                 BigDecimal price,
                                 Integer stockQuantity,
                                 Long categoryId) {
    	if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
    	    return null;
    	}

    	if (stockQuantity == null || stockQuantity < 0) {
    	    return null;
    	}

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Category category = session.get(Category.class, categoryId);

        if (category == null) {
            transaction.rollback();
            session.close();
            return null;
        }

        Product product = new Product(
                name,
                price,
                stockQuantity,
                category
        );

        session.persist(product);

        transaction.commit();
        session.close();

        return product;
    }

    // READ PRODUCT
    public Product getProduct(Long id) {

        Session session = sessionFactory.openSession();

        Product product = session.get(Product.class, id);

        session.close();

        return product;
    }

    // UPDATE PRODUCT
    public void updateProduct(Long id,
                              String name,
                              BigDecimal price,
                              Integer stockQuantity) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {

            product.setName(name);
            product.setPrice(price);
            product.setStockQuantity(stockQuantity);
        }

        transaction.commit();
        session.close();
    }

    // DELETE PRODUCT
    public void deleteProduct(Long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            session.remove(product);
        }

        transaction.commit();
        session.close();
    }
    
 // GET ALL PRODUCTS
    public List<Product> getAllProducts() {

        Session session = sessionFactory.openSession();

        List<Product> products = session
                .createQuery("from Product", Product.class)
                .getResultList();

        session.close();

        return products;
    }
}