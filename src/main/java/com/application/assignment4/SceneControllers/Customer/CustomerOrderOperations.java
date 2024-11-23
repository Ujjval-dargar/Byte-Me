package com.application.assignment4.SceneControllers.Customer;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.SceneControllers.SelectUser;
import com.application.assignment4.Utilities.FileHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Customer_dashboard_order_controller {

    private Stage stage;
    Customer customer;

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

    public void cancelOrder(){
        stage.hide();
        customer.cancelOrder();
        FileHandler.updateCustomer(customer);
        stage.show();
    }

    public void orderHistory(){
        stage.hide();
        customer.orderHistory();
        FileHandler.updateCustomer(customer);
        stage.show();
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/SelectUser.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(select_user);
    }

    public void CustomerMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerItemTable.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_menu_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(select_user);
    }

    public void CustomerCart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerCartOperations.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_cart_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(select_user);
    }

    public void CustomerReview(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerReviewOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_dashboard_review_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setScene(scene);
    }

    public void CustomerViewOrders(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/CustomerOrderTable.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        Customer_view_orders_controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.initialize(customer);

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
