package com.controller;

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

  public static void createUserTable () throws SQLException {
    String createTableDdl = "CREATE TABLE IF NOT EXISTS user (" +
            "id INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(60) NOT NULL, " +
            "email VARCHAR(60) UNIQUE NOT NULL," +
            "user_password VARCHAR(60) NOT NULL)";
    try (Connection conn = getConnection();
         Statement statement = conn.createStatement()) {
      statement.executeUpdate(createTableDdl);
      System.out.println("user table created.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void createCustomersTable() throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS customers (" +
            "customer_id INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(100), " +
            "address VARCHAR(200), " +
            "phone VARCHAR(20), " +
            "email VARCHAR(100))";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("customers table created.");
    }
  }

  public static void createProductsTable() throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS products (" +
            "product_id INTEGER IDENTITY PRIMARY KEY, " +
            "product_category INTEGER, " +
            "name VARCHAR(100), " +
            "description VARCHAR(300), " +
            "price DOUBLE)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("products table created.");
    }
  }

  public static void createOrdersTable() throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS orders (" +
            "order_id INTEGER IDENTITY PRIMARY KEY, " +
            "date DATE, " +
            "quantity INTEGER, " +
            "amount DOUBLE, " +
            "customer_id INTEGER, " +
            "product_id INTEGER)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("orders table created.");
    }
  }

  public static void createProductCategoryTable() throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS product_category (" +
            "category_id INTEGER IDENTITY PRIMARY KEY, " +
            "description VARCHAR(200), " +
            "quantity INTEGER, " +
            "price_amount DOUBLE)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("product_category table created.");
    }
  }

  public static void createDiscountTable() throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS discount (" +
            "customer_id INTEGER, " +
            "amount DOUBLE, " +
            "discount_percentage DOUBLE)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("discount table created.");
    }
  }

  public static void createAllTables() throws SQLException {
    createUserTable();
    createCustomersTable();
    createProductsTable();
    createOrdersTable();
    createProductCategoryTable();
    createDiscountTable();
    System.out.println("✅ All tables created successfully.");
  }
}
