package com.example.Service;


import com.example.Model.Task;
import com.example.Utils.DataBaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements IService<Task> {

    private Statement statement;
    private static TaskService instance;

    private TaskService() {
        try {
            Connection connection = DataBaseConnector.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    @Override
    public void add(Task task) throws SQLException {
        String query = "INSERT INTO tasks (title, description, deadline) VALUES ('"
                + task.getTitle() + "', '"
                + task.getDescription() + "', '"
                + task.getDeadline() + "');";
        statement.executeUpdate(query);
    }

    @Override
    public boolean delete(Task task) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = " + task.getId() + ";";
        int rowsDeleted = statement.executeUpdate(query);
        return rowsDeleted > 0;
    }

    @Override
    public boolean update(Task task) throws SQLException {
        String query = "UPDATE tasks SET title = '" + task.getTitle()
                + "', description = '" + task.getDescription()
                + "', deadline = '" + task.getDeadline()
                + "' WHERE id = " + task.getId() + ";";
        int rowsUpdated = statement.executeUpdate(query);
        return rowsUpdated > 0;
    }

    @Override
    public Task findById(Task task) throws SQLException {
        String query = "SELECT * FROM tasks WHERE id = " + task.getId() + ";";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return new Task(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDate("deadline"));
        }
        return null;
    }

    @Override
    public List<Task> findAll() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks;";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Task task = new Task(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getDate("deadline"));
            tasks.add(task);

        }
        System.out.println(tasks);
        return tasks;

    }
}
