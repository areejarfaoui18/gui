package com.example.Controller;

import com.example.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.Objects;

public class SignInPageController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

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
    private void gotoSignUp(ActionEvent event) {
        // Load the WelcomePage.fxml file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/View/SignUpPage.fxml"));
            Parent root = loader.load();

            // Create a new Scene with the welcome page content
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/Css/styles.css")).toExternalForm());

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
    private void handleSignIn(ActionEvent event) {
        // Retrieve the username and password from text fields
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // Create a User object
        User user = new User(username, password);

        // Perform database authentication
        boolean authenticated = user.authenticate();

        if (authenticated) {
            // Navigate to another page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/MainPage.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/Css/styles2.css")).toExternalForm());
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Display an error message to the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Authentication Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }
}
