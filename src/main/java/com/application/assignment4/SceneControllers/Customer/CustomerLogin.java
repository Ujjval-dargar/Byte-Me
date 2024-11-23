package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.SceneControllers.Select_User_controller;
import com.application.assignment4.Utilities.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.TreeMap;

public class Customer_login_controller {

    private Stage stage;

    @FXML
    TextField email;
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
    public void CustomerSignup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerSignup.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        Customer_signup_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    public void CustomerDashboard(MouseEvent mouseEvent) throws IOException {

        TreeMap<String, Customer> appCustomers = FileHandler.getAppCustomers();

        if (email.getText().isEmpty()){
            System.out.println();
            System.out.println("Enter your registered email.");
            return;
        }

        if (password.getText().isEmpty()){
            System.out.println();
            System.out.println("Enter your registered password.");
            return;
        }

        if (!appCustomers.containsKey(email.getText())){
            System.out.println();
            System.out.println("No such email id found... Please Sign Up first.");
            return;
        }

        if (!Objects.equals(appCustomers.get(email.getText()).getPassword(), password.getText())){
            System.out.println();
            System.out.println("Wrong Password...");
            return;
        }

        System.out.println();
        System.out.println("Login Successful.");

        Customer customer = appCustomers.get(email.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerDashboard.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_controller controller = fxmlLoader.getController();
        controller.setCustomer(customer);

        controller.setStage(stage);

        stage.setScene(scene);
    }
}
