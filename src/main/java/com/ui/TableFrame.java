package com.ui;

import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableFrame extends JFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JButton deleteButton;
  private JButton updateButton;
  private JTextArea searchQuery;
  private JButton searchButton;
  private JButton createButton;
  private JButton backButton;

  public TableFrame (AppState state, TableModel content) {
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setVisible(false);
    setResizable(true);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    mainTable.setModel(content);

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

          //state.userForm.setVisible(true);
          UserForm userForm = new UserForm(state, selectedUser);
        } else {
          JOptionPane.showMessageDialog(TableFrame.this, "Please select a row to delete.");
        }
      }
    });
  }
}
