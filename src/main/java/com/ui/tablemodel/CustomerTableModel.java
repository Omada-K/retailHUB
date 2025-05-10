package com.ui.tablemodel;

import com.dao.CustomersDAO;
import com.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerTableModel extends TableModel<Customer> {

  public CustomerTableModel (List<Customer> customers) {
    super(customers, new String[] {"ID", "Name", "Address", "Phone", "Email", "Balance", "Discount"});
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {
    Customer customer = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> customer.getCustomerId();
      case 1 -> customer.getName();
      case 2 -> customer.getAddress();
      case 3 -> customer.getPhone();
      case 4 -> customer.getEmail();
      case 5 -> customer.getBalance();
      case 6 -> customer.getPoints();
      default -> null;
    };
  }

  @Override
  public void refreshTable () {
    try {
      List<Customer> updatedData = CustomersDAO.getData();
      data.clear();
      data.addAll(updatedData);
      fireTableDataChanged();
    } catch (SQLException e) {
      throw new RuntimeException("Failed to refresh customer table", e);
    }
  }
}
