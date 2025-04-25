package com.dao;

import com.controller.DataBaseConfig;
import com.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  public static ArrayList<Order> getOrders () throws SQLException {
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
}
