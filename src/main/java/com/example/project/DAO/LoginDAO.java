package com.example.project.DAO;

import com.example.project.DBConnection;

import java.sql.*;

public class LoginDAO {

    public boolean authenticateUser(String username, String password) {
        try (Connection connection = new DBConnection().createConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
