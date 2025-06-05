package com.dao;

import com.controller.DataBaseConfig;
import com.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO for accessing 'product_category' table.
 * Only getData(), createItem(), updateItem(), deleteItem() are kept.
 *
 * Για lookup με id ή name, κάνεις loop στο αποτέλεσμα του getData().
 */
public class ProductCategoryDAO {

  /**
   * Fetches all product categories from the DB.
   *
   * @return list of ProductCategory
   * @throws SQLException if database access error occurs
   */
  public static ArrayList<ProductCategory> getData() throws SQLException {
    ArrayList<ProductCategory> productCategories = new ArrayList<>();
    String query = "SELECT * FROM product_category";
    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        productCategories.add(new ProductCategory(
                rs.getInt("category_id"),
                rs.getString("category_name")
        ));
      }
    }
    return productCategories;
  }

  /**
   * Inserts a new product category if it doesn't already exist (case-insensitive).
   *
   * @param productCategory the ProductCategory to insert
   * @throws SQLException if database access error occurs
   */
  public static void createItem(ProductCategory productCategory) throws SQLException {
    String checkSql = "SELECT COUNT(*) FROM product_category WHERE LOWER(category_name) = LOWER(?)";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
      checkStmt.setString(1, productCategory.getCategoryName());
      try (ResultSet rs = checkStmt.executeQuery()) {
        if (rs.next() && rs.getInt(1) > 0) {
          // Category already exists, do not insert
          return;
        }
      }
    }

    String insertSql = "INSERT INTO product_category (category_name) VALUES (?)";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setString(1, productCategory.getCategoryName());
      insertStmt.executeUpdate();
    }
  }

  /**
   * Updates an existing product category.
   *
   * @param productCategory the ProductCategory to update
   * @throws SQLException if database access error occurs
   */
  public static void updateItem(ProductCategory productCategory) throws SQLException {
    String updateSql = "UPDATE product_category SET category_name = ? WHERE category_id = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
      updateStmt.setString(1, productCategory.getCategoryName());
      updateStmt.setInt(2, productCategory.getCategoryId());
      updateStmt.executeUpdate();
    }
  }

  /**
   * Deletes a product category.
   *
   * @param selectedCategory the ProductCategory to delete
   * @throws SQLException if database access error occurs
   */
  public static void deleteItem(ProductCategory selectedCategory) throws SQLException {
    String deleteSql = "DELETE FROM product_category WHERE category_id = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
      deleteStmt.setInt(1, selectedCategory.getCategoryId());
      deleteStmt.executeUpdate();
    }
  }
}
