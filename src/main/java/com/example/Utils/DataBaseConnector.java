package com.example.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/task_tracker_db";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
}


