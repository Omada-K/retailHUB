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
    String query = "SELECT customer_id, name, address, phone, email, balance, points, date_of_birth FROM customers"; // date_of_birth column included
    System.out.println("fetching customers from database");
    try (
            Connection conn = DataBaseConfig.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        // Changed: Convert java.sql.Date to LocalDate for constructor
        Customer c = new Customer(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getDouble("balance"),
                rs.getInt("points"),
                rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null // Change: Use LocalDate
        );
        customers.add(c);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }

  // add customer
  public static void createItem (Customer customer) throws SQLException {
    String insertSql = "INSERT INTO CUSTOMERS (NAME, ADDRESS, PHONE, EMAIL, BALANCE, POINTS, DATE_OF_BIRTH) VALUES (?, ?, ?, ?, ?, ?, ?)"; // date_of_birth included

    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
      insertStmt.setString(1, customer.getName());
      insertStmt.setString(2, customer.getAddress());
      insertStmt.setString(3, customer.getPhone());
      insertStmt.setString(4, customer.getEmail());
      insertStmt.setDouble(5, customer.getBalance());
      insertStmt.setInt(6, customer.getPoints());
      insertStmt.setDate(7, customer.getSqlDateOfBirth()); // Changed: Use helper to convert LocalDate to java.sql.Date
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // edit item
  public static void updateItem (Customer customer) throws SQLException {
    String updateSql = "UPDATE CUSTOMERS SET NAME = ?, ADDRESS = ?, PHONE = ?, EMAIL = ?, BALANCE = ?, POINTS = ?, DATE_OF_BIRTH = ? WHERE CUSTOMER_ID = ?"; // date_of_birth included
    try (Connection conn = DataBaseConfig.getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
      insertStmt.setString(1, customer.getName());
      insertStmt.setString(2, customer.getAddress());
      insertStmt.setString(3, customer.getPhone());
      insertStmt.setString(4, customer.getEmail());
      insertStmt.setDouble(5, customer.getBalance());
      insertStmt.setInt(6, customer.getPoints());
      insertStmt.setDate(7, customer.getSqlDateOfBirth()); // Changed: Use helper to convert LocalDate to java.sql.Date
      insertStmt.setInt(8, customer.getCustomerId());
      insertStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // delete item
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
