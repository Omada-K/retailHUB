package com.dao;

import com.controller.DataBaseConfig;
import com.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
  public static ArrayList<Customer> getCustomers () throws SQLException {
    ArrayList<Customer> customers = new ArrayList<>();
    String query = "SELECT * FROM customers";

    try (Connection conn = DataBaseConfig.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        Customer c = new Customer(
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
