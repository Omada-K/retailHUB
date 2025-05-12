package com.ui;

import com.dao.CustomersDAO;
import com.dao.OrdersDAO;
import com.dao.ProductsDAO;
import com.dao.UserDAO;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.model.User;
import com.ui.tablemodel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableFrame<T> extends BaseFrame {
  private JPanel tablePanel;
  private JTable mainTable;
  private JButton deleteButton;
  private JButton updateButton;
  private JButton searchButton;
  private JButton createButton;
  private JButton backButton;
  private JTextField queryField;
  private JLabel descriptionLabel;

  private TableModel<T> content;

  public TableFrame (AppState state, TableModel<T> content) {
    super();
    setContentPane(tablePanel);// don't forget this, the window will be empty
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setMinimumSize(new Dimension(800, 600));

    Font currentFont = descriptionLabel.getFont();
    descriptionLabel.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 20));
    descriptionLabel.setText("This is the available " + state.selectedTableType);
    getRootPane().setDefaultButton(searchButton);
    this.content = content;

    mainTable.setModel(content);

    //Back button
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        state.resetSelectedTableType();
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
              if (state.selectedTableType.equals(AppState.TableTypes.Users.name())) {
                UserDAO.deleteItem(selectedItem);
                content.refreshTable();
              }
              if (state.selectedTableType.equals(AppState.TableTypes.Customers.name())) {
                CustomersDAO.deleteItem(selectedItem);
                content.refreshTable();
              }
              if (state.selectedTableType.equals(AppState.TableTypes.Products.name())) {
                ProductsDAO.deleteItem(selectedItem);
                content.refreshTable();
              }
              if (state.selectedTableType.equals(AppState.TableTypes.Orders.name())) {
                OrdersDAO.deleteItem(selectedItem);
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

          //edit a user
          if (state.selectedTableType.equals(AppState.TableTypes.Users.name())) {
            new UserFrame(content, selectedItem);
          }

          //editma customer
          if (state.selectedTableType.equals(AppState.TableTypes.Customers.name())) {
            new CustomerFrame(content, selectedItem);
          }

          //Edit order
          //TODO doublecheck this
          if (state.selectedTableType.equals(AppState.TableTypes.Orders.name())) {
            Order selectedOrder = (Order) content.getItem(selectedRow);
            ArrayList<Product> products = new ArrayList<>();
            ArrayList<Customer> availableCustomers = new ArrayList<>();

            try {
              availableCustomers = CustomersDAO.getData();
              products = ProductsDAO.getData();
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(
                      null,
                      "Database error occurred: " + ex.getMessage(),
                      "Error",
                      JOptionPane.ERROR_MESSAGE);
            }
            ProductTableModel t = new ProductTableModel(products);
            new OrderFrame(t, selectedItem, availableCustomers);
          }

          //edit a product
          if (state.selectedTableType.equals(AppState.TableTypes.Products.name())) {
            new ProductFrame(content, selectedItem);
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

        if (state.selectedTableType.equals(AppState.TableTypes.Users.name())) {
          new UserFrame(content);
        }
        if (state.selectedTableType.equals(AppState.TableTypes.Customers.name())) {
          new CustomerFrame(content);
        }

        if (state.selectedTableType.equals(AppState.TableTypes.Orders.name())) {
          ArrayList<Customer> availableCustomers;
          ArrayList<Product> availableProducts;
          try {
            availableCustomers = CustomersDAO.getData();
            availableProducts = ProductsDAO.getData();
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
          new OrderFrame(content, availableCustomers, availableProducts);
        }
        if (state.selectedTableType.equals(AppState.TableTypes.Products.name())) {
          new ProductFrame(content);
        }
      }
    });

    //search
    searchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        String query = queryField.getText();

        if (state.selectedTableType.equals(AppState.TableTypes.Users.name())) {
          List<User> newList = (List<User>) content.search(query);
          UserTableModel newContent = new UserTableModel(newList);
          mainTable.setModel(newContent);
        }
        if (state.selectedTableType.equals(AppState.TableTypes.Customers.name())) {
          List<Customer> newList = (List<Customer>) content.search(query);
          CustomerTableModel newContent = new CustomerTableModel(newList);
          mainTable.setModel(newContent);
        }
        if (state.selectedTableType.equals(AppState.TableTypes.Orders.name())) {
          List<Order> newList = (List<Order>) content.search(query);
          OrderTableModel newContent = new OrderTableModel(newList);
          mainTable.setModel(newContent);
        }
        if (state.selectedTableType.equals(AppState.TableTypes.Products.name())) {
          List<Product> newList = (List<Product>) content.search(query);
          ProductTableModel newContent = new ProductTableModel(newList);
          mainTable.setModel(newContent);
        }
      }
    });
  }
}
