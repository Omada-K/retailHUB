package com.controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConfig{
  private static final String JDBC_URL = "jdbc:hsqldb:file:./database/mydb;shutdown=true";
  private static final String JDBC_USER = "SA";
  private static final String JDBC_PASSWORD = "";

  // database connection
  public static Connection getConnection() throws SQLException{
    Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    System.out.println("Connection established");
    return con;
  }

  // create atable
  public static void createUserTable() throws SQLException{
    try(Connection conn = getConnection();
        Statement stmt = conn.createStatement()){

      String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
              "id INTEGER IDENTITY PRIMARY KEY, " +
              "name VARCHAR(60) NOT NULL, " +
              "email VARCHAR(60) UNIQUE NOT NULL," +
              "user_password VARCHAR(60) UNIQUE NOT NULL)";
      //is_manager? BOOLEAN

      stmt.executeUpdate(createTableSQL);// run the above query

      System.out.println("Database initialized successfully.");
      System.out.println("users table created.");
    }catch(SQLException e){
      e.printStackTrace();
    }
  }
}
