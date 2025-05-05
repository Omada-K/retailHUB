package com.ui;

import com.model.Order;
import com.model.Product;
import com.ui.tablemodel.ProductTableModel;
import com.ui.tablemodel.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderForm extends BaseForm {
  private JPanel formPanel;
  private JButton deleteButton;
  private JButton exitButton;
  private JTextField inputQuantity;
  private JComboBox comboProduct;
  private JTable productsTable;
  private JComboBox comboCustomer;
  private JButton addButton;

  // Edit form (requires an Order)
  public OrderForm (ProductTableModel content, Object OrderInput) {
    super();
    setContentPane(formPanel); // set the main panel
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    Order order = (Order) OrderInput; // cast input to Order
    int id = order.getOrderId();
    inputQuantity.setText(String.valueOf(order.getAmount()));

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        int selectedRow = productsTable.getSelectedRow();
        if (selectedRow != -1) {
          Product selectedItem = content.getItem(selectedRow);

          int confirm = JOptionPane.showConfirmDialog(
                  OrderForm.this,
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
  }

  // Create form (for new orders)
  public OrderForm (TableModel content) {
    super();
    setContentPane(formPanel); // set the main panel
    setContentPane(formPanel);
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        // Similar deletion logic could go here
      }
    });
  }
}
