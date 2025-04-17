package com.controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

  //TASK add more table creation methods eg createCustomerTable()
}
