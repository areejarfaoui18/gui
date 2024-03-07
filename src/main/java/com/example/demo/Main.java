package com.example.demo;

import com.example.Utils.DataBaseConnector;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DataBaseConnector.getConnection();
            if (connection != null) {
                System.out.println("Connection successful!");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
