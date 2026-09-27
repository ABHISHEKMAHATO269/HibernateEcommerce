# CRUD Test Cases - Hibernate Ecommerce

## 1. Category CRUD

### Create Category
- Input:
  - Name: Electronics
  - Description: Electronic products
- Expected Result:
  - Category is saved successfully.
  - Auto-generated ID is returned.

### Read Category
- Input:
  - Category ID: 1
- Expected Result:
  - Category details are displayed.

### Update Category
- Input:
  - Change category name/description.
- Expected Result:
  - Updated category is saved in the database.

### Delete Category
- Input:
  - Existing Category ID
- Expected Result:
  - Category is removed according to the configured relationship rules.


## 2. Product CRUD

### Create Product
- Input:
  - Name: Wireless Mouse
  - Price: 799.00
  - Stock: 50
  - Category: Electronics
- Expected Result:
  - Product is saved successfully.

### Read Product
- Input:
  - Product ID: 1
- Expected Result:
  - Product name, price, stock and category are displayed.

### Update Product
- Input:
  - Existing Product ID
  - Updated price or stock
- Expected Result:
  - Product is updated successfully.

### Delete Product
- Input:
  - Existing Product ID
- Expected Result:
  - Product is deleted according to the configured relationship rules.


## 3. User CRUD

### Create User
- Username: abhishek
- Email: abhishek@example.com
- Role: CUSTOMER

### Read User
- Input:
  - User ID: 1
- Expected Result:
  - Username, email and role are displayed.

### Update User
- Input:
  - Existing User ID
  - Updated user information
- Expected Result:
  - User information is updated.

### Delete User
- Input:
  - Existing User ID
- Expected Result:
  - User is deleted according to the configured relationship rules.


## 4. Order CRUD

### Create Order
- User ID: 1
- Product ID: 1
- Quantity: 2
- Expected Result:
  - Order is created.
  - Order details are created.
  - Product stock is reduced.
  - Total amount is calculated.

### Create Multi-Product Order
- User ID: 1
- Product 1: Wireless Mouse
- Quantity: 2
- Product 2: Laptop
- Quantity: 1
- Expected Result:
  - One order contains multiple order details.
  - Total amount is calculated correctly.

### Read Order
- Input:
  - Order ID: 3
- Expected Result:
  - Order details are displayed with:
    - User
    - Products
    - Quantities
    - Unit prices
    - Categories
    - Total amount

### Update Order
- Input:
  - Existing Order ID
  - Updated order information
- Expected Result:
  - Order is updated successfully.

### Delete Order
- Input:
  - Existing Order ID
- Expected Result:
  - Order is removed according to the configured relationship rules.S