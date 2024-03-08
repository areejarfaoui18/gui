package com.example.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.example.Model.Task;

import java.util.List;

public class MainPageController {

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private Button goBackButton;

    @FXML
    private void initialize() {
        // Call the method to retrieve all tasks from the database
        List<Task> tasks = Task.getAllTasks();

        // Populate the ListView with the retrieved tasks
        taskListView.getItems().addAll(tasks);

        // Set custom cell factory to customize the appearance of each cell
        taskListView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> param) {
                return new ListCell<Task>() {
                    @Override
                    protected void updateItem(Task item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getId() + "\t" + item.getTitle() + "\t" + item.getDescription() + "\t" + item.getDeadline()); // Customize how Task is displayed
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void goBack() {
        // Close the current window (MainPage)
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openTaskForm() {
        // Code to open the task form window
    }
}
