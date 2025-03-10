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
