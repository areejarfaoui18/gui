package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/view/WelcomePage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/css/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to Task Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
