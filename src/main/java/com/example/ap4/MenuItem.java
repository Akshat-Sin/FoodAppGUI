package com.example.ap4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import jdk.jfr.Category;

import java.io.*;

public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int price;
    private String category;
    private int availability; // Number of pieces available
//    private transient SimpleStringProperty nme;
//    private transient SimpleIntegerProperty prce;
//    private transient SimpleIntegerProperty qty;
//    private transient SimpleStringProperty ctgry;

//    public MenuItem(String name, int price,String category, int quantity) {
//        this.nme = new SimpleStringProperty(name);
//        this.prce = new SimpleIntegerProperty(price);
//        this.ctgry=new SimpleStringProperty(category);
//        this.qty=new SimpleIntegerProperty(quantity);
//    }

    public MenuItem(String name, int price, String category, int available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.availability = available;
//        this.nme = new SimpleStringProperty(name);
//        this.prce = new SimpleIntegerProperty(price);
//        this.ctgry=new SimpleStringProperty(category);
//        this.qty=new SimpleIntegerProperty(available);
    }

    public String getName() {
        return name;
    }

//    public int getPrce() {
//        return prce.get();
//    }
//
//    public int getQuantity() {
//        return qty.get();
//    }
//
//    public String getNme() {
//        return nme.get();
//    }
//
//    public String getCtgry() {
//        return ctgry.get();
//    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getAvailability() {
        return availability;
    }

    public boolean isAvailable(int quantityRequested) {
        return availability >= quantityRequested && quantityRequested>0;
    }

    public void decreaseAvailability(int quantity) {
        if (availability >= quantity) {
            availability -= quantity;
        }
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Price: Rs." + price + ", Category: " + category +
                ", Available Pieces: " + availability;
    }
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Serialize non-transient fields
        out.writeUTF(name);
        out.writeInt(price);
        out.writeUTF(category);
        out.writeInt(availability);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize non-transient fields
        this.name = in.readUTF();
        this.price = in.readInt();
        this.category = in.readUTF();
        this.availability = in.readInt();

        // Reinitialize transient fields
//        this.nme = new SimpleStringProperty(name);
//        this.prce = new SimpleIntegerProperty(price);
//        this.ctgry = new SimpleStringProperty(category);
//        this.qty = new SimpleIntegerProperty(availability);
    }
}
