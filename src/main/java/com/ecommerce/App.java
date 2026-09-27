package com.ecommerce;

import java.math.BigDecimal;
import com.ecommerce.entity.OrderDetails;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Users;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import com.ecommerce.util.HibernateUtil;

public class App {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        UserService userService =
                new UserService(sessionFactory);
        
        

        ProductService productService =
                new ProductService(sessionFactory);

        OrderService orderService =
                new OrderService(sessionFactory);

        CategoryService categoryService =
                new CategoryService(sessionFactory);

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("   E-COMMERCE MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Category");
            System.out.println("2. List All Categories");
            System.out.println("3. Add Product");
            System.out.println("4. List All Products");
            System.out.println("5. Register User");
            System.out.println("6. Login");
            System.out.println("7. Find User");
            System.out.println("8. Find Product");
            System.out.println("9. Update Product");
            System.out.println("10. Delete Product");
            System.out.println("11. Create Order");
            System.out.println("12. Find Order");
            System.out.println("13. Exit");
            System.out.println("14. Create Multi-Product Order");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                // ==============================
                // 1. ADD CATEGORY
                // ==============================

                case 1:

                    scanner.nextLine();

                    System.out.print("Enter category name: ");
                    String categoryName = scanner.nextLine();

                    System.out.print("Enter category description: ");
                    String categoryDescription = scanner.nextLine();

                    Category category =
                            categoryService.createCategory(
                                    categoryName,
                                    categoryDescription
                            );

                    System.out.println(
                            "Category created successfully!"
                    );

                    System.out.println(
                            "Category ID: " + category.getId()
                    );

                    break;

                // ==============================
                // 2. LIST CATEGORIES
                // ==============================

                case 2:

                    System.out.println();
                    System.out.println(
                            "========== ALL CATEGORIES =========="
                    );

                    List<Category> categories =
                            categoryService.getAllCategories();

                    if (categories.isEmpty()) {

                        System.out.println(
                                "No categories found."
                        );

                    } else {

                        for (Category c : categories) {

                            System.out.println(
                                    "-------------------------------"
                            );

                            System.out.println(
                                    "ID: " + c.getId()
                            );

                            System.out.println(
                                    "Name: " + c.getName()
                            );

                            System.out.println(
                                    "Description: "
                                            + c.getDescription()
                            );
                        }
                    }

                    break;

                // ==============================
                // 3. ADD PRODUCT
                // ==============================

                case 3:

                    scanner.nextLine();

                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();

                    System.out.print("Enter product price: ");
                    BigDecimal productPrice =
                            scanner.nextBigDecimal();

                    System.out.print("Enter stock quantity: ");
                    Integer stockQuantity =
                            scanner.nextInt();

                    System.out.print("Enter category ID: ");
                    Long categoryId =
                            scanner.nextLong();

                    Product newProduct =
                            productService.createProduct(
                                    productName,
                                    productPrice,
                                    stockQuantity,
                                    categoryId
                            );

                    if (newProduct != null) {

                        System.out.println();
                        System.out.println(
                                "Product created successfully!"
                        );

                        System.out.println(
                                "Product ID: "
                                        + newProduct.getId()
                        );

                        System.out.println(
                                "Product Name: "
                                        + newProduct.getName()
                        );

                        System.out.println(
                                "Price: ₹"
                                        + newProduct.getPrice()
                        );

                        System.out.println(
                                "Stock: "
                                        + newProduct.getStockQuantity()
                        );

                    } else {

                        System.out.println(
                                "Product could not be created."
                        );
                    }

                    break;

                // ==============================
                // 4. LIST PRODUCTS
                // ==============================

                case 4:

                    System.out.println();
                    System.out.println(
                            "========== ALL PRODUCTS =========="
                    );

                    List<Product> products =
                            productService.getAllProducts();

                    if (products.isEmpty()) {

                        System.out.println(
                                "No products found."
                        );

                    } else {

                        for (Product p : products) {

                            System.out.println(
                                    "-------------------------------"
                            );

                            System.out.println(
                                    "ID: " + p.getId()
                            );

                            System.out.println(
                                    "Name: " + p.getName()
                            );

                            System.out.println(
                                    "Price: ₹" + p.getPrice()
                            );

                            System.out.println(
                                    "Stock: "
                                            + p.getStockQuantity()
                            );

                            System.out.println(
                                    "Category: "
                                            + p.getCategory().getName()
                            );
                        }
                    }

