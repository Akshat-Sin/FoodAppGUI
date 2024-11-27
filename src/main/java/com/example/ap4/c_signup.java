package com.example.ap4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class c_signup {
    @FXML
    private Stage stage;
    @FXML
    private TextField email,username,password;

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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("C_Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        C_Login controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void CustomerLogin(MouseEvent mouseEvent) throws IOException {
        HashMap<String,Customer> abc=file.loadCredentials();
        abc.put(email.getText(),new Customer(username.getText(),email.getText(),password.getText(),new OrderManager()));
        file.saveCredentials(abc);
        System.out.println(username.getText()+" "+email.getText()+" "+password.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Screen3.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        C_Login controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}