package com.ui;

import com.model.Customer;
import com.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This model converts an array list of User
 * into something that the JTable understands
 * and can show.
 */
public class TableModel<T> extends AbstractTableModel {
  private final List<T> data;
  private final String[] columnNames;
  private final Object[][] rowData;

  //constructor
  public TableModel (List<T> data, String[] columnNames, Object[][] rowData) {
    this.data = data;
    this.columnNames = columnNames;
    this.rowData = rowData;
  }

  @Override
  public int getRowCount () {
    return data.size();
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
    T item = data.get(rowIndex);
    // Return fields based on columnIndex
    if (item instanceof User user) {
      return switch (columnIndex) {
        case 0 -> user.getId();
        case 1 -> user.getName();
        case 2 -> user.getEmail();
        case 3 -> user.getUserPassword();
        default -> null;
      };
    }
    if (item instanceof Customer customer) {
      return switch (columnIndex) {
        case 0 -> customer.getCustomerId();
        case 1 -> customer.getName();
        case 2 -> customer.getAddress();
        case 3 -> customer.getPhone();
        case 4 -> customer.getEmail();
        default -> null;
      };
    }
    return null;
  }

  //get the whole user
  public T getItem (int rowIndex) {
    return data.get(rowIndex);
  }

  //refresh visuals
  public void refreshTable (List<T> newData) {
    data.clear();
    data.addAll(newData);
    fireTableDataChanged();
  }
}
