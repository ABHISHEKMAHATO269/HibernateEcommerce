package com.ecommerce.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(com.ecommerce.entity.Category.class)
                    .addAnnotatedClass(com.ecommerce.entity.Product.class)
                    .addAnnotatedClass(com.ecommerce.entity.Users.class)
                    .addAnnotatedClass(com.ecommerce.entity.Orders.class)
                    .addAnnotatedClass(com.ecommerce.entity.OrderDetails.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed.");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}