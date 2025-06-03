package com.dao;

import com.controller.DataBaseConfig;
import com.model.ProductCategory;
import java.sql.*;
import java.util.ArrayList;

/**
 * DAO for accessing 'product_category' table.
 */
public class ProductCategoryDAO {

  /**
   * Fetches all product categories.
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
   * Inserts a new product category.
   *
   * @param productCategory the ProductCategory to insert
   * @throws SQLException if database access error occurs
   */
  public static void createItem(ProductCategory productCategory) throws SQLException {
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
  public static void deleteItem(Object selectedCategory) throws SQLException {
    String deleteSql = "DELETE FROM product_category WHERE category_id = ?";
    ProductCategory category = (ProductCategory) selectedCategory;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
      deleteStmt.setInt(1, category.getCategoryId());
      deleteStmt.executeUpdate();
    }
  }

  /**
   * Fetches a product category by id.
   *
   * @param categoryId the id of the category
   * @return ProductCategory or null if not found
   * @throws SQLException if database access error occurs
   */
  public static ProductCategory findById(int categoryId) throws SQLException {
    String query = "SELECT * FROM product_category WHERE category_id = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, categoryId);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return new ProductCategory(
                  rs.getInt("category_id"),
                  rs.getString("category_name")
          );
        }
      }
    }
    return null;
  }

  /**
   * Looks up and returns the categoryId for a given categoryName.
   * Throws SQLException if a database error occurs.
   * Returns -1 if no matching category is found.
   *
   * @param categoryName The name of the category to look up
   * @return categoryId if found, -1 otherwise
   * @throws SQLException if a database access error occurs
   */
  public static int getCategoryIdByName(String categoryName) throws SQLException {
    for (ProductCategory cat : getData()) {
      if (cat.getCategoryName().equalsIgnoreCase(categoryName)) {
        return cat.getCategoryId();
      }
    }
    return -1;
  }

  /**
   * Returns the category name for a given categoryId, or "Unknown" if not found.
   * Throws SQLException if a database error occurs.
   *
   * @param categoryId The id of the category to look up
   * @return categoryName if found, "Unknown" otherwise
   * @throws SQLException if a database access error occurs
   */
  public static String getCategoryNameById(int categoryId) throws SQLException {
    ProductCategory category = findById(categoryId);
    return category != null ? category.getCategoryName() : "Unknown";
  }
}
