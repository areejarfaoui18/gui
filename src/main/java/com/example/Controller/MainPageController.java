package com.example.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.example.Model.Task;
import com.example.Service.TaskService;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MainPageController {

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField deadlineTextField;

    @FXML
    private Button addTaskForm;

    @FXML
    private Button updateTaskForm;
    @FXML
    private Button goBack;

    private TaskService taskService;

    public MainPageController() {
        taskService = TaskService.getInstance();
    }

    public void initialize() throws SQLException {
        try {
            List<Task> taskList = taskService.findAll(); // Fetch the list of tasks from the service
            //ObservableList<Task> taskObservableList = FXCollections.observableArrayList(taskList); // Convert to ObservableList
            this.taskListView.getItems().addAll(taskList); // Set the ObservableList to your ListView
        } catch (SQLException e) {
            e.printStackTrace(); // Properly handle the exception
        }
    }

    private void loadTasks() {
        // Get an instance of TaskService

    }


    @FXML
    private void createTask() throws SQLException {
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        Date deadline = Date.valueOf(deadlineTextField.getText());

        Task task = new Task(null, title, description, deadline);
        taskService.add(task);
        taskListView.getItems().add(task);
    }

    @FXML
    private void updateTask() throws SQLException {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            Date deadline = Date.valueOf(deadlineTextField.getText());

            selectedTask.setTitle(title);
            selectedTask.setDescription(description);
            selectedTask.setDeadline(deadline);

            taskService.update(selectedTask);
            taskListView.refresh();
        }
    }

    @FXML
    private void deleteTask() throws SQLException {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.delete(selectedTask);
            taskListView.getItems().remove(selectedTask);
        }
    }

    @FXML
    private void handleTaskSelection(MouseEvent event) {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            titleTextField.setText(selectedTask.getTitle());
            descriptionTextField.setText(selectedTask.getDescription());
            deadlineTextField.setText(selectedTask.getDeadline().toString());
        }
    }

    @FXML
    public void goBack() {
        try {
            // Load the SignInPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/WelcomePage.fxml"));
            Parent root = loader.load();

            // Get the stage from the button
            Stage stage = (Stage) goBack.getScene().getWindow();


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

    @FXML
    public void addTaskForm() {
        try {
            // Load the SignInPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/Form.fxml"));
            Parent root = loader.load();

            // Get the stage from the button
            Stage stage = (Stage) addTaskForm.getScene().getWindow();


            // Create a new scene with the SignInPage.fxml content
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/Css/styles2.css")).toExternalForm());
            // Set the scene to the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void updateTaskForm() {
        try {
            // Load the SignInPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/Form2.fxml"));
            Parent root = loader.load();

            // Get the stage from the button
            Stage stage = (Stage) updateTaskForm.getScene().getWindow();


            // Create a new scene with the SignInPage.fxml content
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/Css/styles2.css")).toExternalForm());
            // Set the scene to the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
