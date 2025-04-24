package com.dao;

import com.controller.DataBaseConfig;
import com.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO class for the 'product_category' table.
 */
public class ProductCategoryDAO {

  /**
   * Retrieves all product categories.
   *
   * @return list of ProductCategory
   * @throws SQLException if database access error occurs
   */
  public static ArrayList<ProductCategory> getCategories() throws SQLException {
    ArrayList<ProductCategory> categories = new ArrayList<>();
    String query = "SELECT * FROM product_category";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        categories.add(new ProductCategory(
                rs.getInt("category_id"),
                rs.getString("description"),
                rs.getInt("quantity"),
                rs.getDouble("price_amount")
        ));
      }
    }
    return categories;
  }
}
