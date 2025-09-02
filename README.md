# <h1 align="center"> :convenience_store: **Online Marketplace App** :shopping: </h1>

 ###    The application is a web project where users can register as Sellers or Customers, allowing sellers to create a virtual marketplace to sell their products, while customers can shop from these marketplaces.

___
## :bar_chart: ER Diagram

<div align="center">
  <img src="https://github.com/user-attachments/assets/d429a227-29bb-4a7d-b593-72a0a8e895f4" alt="ERD" width="900">
</div>



___
## :man_technologist: Tech Stack
- **Java 21**
- **Spring Boot 3.4.2** (Spring Data, Spring Security, JWT library)
- **MySQL 8.1**
- **Vue.js**
- Coming soon (Testing, Advanced Error Handling, Caching (Redis), Containerization (Docker)...)

___
## 📹 Demo Videos

### 1️⃣ Register (Seller)
<img src="https://github.com/user-attachments/assets/e98507aa-aded-4cbc-a52a-5900046507c9" width="600">

### 2️⃣ List a product (Seller)
<img src="https://github.com/user-attachments/assets/e70ca649-11fd-43b7-9ed5-33e6a0eaf500" width="600">

### 3️⃣ Register (Customer)
<img src="https://github.com/user-attachments/assets/acdb65cf-9e43-4537-aed3-94da37795f41" width="600">

### 4️⃣ Place an order (Customer)
<img src="https://github.com/user-attachments/assets/368c872e-1ffa-4bb3-9854-ba0898e52ea1" width="600">

### 5️⃣ Check incoming orders (Seller)
<img src="https://github.com/user-attachments/assets/103ee2e6-25b0-412b-8196-e297dfda9060" width="600">

### 6️⃣ Admin Panel (Not implemented)
<img src="https://github.com/user-attachments/assets/4f687338-2e7e-453c-8a33-e1173c235a6e" width="600">





___
## 📌 API Endpoints

### 🔹 Authentication (Seller-Customer-Staff)
| Method | Endpoint               | Description |
|--------|------------------------|-------------|
| POST   | `/api/v1/authentication/register/customer`    | Register as a new customer |
| POST   | `/api/v1/authentication/register/seller`    | Register as a new seller |
| POST   | `/api/v1/authentication/login`       | User login and token generation |

### 🔹 Products (Staff)
| Method | Endpoint              | Description |
|--------|-----------------------|-------------|
| GET    | `/api/v1/product-categories`        | Get all product categories |
| POST   | `/api/v1/product-categories`   | Create a new product category |
| DELETE | `/api/v1/product-categories/{productCategoryId}`        | Delete a product category |
| POST   | `/api/v1/product-categories/{productCategoryId}/products`   | Create a new product |
| DELETE | `/api/v1/products/{productId}`   | Delete a product |
| GET    | `/api/v1/products/{productId}`        | Get a product |


### 🔹 Products (Seller-Customer)
| Method | Endpoint              | Description |
|--------|-----------------------|-------------|
| GET    | `/api/v1/products/listed-products`        | Get all listed products |
| GET    | `/api/v1/product-categories/{productCategoryId}/products`   | Get all products of a product category |
| POST   | `/api/v1/products/{productId}/listed-products`        | Create a new listed product |
| GET    | `/api/v1/products/{productId}/listed-products`   | Get all my listed products for a product |


### 🔹 Shopping Cart (Customer)
| Method | Endpoint              | Description |
|--------|-----------------------|-------------|
| GET    | `/api/v1/cart`          | Get my shopping cart |
| POST   | `/api/v1/cart/{listedProductId}`          | Add a listed product to cart |
| DELETE   | `/api/v1/cart/{listedProductId}`          | Delete a listed product from cart |

### 🔹 Orders (Seller-Customer)
| Method | Endpoint              | Description |
|--------|-----------------------|-------------|
| POST    | `/api/v1/orders`          | Create a new order |
| GET   | `/api/v1/orders`          | Get my orders |
| GET   | `/api/v1/incoming-orders`          | Get my incoming orders |



