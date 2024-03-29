package com.example.Model;

import com.example.Utils.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to authenticate user against database
    public boolean authenticate() {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (
                Connection connection = DataBaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            return statement.executeQuery().next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add user to the database
    public boolean addToDatabase() {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (
                Connection connection = DataBaseConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            return statement.executeUpdate() > 0; // Returns true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
