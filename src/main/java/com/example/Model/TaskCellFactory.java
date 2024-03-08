package com.example.Model;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import java.text.SimpleDateFormat;


public class TaskCellFactory implements Callback<ListView<Task>, ListCell<Task>> {

    @Override
    public ListCell<Task> call(ListView<Task> listView) {
        return new ListCell<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null);
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String deadlineString = task.getDeadline() != null ? dateFormat.format(task.getDeadline()) : "No deadline";
                    setText("Task ID: " + task.getId() + ", Description: " + task.getDescription() + ", Deadline: " + deadlineString);
                }
            }
        };
    }
}
