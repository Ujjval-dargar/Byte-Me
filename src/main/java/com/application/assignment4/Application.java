package com.application.assignment4;

import com.application.assignment4.Item.Item;
import com.application.assignment4.Order.Order;
import com.application.assignment4.Utilities.FileHandler;
import com.application.assignment4.Utilities.Pair;
import com.application.assignment4.Review.Review;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.application.assignment4.SceneControllers.SelectUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import static com.application.assignment4.Utilities.TextFormat.textCenter;

public class Application extends javafx.application.Application {

    private static TreeMap<String, Item> appItems = new TreeMap<>();
    private static TreeMap<String, ArrayList<Review>> appReviews = new TreeMap<>();

    public static TreeMap<String, Item> getAppItems() {
        return appItems;
    }

    public static TreeMap<String, ArrayList<Review>> getAppReviews() {
        return appReviews;
    }

    @Override
    public void start(Stage stage) throws IOException {

        addItems();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/ByteMe.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/app-icon_16x16.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/app-icon_32x32.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/application/assignment4/Images/app-icon_64x64.png")));

        stage.setTitle("ByteMe");
        stage.setScene(scene);
        stage.show();

        System.out.println();
        System.out.println(textCenter(" Welcome to Byte Me ", 50));

        Platform.runLater(() -> {

            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        try {
                            FXMLLoader selectUserLoader = new FXMLLoader(getClass().getResource("Scenes/SelectUser.fxml"));
                            Scene selectUserScene = new Scene(selectUserLoader.load(), 600, 450);

                            SelectUser controller = selectUserLoader.getController();
                            controller.setStage(stage);

                            stage.setScene(selectUserScene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }, 3000);

        });


    }

    public void addItems(){
        Item item1 = new Item("Noodles", 50, 30, "Snacks", "Veg");
        Item item2 = new Item("Boiled Eggs(2 pcs)", 20, 20, "Snacks", "Non-Veg");
        Item item3 = new Item("Mojito", 40, 30, "Beverages", "Veg");
        Item item4 = new Item("Pav Bhaji", 35, 50, "Meals", "Veg");
        Item item5 = new Item("Biryani", 45, 20, "Meals", "Non-Veg");

        getAppItems().put(item1.getName(), item1);
        getAppItems().put(item2.getName(), item2);
        getAppItems().put(item3.getName(), item3);
        getAppItems().put(item4.getName(), item4);
        getAppItems().put(item5.getName(), item5);
    }

    public static void viewItems(ArrayList<Item> items) {

        if (items.isEmpty()) {
            System.out.println("No items found...");
        } else {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", "S.No", "Item Name", "Category", "Veg/Non-Veg", "Price", "Available Quantity");
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", "-".repeat(5), "-".repeat(20), "-".repeat(20), "-".repeat(20), "-".repeat(20), "-".repeat(20));
            int i = 1;
            for (Item item : items) {
                System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", i, item.getName(), item.getCategory(), item.getVeg_nonVeg(), item.getPrice(), item.getQuantity());
                i += 1;
            }
        }
    }

    public static void viewCart(TreeMap<String, Pair<Item, Integer>> cart) {

        if (cart.isEmpty()) {
            System.out.println("\tCart is Empty...");
        } else {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", "S.No", "Item Name", "Category", "Veg/Non-Veg", "Price", "Quantity Selected");
            System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", "-".repeat(5), "-".repeat(20), "-".repeat(20), "-".repeat(20), "-".repeat(20), "-".repeat(20));
            int i = 1;
            for (Pair<Item, Integer> pair : cart.values()) {
                System.out.printf("%-5s %-20s %-20s %-20s %-20s %-20s \n", i, pair.getFirst().getName(), pair.getFirst().getCategory(), pair.getFirst().getVeg_nonVeg(), pair.getFirst().getPrice(), pair.getSecond());
                i += 1;
            }
        }
    }

    public static void viewOrders(TreeMap<String, Order> order) {

        if (order.isEmpty()) {
            System.out.println("\tNo Order yet...");
        } else {
            System.out.printf("%-15s %-20s %-20s %-20s \n", "OrderID", "Date", "Status", "Bill Amount");
            System.out.printf("%-15s %-20s %-20s %-20s \n", "-".repeat(15), "-".repeat(20), "-".repeat(20), "-".repeat(20));
            for (Order o : order.values()) {
                System.out.printf("%-15s %-20s %-20s %-20s \n", o.getOrderID(), o.getTimeStamp(), o.getStatus(), o.getTotal());
            }
        }

    }

    public static void viewReviews(TreeMap<String, ArrayList<Review>> reviews) {

        if (reviews.isEmpty()) {
            System.out.println("\tNo Reviews yet...");
        } else {
            System.out.printf("%-5s %-20s %-20s \n", "S.No", "Item Name", "Rating");
            System.out.printf("%-5s %-20s %-20s \n", "-".repeat(5), "-".repeat(20), "-".repeat(20));

            int i = 1;

            for (ArrayList<Review> arr : reviews.values()) {
                for (Review r : arr) {
                    System.out.printf("%-5s %-20s %-20s \n", i, r.getItem().getName(), r.getRating());
                    i++;
                }
            }

        }

    }


    public static void main(String[] args) {

        FileHandler.initializeCustomer();
        FileHandler.initializeOrders();
        FileHandler.initializeVipOrders();

        launch();
    }



}
