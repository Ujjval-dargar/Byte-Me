package com.application.assignment4;

import com.application.assignment4.Admin.Admin;
import static org.junit.jupiter.api.Assertions.*;
import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import org.junit.Test;

public class AdminLoginTest {

    @Test
    public void WrongUsername() throws Exception {

        String username = "WrongUsername";
        String password = "Admin@123";

        String output = tapSystemOut(() -> {
            int result = Admin.login(username, password);

            assertEquals(-1, result);
        });

        assertTrue(output.contains("Wrong Username..."));
    }

    @Test
    public void WrongPassword() throws Exception {

        String username = "Admin";
        String password = "wrongPassword";

        String output = tapSystemOut(() -> {
            int result = Admin.login(username, password);

            assertEquals(-1, result);
        });

        assertTrue(output.contains("Wrong Password..."));
    }

    @Test
    public void CorrectLogin() throws Exception {

        String username = "Admin";
        String password = "Admin@123";

        String output = tapSystemOut(() -> {
            int result = Admin.login(username, password);

            assertEquals(1, result);
        });

        assertTrue(output.contains("Login Successful."));
    }


}