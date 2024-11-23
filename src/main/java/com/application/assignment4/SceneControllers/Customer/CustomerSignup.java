package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.SceneControllers.Select_User_controller;
import com.application.assignment4.Utilities.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TreeMap;

public class Customer_signup_controller{

    private Stage stage;

    @FXML
    TextField name,email;
    @FXML
    PasswordField password;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        Select_User_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void CustomerLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        Customer_login_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void signUp() throws IOException {

        String nm = name.getText();
        String em = email.getText();
        String pwrd = password.getText();

        if (nm.isEmpty()){
            System.out.println();
            System.out.println("Enter your name.");
            return;
        }

        if (em.isEmpty()){
            System.out.println();
            System.out.println("Enter your email.");
            return;
        }

        if (pwrd.isEmpty()){
            System.out.println();
            System.out.println("Enter your password.");
            return;
        }

        TreeMap<String,Customer> appCustomers = FileHandler.getAppCustomers();

        if (appCustomers.containsKey(em)){
            System.out.println();
            System.out.println("You are already signed in. Please login...");
            return;
        }

        FileHandler.addCustomer(nm,em,pwrd);

        System.out.println();
        System.out.println("Sign up Successfully.");


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        Customer_login_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }



}
