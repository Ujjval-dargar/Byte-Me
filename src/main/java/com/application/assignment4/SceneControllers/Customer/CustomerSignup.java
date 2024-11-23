package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.SceneControllers.SelectUser;
import com.application.assignment4.Utilities.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.TreeMap;

public class CustomerSignup {

    private Stage stage;

    @FXML
    TextField name, email;
    @FXML
    PasswordField password;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void CustomerLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        CustomerLogin controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void signUp() throws IOException {

        if (name.getText().isEmpty()) {
            System.out.println();
            System.out.println("Enter your name.");
            return;
        }

        if (email.getText().isEmpty()) {
            System.out.println();
            System.out.println("Enter your email.");
            return;
        }

        if (password.getText().isEmpty()) {
            System.out.println();
            System.out.println("Enter your password.");
            return;
        }

        if (Customer.signup(name.getText(), email.getText(), password.getText()) == -1) {
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        CustomerLogin controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }


}
