package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.Application;
import com.application.assignment4.Item.Item;
import com.application.assignment4.SceneControllers.SelectUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static com.application.assignment4.Utilities.TextFormat.pause;
import static com.application.assignment4.Utilities.TextFormat.textCenter;

public class AdminMenuOperations extends Application {

    private Stage stage;
    Admin admin;


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public void AdminViewItems(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Admin/AdminItemTable.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        AdminItemTable controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);

        stage.setScene(scene);
    }

    public void addItem(MouseEvent mouseEvent) {

        stage.hide();

        System.out.println();
        System.out.println(textCenter(" Add a new Item ", 70));
        System.out.println();

        Item item;
        try {
            item = Item.initialize();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Invalid...Requires integer. Please try again.");
            System.out.println();
            pause();
            return;
        }
        getAppItems().put(item.getName(), item);

        System.out.println();
        pause();

        stage.show();
    }

    public void removeItem(MouseEvent mouseEvent) {
        stage.hide();
        admin.removeItem();
        stage.show();
    }

    public void updateItem(){
        stage.hide();
        admin.updateItem();
        stage.show();
    }

    public void dailyReport(){
        stage.hide();
        admin.reportGeneration();
        stage.show();
    }


}
