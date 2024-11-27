package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class A_dashboard_cont {
    @FXML
    private Stage stage;
    @FXML
    private OrderManager orderManager=new OrderManager();
    @FXML
    Scanner scanner=new Scanner(System.in);

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