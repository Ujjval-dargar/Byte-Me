package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.SceneControllers.Select_User_controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Admin_login_controller {

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

        Scene select_user = new Scene(fxmlLoader.load(), 600, 450);

        Select_User_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(select_user);
    }

    public void AdminDashboard(MouseEvent mouseEvent) throws IOException {

        if (email.getText().isEmpty()){
            System.out.println();
            System.out.println("Enter your username.");
            return;
        }

        if (password.getText().isEmpty()){
            System.out.println();
            System.out.println("Enter your registered password.");
            return;
        }

        if (!Objects.equals("Admin", email.getText())){
            System.out.println();
            System.out.println("Wrong Username...");
            return;
        }

        if (!Objects.equals("Admin@123", password.getText())){
            System.out.println();
            System.out.println("Wrong Password...");
            return;
        }

        System.out.println();
        System.out.println("Login Successful.");

        Admin admin = new Admin();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/AdminDashboard.fxml"));

        Scene signupScreen = new Scene(fxmlLoader.load(), 750, 500);

        Admin_dashboard_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(signupScreen);
    }
}
