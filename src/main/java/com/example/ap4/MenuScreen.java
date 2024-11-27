package com.example.ap4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

public class MenuScreen extends Application {
    private OrderManager orderManager;

    public MenuScreen(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Canteen Menu");

        // Table to display menu items
        TableView<MenuItem> menuTable = new TableView<>();
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Item Name");
//        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
//        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject());

        TableColumn<MenuItem, String> categoryColumn = new TableColumn<>("Category");
//        categoryColumn.setCellValueFactory(data -> data.getValue().categoryProperty());

        TableColumn<MenuItem, Integer> availabilityColumn = new TableColumn<>("Availability");
//        availabilityColumn.setCellValueFactory(data -> data.getValue().availabilityProperty().asObject());

        menuTable.getColumns().addAll(nameColumn, priceColumn, categoryColumn, availabilityColumn);

        // Populate table with menu items
//        ObservableList<MenuItem> menuData = FXCollections.observableArrayList(orderManager.getMenu().values());
//        menuTable.setItems(menuData);

        // Button to navigate to the Pending Orders Screen
        Button viewOrdersButton = new Button("View Pending Orders");
        viewOrdersButton.setOnAction(e -> {
            PendingOrdersScreen pendingOrdersScreen = new PendingOrdersScreen(orderManager);
            try {
                pendingOrdersScreen.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Layout
        BorderPane layout = new BorderPane();
        layout.setCenter(menuTable);
        layout.setBottom(viewOrdersButton);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        orderManager.addMenuItemDirect("Burger", 5, "Meals", 10);
        orderManager.addMenuItemDirect("Pizza", 8, "Meals", 5);
        orderManager.addMenuItemDirect("Cola", 1, "Beverages", 20);
        orderManager.addMenuItemDirect("Fries", 2, "Snacks", 15);

        MenuScreen menuScreen = new MenuScreen(orderManager);
        launch(args);
    }
}
