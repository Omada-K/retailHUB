package com.ui.tablemodel;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic abstract table model to be extended by specific models like UserTableModel or CustomerTableModel.
 */
public abstract class TableModel<T> extends AbstractTableModel {

    protected List<T> data;
    protected String[] columnNames;

    public TableModel(List<T> data, String[] columnNames) {
        this.data = new ArrayList<>(data);
        this.columnNames = columnNames;
    }

    public List<T> search(String query) {
        List<T> result = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (T item : data) {
            for (Field field : item.getClass().getDeclaredFields()) {
                field.setAccessible(true); // allow access to private fields
                try {
                    Object value = field.get(item);
                    if (value != null) {
                        String stringValue = value.toString().toLowerCase();
                        if (stringValue.contains(lowerQuery)) {
                            result.add(item);
                            break; // move along
                        }
                    }
                } catch (IllegalAccessException e) {
                    // Handle error appropriately in real applications
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

    public T getItem(int rowIndex) {
        return data.get(rowIndex);
    }

    public abstract void refreshTable();
}
