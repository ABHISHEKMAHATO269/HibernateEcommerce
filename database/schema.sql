CREATE DATABASE IF NOT EXISTS ecommerce_db;

USE ecommerce_db;

-- =========================================
-- 1. CATEGORIES
-- =========================================

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) DEFAULT NULL,
    name VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- =========================================
-- 2. USERS
-- =========================================

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) DEFAULT NULL,
    password VARCHAR(255) DEFAULT NULL,
    role VARCHAR(255) DEFAULT NULL,
    username VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

-- =========================================
-- 3. PRODUCTS
-- =========================================

CREATE TABLE IF NOT EXISTS products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) DEFAULT NULL,
    price DECIMAL(38,2) DEFAULT NULL,
    stockQuantity INT DEFAULT NULL,
    category_id BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    KEY idx_products_category (category_id),
    CONSTRAINT fk_products_category
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
);

-- =========================================
-- 4. ORDERS
-- =========================================

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    orderDate DATETIME(6) DEFAULT NULL,
    totalAmount DECIMAL(38,2) DEFAULT NULL,
    user_id BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    KEY idx_orders_user (user_id),
    CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

-- =========================================
-- 5. ORDER DETAILS
-- =========================================

CREATE TABLE IF NOT EXISTS order_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    quantity INT DEFAULT NULL,
    unitPrice DECIMAL(38,2) DEFAULT NULL,
    order_id BIGINT DEFAULT NULL,
    product_id BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    KEY idx_order_details_order (order_id),
    KEY idx_order_details_product (product_id),
    CONSTRAINT fk_order_details_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id),
    CONSTRAINT fk_order_details_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);

-- =========================================
-- END OF SCHEMA
-- =========================================