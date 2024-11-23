package com.application.assignment4.Customer;


import com.application.assignment4.Application;
import com.application.assignment4.Item.Item;
import com.application.assignment4.Order.Order;
import com.application.assignment4.Utilities.FileHandler;
import com.application.assignment4.Utilities.Pair;
import com.application.assignment4.Utilities.TextFormat;
import com.application.assignment4.Review.Review;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Customer extends Application implements Serializable, CustomerInterface {

    @Serial
    private static final long serialVersionUID = 1L;

    private transient Scanner scan = new Scanner(System.in);
    private final String name;
    private final String email;
    private final String password;
    private boolean vip;

    private TreeMap<String, Pair<Item, Integer>> cart;
    private TreeMap<String, Order> orders = new TreeMap<>();

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.vip = false;
        this.cart = new TreeMap<>();
        this.orders = new TreeMap<String, Order>();
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.scan = new Scanner(System.in);
    }

    // Getters
    public String getPassword() {
        return password;
    }

    public boolean isVip() {
        return vip;
    }

    public TreeMap<String, Pair<Item, Integer>> getCart() {
        return cart;
    }

    public TreeMap<String, Order> getOrders() {
        return orders;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


    // Setters
    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public static int signup(String name, String email, String password) {
        TreeMap<String,Customer> appCustomers = FileHandler.getAppCustomers();

        if (appCustomers.containsKey(email)){
            System.out.println();
            System.out.println("You are already signed in. Please login...");
            return -1;
        }

        FileHandler.addCustomer(name,email,password);

        System.out.println();
        System.out.println("Sign up Successfully.");
        return 1;
    }


    public static int login(String email, String password) {

        TreeMap<String, Customer> appCustomers = FileHandler.getAppCustomers();

        if (!appCustomers.containsKey(email)){
            System.out.println();
            System.out.println("No such email id found... Please Sign Up first.");
            return -1;
        }

        if (!Objects.equals(appCustomers.get(email).getPassword(), password)){
            System.out.println();
            System.out.println("Wrong Password...");
            return -1;
        }

        System.out.println();
        System.out.println("Login Successful.");

        return 1;
    }


    @Override
    public void purchaseVIP() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Become a VIP Customer ", 60));
        System.out.println();
        System.out.println("\tBenefits - ");
        System.out.println("1. Free Delivery on all orders.");
        System.out.println("2. Orders are given priority.");
        System.out.println();
        System.out.println("All this for just @1299 only.");

        while (true) {
            System.out.println();
            System.out.print("Would you like to become a VIP customer ? (Y/N) : ");
            String choice = scan.nextLine();

            if (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("Invalid Option.");
                continue;
            }

            if (choice.equals("Y")) {
                setVip(true);
                System.out.println();
                System.out.println("You are now a VIP customer. ");
                System.out.println();
                TextFormat.pause();
            }

            break;
        }


    }


    // Item Reviews
    @Override
    public void provideReview() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Provide Item Review ", 110));
        System.out.println();

        viewItems(new ArrayList<>(getAppItems().values()));

        if (getAppItems().isEmpty()) {
            System.out.println();
            TextFormat.pause();
            return;
        }

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter Item's Name to add review (0:Back) : ");
            String name = scan.nextLine();

            if (name.equals("0")) {
                return;
            }

            if (!getAppItems().containsKey(name)) {
                System.out.println("No such item exists.");
                continue;
            }

            for (String s : getOrders().keySet()) {
                if (getOrders().get(s).getItems().containsKey(name) && Objects.equals(getOrders().get(s).getStatus(), "Delivered")) {

                    boolean isDigit;
                    do {
                        System.out.println();
                        System.out.print("Enter your rating (1-5) : ");
                        String input = scan.nextLine();
                        isDigit = input.matches("\\d+");

                        if (!isDigit) {
                            System.out.println("Invalid... enter a valid rating.");
                        } else {
                            if (Integer.parseInt(input) > 5 || Integer.parseInt(input) <= 0) {
                                System.out.println("Invalid... enter a valid rating.");
                                System.out.println();
                                isDigit = false;
                            }
                        }

                        System.out.print("Enter description : ");
                        String desc = scan.nextLine();

                        if (getAppReviews().containsKey(name)) {
                            getAppReviews().get(name).add(new Review(getAppItems().get(name), input, desc));
                            getAppReviews().put(name, getAppReviews().get(name));
                        } else {
                            ArrayList<Review> arr = new ArrayList<>();
                            arr.add(new Review(getAppItems().get(name), input, desc));
                            getAppReviews().put(name, arr);
                        }

                    }
                    while (!isDigit);

                    System.out.println();
                    System.out.println("Review Added Successfully.");
                    System.out.println();
                    TextFormat.pause();
                    return;

                }
            }

            System.out.println("You have not ordered this item yet...");
            TextFormat.pause();
            return;

        }
    }

    @Override
    public void viewReview() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" View Item Reviews ", 70));
        System.out.println();

        viewReviews(getAppReviews());

        if (getAppReviews().isEmpty()) {
            System.out.println();
            TextFormat.pause();
            return;
        }

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter Item's name to view its Reviews (0 : Back) : ");

            String input = scan.nextLine();

            if (Objects.equals(input, "0")) {
                break;
            }

            if (!getAppReviews().containsKey(input)) {
                System.out.println("Invalid... Enter a valid ID");
                continue;
            }

            System.out.println();

            int i = 1;
            for (Review r : getAppReviews().get(input)) {
                System.out.println("Review " + i + " : ");
                r.printDetails();
                System.out.println();
                i++;
            }

        }
    }


    // Order Management
    @Override
    public void cancelOrder() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Cancel Order ", 80));
        System.out.println();

        TreeMap<String, Order> filteredOrders = new TreeMap<>();
        for (String s : getOrders().keySet()) {
            if (Objects.equals(getOrders().get(s).getStatus(), "Received") || Objects.equals(getOrders().get(s).getStatus(), "Preparing")) {
                filteredOrders.put(s, getOrders().get(s));
            }
        }

        viewOrders(filteredOrders);

        if (filteredOrders.isEmpty()) {
            System.out.println();
            TextFormat.pause();
            return;
        }

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter OrderID to cancel order (0 : Back) : ");

            String input = scan.nextLine();

            if (Objects.equals(input, "0")) {
                return;
            }

            if (!getOrders().containsKey(input)) {
                System.out.println("Invalid... Enter a valid ID");
                continue;
            }

            if (!filteredOrders.containsKey(input)) {
                System.out.println("Can't be Cancelled...");
                continue;
            }

            getOrders().get(input).setStatus("Cancelled");

            Order o = getOrders().get(input);
            o.setStatus("Cancelled");

            if (getOrders().get(input).getCustomer().isVip()) {
                FileHandler.updateVipOrder(o);
            } else {
                FileHandler.updateOrder(o);
            }

            for (String i : getOrders().get(input).getItems().keySet()) {
                getAppItems().get(i).setQuantity(getAppItems().get(i).getQuantity() + getOrders().get(input).getItems().get(i).getSecond());
            }

            System.out.println();
            System.out.println("Order Cancelled.");
            break;

        }
        TextFormat.pause();
    }

    @Override
    public void orderHistory() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Order History ", 80));
        System.out.println();

        viewOrders(this.getOrders());

        if (this.getOrders().isEmpty()) {
            System.out.println();
            TextFormat.pause();
            return;
        }

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter OrderID to view order in details (0 : Back) : ");

            String input = scan.nextLine();

            if (Objects.equals(input, "0")) {
                return;
            }

            if (!getOrders().containsKey(input)) {
                System.out.println("Invalid... Enter a valid ID");
                continue;
            }

            System.out.println();
            getOrders().get(input).printDetails();


            while (true) {
                System.out.println();

                if (Objects.equals(getOrders().get(input).getStatus(), "Denied")) {
                    System.out.println("Denied Orders can't be re-ordered.");
                    System.out.println();
                    TextFormat.pause();
                    return;
                }

                System.out.print("Want to re-order this order ? (Y/N) : ");
                String ch = scan.nextLine();

                if (!ch.equals("Y") && !ch.equals("N")) {
                    System.out.println("Invalid Option.");
                    continue;
                }

                for (String i : getOrders().get(input).getItems().keySet()) {
                    if (getAppItems().containsKey(i)) {
                        if (getAppItems().get(i).getQuantity() < getOrders().get(input).getItems().get(i).getSecond()) {
                            System.out.println("This can't be order due to non-availability of items in order...");
                            TextFormat.pause();
                            return;
                        } else {
                            getAppItems().get(i).setQuantity(getAppItems().get(i).getQuantity() - getOrders().get(input).getItems().get(i).getSecond());
                        }
                    }
                }

                if (ch.equals("Y")) {
                    checkOut(new TreeMap<>(getOrders().get(input).getItems()));
                }

                return;
            }
        }
    }


    // Cart Operations
    @Override
    public void addItems() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Add Items ", 110));
        System.out.println();

        viewItems(new ArrayList<>(getAppItems().values()));

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter Item's Name to add to cart (0:Back) : ");
            String name = scan.nextLine();

            if (name.equals("0")) {
                return;
            }

            addItem(name);
        }
    }

    public int addItem(String name) {

        if (!getAppItems().containsKey(name)) {
            System.out.println("No such item exists.");
            return -1;
        }

        Item item = getAppItems().get(name);

        System.out.print("Enter Quantity : ");
        int quantity = scan.nextInt();
        scan.nextLine();

        if (quantity<=0){
            System.out.println();
            System.out.println("Quantity can't be negative or zero.");
            return -2;

        }

        if (quantity > item.getQuantity()) {
            System.out.println();
            System.out.println("Quantity exceeds the maximum available quantity.");
            return 0;
        }

        getAppItems().get(name).setQuantity(item.getQuantity() - quantity);

        if (cart.containsKey(name)) {
            Pair<Item, Integer> pair = cart.get(name);
            pair.setSecond(pair.getSecond() + quantity);
            cart.put(name, pair);
        } else {
            cart.put(name, new Pair<>(item, quantity));
        }


        System.out.println();
        System.out.println("Successfully Added to Cart.");
        return 1;
    }

    @Override
    public void modifyQuantities() {

        System.out.println();
        System.out.println(TextFormat.textCenter(" Modify Item Quantities ", 110));
        System.out.println();

        if (cart.isEmpty()) {
            System.out.println("\tCart is Empty...");
            System.out.println();
            TextFormat.pause();
            return;
        }

        viewCart(cart);

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter Item's Name to modify quantity (0:Back) : ");
            String name = scan.nextLine();

            if (name.equals("0")) {
                return;
            }

            if (!cart.containsKey(name)) {
                System.out.println("No such item exists.");
                continue;
            }

            Pair<Item, Integer> pair = cart.get(name);

            System.out.print("Enter new Quantity : ");
            int quantity = scan.nextInt();
            scan.nextLine();

            if (quantity > pair.getFirst().getQuantity() + pair.getSecond()) {
                System.out.println();
                System.out.println("Quantity exceeds the maximum available quantity.");
                continue;
            }

            getAppItems().get(name).setQuantity(getAppItems().get(name).getQuantity() + pair.getSecond() - quantity);

            pair.setSecond(quantity);
            cart.put(name, pair);

            System.out.println();
            System.out.println("Successfully Modified in Cart.");

        }
    }

    @Override
    public void removeItems() {

        System.out.println();
        System.out.println(TextFormat.textCenter(" Remove Items ", 110));
        System.out.println();

        if (cart.isEmpty()) {
            System.out.println("\tCart is Empty...");
            System.out.println();
            TextFormat.pause();
            return;
        }

        viewCart(cart);

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter Item's Name to remove (0:Back) : ");
            String name = scan.nextLine();

            if (name.equals("0")) {
                return;
            }

            if (!cart.containsKey(name)) {
                System.out.println("No such item exists.");
                continue;
            }

            Item item = getAppItems().get(name);
            getAppItems().get(name).setQuantity(item.getQuantity() + cart.get(name).getSecond());

            cart.remove(name);
            System.out.println();
            System.out.println("Successfully Removed from Cart.");

        }
    }

    @Override
    public void viewTotal() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" View Cart ", 110));
        System.out.println();

        if (cart.isEmpty()) {
            System.out.println("\tCart is Empty...");
            System.out.println();
            TextFormat.pause();
            return;
        }

        viewCart(cart);

        System.out.println();

        double amount = 0;

        for (Pair<Item, Integer> pair : cart.values()) {
            amount += pair.getFirst().getPrice() * pair.getSecond();
        }

        double deliveryFee;

        if (isVip()) {
            deliveryFee = 0;
        } else {
            deliveryFee = 20;
        }

        int total = (int) Math.ceil(amount + deliveryFee);

        System.out.println();
        System.out.println("\tAmount : " + amount);
        System.out.println("\tDelivery Fees : " + deliveryFee);
        System.out.println("\t" + "-".repeat(20));
        System.out.println("\tTotal Amount : " + total);
        System.out.println();
        TextFormat.pause();

    }

    @Override
    public void checkOut(TreeMap<String, Pair<Item, Integer>> cart) {

        System.out.println();
        System.out.println(TextFormat.textCenter(" CheckOut ", 110));
        System.out.println();

        if (cart.isEmpty()) {
            System.out.println("\tCart is Empty...");
            System.out.println();
            TextFormat.pause();
            return;
        }

        double amount = 0;

        for (Pair<Item, Integer> pair : cart.values()) {
            amount += pair.getFirst().getPrice() * pair.getSecond();
        }

        double deliveryFee;

        if (isVip()) {
            deliveryFee = 0;
        } else {
            deliveryFee = 20;
        }

        int total = (int) Math.ceil(amount + deliveryFee);

        System.out.println();
        System.out.println("\tAmount : " + amount);
        System.out.println("\tDelivery Fees : " + deliveryFee);
        System.out.println("\t" + "-".repeat(20));
        System.out.println("\tTotal Amount : " + total);


        String isSqReq;
        String request = "";
        String paymentType;

        while (true) {
            System.out.println();
            System.out.print("Any Special Request (Y/N) : ");
            isSqReq = scan.nextLine();

            if (!isSqReq.equals("Y") && !isSqReq.equals("N")) {
                System.out.println("Invalid Option.");
                continue;
            }

            if (isSqReq.equals("Y")) {
                System.out.print("Enter Request : ");
                request = scan.nextLine();
            }

            break;
        }

        System.out.println();
        System.out.print("Enter Delivery Address : ");
        String address = scan.nextLine();

        while (true) {
            System.out.println();
            System.out.println("Enter Payment Details : ");
            System.out.println("\t0. Cancel");
            System.out.println("\t1. Cash");
            System.out.println("\t2. UPI");
            System.out.println("\t3. Card");
            System.out.print("(0/1/2/3) ~ ");
            String ch = scan.nextLine();

            if (ch.equals("0")) {
                return;
            }

            if (!ch.equals("1") && !ch.equals("2") && !ch.equals("3")) {
                System.out.println("Invalid option.");
                continue;
            }

            if (ch.equals("1")) {
                paymentType = "Cash";
            } else if (ch.equals("2")) {
                paymentType = "UPI";
                System.out.println();
                System.out.println("Payment Successful.");
            } else {
                paymentType = "Card";
                System.out.println();
                System.out.println("Payment Successful.");
            }

            System.out.println();
            System.out.println("Order Placed.");
            TextFormat.pause();
            break;
        }

        TreeMap<String,Order> appOrder = FileHandler.getCustomerOrders();
        TreeMap<String,Order> appVipOrder = FileHandler.getVipCustomerOrders();

        String orderID = "#O" + (appOrder.size() + appVipOrder.size() + 1);

        Order order = new Order(orderID, new TreeMap<>(cart), this, paymentType, address, request, total);

        if (isVip()) {
            FileHandler.addVipOrder(orderID, new TreeMap<>(cart), this, paymentType, address, request, total);
        } else {
            FileHandler.addOrder(orderID, new TreeMap<>(cart), this, paymentType, address, request, total);
        }

        getOrders().put(orderID, order);
        cart.clear();

    }

}
