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

  public static void insertUserIfNotExists (User user) throws SQLException {
    String checkSql = "SELECT COUNT(*) FROM users WHERE email = ?";
    String insertSql = "INSERT INTO users (name, email, user_password) VALUES (?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

      checkStmt.setString(1, user.getEmail()); //Replaces the first ? in the checkSql with the actual email.
      var rs = checkStmt.executeQuery(); //Runs the SELECT COUNT(*) query and stores the result in rs (a ResultSet).
      rs.next(); //Moves to the first (and only) row in the result set.
      int count = rs.getInt(1); //Gets the value from the first column — the number of users with that email.

      //if the user does not exist aka count = 0
      if (count == 0) {
        insertStmt.setString(1, user.getName());
        insertStmt.setString(2, user.getEmail());
        insertStmt.setString(3, user.getUserPassword());
        insertStmt.executeUpdate();
      } else {
        System.out.println("User with email " + user.getEmail() + " already exists.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
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
