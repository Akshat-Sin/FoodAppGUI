package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.PriorityQueue;
import java.io.IOException;
import java.util.Scanner;

public class A_order_cont {
    @FXML
    Scanner scanner=new Scanner(System.in);
    @FXML
    private Stage stage;
    @FXML
    private OrderManager orderManager=new OrderManager();
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> orderStatusColumn;
    @FXML
    private TableColumn<Order, String> specialRequestColumn;


    private PriorityQueue<Order> ordersQueue= Order_file.loadPendOrders();


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
    @FXML
    public void initialize(){
        ObservableList<Order> ordersList = FXCollections.observableArrayList(ordersQueue);

        // Set up Order Table
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        specialRequestColumn.setCellValueFactory(new PropertyValueFactory<>("specialRequest"));
        orderTable.setItems(ordersList);
        orderTable.setOnMouseClicked(this::displayOrderItems);

//        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                try {
//                    displayOrderItems(newValue);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
    }

    private void displayOrderItems(MouseEvent a) {
        Order order=orderTable.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("A_order_details.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        A_order_det_cont controller = fxmlLoader.getController();
        controller.initialize(stage,order);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
//        ObservableList<MenuItem> items = FXCollections.observableArrayList(order.getItems());
//        itemTable.setItems(items);
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("A_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        A_menu_cont controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public void order(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("A_order.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        A_order_cont controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void update(MouseEvent mouseEvent) {
        stage.hide();
        orderManager.updateNextOrderStatus();
        stage.show();
    }

    public void report(MouseEvent mouseEvent) {
        stage.hide();
        Admin.generateReports();
        stage.show();
    }

    public void refund(MouseEvent mouseEvent) throws NonIntegerInputException {
        stage.hide();
        Admin.processRefunds(scanner);
        stage.show();
    }
}