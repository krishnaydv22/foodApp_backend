# 🍽️ Food Land - Online Food Delivery Platform

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

## 🔧 Usage
## Customer:
**Browse Restaurants:** View all available restaurants. <br>
**Add to Cart:** Select food items and add them to the cart. <br>
**Place Order:** Add delivery address and proceed to payment. <br>
**Order Tracking:** Track your order status.<br>
## Restaurant Owner:
**Manage Menu:** Add, edit, or remove food items. <br>
**View Orders:** Check incoming orders and update their status. <br>
**Host Events:** Create and manage restaurant events. <br>

## 🛡️ Security
**Authentication:** JWT for secure API access. <br>
**Encryption:** Passwords are hashed using bcrypt. <br>
**Data Validation:** Backend input validation using Hibernate Validator. <br>

## Screen Shots of some API services.
## 1. Register User as an Owner
<img width="1396" height="836" alt="Image" src="https://github.com/user-attachments/assets/ce491456-16d4-4de0-a279-f1fe8b3b0880" />

## 2. Login user using Email and Password
<img width="1367" height="713" alt="Image" src="https://github.com/user-attachments/assets/4f536332-af0a-4ac5-b460-58159994a67b" />

## 3. Verify Using JWT Token
<img width="1386" height="783" alt="Image" src="https://github.com/user-attachments/assets/0bcc4b6f-bae8-462b-91fc-e75a47d0712c" />


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

## All API services Endpoints Docs Screen Shots from Swagger UI
<img width="1907" height="785" alt="Image" src="https://github.com/user-attachments/assets/54968407-eea3-497c-b531-ba7c75590eff" />

<img width="1867" height="847" alt="Image" src="https://github.com/user-attachments/assets/74679ec0-0c28-4119-8bc3-f2fdd3c2082c" />

<img width="1862" height="642" alt="Image" src="https://github.com/user-attachments/assets/dc021a5c-ae67-4dbb-b568-fd0be497062f" />

<img width="1847" height="755" alt="Image" src="https://github.com/user-attachments/assets/1a07707d-db8e-423f-9a38-0bd261758a03" />

<img width="1840" height="760" alt="Image" src="https://github.com/user-attachments/assets/59ab52c8-1c49-4230-a49f-94658f9b1366" />

<img width="1828" height="367" alt="Image" src="https://github.com/user-attachments/assets/26bac1da-29a9-43b5-a67f-071ee7c8e736" />







   


