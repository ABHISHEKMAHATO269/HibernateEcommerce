package com.ecommerce.service;

import org.mindrot.jbcrypt.BCrypt;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entity.Users;

public class UserService {

    private SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // CREATE USER
    public Users createUser(String username, String password,
                            String email, String role) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hashedPassword = BCrypt.hashpw(
                password,
                BCrypt.gensalt()
        );

        Users user = new Users(
                username,
                hashedPassword,
                email,
                role
        );

        session.persist(user);

        transaction.commit();
        session.close();

        return user;
    }

    // READ USER
    public Users getUser(Long id) {

        Session session = sessionFactory.openSession();

        Users user = session.get(Users.class, id);

        session.close();

        return user;
    }

    // DELETE USER
    public void deleteUser(Long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Users user = session.get(Users.class, id);

        if (user != null) {
            session.remove(user);
        }

        transaction.commit();
        session.close();
    }
 // LOGIN USER
    public Users login(String username, String password) {

        Session session = sessionFactory.openSession();

        Users user = session.createQuery(
                "from Users where username = :username",
                Users.class
        )
        .setParameter("username", username)
        .uniqueResult();

        if (user != null &&
            BCrypt.checkpw(password, user.getPassword())) {

            session.close();
            return user;
        }

        session.close();
        return null;
    }
    public boolean resetPassword(String username, String newPassword) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Users user = session.createQuery(
                "from Users where username = :username",
                Users.class
        )
        .setParameter("username", username)
        .uniqueResult();

        if (user == null) {
            transaction.rollback();
            session.close();
            return false;
        }

        String hashedPassword = BCrypt.hashpw(
                newPassword,
                BCrypt.gensalt()
        );

        user.setPassword(hashedPassword);

        session.merge(user);

        transaction.commit();
        session.close();

        return true;
    }
}