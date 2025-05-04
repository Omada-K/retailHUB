package com.dao;

import com.controller.DataBaseConfig;
import com.model.Order;

import java.sql.*;
import java.time.LocalDate;
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
                rs.getDate("date").toLocalDate(),
                rs.getInt("quantity"),
                rs.getDouble("amount"),
                rs.getInt("customer_id"),
                rs.getInt("product_id")
        ));
      }
    }
    return orders;
  }

  public static void insertOrder (Order order) throws SQLException {
    String insertSql = "INSERT INTO ORDERS (ORDER_ID,DATE,QUANTITY, AMOUNT, CUSTOMER_ID,PRODUCT_ID ) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setInt(1, order.getOrderId());
      insertStmt.setDate(2, Date.valueOf((LocalDate) order.getOrderDate()));
      insertStmt.setInt(3, order.getQuantity());
      insertStmt.setDouble(4, order.getAmount());
      insertStmt.setInt(5, order.getCustomerId());
      insertStmt.setInt(6, order.getProductId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //edit item
  public static void updateItem (Order order) throws SQLException {
    String updateSql = "UPDATE ORDERS SET DATE = ?, QUANTITY = ?, AMOUNT = ? , CUSTOMER_ID = ?,PRODUCT_ID=? WHERE " +
            "CUSTOMER_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setDate(1, Date.valueOf(LocalDate.now()));
      insertStmt.setInt(2, order.getQuantity());
      insertStmt.setDouble(3, order.getAmount());
      insertStmt.setInt(4, order.getCustomerId());
      insertStmt.setInt(5, order.getProductId());
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
