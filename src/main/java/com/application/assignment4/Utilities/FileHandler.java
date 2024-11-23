package com.application.assignment4.Portal;

import com.application.assignment4.Customer.Customer;
import com.application.assignment4.Item.Item;
import com.application.assignment4.Order.Order;

import java.io.*;
import java.util.TreeMap;

public abstract class FileHandler {

    public static void initializeCustomer() {
        String fileName = "Customers.ser";
        File file = new File(fileName);

        if (!file.exists()) {

            TreeMap<String, Customer> map = new TreeMap<>();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addCustomer(String name, String email, String password) {
        String fileName = "Customers.ser";

        TreeMap<String, Customer> map = getAppCustomers();

        map.put(email, new Customer(name, email, password));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(Customer customer) {
        String fileName = "Customers.ser";

        TreeMap<String, Customer> map = getAppCustomers();

        map.put(customer.getEmail(), customer);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreeMap<String, Customer> getAppCustomers() {
        String fileName = "Customers.ser";
        TreeMap<String, Customer> map = new TreeMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            map = (TreeMap<String, Customer>) ois.readObject();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void updateCustomerMap(TreeMap<String, Customer> map) {
        String fileName = "Customers.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void initializeOrders() {

        String fileName = "Orders.ser";

        File file = new File(fileName);

        if (!file.exists()) {

            TreeMap<String, Order> map = new TreeMap<>();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addOrder(String orderID, TreeMap<String, Pair<Item, Integer>> items, Customer customer, String paymentType, String address, String specialRequest, int total) {
        String fileName = "Orders.ser";

        TreeMap<String, Order> map = getCustomerOrders();

        Order order = new Order(orderID, items, customer, paymentType, address, specialRequest, total);

        map.put(orderID, order);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrder(Order order) {
        String fileName = "Orders.ser";

        TreeMap<String, Order> map = getCustomerOrders();

        map.put(order.getOrderID(), order);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreeMap<String, Order> getCustomerOrders() {
        String fileName = "Orders.ser";
        TreeMap<String, Order> map = new TreeMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            map = (TreeMap<String, Order>) ois.readObject();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void updateOrderMap(TreeMap<String, Order> map) {
        String fileName = "Orders.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void initializeVipOrders() {

        String fileName = "VipOrders.ser";

        File file = new File(fileName);

        if (!file.exists()) {

            TreeMap<String, Order> map = new TreeMap<>();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addVipOrder(String orderID, TreeMap<String, Pair<Item, Integer>> items, Customer customer, String paymentType, String address, String specialRequest, int total) {
        String fileName = "VipOrders.ser";

        TreeMap<String, Order> map = getVipCustomerOrders();

        Order order = new Order(orderID, items, customer, paymentType, address, specialRequest, total);

        map.put(orderID, order);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateVipOrder(Order order) {
        String fileName = "VipOrders.ser";

        TreeMap<String, Order> map = getVipCustomerOrders();

        map.put(order.getOrderID(), order);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreeMap<String, Order> getVipCustomerOrders() {
        String fileName = "VipOrders.ser";
        TreeMap<String, Order> map = new TreeMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            map = (TreeMap<String, Order>) ois.readObject();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void updateVipOrderMap(TreeMap<String, Order> map) {
        String fileName = "VipOrders.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
