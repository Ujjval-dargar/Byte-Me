package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Order.Order;
import com.application.assignment4.SceneControllers.SelectUser;
import com.application.assignment4.Utilities.FileHandler;
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

public class CustomerOrderTable {

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
            profile.setImage(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/Profile-VIP-Icon.png")));
        }
    }

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

    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    @FXML
    public void initialize(Customer customer) throws IOException {

        setCustomer(customer);
        orderID.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        billAmount.setCellValueFactory(cellData -> cellData.getValue().billAmountProperty().asObject());

        orderList.clear();
        orderList.addAll(customer.getOrders().values());

        tableView.setItems(orderList);

        tableView.setOnMouseClicked(this::orderDetails);
    }

    public void orderDetails(MouseEvent event) {

        Order selectedOrder = tableView.getSelectionModel().getSelectedItem();

        if (selectedOrder==null){
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/CustomerOrderDetails.fxml"));

        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 750, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerOrderDetails controller = fxmlLoader.getController();
        controller.initialize(stage,customer,selectedOrder);

        stage.setScene(scene);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/SelectUser.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
    }

    public void CustomerMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerItemTable.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        CustomerItemTable controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerCart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerCartOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        CustomerCartOperations controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
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
