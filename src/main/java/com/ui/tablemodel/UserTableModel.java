package com.ui.tablemodel;

import com.dao.UserDAO;
import com.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Table model for displaying User data in a JTable.
 */
public class UserTableModel extends TableModel<User> {

  public UserTableModel (List<User> users) {
    super(users, new String[] {"ID", "Name", "Email", "Password"});
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {
    User user = data.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> user.getId();
      case 1 -> user.getName();
      case 2 -> user.getEmail();
      case 3 -> user.getUserPassword();
      default -> null;
    };
  }

  @Override
  public void refreshTable () {
    try {
      List<User> updatedData = UserDAO.getData();
      data.clear();
      data.addAll(updatedData);
      fireTableDataChanged();
    } catch (SQLException e) {
      throw new RuntimeException("Failed to refresh user table", e);
    }
  }

}