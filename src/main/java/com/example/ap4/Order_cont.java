package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Order_cont {
    @FXML
    private Stage stage;
    @FXML
    private Customer customer;
    Scanner scanner=new Scanner(System.in);
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void changeScreen(MouseEvent mouseEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Screen2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        TypeController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Screen2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        TypeController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void Menu(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        Menu_cont controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void Cart(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Cart.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        Cart_cont controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void Order(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("order.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        Order_cont controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void VIP(MouseEvent mouseEvent) {
        stage.hide();
        customer.becomeVIP(scanner);
        stage.show();

    }

    public void Review(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("review.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        Review_cont controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setCustomer(customer);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void status(MouseEvent mouseEvent) {
        stage.hide();
        customer.orderManager.viewOrderStatus();
        stage.show();
    }

    public void viewpast(MouseEvent mouseEvent) {
        stage.hide();
        customer.orderManager.viewOrderHistory();
        stage.show();
    }

    public void cancelOrder(MouseEvent mouseEvent) throws NonIntegerInputException {
        stage.hide();
        List<Order> pendingOrders = customer.orderManager.getPendingOrders();
        for(Order o:pendingOrders){
            System.out.println(o);
        }
        System.out.println("Enter Order ID to cancel:");
        int orderId = ByteMe.getUserOption(scanner);
        customer.orderManager.cancelOrder(orderId);
        stage.show();
    }

    public void reorder(MouseEvent mouseEvent) throws NonIntegerInputException {
        stage.hide();
        List<Order> orders = customer.orderManager.getCompletedOrders();
        for(Order o:orders){
            System.out.println(o);
        }
        System.out.println("Enter Order ID to reorder:");
        int orderId = ByteMe.getUserOption(scanner);
        customer.orderManager.reorder(orderId, customer.cart, scanner); // Add items to cart based on availability

        // Show the original order details alongside the current cart
        System.out.println("\nOriginal Order Details:");
        customer.orderManager.viewOrderHistory();

        System.out.println("\nPlease review your cart and adjust items if necessary.");
        customer.viewCart(scanner);
        stage.show();
    }
}