                    break;

                // ==============================
                // 5. REGISTER USER
                // ==============================

                case 5:

                    scanner.nextLine();

                    System.out.print("Enter username: ");
                    String username =
                            scanner.nextLine();

                    System.out.print("Enter password: ");
                    String password =
                            scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email =
                            scanner.nextLine();

                    Users user =
                            userService.createUser(
                                    username,
                                    password,
                                    email,
                                    "CUSTOMER"
                            );

                    System.out.println(
                            "User created successfully!"
                    );

                    System.out.println(
                            "User ID: " + user.getId()
                    );

                    break;

                // ==============================
                // 6. LOGIN
                // ==============================

                case 6:

                    scanner.nextLine();

                    System.out.print("Enter username: ");
                    String loginUsername =
                            scanner.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword =
                            scanner.nextLine();

                    Users loggedInUser =
                            userService.login(
                                    loginUsername,
                                    loginPassword
                            );

                    if (loggedInUser != null) {

                        System.out.println();
                        System.out.println(
                                "Login successful!"
                        );

                        System.out.println(
                                "Welcome, "
                                        + loggedInUser.getUsername()
                        );

                        System.out.println(
                                "Role: "
                                        + loggedInUser.getRole()
                        );

                    } else {

                        System.out.println(
                                "Invalid username or password."
                        );
                    }

                    break;

                // ==============================
                // 7. FIND USER
                // ==============================

                case 7:

                    System.out.print("Enter User ID: ");
                    Long userId =
                            scanner.nextLong();

                    Users foundUser =
                            userService.getUser(userId);

                    if (foundUser != null) {

                        System.out.println();
                        System.out.println(
                                "User ID: "
                                        + foundUser.getId()
                        );

                        System.out.println(
                                "Username: "
                                        + foundUser.getUsername()
                        );

                        System.out.println(
                                "Email: "
                                        + foundUser.getEmail()
                        );

                        System.out.println(
                                "Role: "
                                        + foundUser.getRole()
                        );

                    } else {

                        System.out.println(
                                "User not found."
                        );
                    }

                    break;

                // ==============================
                // 8. FIND PRODUCT
                // ==============================

                case 8:

                    System.out.print(
                            "Enter Product ID: "
                    );

                    Long productId =
                            scanner.nextLong();

                    Product product =
                            productService.getProduct(
                                    productId
                            );

                    if (product != null) {

                        System.out.println();
                        System.out.println(
                                "Product ID: "
                                        + product.getId()
                        );

                        System.out.println(
                                "Name: "
                                        + product.getName()
                        );

                        System.out.println(
                                "Price: ₹"
                                        + product.getPrice()
                        );

                        System.out.println(
                                "Stock: "
                                        + product.getStockQuantity()
                        );

                        System.out.println(
                                "Category: "
                                        + product.getCategory()
                                                .getName()
                        );

                    } else {

                        System.out.println(
                                "Product not found."
                        );
                    }

                    break;

                // ==============================
                // 9. UPDATE PRODUCT
                // ==============================

                case 9:

                    System.out.print(
                            "Enter Product ID: "
                    );

                    Long updateId =
                            scanner.nextLong();

                    scanner.nextLine();

                    System.out.print(
                            "Enter new name: "
                    );

                    String newName =
                            scanner.nextLine();

                    System.out.print(
                            "Enter new price: "
                    );

                    BigDecimal newPrice =
                            scanner.nextBigDecimal();

                    System.out.print(
                            "Enter new stock: "
                    );

                    Integer newStock =
                            scanner.nextInt();

                    productService.updateProduct(
                            updateId,
                            newName,
                            newPrice,
                            newStock
                    );

                    System.out.println(
                            "Product updated successfully!"
                    );

                    break;

                // ==============================
                // 10. DELETE PRODUCT
                // ==============================

                case 10:

                    System.out.print(
                            "Enter Product ID: "
                    );

