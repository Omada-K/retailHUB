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
            "user_id INTEGER IDENTITY PRIMARY KEY, " +
            "name VARCHAR(60) NOT NULL, " +
            "email VARCHAR(60) UNIQUE NOT NULL, " +
            "password VARCHAR(60) NOT NULL, " +
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
            "name VARCHAR(40) UNIQUE NOT NULL, " +
            "address VARCHAR(100), " +
            "phone VARCHAR(10) UNIQUE NOT NULL, " +
            "email VARCHAR(40) UNIQUE NOT NULL, " +
            "balance DOUBLE DEFAULT 0," +
            "points INTEGER DEFAULT 0)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("customers table created.");
    }
  }

  public static void createProductsTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS products (" +
            "product_id INTEGER IDENTITY PRIMARY KEY, " +
            "category_id INTEGER, " + // changed from product_category
            "name VARCHAR(60) UNIQUE , " +
            "inv_stock INTEGER," +
            "item_price DOUBLE DEFAULT 0, " +
            "FOREIGN KEY (category_id) REFERENCES product_category(category_id)" +
            ")";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("products table created.");
    }
  }


  public static void createProductCategoryTable() throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS product_category (" +
            "category_id INTEGER IDENTITY PRIMARY KEY, " +
            "category_name VARCHAR(100) NOT NULL UNIQUE)";
    try (Connection conn = getConnection();
      Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("product_category table created.");
      }
  }

  public static void createOrdersTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS orders (" +
            "order_id INTEGER IDENTITY PRIMARY KEY, " +
            "created_at DATE, " +
            "updated_at DATE, " +
            "price DOUBLE default 0, " +
            "product_count INTEGER default 0)";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("orders table created.");
    }
  }

  public static void createOrdersProductsTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS orders_products (" +
            "order_id INTEGER, " +
            "product_id INTEGER," +
            "amount_items INTEGER DEFAULT 0," +
            "PRIMARY KEY (order_id, product_id)," +
            "FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE," +
            "FOREIGN KEY (product_id) REFERENCES products(product_id))";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("orders table created.");
    }
  }

  public static void createCustomerOrdersTable () throws SQLException {
    String ddl = "CREATE TABLE IF NOT EXISTS customerOrders (" +
            "customer_id INTEGER," +
            "order_id INTEGER," +
            "PRIMARY KEY (customer_id, order_id)," +
            "FOREIGN KEY (customer_id) REFERENCES customers(customer_id)," +
            "FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE" +
            ")";
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(ddl);
      System.out.println("Customer-Orders table created.");
    }

  }

  public static void createAllTables () throws SQLException {
    //H seira dimiourgias ehei simasia
    createUsersTable();
    createOrdersTable();
    createCustomersTable();
    createProductCategoryTable();
    createProductsTable();
    createOrdersProductsTable();
    createCustomerOrdersTable();

    System.out.println("✅ All tables created successfully.");
  }

}
