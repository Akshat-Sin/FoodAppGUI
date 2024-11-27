package com.example.ap4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PendingOrdersScreen extends Application {
    private OrderManager orderManager;

    public PendingOrdersScreen(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pending Orders");

        // Table to display pending orders
        TableView<Order> ordersTable = new TableView<>();
        TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("Order ID");
//        orderIdColumn.setCellValueFactory(data -> data.getValue().orderIdProperty().asObject());

        TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
//        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());

        TableColumn<Order, String> itemsColumn = new TableColumn<>("Items");
//        itemsColumn.setCellValueFactory(data -> data.getValue().itemsStringProperty());

        ordersTable.getColumns().addAll(orderIdColumn, statusColumn, itemsColumn);

        // Populate table with pending orders
        ObservableList<Order> ordersData = FXCollections.observableArrayList(orderManager.getPendingOrders());
        ordersTable.setItems(ordersData);

        // Button to navigate back to the Menu Screen
        Button viewMenuButton = new Button("View Menu");
        viewMenuButton.setOnAction(e -> {
            MenuScreen menuScreen = new MenuScreen(orderManager);
            try {
                menuScreen.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout
        BorderPane layout = new BorderPane();
        layout.setCenter(ordersTable);
        layout.setBottom(viewMenuButton);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
