# Restaurant Billing System 🍽️

> **Syntecxhub Internship - Java Programming (Week 1 Task)**

A robust Command Line Interface (CLI) application built in Java to manage restaurant orders, inventory, and billing. This project demonstrates modular programming, object-oriented concepts, and dynamic data handling.

## 📖 Project Overview

This application simulates a real-world restaurant billing workflow. It allows customers (or staff) to view a menu, add multiple items to a cart, adjust quantities dynamically, and generate a professional invoice with tax calculations.

**Key Highlights:**

- **Dynamic Ordering:** Add items multiple times or in batches.
- **Smart Modification:** Reduce item quantity (e.g., change 5 burgers to 3) or remove items entirely.
- **Automated Billing:** accurate subtotal and GST calculation.

## ✨ Features

- **View Menu:** Displays a formatted list of food items with IDs and prices.
- **Add to Order:** Users can select items by ID and specify quantities. The system handles multiple entries for the same item by updating the count.
- **Modify Order:**
  - **Reduce Quantity:** If a user ordered too many items, they can reduce the count without removing the item entirely.
  - **Remove Item:** Delete an item from the order list completely.
- **Generate Bill:** Produces a detailed receipt showing:
  - Itemized breakdown (Name, Qty, Price)
  - Subtotal
  - [cite_start]GST (5%) [cite: 53]
  - Grand Total

## 🛠️ Tech Stack

- **Language:** Java (JDK 8 or higher)
- **Concepts Used:**
  - [cite_start]Object-Oriented Programming (Classes & Objects) [cite: 55]
  - Collections Framework (`ArrayList`)
  - Input/Output (`Scanner` class)
  - Control Flow (Loops, Switch Cases)

## 🚀 How to Run

### Prerequisites

- Ensure **Java Development Kit (JDK)** is installed on your system.

### Steps

1.  **Clone the Repository:**

    ```bash
    git clone [https://github.com/AdityaKumarSah/Syntecxhub_Restaurant_Billing_System.git](https://github.com/AdityaKumarSah/Syntecxhub_Restaurant_Billing_System.git)
    ```

    _(Note: Replace `AdityaKumarSah` with your actual GitHub username)_

2.  **Navigate to the folder:**

    ```bash
    cd Syntecxhub_Restaurant_Billing_System
    ```

3.  **Compile the Java file:**

    ```bash
    javac RestaurantBillingSystem.java
    ```

4.  **Run the application:**
    ```bash
    java RestaurantBillingSystem
    ```

## 📸 Application Flow (Usage)

1.  **Main Menu:** The app starts by offering options to View Menu, Order, Adjust, or Bill.
2.  **Ordering:** Enter the `Food ID` (e.g., `1` for Burger) and `Quantity`. You can keep adding items until you are done.
3.  **Billing:** Select `Generate Bill` to see the final output. The app then asks if you want to start a new order for the next customer.

## 👤 Author

**Aditya Kumar Sah**

- **Role:** Java Development Intern
- **Organization:** Syntecxhub

---

[cite_start]_This project was developed as part of the Syntecxhub Internship Program._ [cite: 5]
