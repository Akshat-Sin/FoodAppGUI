package com.example.ap4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class Order implements Serializable,Comparable<Order> {
    @Serial
    private static final long serialVersionUID=1L;
    private Customer customer;
    private static int idCounter = 1; // Auto-increment ID for each order
    private int orderId;
    private Map<MenuItem, Integer> items; // MenuItem and quantity
    private String status;
    private String customerType;
    private String specialRequest;
    private String deliveryAddress;
    private String paymentMethod;
    private int totalAmount;

    public Order(Customer customer,Map<MenuItem, Integer> items, String customerType, String specialRequest, String deliveryAddress, String paymentMethod,int amount) {
        this.orderId = idCounter++;
        this.items = items;
        this.customerType = customerType;
        this.customer=customer;
        this.specialRequest = specialRequest;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.totalAmount=amount;
        this.status = ("Preparing");

//        public StringProperty statusProperty() {
//            return status;
//        }

    }
    @Serial
    private void writeObject(java.io.ObjectOutputStream out) throws IOException, IOException {
        out.defaultWriteObject();
        out.writeInt(idCounter); // Save the idCounter
    }

    @Serial
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        idCounter = in.readInt(); // Restore the idCounter
    }
    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

//    public int getTotalAmount() {
//        return totalAmount;
//    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // Method to process a refund
    public boolean processRefund() {
        if ("Refunded".equals(status)) {
            System.out.println("Order " + orderId + " has already been refunded.");
            return false;
        } else {
            setStatus("Refunded");
            System.out.println("Refund processed for Order ID: " + orderId+"of total amount of Rs."+totalAmount);
            return true;
        }
    }
    public void updateStatusToNext() {
        if (status.equals("Preparing")) {
            status = "Out for Delivery";
        } else if (status.equals("Out for Delivery")) {
            status = "Delivered";
        }
    }
    public void cancelOrder(OrderManager orderManager) {
        if (status.equals("Preparing")) {
            setStatus("Canceled");
            orderManager.restoreItemsToStock(items); // Restore items to the menu
            System.out.println("Order ID " + orderId + " has been canceled, and items have been returned to stock.");

        }
        else {
            System.out.println("Order cannot be canceled as it is already being processed or delivered.");
        }
    }

    public String getCustomerType() {
        return customerType;
    }

    // Display each item and its quantity in the order
    public void displayItemsInOrder() {
        System.out.println("Items in Order ID " + orderId + ":");
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            System.out.println("- " + entry.getKey().getName() + " x" + entry.getValue());
        }
    }

    @Override
    public int compareTo(Order other) {
        // VIP orders are prioritized over Regular orders
        if (this.customerType.equals("VIP") && other.customerType.equals("Regular")) {
            return -1; // VIP has higher priority
        } else if (this.customerType.equals("Regular") && other.customerType.equals("VIP")) {
            return 1; // Regular has lower priority
        } else {
            return Integer.compare(this.orderId, other.orderId); // Within type, ordered by orderId (FIFO)
        }
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Status: " + status + ", Customer Type: " + customerType +
                ", Special Request: " + specialRequest + ", Delivery Address: " + deliveryAddress +
                ", Payment Method: " + paymentMethod;
    }
}
