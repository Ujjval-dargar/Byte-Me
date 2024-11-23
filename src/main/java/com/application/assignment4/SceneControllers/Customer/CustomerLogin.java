package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.Customer.Customer;
import com.application.assignment4.SceneControllers.SelectUser;
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

public class CustomerLogin {

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void CustomerSignup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerSignup.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        CustomerSignup controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    public void CustomerDashboard(MouseEvent mouseEvent) throws IOException {


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

        if (Customer.login(email.getText(), password.getText()) == -1) {
            return;
        }

        TreeMap<String, Customer> appCustomers = FileHandler.getAppCustomers();

        Customer customer = appCustomers.get(email.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerDashboard.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        CustomerDashboard controller = fxmlLoader.getController();
        controller.setCustomer(customer);

        controller.setStage(stage);

        stage.setScene(scene);
    }
}
