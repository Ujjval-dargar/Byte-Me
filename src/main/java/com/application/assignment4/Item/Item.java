package com.application.assignment4.Item;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

import java.io.*;

public class Item implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final Scanner scan = new Scanner(System.in);

    private transient StringProperty name;
    private transient DoubleProperty price;
    private transient IntegerProperty quantity;
    private transient StringProperty category;
    private transient StringProperty veg_nonVeg;

    private String nameValue;
    private double priceValue;
    private int quantityValue;
    private String categoryValue;
    private String veg_nonVegValue;

    public Item(String name, double price, int quantity, String category, String veg_nonVeg) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.category = new SimpleStringProperty(category);
        this.veg_nonVeg = new SimpleStringProperty(veg_nonVeg);

        this.priceValue = price;
        this.quantityValue = quantity;
        this.categoryValue = category;
        this.veg_nonVegValue = veg_nonVeg;
    }

    // Getters
    public String getName() {
        return name.get();
    }

    public String getCategory() {
        return category.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public String getVeg_nonVeg() {
        return veg_nonVeg.get();
    }

    // Setters
    public void setName(String name) {
        this.nameValue = name;
        this.name.set(name);
    }

    public void setCategory(String category) {
        this.categoryValue = category;
        this.category.set(category);
    }

    public void setPrice(double price) {
        this.priceValue = price;
        this.price.set(price);
    }

    public void setQuantity(int quantity) {
        this.quantityValue = quantity;
        this.quantity.set(quantity);
    }

    public void setVeg_nonVeg(String veg_nonVeg) {
        this.veg_nonVegValue = veg_nonVeg;
        this.veg_nonVeg.set(veg_nonVeg);
    }

    public StringProperty itemNameProperty() {
        return name;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public StringProperty vegNonVegProperty() {
        return veg_nonVeg;
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(name.get());
        out.writeDouble(price.get());
        out.writeInt(quantity.get());
        out.writeObject(category.get());
        out.writeObject(veg_nonVeg.get());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.nameValue = (String) in.readObject();
        this.priceValue = in.readDouble();
        this.quantityValue = in.readInt();
        this.categoryValue = (String) in.readObject();
        this.veg_nonVegValue = (String) in.readObject();

        this.name = new SimpleStringProperty(nameValue);
        this.price = new SimpleDoubleProperty(priceValue);
        this.quantity = new SimpleIntegerProperty(quantityValue);
        this.category = new SimpleStringProperty(categoryValue);
        this.veg_nonVeg = new SimpleStringProperty(veg_nonVegValue);
    }

    public static Item initialize() {

        String name;
        double price;
        int quantity;
        String category;
        String v_ng;

        System.out.print("Enter Item's Name : ");
        name = scan.nextLine();

        System.out.print("Enter Item's Price : ");
        price = scan.nextDouble();
        scan.nextLine();

        System.out.print("Enter Item's Available Quantity : ");
        quantity = scan.nextInt();
        scan.nextLine();

        while (true) {
            System.out.println("\nEnter Item's Category : ");
            System.out.println("\t1. Snacks");
            System.out.println("\t2. Beverages");
            System.out.println("\t3. Meals");
            System.out.print("(1/2/3) ~ ");
            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                category = "Snacks";
            } else if (choice == 2) {
                category = "Beverages";
            } else if (choice == 3) {
                category = "Meals";
            } else {
                System.out.println("Invalid Choice");
                continue;
            }
            break;
        }


        System.out.print("\nEnter Item's Category (Veg/Non-Veg) : ");
        v_ng = scan.nextLine();

        while (!Objects.equals(v_ng, "Veg") && !Objects.equals(v_ng, "Non-Veg")) {
            System.out.println("Category can be either Veg or Non-Veg.");
            System.out.println();
            System.out.print("Enter Item's Category (Veg/Non-Veg) : ");
            v_ng = scan.nextLine();
        }

        System.out.println();
        System.out.println("Item added Successfully");

        return new Item(name, price, quantity, category, v_ng);
    }

    public boolean update() {
        System.out.println("Update Item '" + getName() + "' -");
        System.out.println("\t0. Cancel");
        System.out.println("\t1. Price");
        System.out.println("\t2. Quantity");
        System.out.println("\t3. Category");
        System.out.println("\t4. Veg / Non-Veg");

        System.out.println();
        System.out.print("(0/1/2/3/4) ~ ");
        String choice = scan.nextLine();
        System.out.println();

        switch (choice) {
            case "0" -> {
                return false;
            }
            case "1" -> {
                System.out.print("Enter Item's new Price : ");
                double price = scan.nextDouble();
                scan.nextLine();
                setPrice(price);
                return true;
            }
            case "2" -> {
                System.out.print("Enter Item's new Available Quantity : ");
                int quantity = scan.nextInt();
                scan.nextLine();
                setQuantity(quantity);
                return true;
            }
            case "3" -> {
                while (true) {
                    System.out.println("Enter Item's Category : ");
                    System.out.println("\t1. Snacks");
                    System.out.println("\t2. Beverages");
                    System.out.println("\t3. Meals");

                    System.out.print("(1/2/3) ~ ");
                    String ch = scan.nextLine();

                    switch (ch) {
                        case "1" -> setCategory("Snacks");
                        case "2" -> setCategory("Beverages");
                        case "3" -> setCategory("Meals");
                        case null, default -> {
                            System.out.println("Invalid Choice");
                            continue;
                        }
                    }
                    break;
                }
                return true;
            }
            case "4" -> {
                System.out.print("Enter Item's Category (Veg/Non-Veg) : ");
                String v_ng = scan.nextLine();

                while (!Objects.equals(veg_nonVeg, "Veg") && !Objects.equals(veg_nonVeg, "Non-Veg")) {
                    System.out.println("Category can be either Veg or Non-Veg.");
                    System.out.println();
                    System.out.print("Enter Item's Category (Veg/Non-Veg) : ");
                    v_ng = scan.nextLine();
                }
                setVeg_nonVeg(v_ng);
                return true;
            }
            default -> {
                System.out.println("Invalid Choice");
                return false;
            }
        }

    }

    @Override
    public String toString() {
        return getName();
    }

}
