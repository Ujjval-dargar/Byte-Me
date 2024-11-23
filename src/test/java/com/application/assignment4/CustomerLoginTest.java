package com.application.assignment4;

import com.application.assignment4.Customer.Customer;
import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerLoginTest {

    @Before
    public void setUp() throws Exception {

        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        String name = "customer1";
        String email = "cust1";
        String password = "password";

        Customer.signup(name, email, password);

        System.setOut(originalOut);
    }

    @Test
    public void WrongEmail() throws Exception {

        String email = "WrongEmail";
        String password = "password";

        String output = tapSystemOut(() -> {
            int result = Customer.login(email, password);

            assertEquals(-1, result);
        });

        assertTrue(output.contains("No such email id found... Please Sign Up first."));
    }

    @Test
    public void WrongPassword() throws Exception {

        String email = "cust1";
        String password = "wrongPassword";

        String output = tapSystemOut(() -> {
            int result = Customer.login(email, password);

            assertEquals(-1, result);
        });

        assertTrue(output.contains("Wrong Password..."));
    }

    @Test
    public void CorrectLogin() throws Exception {

        String email = "cust1";
        String password = "password";

        String output = tapSystemOut(() -> {
            int result = Customer.login(email, password);

            assertEquals(1, result);
        });

        assertTrue(output.contains("Login Successful."));
    }


}