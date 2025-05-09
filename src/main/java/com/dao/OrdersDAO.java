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

  public static void createItem (Order order) throws SQLException {
    String insertSql = "INSERT INTO ORDERS (CREATED_AT,UPDATED_AT, PRICE,PRODUCT_COUNT ) VALUES " +
            "(?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setDate(1, Date.valueOf(order.getCreatedAt()));
      insertStmt.setDate(2, Date.valueOf(order.getUpdateAt()));
      insertStmt.setDouble(3, order.getPrice());
      insertStmt.setInt(4, order.getProductCount());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
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
  public static void deleteItem (Object selectedOrder) throws SQLException {
    String deleteSql = "DELETE FROM ORDERS WHERE ORDER_ID = ?";
    Order order = (Order) selectedOrder;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, order.getOrderId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
