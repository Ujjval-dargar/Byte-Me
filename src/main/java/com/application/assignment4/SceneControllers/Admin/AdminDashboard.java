package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.SceneControllers.Select_User_controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_dashboard_controller {

    private Stage stage;
    Admin admin;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        Select_User_controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    public void AdminMenu(MouseEvent mouseEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/AdminMenuOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Admin_dashboard_menu_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(scene);
    }

    public void AdminOrder(MouseEvent mouseEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/AdminOrderOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Admin_dashboard_order_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(scene);
    }

    public void dailyReport(){
        stage.hide();
        admin.reportGeneration();
        stage.show();
    }


}
