package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Order.Order;
import com.application.assignment4.SceneControllers.Select_User_controller;
import com.application.assignment4.Utilities.FileHandler;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Objects;
import java.util.TreeMap;

public class Admin_pending_order_controller {

    private Stage stage;
    Admin admin;

    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> orderID;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order, String> status;

    @FXML
    private TableColumn<Order, Integer> billAmount;

    @FXML
    private TableColumn<Order, String> customerType;

    @FXML
    private TableColumn<Order, String> specialRequest;

    private ObservableList<Order> orderList = FXCollections.observableArrayList();


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    public void initialize() throws IOException {

        orderID.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        billAmount.setCellValueFactory(cellData -> cellData.getValue().billAmountProperty().asObject());
        customerType.setCellValueFactory(cellData -> {
            Customer customer = cellData.getValue().getCustomer();
            return new SimpleStringProperty(customer.isVip() ? "VIP" : "Non-VIP");
        });
        specialRequest.setCellValueFactory(cellData -> cellData.getValue().specialRequestProperty());

        orderList.clear();

        TreeMap<String,Order> appOrders = FileHandler.getCustomerOrders();
        TreeMap<String,Order> appVipOrders = FileHandler.getVipCustomerOrders();

        for (String s : appVipOrders.keySet()) {
            if (Objects.equals(appVipOrders.get(s).getStatus(), "Received") || Objects.equals(appVipOrders.get(s).getStatus(), "Preparing")) {
                orderList.add(appVipOrders.get(s));
            }
        }

        for (String s : appOrders.keySet()) {
            if (Objects.equals(appOrders.get(s).getStatus(), "Received") || Objects.equals(appOrders.get(s).getStatus(), "Preparing")) {
            orderList.add(appOrders.get(s));}
        }

        tableView.setItems(orderList);

        tableView.setOnMouseClicked(this::orderDetails);
    }

    public void orderDetails(MouseEvent event) {

        Order selectedOrder = tableView.getSelectionModel().getSelectedItem();

        if (selectedOrder==null){
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/AdminOrderDetails.fxml"));

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 750, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Admin_order_details_controller controller = fxmlLoader.getController();
        controller.initialize(stage,admin,selectedOrder);

        stage.setScene(scene);
    }

    public void dailyReport(){
        stage.hide();
        admin.reportGeneration();
        stage.show();
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
}
