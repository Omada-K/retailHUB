package com.dao;

import com.controller.DataBaseConfig;
import com.model.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * This is the data access object of the user.
 * It holds all the methods that "talk" to the DB.
 * Get all, delete, create etc...
 */
public class UserDAO {
  public ArrayList<User> users;
  public String[] columns;

  public static void insertUser (User user) throws SQLException {
    String insertSql = "INSERT INTO USERS (name, email, user_password) VALUES (?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setString(1, user.getName());
      insertStmt.setString(2, user.getEmail());
      insertStmt.setString(3, user.getUserPassword());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("user inserted");
  }

  public static void updateItem (User user) throws SQLException {
    String updateSql = "UPDATE users SET name = ?, email = ?, user_password = ? WHERE id = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setString(1, user.getName());
      insertStmt.setString(2, user.getEmail());
      insertStmt.setString(3, user.getUserPassword());
      insertStmt.setInt(4, user.getId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void deleteItem (Object selectedUser) throws SQLException {
    String deleteSql = "DELETE FROM users WHERE id = ?";
    User user = (User) selectedUser;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, user.getId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<User> getData () throws SQLException {
    ArrayList<User> users = new ArrayList<User>();
    String query = "select id, name, email, user_password from users";
    try (
            Connection conn = DataBaseConfig.getConnection();
            Statement statement = conn.createStatement();
            //.executeQuery(String) returns a data list
            ResultSet result = statement.executeQuery(query)) {

      while (result.next()) {
        User user = new User(
                result.getInt("id"),
                result.getString("name"),
                result.getString("email"),
                result.getString("user_password")
        );
        users.add(user);
      }
    } catch (
            SQLException e) {
      e.printStackTrace();
    }
    return users;
  }
}
