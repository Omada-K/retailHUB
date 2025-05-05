package com.ui.tablemodel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic abstract table model to be extended by specific models like UserTableModel or CustomerTableModel.
 */
public abstract class TableModel<T> extends AbstractTableModel {

  protected List<T> data;
  protected String[] columnNames;

  public TableModel (List<T> data, String[] columnNames) {
    this.data = new ArrayList<>(data);
    this.columnNames = columnNames;
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
  public abstract Object getValueAt (int rowIndex, int columnIndex);

  public T getItem (int rowIndex) {
    return data.get(rowIndex);
  }

  public abstract void refreshTable ();
}
