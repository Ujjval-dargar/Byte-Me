package com.application.assignment4.Admin;


import com.application.assignment4.Application;
import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Item.Item;
import com.application.assignment4.Order.Order;
import com.application.assignment4.Utilities.FileHandler;
import com.application.assignment4.Utilities.Pair;
import com.application.assignment4.Utilities.TextFormat;

import java.util.*;


public class Admin extends Application implements AdminInterface {

    // fixed password for Admin
    private static final String password = "Admin@123";
    private static final Scanner scan = new Scanner(System.in);


    //Login
    public static int login(String email, String password) {

        if (!Objects.equals("Admin", email)){
            System.out.println();
            System.out.println("Wrong Username...");
            return -1;
        }

        if (!Objects.equals("Admin@123", password)){
            System.out.println();
            System.out.println("Wrong Password...");
            return -1;
        }

        System.out.println();
        System.out.println("Login Successful.");
        return 1;
    }

    // Report Generation
    @Override
    public void reportGeneration() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Daily Report ", 90));
        System.out.println();

        String todaysDate = TextFormat.getTime();

        todaysDate = todaysDate.substring(0, 10);

        TreeMap<String, Pair<Item, Integer>> allItems = new TreeMap<>();
        TreeMap<String, Order> appOrders = FileHandler.getCustomerOrders();
        TreeMap<String, Order> appVipOrders = FileHandler.getVipCustomerOrders();

        int total = 0;
        int num = 0;

        for (String s : appVipOrders.keySet()) {

            if (Objects.equals(appVipOrders.get(s).getStatus(), "Delivered") && Objects.equals(appVipOrders.get(s).getTimeStamp().substring(0, 10), todaysDate)) {


                for (String key : appVipOrders.get(s).getItems().keySet()) {

                    if (allItems.containsKey(key)) {
                        allItems.put(key, new Pair<>(allItems.get(key).getFirst(), allItems.get(key).getSecond() + appVipOrders.get(s).getItems().get(key).getSecond()));

                    } else {
                        allItems.put(key, new Pair<>(appVipOrders.get(s).getItems().get(key).getFirst(), appVipOrders.get(s).getItems().get(key).getSecond()));
                    }
                }
                total += appVipOrders.get(s).getTotal();
                num++;
            }
        }

        for (String s : appOrders.keySet()) {
            if (Objects.equals(appOrders.get(s).getStatus(), "Delivered") && Objects.equals(appOrders.get(s).getTimeStamp().substring(0, 10), todaysDate)) {

                for (String key : appOrders.get(s).getItems().keySet()) {

                    if (allItems.containsKey(key)) {
                        allItems.put(key, new Pair<>(allItems.get(key).getFirst(), allItems.get(key).getSecond() + appOrders.get(s).getItems().get(key).getSecond()));

                    } else {
                        allItems.put(key, new Pair<>(appOrders.get(s).getItems().get(key).getFirst(), appOrders.get(s).getItems().get(key).getSecond()));
                    }
                }

                total += appOrders.get(s).getTotal();
                num++;
            }
        }

        System.out.println();

        if (allItems.isEmpty()) {
            System.out.println("\tNo Order...");
        } else {
            System.out.printf("%-5s %-20s %-20s %-20s %-20s \n", "S.No", "Item Name", "Category", "Price", "Quantity Sold");
            System.out.printf("%-5s %-20s %-20s %-20s %-20s \n", "-".repeat(5), "-".repeat(20), "-".repeat(20), "-".repeat(20), "-".repeat(20));
            int i = 1;
            for (Pair<Item, Integer> pair : allItems.values()) {
                System.out.printf("%-5s %-20s %-20s %-20s %-20s \n", i, pair.getFirst().getName(), pair.getFirst().getCategory(), pair.getFirst().getPrice(), pair.getSecond());
                i += 1;
            }
        }

        System.out.println();
        System.out.println();
        System.out.print("\tTotal Orders : " + num);
        System.out.print("\tTotal Sales : " + total);
        System.out.println();

