package com.dao;

import com.controller.DataBaseConfig;
import com.model.Products;

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
  public static ArrayList<Products> getProducts() throws SQLException {
    ArrayList<Products> products = new ArrayList<>();
    String query = "SELECT * FROM products";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        products.add(new Products(
                rs.getInt("product_id"),
                rs.getInt("product_category"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price")
        ));
      }
    }
    return products;
  }
}
