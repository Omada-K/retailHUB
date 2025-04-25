package com.dao;

import com.controller.DataBaseConfig;
import com.model.Discount;

import java.sql.*;

/**
 * DAO class for retrieving discount information for customers.
 */
public class DiscountDAO {

  /**
   * Gets discount by customer ID.
   *
   * @param customerId the customer ID
   * @return a Discount object or null if not found
   * @throws SQLException if a database access error occurs
   */
  public static Discount getDiscountByCustomerId(int customerId) throws SQLException {
    String query = "SELECT * FROM discount WHERE customer_id = ?";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

      stmt.setInt(1, customerId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Discount(
                rs.getInt("customer_id"),
                rs.getDouble("amount"),
                rs.getDouble("discount_percentage")
        );
      }
    }
    return null;
  }
}
