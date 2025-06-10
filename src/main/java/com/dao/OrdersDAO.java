package com.dao;

import com.controller.DataBaseConfig;
import com.model.Order;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO class for handling orders.
 */
public class OrdersDAO {

  /**
   * Gets all orders from the database.
   *
   * @return list of Orders
   * @throws SQLException if a database access error occurs
   */
  public static ArrayList<Order> getData () throws SQLException {
    ArrayList<Order> orders = new ArrayList<>();
    String query = "SELECT * FROM orders";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        orders.add(new Order(
                rs.getInt("order_id"),
                rs.getDate("created_at").toLocalDate(),
                rs.getDate("updated_at").toLocalDate(),
                rs.getDouble("price"),
                rs.getInt("product_count")
        ));
      }
    }
    return orders;
  }

  public static ArrayList<Order> getDataToDisplay () throws SQLException {
    ArrayList<Order> orders = new ArrayList<>();

    String query = "SELECT o.ORDER_ID, o.CREATED_AT, o.UPDATED_AT, o.PRICE, o.PRODUCT_COUNT, " +
            "c.NAME, c.POINTS " +
            "FROM orders o " +
            "JOIN CUSTOMERORDERS co ON o.ORDER_ID = co.ORDER_ID " +
            "JOIN CUSTOMERS c ON co.CUSTOMER_ID = c.CUSTOMER_ID";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        orders.add(new Order(
                rs.getInt("order_id"),
                rs.getDate("created_at").toLocalDate(),
                rs.getDate("updated_at").toLocalDate(),
                rs.getDouble("price"),
                rs.getInt("product_count"),
                rs.getString("customer_name"),
                rs.getInt("points")
        ));
      }
    }

    return orders;
  }

  public static Order getOrderById (int orderId) throws SQLException {
    Order order = null;
    String query = "SELECT * FROM orders WHERE ORDER_ID = ?";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

      pstmt.setInt(1, orderId);  // set the value for the ?

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          order = new Order(
                  rs.getInt("order_id"),
                  rs.getDate("created_at").toLocalDate(),
                  rs.getDate("updated_at").toLocalDate(),
                  rs.getDouble("price"),
                  rs.getInt("product_count")
          );
        }
      }
    }
    return order;
  }

  public static int createItem (Order order) throws SQLException {
    String insertSql = "INSERT INTO ORDERS (CREATED_AT,UPDATED_AT, PRICE,PRODUCT_COUNT ) VALUES " +
            "(?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
      insertStmt.setDate(1, Date.valueOf(order.getCreatedAt()));
      insertStmt.setDate(2, Date.valueOf(order.getUpdateAt()));
      insertStmt.setDouble(3, order.getPrice());
      insertStmt.setInt(4, order.getProductCount());
      insertStmt.executeUpdate();

      ResultSet generatedKeys = insertStmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        return generatedKeys.getInt(1); // Return the generated key
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  //edit item
  public static void updateItem (Order order) throws SQLException {
    String updateSql = "UPDATE ORDERS SET CREATED_AT = ?,UPDATED_AT =?, PRICE = ? ,PRODUCT_COUNT=? " +
            "WHERE ORDER_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setDate(1, Date.valueOf(order.getCreatedAt()));
      insertStmt.setDate(2, Date.valueOf(order.getUpdateAt()));
      insertStmt.setDouble(3, order.getPrice());
      insertStmt.setInt(4, order.getProductCount());
      insertStmt.setInt(5, order.getOrderId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //delete item
  public static void deleteItem (int selectedOrderId) throws SQLException {
    String deleteSql = "DELETE FROM ORDERS WHERE ORDER_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, selectedOrderId);
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // create a product entry for an order
  public static void createOrderProduct (int orderId, int productId, int amount) throws SQLException {
    String insertSql = "INSERT INTO ORDERS_PRODUCTS (ORDER_ID, PRODUCT_ID,AMOUNT_ITEMS ) VALUES (?,?,?)";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setInt(1, orderId);
      insertStmt.setInt(2, productId);
      insertStmt.setInt(3, amount);
      insertStmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //delete a product from an order
  public static void deleteOrderProduct (int orderId, int productId) throws SQLException {
    String deleteSql = "delete from ORDERS_PRODUCTS where ORDER_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, orderId);
      insertStmt.executeUpdate();
    }
  }

  // create a customer entry for an order
  public static void createOrderCustomer (int orderId, int customerId) throws SQLException {
    String insertSql = "INSERT INTO CUSTOMERORDERS (ORDER_ID, CUSTOMER_ID ) VALUES (?,?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setInt(1, orderId);
      insertStmt.setInt(2, customerId);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
