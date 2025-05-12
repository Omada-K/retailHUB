package com.ui;

import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.ui.tablemodel.ProductTableModel;
import com.ui.tablemodel.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderFrame extends BaseFrame {
  private JPanel formPanel;
  private JButton deleteButton;
  private JButton exitButton;
  private JTextField inputQuantity;
  private JComboBox comboProduct;
  private JTable productsTable;
  private JComboBox comboCustomer;
  private JButton addButton;

  // Edit form (requires an Order)
  public OrderFrame (ProductTableModel content, Object OrderInput, ArrayList<Customer> availableCustomers) {
    super();
    setContentPane(formPanel); // set the main panel
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(900, 600);
    Order order = (Order) OrderInput; // cast input to Order

    List<String> customerNamesList = new ArrayList<>();
    for (Customer customer : availableCustomers) {
      customerNamesList.add(customer.getName());
    }
    comboCustomer.setModel(new DefaultComboBoxModel<>(customerNamesList.toArray(new String[0])));
    //comboCustomer.setSelectedItem(order());
    comboCustomer.setEnabled(false);

    int id = order.getOrderId();
    inputQuantity.setText(String.valueOf(order.getPrice()));

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        int selectedRow = productsTable.getSelectedRow();
        if (selectedRow != -1) {
          Product selectedItem = content.getItem(selectedRow);

          int confirm = JOptionPane.showConfirmDialog(
                  OrderFrame.this,
                  "Are you sure you want to delete this row?",
                  "Confirm Delete",
                  JOptionPane.YES_NO_OPTION
                                                     );

          if (confirm == JOptionPane.YES_OPTION) {
            // remove from table model

          }
        }
      }
    });
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //save product to order
      }
    });
  }

  // Create form (for new orders)
  public OrderFrame (TableModel content, ArrayList<Customer> availableCustomers, ArrayList<Product> availableProducts) {
    super();
    setContentPane(formPanel); // set the main panel
    setContentPane(formPanel);
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(900, 600);

    List<String> customerNamesList = new ArrayList<>();
    for (Customer customer : availableCustomers) {
      customerNamesList.add(customer.getName());
    }
    comboCustomer.setModel(new DefaultComboBoxModel<>(customerNamesList.toArray(new String[0])));

    List<String> productsNamesList = new ArrayList<>();
    for (Product product : availableProducts) {
      productsNamesList.add(product.getName());
    }
    comboProduct.setModel(new DefaultComboBoxModel<>(productsNamesList.toArray(new String[0])));
    comboProduct.setSelectedIndex(0);

    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        //save product to order
      }
    });

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        // Similar deletion logic could go here
      }
    });
  }
}
