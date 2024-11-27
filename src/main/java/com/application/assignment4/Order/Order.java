package com.application.assignment4.Order;

import com.application.assignment4.Application;
import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Item.Item;
import com.application.assignment4.Utilities.Pair;
import com.application.assignment4.Utilities.TextFormat;
import javafx.beans.property.*;

import java.io.*;
import java.util.TreeMap;

public class Order extends Application implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String timeStamp;
    private String orderID;
    private TreeMap<String, Pair<Item, Integer>> items;
    private Customer customer;
    private String paymentType;
    private String Address;
    private String SpecialRequest;
    private String status;
    private int total;
    private boolean refunded;

    private transient StringProperty orderID_property;
    private transient StringProperty date_property;
    private transient StringProperty status_property;
    private transient IntegerProperty billAmount_property;
    private transient StringProperty specialRequest_property;

    public Order(String orderID, TreeMap<String, Pair<Item, Integer>> items, Customer customer, String paymentType, String address, String specialRequest, int total) {
        this.orderID = orderID;
        this.items = items;
        this.customer = customer;
        this.paymentType = paymentType;
        this.Address = address;
        this.SpecialRequest = specialRequest;
        this.timeStamp = TextFormat.getTime();
        this.status = "Received";
        this.total = total;
        this.refunded = false;

        orderID_property = new SimpleStringProperty(orderID);
        date_property = new SimpleStringProperty(timeStamp);
        status_property = new SimpleStringProperty(status);
        billAmount_property = new SimpleIntegerProperty(total);
        specialRequest_property = new SimpleStringProperty(specialRequest);
    }

    // Getters
    public TreeMap<String, Pair<Item, Integer>> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setStatus(String status) {
        this.status = status;
        status_property.set(status);
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public StringProperty orderIDProperty() {
        return orderID_property;
    }

    public StringProperty dateProperty() {
        return date_property;
    }

    public StringProperty statusProperty() {
        return status_property;
    }

    public IntegerProperty billAmountProperty() {
        return billAmount_property;
    }

    public StringProperty specialRequestProperty() {
        return specialRequest_property;
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        out.writeObject(orderID);
        out.writeObject(timeStamp);
        out.writeObject(status);
        out.writeInt(total);
        out.writeObject(items);
        out.writeObject(paymentType);
        out.writeObject(Address);
        out.writeObject(SpecialRequest);
        out.writeBoolean(refunded);
        out.writeObject(customer);

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        in.defaultReadObject();

        this.orderID = (String) in.readObject();
        this.timeStamp = (String) in.readObject();
        this.status = (String) in.readObject();
        this.total = in.readInt();
        this.items = (TreeMap<String, Pair<Item, Integer>>) in.readObject();
        this.paymentType = (String) in.readObject();
        this.Address = (String) in.readObject();
        this.SpecialRequest = (String) in.readObject();
        this.refunded = in.readBoolean();
        this.customer = (Customer) in.readObject();

        this.orderID_property = new SimpleStringProperty(orderID);
        this.date_property = new SimpleStringProperty(timeStamp);
        this.status_property = new SimpleStringProperty(status);
        this.billAmount_property = new SimpleIntegerProperty(total);
        this.specialRequest_property = new SimpleStringProperty(SpecialRequest);

    }

    public void printDetails() {

        System.out.println("\tDate : " + this.getTimeStamp());
        System.out.println("\tOrderID : " + this.getOrderID());
        System.out.println("\tItems & Quantities : ");
        for (Pair<Item, Integer> p : items.values()) {
            System.out.println("\t\t" + p.getFirst().getName() + "\t" + p.getSecond());
        }
        if (!SpecialRequest.isEmpty()) {
            System.out.println("\tSpecial Request : " + SpecialRequest);
        }
        System.out.println("\tStatus : " + this.getStatus());
        System.out.println("\tBilling Amount : " + this.total);
        if (isRefunded()) {
            System.out.println();
            System.out.println("\t\tRefunded");
        }
    }

}
