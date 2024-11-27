package com.example.ap4;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static com.example.ap4.ByteMe.orderManager;

public class A_order_det_cont {
    @FXML
    private Stage stage;
    @FXML
    private Order order;
    @FXML
    Scanner scanner=new Scanner(System.in);
//    @FXML
//    private Customer customer;
    @FXML
    private TableView<MenuItem> itemTable;
    @FXML
    TableColumn<OrderDetails, String> itemNameColumn=new TableColumn<>("Item Name");
    @FXML
    TableView<OrderDetails> orderDetailsTable=new TableView<>();
    @FXML
    TableColumn<OrderDetails, Integer> quantityColumn=new TableColumn<>("Quantity");

    public void setOrder(Order order) {
        this.order = order;
    }

    public void initialize(Stage stage, Order order) {
        setOrder(order);
        setStage(stage);
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderDetailsTable.getColumns().addAll(itemNameColumn, quantityColumn);
//        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//        itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        itemCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        populateOrderDetailsTable(order, orderDetailsTable);
//        ObservableList<OrderDetails> items = FXCollections.observableArrayList();
//        orderDetailsTable.setItems(items);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
    private void populateOrderDetailsTable(Order order, TableView<OrderDetails> table) {
        ObservableList<OrderDetails> orderItems = FXCollections.observableArrayList();

        // Convert Map<MenuItem, Integer> to OrderItem
        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {

            orderItems.add(new OrderDetails(entry.getKey().getName(), entry.getValue()));
        }

        table.setItems(orderItems);
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

        A_order_det_cont controller = fxmlLoader.getController();
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