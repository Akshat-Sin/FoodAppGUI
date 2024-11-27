package com.example.ap4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Menu_cont {
    @FXML
    private Stage stage;
    @FXML
    private Customer customer;
    @FXML
    private TableView<MenuItem> menuTable;
    @FXML
    private TableColumn<MenuItem, Integer> serialNoColumn;
    @FXML
    private TableColumn<MenuItem, String> nameColumn;
    @FXML
    private TableColumn<MenuItem, Integer> priceColumn;
    @FXML
    private TableColumn<MenuItem, Integer> quantityColumn;
    @FXML
    private TableColumn<MenuItem, String> categoryColumn;
    @FXML
    private TextField searchByNameField;
    @FXML
    private TextField searchByCategoryField;

    Scanner scanner=new Scanner(System.in);

    private ObservableList<MenuItem> menuItems;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Example menu data
    private Map<String, MenuItem> menu = OrderManager.getMenu();

    @FXML
    public void initialize() {

        // Convert the map to an ObservableList
        menuItems = FXCollections.observableArrayList(menu.values());

        // Configure Serial Number column (auto-generated)
        serialNoColumn.setCellValueFactory(cellData -> {
            int index = menuTable.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleIntegerProperty(index).asObject();
        });

        // Configure other columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Populate the TableView
        menuTable.setItems(menuItems);
    }
    // Search by Name
    @FXML
    private void searchByName() {
        String searchText = searchByNameField.getText().trim().toLowerCase();

        if (!searchText.isEmpty()) {
            FilteredList<MenuItem> filteredList = new FilteredList<>(menuItems, item ->
                    item.getName().toLowerCase().contains(searchText));
            menuTable.setItems(filteredList);
        } else {
            // Reset to full list if search text is empty
            menuTable.setItems(menuItems);
        }
    }

    // Search by Category
    @FXML
    private void searchByCategory() {
        String searchText = searchByCategoryField.getText().trim().toLowerCase();

        if (!searchText.isEmpty()) {
            FilteredList<MenuItem> filteredList = new FilteredList<>(menuItems, item ->
                    item.getCategory().toLowerCase().contains(searchText));
            menuTable.setItems(filteredList);
        } else {
            // Reset to full list if search text is empty
            menuTable.setItems(menuItems);
        }
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
}