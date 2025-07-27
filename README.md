# 🍽️ Food Ordering App - Spring Boot Backend

This is a **Food Ordering REST API** built using **Java and Spring Boot**. It provides all the core backend functionalities required for an online food delivery platform — including user registration, menu browsing, order placement, and admin-based food item management.

Whether you're building a food delivery system, an internal restaurant dashboard, or just learning Spring Boot — this project gives you a scalable and modular backend foundation.

---

## 📝 Project Description

The **Food Ordering App** allows:
- **Customers** to register, log in, browse food items, add items to their cart, and place orders.
- **Admins** to manage food inventory by adding, updating, or deleting items from the menu.

All actions are protected using **JWT-based authentication**, and only authorized users can access specific endpoints.

This project demonstrates how to build a real-world, layered Spring Boot application using clean REST principles, proper service-repository-controller structure, and role-based access control.

---

## 🚀 Features

- 👤 **User Registration & Login**
- 📋 **Browse Menu**
- 🛒 **Add to Cart & Checkout**
- 🧾 **Place Orders**
- 🛠️ **Admin Panel to Add/Update/Delete Food Items**
- 🔐 **JWT-based Authentication**

---

## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot, Spring MVC, Spring Security
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Token)
- **Build Tool**: Maven
- **IDE**: IntelliJ / Eclipse

---

## 🧱 Modules / Entities

- **User**
- **FoodItem**
- **Order**
- **Cart**

---

## Screen Shots of some API services.
## 1. Register User as an Owner
<img width="1396" height="836" alt="Image" src="https://github.com/user-attachments/assets/ce491456-16d4-4de0-a279-f1fe8b3b0880" />


---

## 🔧 How to Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/krishnaydv22/foodApp_backend.git

2 **Set up MySQL Database**

Create a database, e.g. foodappdb

Configure your application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/foodappdb
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password

3 **Run the application**
mvn spring-boot:run

4 **Access Api**
http://localhost:8080/api/...

**🔐 Authentication**
JWT Token is required for protected endpoints.

After login, use the returned token as:

Authorization: Bearer <token>

**📬 Example Endpoints**
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login user & get JWT
GET	/api/foods	Get all food items
POST	/api/orders	Place an order
GET	/api/orders/user	Get user's order history


**📦 Dependencies**
Spring Web

Spring Data JPA

Spring Security

MySQL Connector

JWT (io.jsonwebtoken)



   


