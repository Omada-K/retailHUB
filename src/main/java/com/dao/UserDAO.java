package com.dao;

import com.controler.DataBaseConfig;
import com.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//all the communication between users and db is here
public class UserDAO {

  public static ArrayList<User> getUsers () throws SQLException {
    ArrayList<User> users = new ArrayList<User>();
    String query = "select id, name, email, user_password from user";
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
