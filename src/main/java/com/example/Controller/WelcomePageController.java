package com.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WelcomePageController {

    @FXML
    private Button getStartedButton;

    @FXML
    private void handleGetStarted() {
        try {
            // Load the SignInPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/SignInPage.fxml"));
            Parent root = loader.load();

            // Get the stage from the button
            Stage stage = (Stage) getStartedButton.getScene().getWindow();

            // Create a new scene with the SignInPage.fxml content
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/Css/styles.css")).toExternalForm());
            // Set the scene to the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
