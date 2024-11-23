package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.SceneControllers.SelectUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminLogin {

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

        Scene select_user = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(select_user);
    }

    public void AdminDashboard(MouseEvent mouseEvent) throws IOException {

        if (email.getText().isEmpty()) {
            System.out.println();
            System.out.println("Enter your username.");
            return;
        }

        if (password.getText().isEmpty()) {
            System.out.println();
            System.out.println("Enter your registered password.");
            return;
        }

        if (Admin.login(email.getText(), password.getText()) == -1) {
            return;
        }

        Admin admin = new Admin();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Admin/AdminDashboard.fxml"));

        Scene signupScreen = new Scene(fxmlLoader.load(), 750, 500);

        AdminDashboard controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(signupScreen);
    }
}
