package com.ui;

import com.model.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableFrame extends JFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JButton deleteButton;
  private JButton updateButton;
  private JButton searchButton;
  private JButton createButton;
  private JButton backButton;
  private JTextField queryField;

  public TableFrame (AppState state, TableModel content) {
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    mainTable.setModel(content);
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(content);
    mainTable.setRowSorter(sorter);

    //Back button
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        state.tableFrame.setVisible(false);
        state.mainFrame.setVisible(true);

      }
    });

    // delete button
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        // get the selected row
        int selectedRow = mainTable.getSelectedRow();
        //check if there is selection
        if (selectedRow != -1) {
          //throw confirm message
          int confirm = JOptionPane.showConfirmDialog(
                  TableFrame.this,
                  "Are you sure you want to delete this row?",
                  "Confirm Delete",
                  JOptionPane.YES_NO_OPTION
                                                     );
          if (confirm == JOptionPane.YES_OPTION) {
            ( (TableModel) mainTable.getModel() ).removeRow(selectedRow);
          }
        } else {
          JOptionPane.showMessageDialog(TableFrame.this, "Please select a row to delete.");
        }
      }
    });

    //Edit
    updateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        // get the selected row
        int selectedRow = mainTable.getSelectedRow();
        User selectedUser = (User) content.getUser(selectedRow);
        if (selectedRow != -1) {
          UserForm userForm = new UserForm(content, selectedUser);
        } else {
          JOptionPane.showMessageDialog(TableFrame.this, "Please select a row to delete.");
        }
      }
    });

    //Create
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        UserForm userForm = new UserForm(content);
      }
    });

    //search
    queryField.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate (DocumentEvent e) {
        //get text user entered
        String query = queryField.getText().trim();
        searchTable(query);
      }

      @Override
      public void removeUpdate (DocumentEvent e) {
        //get text user entered
        String query = queryField.getText().trim();
        searchTable(query);
      }

      @Override
      public void changedUpdate (DocumentEvent e) {
        //get text user entered
        String query = queryField.getText().trim();
        searchTable(query);
      }

      private void searchTable (String query) {

        // Get the sorter associated with the table
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) mainTable.getRowSorter();

        // if no filter
        if (query.isEmpty()) {
          sorter.setRowFilter(null);
          return;
        }

        // apply a custom filter
        sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
          @Override
          public boolean include (Entry<? extends TableModel, ? extends Integer> entry) {
            int columnCount = entry.getValueCount();

            for (int col = 0; col < columnCount; col++) {
              Object cellValue = entry.getValue(col);

              if (cellValue != null) {
                String cellText = String.valueOf(cellValue);

                if (cellText.contains(query)) {
                  System.out.println("Searching for: " + query);

                  return true; // at least one cell matches the query
                }
              }
            }
            return false;
          }
        });
      }
    });
  }
}
