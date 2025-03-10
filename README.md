---
# Byte Me
_A GUI + CLI-based Food Ordering System using JavaFX_

[![Language:Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-11-blue.svg)](https://openjfx.io/)
[![JUnit](https://img.shields.io/badge/JUnit-5-green.svg)](https://junit.org/junit5/)

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Assumptions](#assumptions)
4. [Implementation Details](#implementation-details)

---

## Overview

**Byte Me** is a comprehensive food ordering system developed in Java using the JavaFX framework. This application features both graphical (GUI) and command-line (CLI) interfaces, enabling users to easily browse, order, and review food items. The system supports both user and admin roles with functionalities such as login/sign-up, menu management, order placement, cart operations, and item reviews. Data persistence is achieved using serialization, and JUnit is utilized for thorough testing. Managed by an administrator who oversees orders and ensures smooth operation, Byte Me demonstrates robust exception handling, abstraction, and other essential programming practices.

---

## Features

- **User & Admin Roles:**  
  Supports separate functionalities for customers (order placement, cart operations, reviews) and administrators (menu management, order tracking, and system updates).
- **Order Management:**  
  Seamlessly place orders, track order statuses, and manage food items.
- **User Reviews:**  
  Allows customers to submit anonymous reviews for items that have been delivered.
- **VIP Customer Benefits:**  
  Differentiates between VIP and non-VIP customers—VIPs enjoy delivery fee waivers.
- **Robust Exception Handling:**  
  Comprehensive error management ensures smooth system performance.
- **Data Persistence:**  
  Uses serialization to maintain data across sessions.
- **Automated Testing:**  
  JUnit tests ensure reliable functionality and facilitate maintenance.

---

## Assumptions

- **Admin Credentials:**  
  - Username: `Admin`  
  - Password: `Admin@123`  
  - No sign-up option is available for the administrator.
- **Order Management:**  
  - The admin can update the status of the first three prior orders, with VIP orders given priority.
- **Delivery Fees:**  
  - Non-VIP customers are charged a delivery fee of 20.  
  - VIP customers enjoy free delivery.
- **Refund Policy:**  
  - Refunds can only be processed for orders with a status of `Denied` or `Cancelled`.
- **Entity Identification:**  
  - Customers are uniquely identified by their `email`.  
  - Food items are uniquely identified by their `name`.  
  - Orders are uniquely identified by their `orderID`.
- **Security:**  
  - A maximum of three incorrect login attempts is allowed.
- **Review Submission:**  
  - Customers can only add reviews for items they have ordered and received.  
  - Reviews are submitted anonymously, and multiple reviews per item are allowed.
- **Order Statuses:**  
  - `Received`: Order has just been placed by the customer.  
  - `Preparing`: Order is being prepared.  
  - `Out For Delivery`: Order is ready and has been dispatched.  
  - `Delivered`: Order has been successfully received by the customer.  
  - `Cancelled`: Order has been cancelled by the customer.  
  - `Denied`: Order contains removed items and is either not prepared or already delivered.

---

## Implementation Details

For a comprehensive overview of the system's architecture, design decisions, and coding practices, please refer to the [Implementation Details](implementation.md) document.

---
