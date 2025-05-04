package com.ui;

import com.dao.CustomersDAO;
import com.dao.ProductsDAO;
import com.dao.UserDAO;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TableFrame<T> extends JFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JButton deleteButton;
  private JButton updateButton;
  private JButton searchButton;
  private JButton createButton;
  private JButton backButton;
  private JTextField queryField;

  private TableModel<T> content;

  public TableFrame (AppState state, TableModel<T> content) {
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setVisible(true);
    setResizable(true);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    this.content = content;

    mainTable.setModel(content);

    //Back button
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        dispose();
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
          T selectedItem = (T) content.getItem(selectedRow);
          //throw confirm message
          int confirm = JOptionPane.showConfirmDialog(
                  TableFrame.this,
                  "Are you sure you want to delete this row?",
                  "Confirm Delete",
                  JOptionPane.YES_NO_OPTION
                                                     );
          if (confirm == JOptionPane.YES_OPTION) {
            try {
              if (selectedItem instanceof User) {
                UserDAO.deleteItem(selectedItem);
                content.refreshTable();
              }
              if (selectedItem instanceof Customer) {
                CustomersDAO.deleteItem(selectedItem);
                content.refreshTable();
              }
              if (selectedItem instanceof Product) {
                ProductsDAO.deleteItem(selectedItem);
                content.refreshTable();
              }
            } catch (SQLException ex) {
              throw new RuntimeException(ex);
            }
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
        if (selectedRow != -1) {
          T selectedItem = (T) content.getItem(selectedRow);
          if (selectedItem instanceof User) {
            new UserForm(content, selectedItem);
          }
          if (selectedItem instanceof Customer) {
            new CustomerForm(content, selectedItem);
          }
          if (selectedItem instanceof Order) {
            //new OrderForm(content, selectedItem);
          }
          if (selectedItem instanceof Product) {
            new ProductForm(content, selectedItem);
          }
        } else {
          JOptionPane.showMessageDialog(TableFrame.this, "Please select a row to delete.");
        }
      }
    });

    //Create
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (content.getRowCount() > 0) {
          Object firstItem = content.getItem(0);
          if (firstItem instanceof User) {
            new UserForm(content);
          }
          if (firstItem instanceof Customer) {
            new CustomerForm(content);
          }
          if (firstItem instanceof Order) {
            //new OrderForm(content);
          }
          if (firstItem instanceof Product) {
            new ProductForm(content);
          }
        } else {
          //if the sql returns null....
          JOptionPane.showMessageDialog(TableFrame.this, "No existing data");
        }
      }
    });

    //search
    //TODO
  }
}