        System.out.println();
        TextFormat.pause();

    }

    // Order Management
    @Override
    public void updateOrderStatus() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Pending Orders ", 80));
        System.out.println();

        TreeMap<String, Order> appOrders = FileHandler.getCustomerOrders();
        TreeMap<String, Order> appVipOrders = FileHandler.getVipCustomerOrders();

        TreeMap<String, Order> filteredVipOrders = new TreeMap<>();
        TreeMap<String, Order> filteredOrders = new TreeMap<>();

        for (String s : appVipOrders.keySet()) {
            if (Objects.equals(appVipOrders.get(s).getStatus(), "Received") || Objects.equals(appVipOrders.get(s).getStatus(), "Preparing") || Objects.equals(appVipOrders.get(s).getStatus(), "Out For Delivery")) {
                filteredVipOrders.put(s, appVipOrders.get(s));
            }
        }

        for (String s : appOrders.keySet()) {
            if (Objects.equals(appOrders.get(s).getStatus(), "Received") || Objects.equals(appOrders.get(s).getStatus(), "Preparing") || Objects.equals(appOrders.get(s).getStatus(), "Out For Delivery")) {
                filteredOrders.put(s, appOrders.get(s));
            }
        }

        System.out.println();
        System.out.println(TextFormat.textCenter(" VIP Orders ", 80));
        System.out.println();
        viewOrders(filteredVipOrders);

        System.out.println();
        System.out.println();
        System.out.println(TextFormat.textCenter(" Normal Orders ", 80));
        System.out.println();
        viewOrders(filteredOrders);

        if (filteredVipOrders.isEmpty() && filteredOrders.isEmpty()) {
            System.out.println();
            TextFormat.pause();
            return;
        }

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter OrderID to update its status (0 : Back) : ");

            String input = scan.nextLine();

            if (Objects.equals(input, "0")) {
                return;
            }

            if (!filteredOrders.containsKey(input) && !filteredVipOrders.containsKey(input)) {
                System.out.println("Invalid... Enter a valid OrderID");
                continue;
            }

            int pos = 0;
            boolean found = false;

            for (String s : filteredVipOrders.keySet()) {
                pos++;
                if (s.equals(input)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (String s : filteredOrders.keySet()) {
                    pos++;
                    if (s.equals(input)) {
                        break;
                    }
                }
            }

            if (pos > 3) {
                System.out.println("Can't be updated...First complete prior orders.");
                continue;
            }

            if (filteredVipOrders.containsKey(input)) {

                String st = filteredVipOrders.get(input).getStatus();

                if (Objects.equals(st, "Received")) {
                    filteredVipOrders.get(input).setStatus("Preparing");
                    appVipOrders.get(input).setStatus("Preparing");
                } else if (Objects.equals(st, "Preparing")) {
                    filteredVipOrders.get(input).setStatus("Out For Delivery");
                    appVipOrders.get(input).setStatus("Out For Delivery");
                } else if (Objects.equals(st, "Out For Delivery")) {
                    filteredVipOrders.get(input).setStatus("Delivered");
                    appVipOrders.get(input).setStatus("Delivered");
                }

            }

            if (filteredOrders.containsKey(input)) {

                String st = filteredOrders.get(input).getStatus();

                if (Objects.equals(st, "Received")) {
                    filteredOrders.get(input).setStatus("Preparing");
                    appOrders.get(input).setStatus("Preparing");
                } else if (Objects.equals(st, "Preparing")) {
                    filteredOrders.get(input).setStatus("Out For Delivery");
                    appOrders.get(input).setStatus("Out For Delivery");
                } else if (Objects.equals(st, "Out For Delivery")) {
                    filteredOrders.get(input).setStatus("Delivered");
                    appOrders.get(input).setStatus("Delivered");
                }

            }

            TreeMap<String,Customer> appCustomer = FileHandler.getAppCustomers();

            for (Customer c : appCustomer.values()) {
                if (c.getOrders().containsKey(input)) {

                    String st = c.getOrders().get(input).getStatus();

                    if (Objects.equals(st, "Received")) {
                        c.getOrders().get(input).setStatus("Preparing");
                    } else if (Objects.equals(st, "Preparing")) {
                        c.getOrders().get(input).setStatus("Out For Delivery");
                    } else if (Objects.equals(st, "Out For Delivery")) {
                        c.getOrders().get(input).setStatus("Delivered");
                    }
                }
            }

            FileHandler.updateCustomerMap(appCustomer);
            FileHandler.updateOrderMap(appOrders);
            FileHandler.updateVipOrderMap(appVipOrders);

            System.out.println();
            System.out.println("Status Updated Successfully.");

            break;

        }

        System.out.println();
        TextFormat.pause();

    }

    @Override
    public void processRefunds() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Process Refunds ", 80));
        System.out.println();

        TreeMap<String, Order> appOrders = FileHandler.getCustomerOrders();
        TreeMap<String, Order> appVipOrders = FileHandler.getVipCustomerOrders();

        TreeMap<String, Order> filteredVipOrders = new TreeMap<>();
        TreeMap<String, Order> filteredOrders = new TreeMap<>();

        for (String s : appVipOrders.keySet()) {
            if ((Objects.equals(appVipOrders.get(s).getStatus(), "Cancelled") || Objects.equals(appVipOrders.get(s).getStatus(), "Denied")) && !Objects.equals(appVipOrders.get(s).getPaymentType(), "Cash") && !appVipOrders.get(s).isRefunded()) {
                filteredVipOrders.put(s, appVipOrders.get(s));
            }
        }

        for (String s : appOrders.keySet()) {
            if ((Objects.equals(appOrders.get(s).getStatus(), "Cancelled") || Objects.equals(appOrders.get(s).getStatus(), "Denied")) && !Objects.equals(appOrders.get(s).getPaymentType(), "Cash") && !appOrders.get(s).isRefunded()) {
                filteredOrders.put(s, appOrders.get(s));
            }
        }

        System.out.println();
        System.out.println(TextFormat.textCenter(" VIP Orders ", 80));
        System.out.println();
        viewOrders(filteredVipOrders);

        System.out.println();
        System.out.println();
        System.out.println(TextFormat.textCenter(" Normal Orders ", 80));
        System.out.println();
        viewOrders(filteredOrders);

        if (filteredVipOrders.isEmpty() && filteredOrders.isEmpty()) {
            System.out.println();
            TextFormat.pause();
            return;
        }

        System.out.println();
        while (true) {
            System.out.println();
            System.out.print("Enter OrderID to process its refund (0 : Back) : ");

            String input = scan.nextLine();

            if (Objects.equals(input, "0")) {
                return;
            }

            if (!filteredOrders.containsKey(input) && !filteredVipOrders.containsKey(input)) {
                System.out.println("Invalid... Enter a valid OrderID");
                continue;
            }

            System.out.println();
            if (filteredVipOrders.containsKey(input)) {
                filteredVipOrders.get(input).setRefunded(true);
                appVipOrders.get(input).setRefunded(true);
                filteredVipOrders.get(input).printDetails();
            }

            if (filteredOrders.containsKey(input)) {
                filteredOrders.get(input).setRefunded(true);
                appOrders.get(input).setRefunded(true);
                filteredOrders.get(input).printDetails();
            }

            TreeMap<String,Customer> appCustomer = FileHandler.getAppCustomers();

            for (Customer c : appCustomer.values()) {
                if (c.getOrders().containsKey(input)) {
                    c.getOrders().get(input).setRefunded(true);
                }
            }

            FileHandler.updateCustomerMap(appCustomer);
            FileHandler.updateOrderMap(appOrders);
            FileHandler.updateVipOrderMap(appVipOrders);

            System.out.println();
            System.out.println("Refunded Successfully.");
            break;
        }

        System.out.println();
        TextFormat.pause();


    }

    // Menu Management
    @Override
    public void updateItem() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Update an existing Item ", 110));
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
            System.out.print("Enter Item's Name to update (0:Back) : ");
            String name = scan.nextLine();

            if (Objects.equals(name, "0")) {
                return;
            }

            if (!getAppItems().containsKey(name)) {
                System.out.println("No such Item...");
                continue;
            }

            Item item = getAppItems().get(name);
            System.out.println("Item found successfully.");
            System.out.println();

            boolean updated;

            try {
                updated = item.update();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid...Requires integer. Please try again.");
                System.out.println();
                TextFormat.pause();
                return;
            }

            System.out.println();
            if (updated) {
                System.out.println("Item updated successfully.");
            } else {
                System.out.println("Item update failed.");
            }

            System.out.println();
            TextFormat.pause();

            break;

        }
    }

    @Override
    public void removeItem() {
        System.out.println();
        System.out.println(TextFormat.textCenter(" Remove an Item ", 110));
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
            System.out.print("Enter Item's Name to remove (0:Back) : ");
            String name = scan.nextLine();

            if (Objects.equals(name, "0")) {
                return;
            }

            if (!getAppItems().containsKey(name)) {
                System.out.println("No such Item...");
                continue;
            }

            getAppItems().remove(name);
            System.out.println();
            System.out.println("Item removed successfully.");
            System.out.println();

            TreeMap<String, Customer> customers = FileHandler.getAppCustomers();

            for (String s : customers.keySet()) {

                Customer c = customers.get(s);
                c.getCart().remove(name);

                for (String o : c.getOrders().keySet()) {
                    if (c.getOrders().get(o).getItems().containsKey(name) && !c.getOrders().get(o).getStatus().equals("Delivered") && !c.getOrders().get(o).getStatus().equals("Out For Delivery") && !c.getOrders().get(o).getStatus().equals("Cancelled")) {
                        c.getOrders().get(o).getItems().remove(name);
                        c.getOrders().get(o).setStatus("Denied");

                        for (String i : c.getOrders().get(o).getItems().keySet()) {
                            getAppItems().get(i).setQuantity(getAppItems().get(i).getQuantity() + c.getOrders().get(o).getItems().get(i).getSecond());
                        }
                    }
                }
            }

            FileHandler.updateCustomerMap(customers);

            TreeMap<String, Order> orders = FileHandler.getCustomerOrders();
            TreeMap<String, Order> vipOrders = FileHandler.getVipCustomerOrders();


            for (String s : orders.keySet()) {
                Order o = orders.get(s);

                if (o.getItems().containsKey(name) && !o.getStatus().equals("Delivered") && !o.getStatus().equals("Out For Delivery")) {
                    o.setStatus("Denied");
                }
            }

            for (String s : vipOrders.keySet()) {
                Order o = vipOrders.get(s);
                if (o.getItems().containsKey(name) && !o.getStatus().equals("Delivered") && !o.getStatus().equals("Out For Delivery")) {
                    o.setStatus("Denied");
                }
            }

            FileHandler.updateOrderMap(orders);
            FileHandler.updateVipOrderMap(vipOrders);

            TextFormat.pause();
            return;

        }
    }

}
