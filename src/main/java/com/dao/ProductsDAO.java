package com.dao;

import com.controller.DataBaseConfig;
import com.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  public static ArrayList<Product> getProducts () throws SQLException {
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
                rs.getDouble("price")
        ));
      }
    }
    return products;
  }
}
