package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

  public static void shutdownDatabase () {
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.execute("SHUTDOWN");
      System.out.println("Database shutdown successfully.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void createUsersTable () throws SQLException {
    String createTableDdl = "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(60) NOT NULL, " +
            "email VARCHAR(60) UNIQUE NOT NULL, " +
            "user_password VARCHAR(60) NOT NULL, " +  // <-- comma, not closing the parenthesis
            "is_admin BOOLEAN DEFAULT FALSE" +
            ")";

    try (Connection conn = getConnection();
         Statement statement = conn.createStatement()) {
      statement.executeUpdate(createTableDdl);
      System.out.println("users table created.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void createCustomersTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS customers (" +
            "customer_id INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(100) UNIQUE NOT NULL, " +
            "address VARCHAR(200), " +
            "phone VARCHAR(20) UNIQUE NOT NULL, " +
            "email VARCHAR(100))";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("customers table created.");
    }
  }

  public static void createProductsTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS products (" +
            "product_id INTEGER IDENTITY PRIMARY KEY, " +
            "product_category VARCHAR(100), " +
            "name VARCHAR(100) UNIQUE , " +
            "description VARCHAR(300), " +
            "price DOUBLE," +
            "amount_in_stock INTEGER)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("products table created.");
    }
  }

  public static void createOrdersTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS orders (" +
            "order_id INTEGER IDENTITY PRIMARY KEY, " +
            "date DATE, " +
            "customer_id INTEGER, " +
            "product_id INTEGER," +
            "FOREIGN KEY (customer_id) REFERENCES customers(customer_id))";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("orders table created.");
    }
  }

  public static void createOrdersProductsTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS orders_products (" +
            "order_id INTEGER, " +
            "product_id INTEGER," +
            "quantity INTEGER," +
            "PRIMARY KEY (order_id, product_id)," +
            "FOREIGN KEY (order_id) REFERENCES orders(order_id)," +
            "FOREIGN KEY (product_id) REFERENCES products(product_id)" +
            ")";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("orders table created.");
    }
  }

  public static void createDiscountsTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS discounts (" +
            "customer_id INTEGER PRIMARY KEY, " +
            "balance DOUBLE, " +
            "discount_percentage FLOAT," +
            "FOREIGN KEY (customer_id) REFERENCES customers(customer_id))";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("discounts table created.");
    }
  }

  public static void createAllTables () throws SQLException {
    createUsersTable();
    createCustomersTable();
    createProductsTable();
    createOrdersTable();
    createDiscountsTable();
    createOrdersProductsTable();
    System.out.println("✅ All tables created successfully.");
  }

}
