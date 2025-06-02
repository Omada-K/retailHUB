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
                rs.getInt("inv_stock"),
                rs.getDouble("item_price")
        ));
      }
    }
    return products;
  }

  public static void createItem (Product product) throws SQLException {
    String insertSql = "INSERT INTO PRODUCTS (PRODUCT_CATEGORY,NAME,INV_STOCK, ITEM_PRICE ) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setString(1, product.getCategory());
      insertStmt.setString(2, product.getName());
      insertStmt.setInt(3, product.getAmountInStock());
      insertStmt.setDouble(4, product.getItemPrice());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //edit item
  public static void updateItem (Product product) throws SQLException {
    String updateSql = "UPDATE PRODUCTS SET PRODUCT_CATEGORY = ?, NAME = ?, INV_STOCK = ? , ITEM_PRICE = ? WHERE " +
            "PRODUCT_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setString(1, product.getCategory());
      insertStmt.setString(2, product.getName());
      insertStmt.setInt(3, product.getAmountInStock());
      insertStmt.setDouble(4, product.getItemPrice());
      insertStmt.setInt(5, product.getProductId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //delete item
  public static void deleteItem (Object selectedProduct) throws SQLException {
    String deleteSql = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
    Product product = (Product) selectedProduct;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, product.getProductId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<Product> getOrderedProducts (int orderId) throws SQLException {
    ArrayList<Product> orderedProducts = new ArrayList<>();

    String query = "SELECT p.product_category, p.name, p.item_price, p.inv_stock, op.amount_items " +
            "FROM products p " +
            "JOIN orders_products op ON p.PRODUCT_ID = op.PRODUCT_ID " +
            "WHERE op.order_id = ?";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setInt(1, orderId);  // Set the orderId in the query
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          String category = rs.getString("product_category");
          String name = rs.getString("name");
          double price = rs.getDouble("item_price");
          int stock = rs.getInt("inv_stock");
          int itemsInOrder = rs.getInt("amount_items");

          Product product = new Product(category, name, price, itemsInOrder, stock);
          orderedProducts.add(product);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return orderedProducts;
  }
}
