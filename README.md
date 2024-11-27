<hr>

# <u>Byte Me</u> -

<i>A GUI + CLI-based Food Ordering System using JavaFX</i>
<hr>

## Name - Ujjval Dargar

## Roll no. - 2023564

<hr>

## Table of Content

1. [Overview](#overview)
2. [Classes](#classes)
3. [Interfaces](#interfaces)
4. [Tests](#tests)
5. [Files](#files)
4. [Exceptions](#exceptions)
5. [OOPs Concept](#interfaces)
6. [Assumptions](#assumptions)
7. [GitHub Link](#github-link---byteme)

## Overview

- This project is a javaFX interface based food ordering system designed to order food items and managed by admin,
  implemented in Java. Also demonstrate various fundamental OOP concepts—such as
  classes, interfaces, inheritance, polymorphism, encapsulation, generic programming, exception handling and
  abstraction.

## Classes

1. ### Admin :
   Provide various methods and attributes related to Admin for managing menu and orders.

2. ### Customer :
   Provide various methods and attributes related to Customer for ordering food items and managing orders.

3. ### Item :
   Provide various methods and attributes related to Item for storing and accessing related details like
   ItemName,Price,Category,etc.

4. ### Order :
   Provide various methods and attributes related to Order for storing and accessing related details like
   OrderId,Items,Customer,etc.

5. ### Review :
   Provide various methods and attributes related to Review for storing and accessing related details like
   Items,Rating,Description,etc.

6. ### TextFormat (abstract)

   #### Methods
    - `textCenter()` : return a formatted string which has text at center and fills empty space with '-'
    - `cls()` : clear the console for more readable view
    - `pause()` : pause execution until pressed Enter

7. ### App

   #### Attributes
    - `appCustomers` : stores all signed customer
    - `appItems` : stores all added items
    - `appOrders` : stores all order from non-vip customers
    - `appVipOrders` : stores all order from VIP customers
    - `appReviews` : stores all item reviews

   #### Methods
    - Corresponding getters and setters for above all attributes

    - `Menu()` : provide main menu to select user type
    - `viewItems()` : show all details of the courses provided to its parameter
    - `viewCart()` : show all details of the student provided to its parameter
    - `viewOrders()` : show all details of the complaints provided to its parameter
    - `viewReviews()` : show all details of the complaints provided to its parameter

8. ### Pair
   Used to store a pair of objects

## Interfaces

Following interfaces provides blueprint of their corresponding classes -

1. `Admin_Interface`
2. `Customer_Interface`

## Tests

1. `AdminLoginTest` : Test cases to check admin login on wrong and correct case.
2. `CustomerLoginTest` : Test cases to check customer login on wrong and correct case.
3. `ItemOrderingTest` : Test cases to check adding of an out-of-stock order.

## Files

1. `Customers.ser` : stores an TreeMap of Customer email and object used for logging and signing.
2. `Orders.ser` : stores an TreeMap of OrderID and its corresponding Order object for a Non-Vip customer.
3. `VipOrders.ser` : stores an TreeMap of OrderID and its corresponding Order object for a Vip customer..

## Exceptions

- `InvalidLoginException` : Wrong email or password

## OOPs Concept

1. **Encapsulation** :<br>
   It is implemented by making data or attributes private within each class and
   provides public getter and setter methods to access or set it.<br>
   For example:
    - The `Customer` class encapsulates `name`, `email`, `password` and provides method like `getName()`, `setName()`,
      `setEmail()`, etc.


2. **Abstract Class** :<br>
   The `TextFormat` class is an abstract class which implements some of the common properties and behaviors which is
   used in almost all classes (`Customer`, `Admin`, and `Item`).
   It is inherited by `Customer`, `Admin` class.


3. **Inheritance** :<br>
   It is applied by creating a base class `App` which is inherited by `Admin`, `Customer`, `Order`. Each
   subclass extends the functionality of `App`.


4. **Interfaces** : <br>
   `Admin_Interface`, `Customer_Interface` interface defines the contract for following classes - `Admin`, `Customer`.
   This ensures that any class implementing this interface must provide concrete implementations for these
   methods.


5. **Polymorphism** : <br>
   It is demonstrated by method overriding. All subclasses of `App` (`Customer`, `Admin`, `Order`) override the `Menu()`
   method to display their specific menu.


6. **Generic Programming** : <br>
   It is demonstrated by the `Pair<T,U>` class which is a generic class designed to store a pair.


7. **Exception Handling** : <br>
   Custom exceptions are used to handle specific errors, such as :

    - **Invalid login credentials** : `InvalidLoginException`

## Assumptions

- **Fixed username for Administration** : Admin
- **Fixed password for Administration** : Admin@123
- **No sign up for Administration**
- The admin can update the status of first three prior orders only with VIP orders at first.
- For a non-VIP customer, there is a delivery fee of 20.
- For a VIP customer, there is no delivery fee.
- Admin can only refund the orders which are `Denied` or `Cancelled`.
- `Customer` is identified uniquely by their `email`.
- `Item` is identified uniquely by their `name`.
- `Order` is identified uniquely by their `orderID`.
- During login only three wrong password tries given.
- Customer can only add review for items that he/she has ordered and delivered.
- A customer's review is anonymous.
- A customer can submit any number of reviews for an item.
- An order has the following status - 
  - `Recieved` : When order is just placed by a customer
  - `Preparing` : When order is being prepared
  - `Out For Delivery` : When order is prepared and sent to be delivered 
  - `Delivered` : Order received by customer
  - `Cancelled` : Cancelled by customer
  - `Denied` : Order with a removed items which is not prepared or already delivered

## GitHub Link - [ByteMe](https://github.com/Ujjval-dargar/Byte_Me)
