package com.application.assignment4.SceneControllers.Admin;

import com.application.assignment4.Admin.Admin;
import com.application.assignment4.Application;
import com.application.assignment4.Item.Item;
import com.application.assignment4.SceneControllers.SelectUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class AdminItemTable extends Application {

    private Stage stage;
    Admin admin;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    private TextField search;

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
    public void initialize() {
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        vegNonVegColumn.setCellValueFactory(cellData -> cellData.getValue().vegNonVegProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        itemList.clear();
        itemList.addAll(getAppItems().values());

        tableView.setItems(itemList);
    }

    public void searchItems() {

        ArrayList<Item> searchedResult = new ArrayList<>();

        for (String name : getAppItems().keySet()) {
            if ((name.toLowerCase()).startsWith((search.getText().toLowerCase()))) {
                searchedResult.add(getAppItems().get(name));
            }
        }

        itemList.clear();
        itemList.addAll(searchedResult);

        tableView.setItems(itemList);
    }

    public void itemsAsc(MouseEvent mouseEvent) {

        itemList.sort(Comparator.comparingDouble(Item::getPrice));

        tableView.setItems(itemList);
    }

    public void itemsDesc(MouseEvent mouseEvent) {

        itemList.sort(Comparator.comparingDouble(Item::getPrice).reversed());

        tableView.setItems(itemList);
    }

    public void displaySnacks(ActionEvent event) {

        ArrayList<Item> searchedResult = new ArrayList<>();

        for (String name : getAppItems().keySet()) {
            if (getAppItems().get(name).getCategory().equals("Snacks")) {
                searchedResult.add(getAppItems().get(name));
            }
        }

        itemList.clear();
        itemList.addAll(searchedResult);

        tableView.setItems(itemList);

    }

    public void displayMeals(ActionEvent event) {
        ArrayList<Item> searchedResult = new ArrayList<>();

        for (String name : getAppItems().keySet()) {
            if (getAppItems().get(name).getCategory().equals("Meals")) {
                searchedResult.add(getAppItems().get(name));
            }
        }

        itemList.clear();
        itemList.addAll(searchedResult);

        tableView.setItems(itemList);
    }

    public void displayBeverages(ActionEvent event) {
        ArrayList<Item> searchedResult = new ArrayList<>();

        for (String name : getAppItems().keySet()) {
            if (getAppItems().get(name).getCategory().equals("Beverages")) {
                searchedResult.add(getAppItems().get(name));
            }
        }

        itemList.clear();
        itemList.addAll(searchedResult);

        tableView.setItems(itemList);
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

    public void dailyReport(){
        stage.hide();
        admin.reportGeneration();
        stage.show();
    }


}
