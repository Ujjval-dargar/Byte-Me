package com.application.assignment4.SceneControllers;

import com.application.assignment4.SceneControllers.Admin.AdminLogin;
import com.application.assignment4.SceneControllers.Customer.CustomerLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectUser {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void Change_CustomerLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        CustomerLogin controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void Change_AdminLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Admin/AdminLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        AdminLogin controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }
}
