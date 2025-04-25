
package com.dao;

import com.controller.DataBaseConfig;
import com.model.Customers;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data Access Object (DAO) for the 'customers' table.
 */
public class CustomersDAO {

  /**
   * Retrieves all customers from the 'customers' table.
   *
   * @return a list of Customers
   * @throws SQLException if there is a database access error
   */
  public static ArrayList<Customers> getCustomers() throws SQLException {
    ArrayList<Customers> customers = new ArrayList<>();
    String query = "SELECT * FROM customers";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        Customers c = new Customers(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email")
        );
        customers.add(c);
      }
    }

    return customers;
  }
}
