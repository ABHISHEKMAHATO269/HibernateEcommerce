package com.ecommerce.service;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entity.Category;

public class CategoryService {

    private SessionFactory sessionFactory;

    public CategoryService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE CATEGORY
    public Category createCategory(String name, String description) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Category category = new Category(
                name,
                description
        );

        session.persist(category);

        transaction.commit();
        session.close();

        return category;
    }

    // READ CATEGORY
    public Category getCategory(Long id) {

        Session session = sessionFactory.openSession();

        Category category = session.get(Category.class, id);

        session.close();

        return category;
    }
 // GET ALL CATEGORIES
    public List<Category> getAllCategories() {

        Session session = sessionFactory.openSession();

        List<Category> categories = session
                .createQuery("from Category", Category.class)
                .getResultList();

        session.close();

        return categories;
    }
}