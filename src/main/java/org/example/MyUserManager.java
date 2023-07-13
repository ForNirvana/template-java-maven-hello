package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUserManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public boolean registerUser(String username, String password) { //注册验证
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username, password) VALUES (?, ?)")) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("User registered successfully!\n\n");

            return true;
        } catch (SQLException e) {
            System.out.println("Failed to register user: " + e.getMessage() + "\n\n");
        }

        return false;
    }

    public boolean login(String username, String password) { //登录验证
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (password.equals(storedPassword)) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("Username does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to modify: " + e.getMessage() + "\n\n");
        }

        return false;
    }

    public boolean Modify(String username, String passwordOld, String passwordNew){ //密码修改验证
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("passwordOld");
                if (passwordOld.equals(storedPassword)) {
                    statement.setString(2, passwordNew);
                    System.out.println("Modify successful!");
                    return true;
                } else {
                    System.out.println("Incorrect oldPassword.");
                }
            } else {
                System.out.println("Username does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to login: " + e.getMessage() + "\n\n");
        }

        return false;
    }

    public boolean Reset(String username, String passwordOld, String passwordNew){ //密码重置验证
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("passwordOld");
                if (passwordOld.equals(storedPassword)) {
                    statement.setString(2, passwordNew);
                    System.out.println("Reset successful!");
                    return true;
                } else {
                    System.out.println("Incorrect oldPassword.");
                }
            } else {
                System.out.println("Username does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to reset: " + e.getMessage() + "\n\n");
        }
        
        return false;
    }
}
