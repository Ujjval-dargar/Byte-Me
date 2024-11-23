package com.application.assignment4.Customer;

import com.application.assignment4.Item.Item;
import com.application.assignment4.Utilities.Pair;

import java.util.TreeMap;

public interface CustomerInterface {
    void purchaseVIP();

    // Item Reviews
    void provideReview();

    void viewReview();

    // Order Management
    void cancelOrder();

    void orderHistory();

    // Cart Operations
    void addItems();

    void modifyQuantities();

    void removeItems();

    void viewTotal();

    void checkOut(TreeMap<String, Pair<Item, Integer>> cart);
}
