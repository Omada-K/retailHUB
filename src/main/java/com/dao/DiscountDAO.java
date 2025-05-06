package com.dao;

import com.controller.DataBaseConfig;
import com.model.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
  public static Discount getDiscountByCustomerId (int customerId) throws SQLException {
    String query = "SELECT * FROM DISCOUNTS WHERE customer_id = ?";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

      stmt.setInt(1, customerId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Discount(
                rs.getInt("customer_id"),
                rs.getDouble("balance"),
                rs.getFloat("discount_percentage")
        );
      }
    }
    return null;
  }

  public static void createItem (Discount discount, int customerId) throws SQLException {
    String createSql = "INSERT INTO DISCOUNTS (CUSTOMER_ID, BALANCE, DISCOUNT_PERCENTAGE) VALUES (?,?,?)";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(createSql)) {
      insertStmt.setInt(1, customerId);
      insertStmt.setDouble(2, discount.getBalance());
      insertStmt.setDouble(3, discount.getDiscountPercentage());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void updateItem (Discount discount) throws SQLException {
    String updateSql = "UPDATE DISCOUNTS SET BALANCE = ?, DISCOUNT_PERCENTAGE = ? WHERE CUSTOMER_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setDouble(1, discount.getBalance());
      insertStmt.setDouble(2, discount.getDiscountPercentage());
      insertStmt.setInt(3, discount.getCustomerId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
