package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class C_Login {
    @FXML
    private Stage stage;
    @FXML
    private TextField email,password;

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

    public void Back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Screen2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        TypeController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void c_dashboard(MouseEvent mouseEvent) throws IOException {
        HashMap<String,Customer> abc=file.loadCredentials();
        if(abc.containsKey(email.getText()) && abc.get(email.getText()).getPassword().equals(password.getText()) ) {
            System.out.println("Login Successful.");

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("C_Dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);

            C_dashboard controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.setUsername(abc.get(email.getText()).getName());
            controller.setCustomer(abc.get(email.getText()));


            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("Invalid Login Credentials");
        }
    }
}