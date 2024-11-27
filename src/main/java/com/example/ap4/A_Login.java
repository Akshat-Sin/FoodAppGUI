package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class A_Login {
    @FXML
    private Stage stage;

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

    public void CustomerSignup(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("c_signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        c_signup controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void AdminSignup(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("a_signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        a_signup controller = fxmlLoader.getController();
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

    public void a_dashboard(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("A_Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        A_dashboard_cont controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}