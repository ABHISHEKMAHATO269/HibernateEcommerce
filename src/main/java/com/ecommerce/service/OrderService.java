package com.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Users;

public class OrderService {

    private SessionFactory sessionFactory;

    public OrderService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE ORDER
    public Orders createOrder(Long userId, Long productId, Integer quantity) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Find User
        Users user = session.get(Users.class, userId);

        if (user == null) {
            transaction.rollback();
            session.close();
            return null;
        }

        // Find Product
        Product product = session.get(Product.class, productId);

        if (product == null) {
            transaction.rollback();
            session.close();
            return null;
        }

     // Validate quantity
        if (quantity == null || quantity <= 0) {
            transaction.rollback();
            session.close();
            return null;
        }

        // Check stock
        if (product.getStockQuantity() < quantity) {
            transaction.rollback();
            session.close();
            return null;
        }

        // Calculate total
        BigDecimal totalAmount = product.getPrice()
                .multiply(BigDecimal.valueOf(quantity));

        // Create Order
        Orders order = new Orders(
                LocalDateTime.now(),
                totalAmount,
                user
        );

        session.persist(order);

        // Create Order Details
        OrderDetails orderDetails = new OrderDetails(
                quantity,
                product.getPrice(),
                order,
                product
        );

        session.persist(orderDetails);

        // Reduce stock
        product.setStockQuantity(
                product.getStockQuantity() - quantity
        );

        transaction.commit();

        session.close();

        return order;
    }
 // CREATE ORDER WITH MULTIPLE PRODUCTS
    public Orders createMultiProductOrder(
            Long userId,
            Long[] productIds,
            Integer[] quantities) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Find User
            Users user = session.get(Users.class, userId);

            if (user == null) {
                transaction.rollback();
                session.close();
                return null;
            }

            // Validate arrays
            if (productIds == null ||
                quantities == null ||
                productIds.length == 0 ||
                productIds.length != quantities.length) {

                transaction.rollback();
                session.close();
                return null;
            }

            // Calculate total amount
            BigDecimal totalAmount = BigDecimal.ZERO;

            // Create Order first
            Orders order = new Orders(
                    LocalDateTime.now(),
                    totalAmount,
                    user
            );

            session.persist(order);

            // Add multiple products
            for (int i = 0; i < productIds.length; i++) {

                Product product =
                        session.get(Product.class, productIds[i]);

                Integer quantity = quantities[i];

                // Validate product
                if (product == null) {
                    transaction.rollback();
                    session.close();
                    return null;
                }

                // Validate quantity
                if (quantity == null || quantity <= 0) {
                    transaction.rollback();
                    session.close();
                    return null;
                }

                // Check stock
                if (product.getStockQuantity() < quantity) {
                    transaction.rollback();
                    session.close();
                    return null;
                }

                // Calculate item total
                BigDecimal itemTotal =
                        product.getPrice()
                                .multiply(
                                    BigDecimal.valueOf(quantity)
                                );

                totalAmount =
                        totalAmount.add(itemTotal);

                // Create OrderDetails
                OrderDetails orderDetails =
                        new OrderDetails(
                                quantity,
                                product.getPrice(),
                                order,
                                product
                        );

                session.persist(orderDetails);

                // Reduce stock
                product.setStockQuantity(
                        product.getStockQuantity() - quantity
                );
            }

            // Update order total
            order.setTotalAmount(totalAmount);

            transaction.commit();

            session.close();

            return order;

        } catch (Exception e) {

            transaction.rollback();
            session.close();

            e.printStackTrace();

            return null;
        }
    }
 // READ ORDER WITH USER, ORDER DETAILS AND PRODUCTS
    public Orders getOrder(Long orderId) {

        Session session = sessionFactory.openSession();

        Orders order = session.createQuery(
                "select distinct o " +
                "from Orders o " +
                "join fetch o.user " +
                "left join fetch o.orderDetails od " +
                "left join fetch od.product " +
                "where o.id = :orderId",
                Orders.class
        )
        .setParameter("orderId", orderId)
        .uniqueResult();

        session.close();

        return order;
    }
}