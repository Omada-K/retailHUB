package com.ui;

import com.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This model converts an array list of User
 * into something that the JTable understands
 * and can show.
 */
public class TableModel extends AbstractTableModel {
  private String[] columnNames;
  private List<User> users;

  //constructor
  public TableModel (List<User> content, String[] columnNames) {
    this.users = content;
    this.columnNames = columnNames;
  }

  @Override
  public int getRowCount () {
    return users.size();
  }

  @Override
  public int getColumnCount () {
    return columnNames.length;
  }

  @Override
  public String getColumnName (int column) {
    return columnNames[column];
  }

  @Override
  public Object getValueAt (int rowIndex, int columnIndex) {
    User selectedUser = users.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> selectedUser.getId();
      case 1 -> selectedUser.getName();
      case 2 -> selectedUser.getEmail();
      case 3 -> selectedUser.getUserPassword();
      default -> null;
    };
  }

  //get the whole user
  public User getUser (int rowIndex) {
    return users.get(rowIndex);
  }

  //Delete an item
  public void removeRow (int rowIndex) {
    users.remove(rowIndex);
    fireTableRowsDeleted(rowIndex, rowIndex); //this updates the visuals
  }

  //add a user
  public void addUser (int rowIndex, User user) {
    users.addLast(user);
    fireTableRowsInserted(rowIndex, rowIndex);
  }

  //update a user
  public void editUser (int rowIndex, User user) {
    users.set(rowIndex, user);
    fireTableRowsUpdated(rowIndex, rowIndex);
  }

}
