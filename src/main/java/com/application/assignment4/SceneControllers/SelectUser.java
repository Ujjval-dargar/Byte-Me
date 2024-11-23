package com.application.assignment4.SceneControllers;

import com.application.assignment4.SceneControllers.Admin.Admin_login_controller;
import com.application.assignment4.SceneControllers.Customer.Customer_login_controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Select_User_controller {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void Change_CustomerLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        Customer_login_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    @FXML
    public void Change_AdminLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/AdminLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        Admin_login_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }
}
