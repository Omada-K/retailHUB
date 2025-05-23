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
    //updated sql query, applied join method ;)

    String query = "SELECT p.product_id, c.category_name, p.name, p.inv_stock, p.item_price " +
            "FROM products p " +
            "LEFT JOIN product_category c ON p.category_id = c.category_id";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        products.add(new Product(
                rs.getInt("product_id"),
                rs.getString("category_name"),
                rs.getString("name"),
                rs.getInt("inv_stock"),
                rs.getDouble("item_price")
        ));
      }
    }
    return products;
  }

  public static void createItem (Product product) throws SQLException {
    String insertSql = "INSERT INTO PRODUCTS (CATEGORY_ID,NAME,INV_STOCK, ITEM_PRICE ) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setInt(1, product.getCategoryId());
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
    String updateSql = "UPDATE PRODUCTS SET CATEGORY_ID = ?, NAME = ?, INV_STOCK = ?, ITEM_PRICE = ? " +
            "WHERE PRODUCT_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setInt(1, product.getCategoryId());
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

  public static ArrayList<Product> getOrderedProducts () throws SQLException {
    ArrayList<Product> orderedProducts = new ArrayList<>();

    String query = "SELECT c.category_name, p.name, p.item_price, p.inv_stock, op.amount_items " +
            "FROM products p " +
            "JOIN orders_products op ON p.product_id = op.product_id " +
            "LEFT JOIN product_category c ON p.category_id = c.category_id";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        String category = rs.getString("category_name");
        String name = rs.getString("name");
        double price = rs.getDouble("item_price");
        int itemsInOrder = rs.getInt("amount_items");
        int stock = rs.getInt("inv_stock");

        Product product = new Product(category, name, price, itemsInOrder, stock);

        orderedProducts.add(product);
      }

      return orderedProducts;
    }
  }
}
