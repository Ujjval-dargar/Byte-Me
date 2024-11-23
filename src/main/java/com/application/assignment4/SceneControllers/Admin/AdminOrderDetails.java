package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.Item.Item;
import com.application.assignment4.Order.Order;
import com.application.assignment4.SceneControllers.Select_User_controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_order_details_controller {

    private Stage stage;
    Admin admin;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, String> itemNameColumn;

    @FXML
    private TableColumn<Item, String> categoryColumn;

    @FXML
    private TableColumn<Item, String> vegNonVegColumn;

    @FXML
    private TableColumn<Item, Double> priceColumn;

    @FXML
    private TableColumn<Item, Integer> quantityColumn;

    private ObservableList<Item> itemList = FXCollections.observableArrayList();

    @FXML
    public void initialize(Stage stage,Admin admin,Order order) {

        setStage(stage);
        setAdmin(admin);

        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        vegNonVegColumn.setCellValueFactory(cellData -> cellData.getValue().vegNonVegProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        quantityColumn.setCellValueFactory(cellData -> {
            Item item = cellData.getValue();
            Integer quantity = order.getItems().get(item.getName()).getSecond();
            return new SimpleIntegerProperty(quantity).asObject();
        });

        itemList.clear();
        order.getItems().forEach((itemName, itemPair) -> {
            Item item = itemPair.getFirst();
            itemList.add(item);
        });

        tableView.setItems(itemList);
    }


    public void back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/AdminPendingOrders.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Admin_pending_order_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAdmin(admin);
        controller.initialize();

        stage.setScene(scene);
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
