package com.example.ap4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Order_file {
    @SuppressWarnings("unchecked")
    public static PriorityQueue<Order> loadPendOrders() {
        try (FileInputStream fis = new FileInputStream("pend_orders.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            PriorityQueue<Order> map = (PriorityQueue<Order>) ois.readObject();
            return map;

        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
            return new PriorityQueue<>(); // Return an empty map instead of null
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new PriorityQueue<>(); // Return an empty map as a fallback
    }


    // Save data to the file
    public static void savePendOrder(PriorityQueue<Order> map) {
        try (FileOutputStream fos = new FileOutputStream("pend_orders.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Order> loadCompOrders() {
        try (FileInputStream fis = new FileInputStream("CompOrders_data.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Order>) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
            return new ArrayList<>() {
            }; // Return a new OrderData object instead of null
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return a new OrderData object as a fallback
    }

    // Save all data to the file
    public static void saveCompOrders(List<Order> orderData) {
        try (FileOutputStream fos = new FileOutputStream("CompOrders_data.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(orderData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Order> loadDenOrders() {
        try (FileInputStream fis = new FileInputStream("DeniedOrders_data.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<Order>) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
            return new ArrayList<>() {
            }; // Return a new OrderData object instead of null
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return a new OrderData object as a fallback
    }

    // Save all data to the file
    public static void saveDenOrders(List<Order> orderData) {
        try (FileOutputStream fos = new FileOutputStream("DeniedOrders_data.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(orderData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
