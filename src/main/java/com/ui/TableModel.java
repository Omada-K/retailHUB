package com.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * This model converts an array list of User
 * into something that the JTable understands
 * and can show.
 */
public class TableModel<T> extends AbstractTableModel {
  private final String[] columnNames;
  private final List<T> data;
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
    return rowData[rowIndex][columnIndex];
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

}
