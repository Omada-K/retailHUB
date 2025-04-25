package com.dao;

import com.controller.DataBaseConfig;
import com.model.Orders;

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
  public static ArrayList<Orders> getOrders() throws SQLException {
    ArrayList<Orders> orders = new ArrayList<>();
    String query = "SELECT * FROM orders";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        orders.add(new Orders(
                rs.getInt("order_id"),
                rs.getDate("date"),
                rs.getInt("quantity"),
                rs.getDouble("amount"),
                rs.getInt("customer_id"),
                rs.getInt("product_id")
        ));
      }
    }

    return orders;
  }
}
