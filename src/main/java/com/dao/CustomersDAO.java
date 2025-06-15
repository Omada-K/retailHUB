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

    public static ArrayList<Customer> getData() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String query = "SELECT customer_id, name,address,phone,email,balance,points FROM customers ";
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
                        rs.getInt("points")
                );
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //add customer
    public static void createItem(Customer customer) throws SQLException {
        String insertSql = "INSERT INTO CUSTOMERS (NAME, ADDRESS, PHONE, EMAIL,BALANCE,POINTS) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setString(1, customer.getName());
            insertStmt.setString(2, customer.getAddress());
            insertStmt.setString(3, customer.getPhone());
            insertStmt.setString(4, customer.getEmail());
            insertStmt.setDouble(5, customer.getBalance());
            insertStmt.setInt(6, customer.getPoints());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //edit item
    public static void updateItem(Customer customer) throws SQLException {
        String updateSql = "UPDATE CUSTOMERS SET NAME = ?, ADDRESS = ?, PHONE = ? , EMAIL = ?,BALANCE=?, POINTS =? WHERE " +
                "CUSTOMER_ID = ?";
        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(updateSql)) {
            insertStmt.setString(1, customer.getName());
            insertStmt.setString(2, customer.getAddress());
            insertStmt.setString(3, customer.getPhone());
            insertStmt.setString(4, customer.getEmail());
            insertStmt.setDouble(5, customer.getBalance());
            insertStmt.setInt(6, customer.getPoints());
            insertStmt.setInt(7, customer.getCustomerId());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete item
    public static void deleteItem(Object selectedCustomer) throws SQLException {
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
