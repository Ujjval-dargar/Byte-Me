package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Application;
import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Item.Item;
import com.application.assignment4.SceneControllers.SelectUser;
import com.application.assignment4.Utilities.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomerItemTable extends Application {

    private Stage stage;
    private Customer customer;

    @FXML
    private Text name;
    @FXML
    private ImageView profile;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
        name.setText(customer.getName());

        if (customer.isVip()){
            profile.setImage(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/Profile-VIP-Icon.png")));
        }
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

        Scene select_user = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(select_user);
    }

    public void CustomerCart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerCartOperations.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 750, 500);

        CustomerCartOperations controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(select_user);
    }

    public void CustomerOrder(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerOrderOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        CustomerOrderOperations controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerReview(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerReviewOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        CustomerReviewOperations controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerItemTable.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 750, 500);

        CustomerItemTable controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(select_user);
    }


    public void becomeVIP(){
        if (customer.isVip()){
            System.out.println();
            System.out.println("Already a VIP customer.");
        }else {
            stage.hide();
            customer.purchaseVIP();
            FileHandler.updateCustomer(customer);
            if (customer.isVip()){
                profile.setImage(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/Profile-VIP-Icon.png")));
            }
            stage.show();
        }
    }






}
