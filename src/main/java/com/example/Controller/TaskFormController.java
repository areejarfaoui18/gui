package com.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.example.Model.Task;
import com.example.Service.TaskService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class TaskFormController {

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField deadlineTextField;

    private TaskService taskService;

    private Stage dialogStage;

    private Task task;

    @FXML
    Button cancel;

    public TaskFormController(){
        taskService = TaskService.getInstance();
        dialogStage = new Stage();
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void setTask(Task task) {
        this.task = task;
        if (task != null) {
            titleTextField.setText(task.getTitle());
            descriptionTextField.setText(task.getDescription());
            deadlineTextField.setText(task.getDeadline().toString());
        }
    }

    @FXML
    private void saveTask() throws SQLException,NullPointerException {
        if (isInputValid()) {
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            Date deadline = Date.valueOf(deadlineTextField.getText());
            setTask(task);
            if (task == null) {
                Task newTask = new Task(0,title, description, deadline);
                taskService.add(newTask);
            } else {
                task.setTitle(title);
                task.setDescription(description);
                task.setDeadline(deadline);
                taskService.delete(task);
            }


        }
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
        cancel();
    }

    private boolean isInputValid() {
        // Perform validation here, such as checking if fields are not empty
        // You can add more sophisticated validation logic as needed
        return true;
    }

    @FXML
    public void cancel() {
        try {
            // Load the SignInPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/view/MainPage.fxml"));
            Parent root = loader.load();

            // Get the stage from the button
            Stage stage = (Stage) cancel.getScene().getWindow();


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
