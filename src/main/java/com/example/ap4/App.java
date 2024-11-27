package com.example.ap4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        OrderManager.addMenuItemDirect("Burger", 50, "Meals", 10);
        OrderManager.addMenuItemDirect("Pizza", 150, "Meals", 5);
        OrderManager.addMenuItemDirect("Cola", 60, "Beverages", 20);
        OrderManager.addMenuItemDirect("Fries",40, "Snacks", 15);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ByteMe.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}