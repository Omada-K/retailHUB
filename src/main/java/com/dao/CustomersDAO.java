package com.dao;

import com.controller.DataBaseConfig;
import com.model.Customer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data Access Object (DAO) for the 'customers' table.
 */
public class CustomersDAO {
  public ArrayList<Customer> customers;
  public String[] columns;

  public static ArrayList<Customer> getData () throws SQLException {
    ArrayList<Customer> customers = new ArrayList<Customer>();
    String query = """
              SELECT c.customer_id,
                c.name,
                c.address,
                c.phone,
                c.email,
                d.balance,
                d.discount_percentage
              FROM customers c
              LEFT JOIN discounts d ON c.customer_id = d.customer_id
            """;
    System.out.println("fetching customers from database");
    try (
            Connection conn = DataBaseConfig.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        Customer c = new Customer(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getDouble("balance"),
                rs.getFloat("discount_percentage")
        );
        customers.add(c);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }

  //add customer
  public static int createItem (Customer customer) throws SQLException {
    String insertSql = "INSERT INTO CUSTOMERS (NAME, ADDRESS, PHONE, EMAIL) VALUES (?, ?, ?, ?)";

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
      insertStmt.setString(1, customer.getName());
      insertStmt.setString(2, customer.getAddress());
      insertStmt.setString(3, customer.getPhone());
      insertStmt.setString(4, customer.getEmail());
      insertStmt.executeUpdate();

      ResultSet generatedKeys = insertStmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        return generatedKeys.getInt(1); // ✅ return the generated customer_id
      } else {
        throw new SQLException("Creating customer failed, no ID obtained.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  //edit item
  public static void updateItem (Customer customer) throws SQLException {
    String updateSql = "UPDATE CUSTOMERS SET NAME = ?, ADDRESS = ?, PHONE = ? , EMAIL = ? WHERE CUSTOMER_ID = ?";
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setString(1, customer.getName());
      insertStmt.setString(2, customer.getAddress());
      insertStmt.setString(3, customer.getPhone());
      insertStmt.setString(4, customer.getEmail());
      insertStmt.setInt(5, customer.getCustomerId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //delete item
  public static void deleteItem (Object selectedCustomer) throws SQLException {
    String deleteSql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
    Customer customer = (Customer) selectedCustomer;
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(deleteSql)) {
      insertStmt.setInt(1, customer.getCustomerId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
