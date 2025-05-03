package com.ui;

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
    return null;
  }

  //get the whole user
  public T getItem (int rowIndex) {
    return data.get(rowIndex);
  }

  //Delete an item
  public void removeRow (int rowIndex) {
    data.remove(rowIndex);
    fireTableRowsDeleted(rowIndex, rowIndex); //this updates the visuals
  }

  //add an item
  public void addItem (int rowIndex, T item) {
    data.addLast(item);
    fireTableRowsInserted(rowIndex, rowIndex);
  }

  //update a user
  public void editItem (int rowIndex, T item) {
    data.set(rowIndex, item);
    fireTableRowsUpdated(rowIndex, rowIndex);
  }

  //refresh visuals
  public void refreshTable (List<T> newData) {
    data.clear();
    data.addAll(newData);
    fireTableDataChanged();
  }
}
