# 🚀 Smart ATM System (Java + MySQL + JDBC)

## 📌 Project Overview
Designed and developed a **Smart ATM System** using **Java** and integrated it with a **MySQL** database using **JDBC**. This project simulates real-world banking operations including:
- 🏦 Account Creation
- 💰 Balance Inquiry
- ➕ Deposit
- ➖ Withdrawal
- 📄 Mini Statement Generation

---

## ✅ Key Features & Concepts Used

### 🧠 Java Core & OOP
- ✅ Java Fundamentals: Classes, objects, data types, control structures
- 🧩 Object-Oriented Programming: Inheritance used in account and transaction modules
- 🔐 Access Modifiers: Applied for encapsulation and secure access to fields
- ⚠️ Exception Handling: Robust handling for invalid PINs, insufficient balance, etc.

### 💾 Database Integration (JDBC)
- 📂 `personal_details` table: Stores user data
- 📂 `transaction_details` table: Logs deposits, withdrawals, and timestamps
- 🔗 JDBC API: Used to connect Java with MySQL and perform **CRUD** operations

### 📊 Mini Statement
- Transaction history is fetched from the database using **SQL SELECT queries** and displayed to the user in a readable format.

### 🔑 Secure Login System
- User credentials are verified before permitting access to ATM operations.

---

## 🛠 Tools & Technologies

| Category        | Tools/Technologies               |
|----------------|----------------------------------|
| Language        | Java                             |
| Backend         | MySQL                            |
| DB Connection   | JDBC                             |
| IDE             | IntelliJ IDEA                    |
| DB Tools        | MySQL Workbench                  |

---

## 📷 Screenshots (optional)
> *(Add images or terminal outputs here if available)*

---

## 📂 Folder Structure (optional)
```bash
Smart-ATM-System/
│
├── src/
│   ├── com/
│       ├── DBConnection.java
│       ├── ATM.java
│       ├── Account.java
│       └── Transaction.java
│
├── SQL/
│   └── DataBase.sql
│
└── README.md
