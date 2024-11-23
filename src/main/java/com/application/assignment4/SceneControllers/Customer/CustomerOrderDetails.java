package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Item.Item;
import com.application.assignment4.Order.Order;
import com.application.assignment4.SceneControllers.SelectUser;
import com.application.assignment4.Utilities.FileHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Customer_order_details_controller {

    @FXML
    private Text name;
    @FXML
    private ImageView profile;

    private Stage stage;
    Customer customer;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        name.setText(customer.getName());
        if (customer.isVip()){
            profile.setImage(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/Profile-VIP-Icon.png")));        }
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
    public void initialize(Stage stage, Customer customer, Order order) {

        setStage(stage);
        setCustomer(customer);

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerOrderTable.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_view_orders_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.initialize(customer);

        stage.setScene(scene);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    public void CustomerMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerItemTable.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_menu_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerCart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerCartOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_cart_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerOrder(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerOrderOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_order_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerReview(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerReviewOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_review_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
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
                profile.setImage(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/Profile-VIP-Icon.png")));            }
            stage.show();
        }
    }
}
