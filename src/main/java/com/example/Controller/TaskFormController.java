package com.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.example.Model.Task;
import com.example.Service.TaskService;

import java.sql.Date;
import java.sql.SQLException;

public class TaskFormController {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField deadlineTextField;

    private TaskService taskService;

    private Stage dialogStage;

    private Task task;

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
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
    private void saveTask() throws SQLException {
        if (isInputValid()) {
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            Date deadline = Date.valueOf(deadlineTextField.getText());

            if (task == null) {
                // Adding new task
                Task newTask = new Task(null,title, description, deadline);
                taskService.add(newTask);
            } else {
                // Updating existing task
                task.setTitle(title);
                task.setDescription(description);
                task.setDeadline(deadline);
                taskService.delete(task);
            }

            dialogStage.close();
        }
    }

    @FXML
    private void cancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        // Perform validation here, such as checking if fields are not empty
        // You can add more sophisticated validation logic as needed
        return true;
    }
}
