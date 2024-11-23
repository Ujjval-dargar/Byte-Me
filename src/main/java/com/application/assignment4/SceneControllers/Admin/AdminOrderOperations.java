package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.SceneControllers.SelectUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminOrderOperations {

    private Stage stage;
    Admin admin;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void updateOrderStatus(){
        stage.hide();
        admin.updateOrderStatus();
        stage.show();
    }

    public void processRefund(){
        stage.hide();
        admin.processRefunds();
        stage.show();
    }

    public void dailyReport(){
        stage.hide();
        admin.reportGeneration();
        stage.show();
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    public void AdminMenu(MouseEvent mouseEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Admin/AdminMenuOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        AdminMenuOperations controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(scene);
    }

    public void AdminOrder(MouseEvent mouseEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Admin/AdminOrderOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        AdminOrderOperations controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(scene);
    }

    public void AdminPendingOrders(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Admin/AdminPendingOrders.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        AdminPendingOrders controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(scene);
    }
}
