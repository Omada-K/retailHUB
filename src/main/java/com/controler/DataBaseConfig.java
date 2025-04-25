package com.controler;

import java.sql.*;

/**
 * This is where the DB is created.
 * It holds all the methods that create the SQL tables.
 */
public class DataBaseConfig {
  private static final String JDBC_URL = "jdbc:hsqldb:file:./database/retailDB;shutdown=true";
  private static final String JDBC_USER = "SA";
  private static final String JDBC_PASSWORD = "";

  // database connection
  public static Connection getConnection () throws SQLException {
    Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    System.out.println("Connection established");
    return con;
  }

  //TASK someone rename this to creatTables and create all the 4(or how many...) tables from diagram
  //Task extend user table to have all the correct fields
  public static void createUserTable () throws SQLException {
    String createTableDdl = "CREATE TABLE IF NOT EXISTS user (" +
            "id INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(60) NOT NULL, " +
            "email VARCHAR(60) UNIQUE NOT NULL," +
            "user_password VARCHAR(60) NOT NULL)";

    //is_manager? BOOLEAN
    try (Connection conn = getConnection();
         Statement statement = conn.createStatement()) {
      //.executeUpdate(String)
      statement.executeUpdate(createTableDdl);

      System.out.println("user table created.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("Database initialized successfully.");
  }
  // Adding users to the DB using PreparedStatement for security
  public static void insertUserIfNotExists(String name, String email, String password) {
    String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
    String insertSql = "INSERT INTO user (name, email, user_password) VALUES (?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

      checkStmt.setString(1, email); //Replaces the first ? in the checkSql with the actual email.
      var rs = checkStmt.executeQuery(); //Runs the SELECT COUNT(*) query and stores the result in rs (a ResultSet).
      rs.next(); //Moves to the first (and only) row in the result set.
      int count = rs.getInt(1); //Gets the value from the first column — the number of users with that email.

      //if the user does not exist aka count = 0
      if (count == 0) {
        insertStmt.setString(1, name);
        insertStmt.setString(2, email);
        insertStmt.setString(3, password);
        insertStmt.executeUpdate();
        System.out.println("Inserted user: " + name);
      } else {
        System.out.println("User with email " + email + " already exists.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  //TASK add more table creation methods eg createCustomerTable()
}
