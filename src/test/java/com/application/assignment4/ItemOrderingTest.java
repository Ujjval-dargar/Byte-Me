package com.application.assignment4;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Item.Item;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class ItemOrderingTest {

    Item NotAvailableItem;
    Item AvailableItem;

    private Scanner mockScanner;

    Customer customer;

    @Before
    public void setup() {
        mockScanner = mock(Scanner.class);

        NotAvailableItem = new Item("Burger", 30, 0, "Snacks", "Veg");
        AvailableItem = new Item("Chips", 10, 100, "Snacks", "Veg");

        Application.getAppItems().put(AvailableItem.getName(), AvailableItem);
        Application.getAppItems().put(NotAvailableItem.getName(), NotAvailableItem);

        customer = new Customer("Test","test@email","test");
        customer.setScan(mockScanner);
    }

    @Test
    public void ItemDoesNotExist() throws Exception {
        when(mockScanner.nextInt()).thenReturn(5);

        String output = tapSystemOut(() -> {
            int result = customer.addItem("NotAvailableItem");

            assertEquals(-1, result);
        });

        assertTrue(output.contains("No such item exists."));
    }

    @Test
    public void ItemNotAvailable() throws Exception {
        when(mockScanner.nextInt()).thenReturn(5);

        String output = tapSystemOut(() -> {
            int result = customer.addItem("Burger");

            assertEquals(0, result);
        });

        assertTrue(output.contains("Quantity exceeds the maximum available quantity."));
    }

    @Test
    public void ItemAvailable() throws Exception {
        when(mockScanner.nextInt()).thenReturn(5);

        String output = tapSystemOut(() -> {
            int result = customer.addItem("Chips");

            assertEquals(1, result);
        });

        assertTrue(output.contains("Successfully Added to Cart."));
    }


}
