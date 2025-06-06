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
  public static ArrayList<Product> getData() throws SQLException {
    ArrayList<Product> products = new ArrayList<>();

    String query = "SELECT product_id, category_id, name, inv_stock, item_price" +
            " FROM products";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        products.add(new Product(
                rs.getInt("product_id"),
                rs.getInt("category_id"),
                rs.getString("name"),
                rs.getInt("inv_stock"),
                rs.getDouble("item_price"))
                    );
      }
    }
    return products;
  }

  /**
   * Inserts a new product record.
   *
   * @param product the Product to insert
   * @throws SQLException if database access error occurs
   */
  public static void createItem(Product product) throws SQLException {
    String insertSql = "INSERT INTO products (category_id, name, inv_stock, item_price) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setInt(1, product.getCategoryId());
      insertStmt.setString(2, product.getProductName());
      insertStmt.setInt(3, product.getAmountInStock());
      insertStmt.setDouble(4, product.getProductPrice());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates an existing product record.
   *
   * @param product the Product to update
   * @throws SQLException if database access error occurs
   */
  public static void updateItem(Product product) throws SQLException {
    String updateSql = "UPDATE products SET category_id = ?, name = ?, inv_stock = ?, item_price = ?" +
            "WHERE product_id = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
      updateStmt.setInt(1, product.getCategoryId());
      updateStmt.setString(2, product.getProductName());
      updateStmt.setInt(3, product.getAmountInStock());
      updateStmt.setDouble(4, product.getProductPrice());
      updateStmt.setInt(5, product.getProductId());
      updateStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deletes a product record.
   *
   * @param selectedProduct the Product to delete
   * @throws SQLException if database access error occurs
   */
  public static void deleteItem(Object selectedProduct) throws SQLException {
    String deleteSql = "DELETE FROM products WHERE product_id = ?";
    Product product = (Product) selectedProduct;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
      deleteStmt.setInt(1, product.getProductId());
      deleteStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fetches products included in orders.
   *
   * @return list of Products in orders
   * @throws SQLException if database access error occurs
   */
  public static ArrayList<Product> getOrderedProducts() throws SQLException {
    ArrayList<Product> orderedProducts = new ArrayList<>();

    String query = "SELECT p.product_id, p.category_id, p.name, p.inv_stock,p.item_price FROM products p "+
                    "JOIN orders_products op ON p.product_id = op.product_id";
    try(Connection conn = DataBaseConfig.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        Product product = new Product(
                rs.getInt("product_id"),
                rs.getInt("category_id"),
                rs.getString("name"),
                rs.getInt("inv_stock"),
                rs.getDouble("item_price")
        );
        orderedProducts.add(product);
        }
      }
    return orderedProducts;
  }
}