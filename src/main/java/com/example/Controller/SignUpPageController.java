package com.example.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.example.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpPageController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleGoBack(ActionEvent event) {
        // Load the WelcomePage.fxml file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/View/WelcomePage.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the welcome page content
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/Css/styles.css").toExternalForm());

            // Get the stage from the event source
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        // Retrieve the username and password from text fields
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        // Create a User object
        User user = new User(username, password);

        // Add the user to the database
        boolean added = user.addToDatabase();

        if (added) {
            // Display a success message to the user
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sign Up Successful");
            alert.setHeaderText(null);
            alert.setContentText("User has been successfully added!");
            alert.showAndWait();

            // Navigate to another page (e.g., WelcomePage)
            handleGoBack(event);
        } else {
            // Display an error message to the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Sign Up Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add user. Please try again.");
            alert.showAndWait();
        }
    }
}
