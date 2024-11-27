package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Cart_cont {
    @FXML
    private Stage stage;
    @FXML
    private Customer customer;
    private Scanner scanner;
    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.scanner=new Scanner(System.in);
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

    public void addItem(MouseEvent mouseEvent) throws NonIntegerInputException {
        stage.hide();
        customer.addItemToCart(scanner);
        stage.show();
    }

    public void Remove(MouseEvent mouseEvent) {
        stage.hide();
        customer.removeItemFromCart(scanner);
        stage.show();
    }

    public void Modify(MouseEvent mouseEvent) throws NonIntegerInputException {
        stage.hide();
        customer.modifyItemQuantity(scanner);
        stage.show();
    }

    public void checkout(MouseEvent mouseEvent) throws NonIntegerInputException {
        stage.hide();
        customer.checkout(scanner);
        stage.show();
    }

    public void viewTotal(MouseEvent mouseEvent) {
        stage.hide();
        customer.orderManager.calculateTotalWithDelivery(customer.cart,customer.isVIP());
        stage.show();
    }
}