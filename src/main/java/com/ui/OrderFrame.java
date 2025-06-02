package com.ui;

import com.dao.OrdersDAO;
import com.dao.ProductsDAO;
import com.model.Customer;
import com.model.Order;
import com.model.Product;
import com.ui.tablemodel.ProductTableModel;
import com.ui.tablemodel.ProductsOrderTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
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
  private JButton editProductButton;

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
  //Create
  public OrderFrame (
          ArrayList<Customer> availableCustomers,
          ArrayList<Product> availableProducts
                    ) throws SQLException {

    super();
    setContentPane(formPanel); // set the main panel
    setContentPane(formPanel);
    setupCancelButton(exitButton);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(900, 680);
    int createdOrderId = OrdersDAO.createItem(new Order(LocalDate.now(), LocalDate.now(), 0, 0));

    ArrayList<Product> productsInOrder = ProductsDAO.getOrderedProducts(createdOrderId);
    ProductsOrderTableModel contentModel = new ProductsOrderTableModel(productsInOrder);
    productsTable.setModel(contentModel);

    //setting the dropdown list
    DefaultComboBoxModel<Customer> customerModel = new DefaultComboBoxModel<>();
    for (Customer customer : availableCustomers) {
      customerModel.addElement(customer);
    }
    comboCustomer.setModel(customerModel);
    comboCustomer.setSelectedIndex(0);

    DefaultComboBoxModel<Product> productModel = new DefaultComboBoxModel<>();
    for (Product product : availableProducts) {
      productModel.addElement(product);
    }
    comboProduct.setModel(productModel);
    comboProduct.setSelectedIndex(0);

    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        if (!inputQuantity.getText().isEmpty()) {
          try {
            Product selectedProduct = (Product) comboProduct.getSelectedItem();
            int selectedProductId = selectedProduct.getProductId();
            int quantity = Integer.parseInt(inputQuantity.getText());
            //debug...
            System.out.println("Order id " + createdOrderId);
            System.out.println("Product id " + selectedProductId);
            OrdersDAO.createOrderProduct(createdOrderId, selectedProductId, quantity);
            //            ArrayList<Product> newProducts = ProductsDAO.getOrderedProducts(createdOrderId);
            //            productsTable.setModel(new ProductsOrderTableModel(newProducts));
          } catch (SQLException ex) {
            throw new RuntimeException(ex);
          }
        }
        contentModel.refreshTableWithOrder(createdOrderId);

      }
    });

    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed (ActionEvent e) {
        int selectedProductId = productsTable.getSelectedRow();
        if (selectedProductId != -1) {
          int confirm = JOptionPane.showConfirmDialog(
                  OrderFrame.this,
                  "Are you sure you want to delete this row?",
                  "Confirm Delete",
                  JOptionPane.YES_NO_OPTION
                                                     );
          if (confirm == JOptionPane.YES_OPTION) {
            try {
              OrdersDAO.deleteOrderProduct(createdOrderId, selectedProductId);
            } catch (SQLException ex) {
              throw new RuntimeException(ex);
            }
          }

        } else {
          JOptionPane.showMessageDialog(null, "Please select a product first");
        }
      }
    });
  }
}
