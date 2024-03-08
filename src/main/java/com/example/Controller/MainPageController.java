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

public class MainPageController {

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField deadlineTextField;

    private TaskService taskService;

    public MainPageController(){
        taskService = TaskService.getInstance();
    }
    public void initialize() throws SQLException {
        loadTasks();
    }

    private void loadTasks() {
        TaskService taskService = TaskService.getInstance(); // Get an instance of TaskService
        try {
            List<Task> taskList = taskService.findAll(); // Fetch the list of tasks from the service
            ObservableList<Task> taskObservableList = FXCollections.observableArrayList(taskList); // Convert to ObservableList
            taskListView.setItems(taskObservableList); // Set the ObservableList to your ListView
        } catch (SQLException e) {
            e.printStackTrace(); // Properly handle the exception
        }
    }


    @FXML
    private void createTask() throws SQLException {
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        Date deadline = Date.valueOf(deadlineTextField.getText());

        Task task = new Task(title, description, deadline);
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
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/WelcomePage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openTaskForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/TaskForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
