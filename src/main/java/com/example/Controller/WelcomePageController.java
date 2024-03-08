package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePageController implements Initializable {

    @FXML
    private Button getStartedButton;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any required setup
    }

    @FXML
    private void handleGetStarted(ActionEvent event) {
        // Handle "Get Started" button click event
        // Navigate to the next page (to be implemented)
    }
}

