package com.example.ap4;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Cust_file {
    @SuppressWarnings("unchecked")
    public static HashMap<String, Customer> loadCredentials() {
        try (FileInputStream fis = new FileInputStream("cust_cred.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            HashMap<String, Customer> map = (HashMap<String, Customer>) ois.readObject();
            System.out.println("Customer credentials loaded successfully.");
            return map;

        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
            return new HashMap<>(); // Return an empty map instead of null
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>(); // Return an empty map as a fallback
    }


    // Save data to the file
    public static void saveCredentials(HashMap<String, Customer> map) {
        try (FileOutputStream fos = new FileOutputStream("cust_cred.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(map);
            System.out.println("Customer credentials saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
