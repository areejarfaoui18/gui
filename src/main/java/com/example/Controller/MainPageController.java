package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPageController {

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Load the WelcomePage.fxml
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/View/WelcomePage.fxml")));

            // Set the scene onto the stage
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/Css/styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
