package com.dao;

import com.controller.DataBaseConfig;
import com.model.Product;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO for accessing 'products' table.
 */
public class ProductsDAO {

  /**
   * Fetches all product records.
   *
   * @return list of Products
   * @throws SQLException if database access error occurs
   */
  public static ArrayList<Product> getData () throws SQLException {
    ArrayList<Product> products = new ArrayList<>();
    String query = "SELECT * FROM products";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        products.add(new Product(
                rs.getInt("product_id"),
                rs.getString("product_category"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("amount_in_stock")
        ));
      }
    }
    return products;
  }

  public static void insertProduct (Product product) throws SQLException {
    String insertSql = "INSERT INTO PRODUCTS (PRODUCT_CATEGORY,NAME,DESCRIPTION, PRICE ) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setString(1, product.getProductCategory());
      insertStmt.setString(2, product.getProductName());
      insertStmt.setString(3, product.getProductDescription());
      insertStmt.setDouble(4, product.getProductPrice());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //edit item
  public static void updateItem (Product product) throws SQLException {
    String updateSql = "UPDATE PRODUCTS SET PRODUCT_CATEGORY = ?, NAME = ?, DESCRIPTION = ? , PRICE = ? WHERE " +
            "PRODUCT_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setString(1, product.getProductCategory());
      insertStmt.setString(2, product.getProductName());
      insertStmt.setString(3, product.getProductDescription());
      insertStmt.setDouble(4, product.getProductPrice());
      insertStmt.setInt(5, product.getProductId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //delete item
  public static void deleteItem (Object selectedProduct) throws SQLException {
    String deleteSql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
    Product product = (Product) selectedProduct;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, product.getProductId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Product> getProductsOfOrder (int orderId) throws SQLException {
    ArrayList<Product> products = new ArrayList<>();
    String query = "SELECT p.* " +
            "FROM products p " +
            "JOIN orders_products op ON p.product_id = op.product_id " +
            "WHERE op.order_id = ?;";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, orderId); // bind the orderId to the query

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          products.add(new Product(
                  rs.getInt("product_id"),
                  rs.getString("product_category"),
                  rs.getString("name"),
                  rs.getString("description"),
                  rs.getDouble("price"),
                  rs.getInt("ammount_in_stock")
          ));
        }
      }
    }
    return products;
  }
}
