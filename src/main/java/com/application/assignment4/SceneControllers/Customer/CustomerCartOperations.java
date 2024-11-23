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
import java.util.InputMismatchException;

import static com.application.assignment4.Utilities.TextFormat.pause;

public class CustomerCartOperations {

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

    public void addItems(){
        stage.hide();
        try {
            customer.addItems();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Invalid...Requires integer. Please try again.");
            System.out.println();
            pause();
        }
        FileHandler.updateCustomer(customer);
        stage.show();
    }

    public void removeItems(){
        stage.hide();
        customer.removeItems();
        FileHandler.updateCustomer(customer);
        stage.show();
    }

    public void modifyQuantity(){
        stage.hide();
        try {
            customer.modifyQuantities();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Invalid...Requires integer. Please try again.");
            System.out.println();
            pause();
        }
        FileHandler.updateCustomer(customer);
        stage.show();
    }

    public void viewTotal(){
        stage.hide();
        customer.viewTotal();
        FileHandler.updateCustomer(customer);
        stage.show();
    }

    public void checkout(){
        stage.hide();
        customer.checkOut(customer.getCart());
        FileHandler.updateCustomer(customer);
        stage.show();

    }


    public void logout(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/SelectUser.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 600, 450);

        SelectUser controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(select_user);
    }

    public void CustomerMenu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerItemTable.fxml"));

        Scene select_user = new Scene(fxmlLoader.load(), 750, 500);

        CustomerItemTable controller = fxmlLoader.getController();
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

    public void CustomerCart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/assignment4/Scenes/Customer/CustomerCartOperations.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 500);

        CustomerCartOperations controller = fxmlLoader.getController();
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