                    Long deleteId =
                            scanner.nextLong();

                    productService.deleteProduct(
                            deleteId
                    );

                    System.out.println(
                            "Product deleted successfully!"
                    );

                    break;

                // ==============================
                // 11. CREATE ORDER
                // ==============================

                case 11:

                    System.out.print(
                            "Enter User ID: "
                    );

                    Long orderUserId =
                            scanner.nextLong();

                    System.out.print(
                            "Enter Product ID: "
                    );

                    Long orderProductId =
                            scanner.nextLong();

                    System.out.print(
                            "Enter Quantity: "
                    );

                    Integer quantity =
                            scanner.nextInt();

                    Orders order =
                            orderService.createOrder(
                                    orderUserId,
                                    orderProductId,
                                    quantity
                            );

                    if (order != null) {

                        System.out.println();
                        System.out.println(
                                "Order created successfully!"
                        );

                        System.out.println(
                                "Order ID: "
                                        + order.getId()
                        );

                        System.out.println(
                                "Total Amount: ₹"
                                        + order.getTotalAmount()
                        );

                    } else {

                        System.out.println(
                                "Order could not be created."
                        );
                    }

                    break;

                // ==============================
                // 12. FIND ORDER
                // ==============================

                case 12:

                    System.out.print(
                            "Enter Order ID: "
                    );

                    Long orderId =
                            scanner.nextLong();

                    Orders foundOrder =
                            orderService.getOrder(
                                    orderId
                            );

                    if (foundOrder != null) {

                        System.out.println();
                        System.out.println(
                                "Order ID: "
                                        + foundOrder.getId()
                        );

                        System.out.println(
                                "Order Date: "
                                        + foundOrder.getOrderDate()
                        );

                        System.out.println(
                                "Total Amount: ₹"
                                        + foundOrder.getTotalAmount()
                        );

                        System.out.println(
                                "User: "
                                        + foundOrder.getUser()
                                                .getUsername()
                        );
                        System.out.println();
                        System.out.println("Order Details:");
                        System.out.println("-------------------------");

                        for (OrderDetails detail : foundOrder.getOrderDetails()) {

                            System.out.println(
                                    "Product: " + detail.getProduct().getName()
                            );

                            System.out.println(
                                    "Quantity: " + detail.getQuantity()
                            );

                            System.out.println(
                                    "Unit Price: ₹" + detail.getUnitPrice()
                            );

                            System.out.println(
                                    "Category: "
                                    + detail.getProduct().getCategory().getName()
                            );

                            System.out.println("-------------------------");
                        }

                    } else {

                        System.out.println(
                                "Order not found."
                        );
                    }

                    break;

                // ==============================
                // 13. EXIT
                // ==============================

                case 13:

                    running = false;

                    System.out.println(
                            "Thank you for using the system!"
                    );

                    break;
                    
                case 14:

                    System.out.println("=== Create Multi-Product Order ===");

                    System.out.print("Enter User ID: ");
                    Long multiUserId = scanner.nextLong();

                    System.out.print("Enter first Product ID: ");
                    Long productId1 = scanner.nextLong();

                    System.out.print("Enter first Quantity: ");
                    Integer quantity1 = scanner.nextInt();

                    System.out.print("Enter second Product ID: ");
                    Long productId2 = scanner.nextLong();

                    System.out.print("Enter second Quantity: ");
                    Integer quantity2 = scanner.nextInt();

                    Long[] productIds = {
                        productId1,
                        productId2
                    };

                    Integer[] quantities = {
                        quantity1,
                        quantity2
                    };

                    Orders multiOrder =
                            orderService.createMultiProductOrder(
                                    multiUserId,
                                    productIds,
                                    quantities
                            );

                    if (multiOrder != null) {

                        System.out.println(
                                "Multi-product order created successfully!"
                        );

                        System.out.println(
                                "Order ID: " + multiOrder.getId()
                        );

                        System.out.println(
                                "Total Amount: ₹"
                                + multiOrder.getTotalAmount()
                        );

                    } else {

                        System.out.println(
                                "Failed to create multi-product order."
                        );
                    }

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
        sessionFactory.close();
    }
}